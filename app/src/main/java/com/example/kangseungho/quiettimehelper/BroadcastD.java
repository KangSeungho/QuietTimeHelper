package com.example.kangseungho.quiettimehelper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.kangseungho.quiettimehelper.sqlite.DataBaseHelper;
import com.example.kangseungho.quiettimehelper.sqlite.DatabasesTables;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class BroadcastD extends BroadcastReceiver {
    String INTENT_ACTION = Intent.ACTION_BOOT_COMPLETED;
    SQLiteDatabase db;
    DataBaseHelper DBHelper;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = dateFormat.format(new Date());

    @Override
    public void onReceive(Context context, Intent intent) {
        DBHelper = new DataBaseHelper(context);
        db = DBHelper.getWritableDatabase();

        if(dbCheck()) {
            broadDB();
        }
        else {
            JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask(MainActivity.htmlPageUrl, 1, context);
            jsoupAsyncTask.execute();
        }

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

    private boolean dbCheck() {
        String sql = "SELECT * FROM " + DatabasesTables.CreateDB_wordInfomationTable._TABLENAME
                + " WHERE " + DatabasesTables.CreateDB_wordInfomationTable._DATE
                + " = '" + date + "';";
        Cursor results = db.rawQuery(sql, null);

        results.close();

        if(results.getCount() != 0) {
            return true;
        }
        else {
            return false;
        }
    }

    private void broadDB() {
        String sql = "SELECT * FROM " + DatabasesTables.CreateDB_wordInfomationTable._TABLENAME
                + " WHERE " + DatabasesTables.CreateDB_wordInfomationTable._DATE
                + " = '" + date + "';";
        Cursor results = db.rawQuery(sql, null);

        WordItem.instance.setTitle(results.getString(2));
        WordItem.instance.setToday(results.getString(3));

        results.close();
    }
}
