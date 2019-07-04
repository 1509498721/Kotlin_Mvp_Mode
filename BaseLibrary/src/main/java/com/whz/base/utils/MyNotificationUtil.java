package com.whz.base.utils;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import com.whz.base.common.BaseApplication;
import static android.app.NotificationManager.IMPORTANCE_DEFAULT;
import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by whz  on 2019-06-27
 */
public class MyNotificationUtil {
    private static NotificationManager mNotificationManager = null;
    private static NotificationChannel channel = null;
    private  static Context mContext;
    private static String mChannelId = "";
    private static Notification notification = null;

    public static void init(Context context) {
        mContext = context;
    }


    public static void  createNotification(String channelId, String channelName){
        mChannelId = channelId;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            channel = new NotificationChannel(channelId, channelName, IMPORTANCE_DEFAULT);
            mNotificationManager = (NotificationManager)mContext. getSystemService(
                    NOTIFICATION_SERVICE);
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(channel);
            }
        }

    }



    @SuppressLint("InlinedApi")
    public static void sendNotification(String title, String content, int smallIcon) {
        notification = new NotificationCompat.Builder(mContext, mChannelId)
                .setContentTitle(title)
                .setContentText(content)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(smallIcon)
                .setAutoCancel(true)
                .build();
        if (mNotificationManager != null) {
            mNotificationManager.notify(500008, notification);
        }
    }


    @SuppressLint("InlinedApi")
    public static void sendNotification(Intent intent, String title, String content, int smallIcon) {
        PendingIntent pi = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        notification = new NotificationCompat.Builder(mContext, mChannelId)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pi)
                .setWhen(System.currentTimeMillis())
                .setLargeIcon(BitmapFactory.decodeResource(BaseApplication.context.getResources(),smallIcon))
                .setAutoCancel(true)
                .setFullScreenIntent(pi,true)
                .build();
        if (mNotificationManager != null) {
            mNotificationManager.notify(500008, notification);
        }
    }

}

