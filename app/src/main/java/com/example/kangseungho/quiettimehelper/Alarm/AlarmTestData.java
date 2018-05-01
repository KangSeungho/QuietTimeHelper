package com.example.kangseungho.quiettimehelper.Alarm;

import java.util.ArrayList;

public class AlarmTestData {
    private static ArrayList<AlarmItem> alarmItems = new ArrayList<>();
    private static String day[] = new String[]{"일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"};

    public static ArrayList<AlarmItem> getAlarmItems() {
        for(int i=0; i<7; i++) {
            AlarmItem item = new AlarmItem();
            item.setId(i);
            item.setDay(day[i]);
            item.setAmpm(false);
            item.setHour(5);
            item.setMinute(0);
            item.setOnoff(true);

            alarmItems.add(item);
        }

        return alarmItems;
    }
}
