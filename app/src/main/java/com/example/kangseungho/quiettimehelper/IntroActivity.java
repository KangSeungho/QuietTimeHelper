package com.example.kangseungho.quiettimehelper;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.kangseungho.quiettimehelper.sqlite.DataBaseHelper;
import com.example.kangseungho.quiettimehelper.sqlite.DatabasesTables;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import static com.example.kangseungho.quiettimehelper.MainActivity.htmlPageUrl;

public class IntroActivity extends AppCompatActivity {
    SQLiteDatabase db;
    DataBaseHelper DBHelper;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = dateFormat.format(new Date());

    Handler handler = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent); // 다음 화면으로 전환
            finish(); // Activity화면 제거
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        DBHelper = new DataBaseHelper(getApplicationContext());
        db = DBHelper.getWritableDatabase();

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }

        ImageView intro = (ImageView) findViewById(R.id.intro_gif);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(intro);
        Glide.with(this).load(R.drawable.intro).into(gifImage);
    }

    @Override
    protected void onResume() {
        super.onResume();

        try{
            if(dbCheck()) {
                dbSelect();
            }
            else {
                JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask(htmlPageUrl, 0, getApplicationContext());
                jsoupAsyncTask.execute();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        handler.postDelayed(r, 2000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(r);
    }

    private boolean dbCheck() {
        String sql = "SELECT * FROM " + DatabasesTables.CreateDB_prayTable._TABLENAME
                + " WHERE " + DatabasesTables.CreateDB_wordInfomationTable._DATE
                + " = '" + date + "';";
        Cursor results = db.rawQuery(sql, null);
        results.moveToFirst();

        if(results.getCount() != 0) {
            results.close();
            return true;
        }

        else {
            results.close();
            return false;
        }
    }

    private void dbSelect() {
        selectWordInfo();
        selectWord();
        selectPray();
    }

    private void selectWordInfo() {
        String sql = "SELECT * FROM " + DatabasesTables.CreateDB_wordInfomationTable._TABLENAME
                + " WHERE " + DatabasesTables.CreateDB_wordInfomationTable._DATE
                + " = '" + date + "';";
        Cursor results = db.rawQuery(sql, null);
        results.moveToFirst();

        WordItem.instance.setDate(results.getString(1));
        WordItem.instance.setTitle(results.getString(2));
        WordItem.instance.setToday(results.getString(3));
        WordItem.instance.setBible(results.getString(4));
        WordItem.instance.setChapter(results.getString(5));
        WordItem.instance.setPassageStartNum(results.getString(6));
        WordItem.instance.setPassageEndNum(results.getString(7));

        results.close();
    }

    private void selectWord() {
        LinkedList<String> word = new LinkedList<>();
        String sql = "SELECT * FROM " + DatabasesTables.CreateDB_wordTable._TABLENAME
                + " WHERE " + DatabasesTables.CreateDB_wordTable._DATE + " = '" + date + "'"
                + " ORDER BY passage;";
        Cursor results = db.rawQuery(sql, null);
        results.moveToFirst();

        while(!results.isAfterLast()) {
            word.add(results.getString(3));
            results.moveToNext();
        }

        WordItem.instance.setWords(word);

        results.close();
    }

    private void selectPray() {
        LinkedList<String> prayTitle = new LinkedList<>();
        LinkedList<String> pray = new LinkedList<>();

        String sql = "SELECT * FROM " + DatabasesTables.CreateDB_prayTable._TABLENAME
                + " WHERE " + DatabasesTables.CreateDB_prayTable._DATE + " = '" + date + "';";
        Cursor results = db.rawQuery(sql, null);
        results.moveToFirst();

        while(!results.isAfterLast()) {
            prayTitle.add(results.getString(2));
            pray.add(results.getString(3));
            results.moveToNext();
        }

        WordItem.instance.setPrayTitle(prayTitle);
        WordItem.instance.setPray(pray);

        results.close();
    }
}
