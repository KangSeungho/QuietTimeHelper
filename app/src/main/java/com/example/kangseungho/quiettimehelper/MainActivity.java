package com.example.kangseungho.quiettimehelper;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.kangseungho.quiettimehelper.Fragment.*;

public class MainActivity extends AppCompatActivity {

    public static String htmlPageUrl = "http://www.365qt.com/TodaysQT.asp";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_words:
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, new WordsFragment()).commit();
                    return true;
                case R.id.navigation_pray:
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, new PrayFragment()).commit();
                    return true;
                case R.id.navigation_setting:
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, new SettingFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.content, new WordsFragment()).commit();
    }
}