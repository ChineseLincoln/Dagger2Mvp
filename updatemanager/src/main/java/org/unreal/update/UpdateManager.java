package org.unreal.update;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.unreal.download.R;
import org.unreal.update.config.BroadCastActions;
import org.unreal.update.config.UpdateConfig;
import org.unreal.update.vo.OnlineAppInfo;

import java.io.File;
import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

    private final OkHttpClient client;
    private String firUrl;
    private Activity context;
    private final RxDownload rxDownload;
    private final RxPermissions permissions;

    public UpdateManager(Activity context) {
        this.context = context;
        client = new OkHttpClient();
        rxDownload = RxDownload.getInstance(context);
        permissions = new RxPermissions(context);
        firUrl = String.format("%s%s?api_token=%s&type=%s",
                UpdateConfig.FIR_URL,
                UpdateConfig.FIR_APP_ID,
                UpdateConfig.FIR_API_TOKEN,
                UpdateConfig.FIR_TYPE);
    }

    public void checkUpdate(int nowAppVersion, UpdateStateListener listener) {
        Request build = new Request.Builder()
                .url(firUrl)
                .build();
        Call call = client.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                if (listener != null) {
                    listener.checkStateError(e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    OnlineAppInfo appInfo = JSON.parseObject(response.body().bytes(), OnlineAppInfo.class);
                    if (appInfo != null) {
                        if (nowAppVersion < appInfo.getVersion()) {
                            //如果打包时，versionName值中包含force,则强制更新(取消按钮 换成 退出按钮),并且给出提示语
                            showUpdateDialog(appInfo);
                        }
                    } else {
                        showHttpError(listener, response);
                    }
                } else {
                    showHttpError(listener, response);
                }
            }
        });
    }

    private void showUpdateDialog(OnlineAppInfo appInfo) {
        context.runOnUiThread(() -> {
            if (appInfo.getVersionShort().contains("force")) {
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
            } else {
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
                        installApk(file);
                    }
                });
    }

    private void installApk(File apk) {
        if (!apk.exists()) {
            return;
        }
        Uri data;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // "org.unreal.update"即是在清单文件中配置的authorities
            data = FileProvider.getUriForFile(context, UpdateConfig.FILE_PROVIDER_AUTH, apk);
            // 给目标应用一个临时授权
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            data = Uri.fromFile(apk);
        }
        intent.setDataAndType(data, "application/vnd.android.package-archive");
        context.startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    private void showHttpError(UpdateStateListener listener, Response response) {
        if (listener != null) {
            listener.checkStateError(
                    new IllegalStateException(
                            String.format("check update status error ," +
                                            " http code is ---- %d ---- ," +
                                            " check url is ---- %s ----" +
                                            " check body is ---- %s ----",
                                    response.code(),
                                    firUrl,
                                    response.body().toString()
                            )));
        }
    }


    public interface UpdateStateListener {
        void checkStateError(Throwable throwable);
    }


}


