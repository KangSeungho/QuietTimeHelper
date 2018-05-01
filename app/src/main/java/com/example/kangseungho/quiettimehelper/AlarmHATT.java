package com.example.kangseungho.quiettimehelper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.Calendar;

public class AlarmHATT {
    private Context context;
    private long interval = 1000 * 60 * 60 * 24 * 7;

    public AlarmHATT(Context context) {
        this.context = context;
    }

    public void Alarm() {
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, BroadcastD.class);

        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();

        // 알람 시간 캘린더에 set 해주기
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 17, 0);

        // 알람 예약
        if(Build.VERSION.SDK_INT >= 23)
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
        else if(Build.VERSION.SDK_INT >= 19)
            am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
        else
            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
    }

    public void setAlarm(int day, int hour, int minute) {
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, BroadcastD.class);

        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();

        // 알람 시간 캘린더에 set 해주기


        // 알람 예약
        if(Build.VERSION.SDK_INT >= 23)
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
        else if(Build.VERSION.SDK_INT >= 19)
            am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
        else
            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), interval, sender);
    }

    public void cancelAlarm() {
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, BroadcastD.class);

        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);

        am.cancel(sender);
    }
}
