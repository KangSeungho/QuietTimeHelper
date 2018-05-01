package com.example.kangseungho.quiettimehelper.sqlite;

import android.provider.BaseColumns;

public class DatabasesTables {
    public static final class CreateDB_alarmTable implements BaseColumns {
        public static final String _TABLENAME = "alarm";

        public static final String _DAY = "day";
        public static final String _AMPM = "ampm";
        public static final String _HOUR = "hour";
        public static final String _MINUTE = "minute";
        public static final String _ONOFF = "onoff";

        public static final String _CREATE_SETTING =
                "CREATE TABLE " + _TABLENAME + "(";
    }
}
