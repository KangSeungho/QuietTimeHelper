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

    public static final class CreateDB_wordInfomationTable implements BaseColumns {
        public static final String _TABLENAME = "wordInfomation";

        public static final String _DATE = "date";

        public static final String _TITLE = "title";
        public static final String _TODAY = "today";
        public static final String _BIBLE = "bible";
        public static final String _CHAPTER = "chapter";
        public static final String _PASSAGESTARTNUM = "passageStartNum";
        public static final String _PASSAGEENDNUM = "passageEndNum";

        public static final String _CREATE_SETTING =
                "CREATE TABLE " + _TABLENAME + "("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + _DATE + " TEXT, "
                + _TITLE + " TEXT, "
                + _TODAY + " TEXT, "
                + _BIBLE + " TEXT, "
                + _CHAPTER + " TEXT, "
                + _PASSAGESTARTNUM + " INTEGER, "
                + _PASSAGEENDNUM + " INTEGER);";
    }

    public static final class CreateDB_wordTable implements BaseColumns {
        public static final String _TABLENAME = "word";

        public static final String _DATE = "date";
        public static final String _PASSAGE = "passage";

        public static final String _WORD = "word";

        public static final String _CREATE_SETTING =
                "CREATE TABLE " + _TABLENAME + "("
                        + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + _DATE + " TEXT, "
                        + _PASSAGE + " INTEGER, "
                        + _WORD + " TEXT);";
    }

    public static final class CreateDB_prayTable implements BaseColumns {
        public static final String _TABLENAME = "pray";

        public static final String _DATE = "date";
        public static final String _PRAYTITLE = "prayTitle";

        public static final String _PRAY = "pray";
        public static final String _NUMBER = "number";

        public static final String _CREATE_SETTING =
                "CREATE TABLE " + _TABLENAME + "("
                        + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + _DATE + " TEXT, "
                        + _PRAYTITLE + " TEXT, "
                        + _PRAY + " TEXT);";
    }
}
