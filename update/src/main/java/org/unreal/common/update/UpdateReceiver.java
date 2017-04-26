package org.unreal.common.update;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import org.unreal.common.update.config.BroadCastActions;


public class UpdateReceiver extends BroadcastReceiver {

    private NotificationManager manager;
    private RemoteViews views;
    private Notification notification;

    @Override
    public void onReceive(Context context, Intent intent) {

        if(notification == null ){
            initNotificationForLowVersion(context);
        }
        String action = intent.getAction();
        switch (action) {
            case BroadCastActions.ACTION_ERROR:
                views.setTextViewText(R.id.progressStatus,"下载出错,请重新下载!");
                manager.notify(0, notification);
                break;
            case BroadCastActions.ACTION_DONE:
                views.setTextViewText(R.id.progressStatus,"下载完成,安装中...");
                notification.flags |= Notification.FLAG_AUTO_CANCEL;
                manager.notify(0, notification);
                break;
            case BroadCastActions.ACTION_PROGRESS:
                String fileSize = intent.getStringExtra("fileSize");
                String downloadSize = intent.getStringExtra("downloadSize");
                long progressNum = intent.getLongExtra("progressNum", 0);
                String progress = intent.getStringExtra("progress");
                views.setTextViewText(R.id.progressStatus,String.format("%s / %s %s", downloadSize , fileSize , progress) );
                views.setProgressBar(R.id.progressBar,100,(int) progressNum,false);
                manager.notify(0, notification);
        }
    }

    private void initNotificationForLowVersion(Context context) {
        manager = (NotificationManager)context. getSystemService(Context.NOTIFICATION_SERVICE);
        views = new RemoteViews(context.getPackageName(), R.layout.notification_update);
        Notification.Builder builder = new Notification.Builder(context.getApplicationContext());
        notification = builder.setAutoCancel(false).setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("下载中")
                .setContentTitle(context.getResources().getString(R.string.app_name)).
                setWhen(System.currentTimeMillis()).setTicker("新版正在下载")
                .setContent(views).build();

        views.setTextViewText(R.id.progressStatus, "下载中....0%");
        views.setProgressBar(R.id.progressBar, 100, 0, false);
    }

}
