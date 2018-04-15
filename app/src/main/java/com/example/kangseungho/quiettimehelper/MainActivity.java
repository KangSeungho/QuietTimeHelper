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

public class MainActivity extends AppCompatActivity {

    private String htmlPageUrl = "http://www.365qt.com/TodaysQT.asp?WD=0";
    private TextView textViewHtmlDocument;
    private String htmlContentInStringFormat= "";

    private String qtTitle;
    private String wordNum[], words[];
    private String prayTitle[], praies[];
    private String wordsTmp = "", prayTitleTmp = "", praiesTmp = "";

    int cnt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewHtmlDocument = (TextView) findViewById(R.id.textView);
        textViewHtmlDocument.setMovementMethod(new ScrollingMovementMethod());

        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute();

        Button htmlTitleButton = (Button) findViewById(R.id.button);
        htmlTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
                jsoupAsyncTask.execute();
                cnt++;

                /*
                if(qtTitle != null) {
                    try {
                        htmlContentInStringFormat += "오늘의 말씀\n" + qtTitle + "\n\n";

                        for (int i = 0; i < wordNum.length; i++) {
                            htmlContentInStringFormat += words[i] + "\n";
                        }

                        htmlContentInStringFormat += "\n";

                        for(int i=0; i<prayTitle.length; i++) {
                            htmlContentInStringFormat += prayTitle[i] + " : " + praies[i] + "\n";
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                */
            }
        });
    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void result) {
            textViewHtmlDocument.setText(htmlContentInStringFormat);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect(htmlPageUrl).get();

                // 오늘의 말씀
                todaySaySelect(doc, "#qtDayText2");

                // 성경 구절
                wordsSelect(doc, "div.qtBox li");

                // 말씀나누기, 은혜나누기, 함께기도하기
                elementSelect(doc, "div.bx2 div.box2Title", 0);

                // 각 컨텐츠
                elementSelect(doc, "div.bx2 div.box2Content", 1);

                textDivision();
                /*
                // 테스트 1
                elementSelect(doc, "div.news-con h1.tit-news");

                // 테스트 2
                elementSelect(doc, "div.news-con h2.tit-news");

                // 테스트 3
                elementSelect(doc, "li.section02 div.con h2.news-tl");
                */
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        private void todaySaySelect(Document doc, String cssQuery) {
            Elements titles = doc.select(cssQuery);

            for(Element e : titles) {
                String buffer = e.html().trim();
                int start = buffer.indexOf("(") + 1;
                int end = buffer.indexOf(")");

                qtTitle = buffer.substring(start, end);
                htmlContentInStringFormat += qtTitle + "\n\n";
            }
        }

        private void wordsSelect(Document doc, String cssQuery) {
            Elements titles = doc.select(cssQuery);

            for(Element e : titles) {
                wordsTmp += e.text().trim() + "\n";
            }
            htmlContentInStringFormat += wordsTmp + "\n";
        }

        private void elementSelect(Document doc, String cssQuery, int check) {
            Elements titles = doc.select(cssQuery);

            for(Element e : titles) {
                if(check == 0)
                    prayTitleTmp += e.text().trim() + "\n";
                else
                    praiesTmp += e.text().trim() + "\n";
                /*
                if(count == 0) {
                    prayTitleTmp += e.text().trim() + "\n";
                    htmlContentInStringFormat += prayTitleTmp;
                }
                else {
                    praiesTmp += e.text().trim() + "\n";
                    htmlContentInStringFormat += praiesTmp;
                }
                */
            }

            /*
            if(check == 0)
                htmlContentInStringFormat += prayTitleTmp;
            else
                htmlContentInStringFormat += praiesTmp;
                */
            //htmlContentInStringFormat += "\n";
        }

        private void textDivision() {
            //words = wordsTmp.split("\n");
            prayTitle = prayTitleTmp.split("\n");
            praies = praiesTmp.split("\n");

            if(prayTitle.length == praies.length) {
                for(int i=0; i<prayTitle.length; i++) {
                    htmlContentInStringFormat += prayTitle[i] + "\n" + praies[i] + "\n\n";
                }
            }
        }
    }
}