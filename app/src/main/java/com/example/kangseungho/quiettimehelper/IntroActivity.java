package com.example.kangseungho.quiettimehelper;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import static com.example.kangseungho.quiettimehelper.MainActivity.htmlPageUrl;

public class IntroActivity extends AppCompatActivity {
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

        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask(htmlPageUrl, 0);
        jsoupAsyncTask.execute();

        handler.postDelayed(r, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(r);
    }
}
