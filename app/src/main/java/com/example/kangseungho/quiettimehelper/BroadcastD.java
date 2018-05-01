package com.example.kangseungho.quiettimehelper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BroadcastD extends BroadcastReceiver {
    String INTENT_ACTION = Intent.ACTION_BOOT_COMPLETED;

    @Override
    public void onReceive(Context context, Intent intent) {
        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask(MainActivity.htmlPageUrl, 1);
        jsoupAsyncTask.execute();

        try {
            Thread.sleep(5000);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, IntroActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);
            Notification.Builder builder = new Notification.Builder(context);
            builder.setSmallIcon(R.drawable.ic_launcher_foreground).setTicker("TICKER").setWhen(System.currentTimeMillis())
                    .setNumber(1)
                    .setContentTitle(WordItem.instance.getTitle())
                    .setContentText(WordItem.instance.getToday())
                    .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE).setContentIntent(pendingIntent).setAutoCancel(true);

            notificationManager.notify(1, builder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
