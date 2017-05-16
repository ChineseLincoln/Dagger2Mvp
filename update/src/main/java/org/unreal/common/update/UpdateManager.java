package org.unreal.common.update;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.blankj.utilcode.util.IntentUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.unreal.common.config.UpdateConfig;
import org.unreal.common.core.core.UnrealCore;
import org.unreal.common.core.http.covert.ResponseTransformer;
import org.unreal.common.update.config.AuthoritiesConfig;
import org.unreal.common.update.config.BroadCastActions;
import org.unreal.common.update.http.component.DaggerUpdateComponent;
import org.unreal.common.update.http.server.FirServer;
import org.unreal.common.update.http.vo.OnlineAppInfo;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import zlc.season.rxdownload2.RxDownload;

/**
 * <b>类名称：</b> UpdateManager <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月11日 14:08<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class UpdateManager {

    private Activity context;
    private RxDownload rxDownload;
    private RxPermissions permissions;
    private boolean isDebug;

    @Inject
    FirServer firServer;

    public UpdateManager(Activity context, boolean isDebug) {
        this.context = context;
        this.isDebug = isDebug;
        init(context);
        DaggerUpdateComponent.builder()
                .netComponent(UnrealCore.getNetComponent())
                .build()
                .inject(this);
    }

    public void checkUpdate(int nowAppVersion, UpdateStateListener listener) {
        firServer.checkUpdateState(UpdateConfig.FIR_APP_ID,
                UpdateConfig.FIR_API_TOKEN,
                UpdateConfig.FIR_TYPE)
                .compose(new ResponseTransformer<>())
                .subscribe(onlineAppInfo -> {
                    if (onlineAppInfo != null) {
                        if (nowAppVersion < onlineAppInfo.getVersion()) {
                            //如果打包时，versionName值中包含force,则强制更新(取消按钮 换成 退出按钮),并且给出提示语
                            listener.checkNeedUpdate(onlineAppInfo);
                            showUpdateDialog(onlineAppInfo);
                        }
                    } else {
                        listener.checkStateError(new NullPointerException("Update State is Null"));
                    }
                }, throwable -> {
                    if (listener != null) {
                        listener.checkStateError(throwable);
                    }
                });
    }

    private void init(Activity context) {
        rxDownload = RxDownload.getInstance(context);
        permissions = new RxPermissions(context);
    }

    private void showUpdateDialog(OnlineAppInfo appInfo) {
        context.runOnUiThread(() -> {
            if (appInfo.getChangelog().contains("强制升级")) {
                new AlertDialog.Builder(context).setTitle("发现新版本！")
                        .setMessage(appInfo.getChangelog())
                        .setPositiveButton("升级", (dialog, which) -> {
                            fetchApk(appInfo);
                            context.finish();
                        }).setNegativeButton("退出", (dialog, which) -> {
                    Toast.makeText(context,
                            "此版本需要强制升级才能正常使用,请务必升级!!",
                            Toast.LENGTH_LONG).show();
                    context.finish();
                })
                        .show();
            }else {
                new AlertDialog.Builder(context).setTitle("发现新版本！")
                        .setMessage(appInfo.getChangelog())
                        .setPositiveButton("升级", (dialog, which) -> {
                            fetchApk(appInfo);
                        }).setNegativeButton("取消", null)
                        .show();
            }
        });
    }

    private void fetchApk(OnlineAppInfo appInfo) {
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE) //申请存储卡权限
                .doOnNext(granted -> {
                    if (!granted) {  //权限被拒绝
                        String msg = "需要存储权限,请到设置-应用-"
                                + context.getResources().getString(R.string.app_name)
                                + "权限中信任存储权限!";
                        Toast.makeText(context,
                                msg,
                                Toast.LENGTH_LONG).show();
                    }
                })
                .observeOn(Schedulers.io())
                .compose(rxDownload.transform(appInfo.getInstallUrl()))  //download
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(downloadStatus -> {
                    Intent intent = new Intent(context, UpdateReceiver.class);
                    intent.putExtra("fileSize", downloadStatus.getFormatTotalSize());
                    intent.putExtra("downloadSize", downloadStatus.getFormatDownloadSize());
                    intent.putExtra("progressNum", downloadStatus.getPercentNumber());
                    intent.putExtra("progress", downloadStatus.getPercent());
                    intent.setAction(BroadCastActions.ACTION_PROGRESS);
                    context.sendBroadcast(intent);
                }, throwable -> {
                    throwable.printStackTrace();
                    Intent intent = new Intent(context, UpdateReceiver.class);
                    intent.setAction(BroadCastActions.ACTION_ERROR);
                    context.sendBroadcast(intent);
                }, () -> {
                    Intent intent = new Intent(context, UpdateReceiver.class);
                    intent.setAction(BroadCastActions.ACTION_DONE);
                    File[] realFiles = rxDownload.getRealFiles(appInfo.getInstallUrl());
                    if (realFiles != null) {
                        File file = realFiles[0];
                        IntentUtils.getInstallAppIntent(file, AuthoritiesConfig.AUTHOR);
                    }
                });
    }

    public interface UpdateStateListener {
        void checkStateError(Throwable throwable);

        void checkNeedUpdate(OnlineAppInfo appInfo);
    }


}


