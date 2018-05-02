package com.example.kangseungho.quiettimehelper.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "qt.db";
    private static final int DATABASE_VERSION = 5;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabasesTables.CreateDB_wordInfomationTable._CREATE_SETTING);
        db.execSQL(DatabasesTables.CreateDB_wordTable._CREATE_SETTING);
        db.execSQL(DatabasesTables.CreateDB_prayTable._CREATE_SETTING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DatabasesTables.CreateDB_wordInfomationTable._TABLENAME);
        db.execSQL("DROP TABLE IF EXISTS "+DatabasesTables.CreateDB_wordTable._TABLENAME);
        db.execSQL("DROP TABLE IF EXISTS "+DatabasesTables.CreateDB_prayTable._TABLENAME);
        onCreate(db);
    }
}
