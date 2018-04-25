package com.example.kangseungho.quiettimehelper;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    public static String htmlPageUrl = "http://www.365qt.com/TodaysQT.asp";

    private TextView textViewHtmlDocument;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewHtmlDocument = (TextView) findViewById(R.id.textView);
        textViewHtmlDocument.setMovementMethod(new ScrollingMovementMethod());

        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask(htmlPageUrl, textViewHtmlDocument);
        jsoupAsyncTask.execute();

        new AlarmHATT(getApplicationContext()).Alarm();
    }
}