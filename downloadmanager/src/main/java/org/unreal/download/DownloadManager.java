package org.unreal.download;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;


import com.cm.retrofit2.converter.file.body.ProgressResponseListener;

import org.unreal.download.retrofit.DownloadRetrofitClient;
import org.unreal.download.service.DownloadService;

import java.io.File;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <b>类名称：</b> DownloadAPK <br  />
 * <b>类描述：</b> <br  />
 * <b>创建人：</b> wqq <br  />
 * <b>修改人：</b> wqq <br  />
 * <b>修改时间：</b> 2016年07月04日 8:59<br  />
 * <b>修改备注：</b> <br  />
 *
 * @version 1.0.0 <br  />
 */

public class DownloadManager {

    public interface DownloadListener{
        void complete(File file);
    }

    public static void donwloadApk(final Context context , String downloadUrl ,
                                   final String saveFilePath){
        retrofitDownload(context,downloadUrl,saveFilePath,null);
    }

    public static void donloadFile(final Context context , String downloadUrl ,
                                   final String saveFilePath,DownloadListener downloadListener){
        retrofitDownload(context,downloadUrl,saveFilePath,downloadListener);
    }

    private static void retrofitDownload(final Context context , String downloadUrl ,
                                         final String saveFilePath , final DownloadListener downloadListener){
        //监听下载进度
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setProgressNumberFormat("%1d KB/%2d KB");
        dialog.setTitle("下载");
        dialog.setMessage("正在下载，请稍后...");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false);
        dialog.show();

        DownloadService downloadService =
                DownloadRetrofitClient.createResponseService(DownloadService.class,
                        new ProgressResponseListener() {
                            @Override
                            public void onResponseProgress(long bytesRead, long contentLength, boolean done) {
                                dialog.setMax((int) (contentLength/1024));
                                dialog.setProgress((int) (bytesRead/1024));
                                if(done){
                                    dialog.dismiss();
                                }
                            }
                        });

        downloadService.downloadWithDynamicUrl(downloadUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<File>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(File file) {
                        if(downloadListener != null){
                            downloadListener.complete(file);
                        }else{
                            install(context,file);
                        }
                    }
                });
    }

    /***
     * install app
     *
     */
    public static void install(Context context , File apk) {
        if (apk == null || !apk.exists()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://" + apk.toString()), "application/vnd.android.package-archive");
        context.startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
