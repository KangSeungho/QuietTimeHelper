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

    private String htmlPageUrl = "http://www.365qt.com/TodaysQT.asp";
    private TextView textViewHtmlDocument;
    private String htmlContentInStringFormat= "";

    private String qtTitle;
    private LinkedList<String> wordNum = new LinkedList<>();
    private LinkedList<String> words = new LinkedList<>();
    private LinkedList<String> meditationTitle = new LinkedList<>();
    private LinkedList<String> meditation = new LinkedList<>();
    private LinkedList<String> guideTitle = new LinkedList<>();
    private LinkedList<String> guides = new LinkedList<>();
    private LinkedList<String> prayerTitle = new LinkedList<>();
    private LinkedList<String> prayer = new LinkedList<>();
    private String wordsTmp = "";

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

                // 내용 관찰 제목
                meditationTitleSelect(doc, "span.box2Title");

                // 내용 관찰
                meditationSelect(doc, "p");

                // 말씀나누기, 은혜나누기, 함께기도하기
                elementSelect(doc, "div.bx2 div.box2Title", 0);

                // 각 컨텐츠
                elementSelect(doc, "div.bx2 div.box2Content", 1);

                // 골방, 중보, 열방
                prayerSelect(doc, "div.box2 div.box2Content li");


                // 글자 셋팅
                textSetting();
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

        private void meditationTitleSelect(Document doc, String cssQuery) {
            Elements titles = doc.select(cssQuery);

            for(Element e : titles) {
                meditationTitle.add(e.text().trim());
            }
        }

        private void meditationSelect(Document doc, String cssQuery) {
            Elements titles = doc.select(cssQuery);
            LinkedList<String> htmlStr = new LinkedList<>();
            String selHtml ="";

            for(Element e : titles) {
                htmlStr.add(e.html());
            }

            for(String tmp : htmlStr) {
                if(tmp.contains("box2Title")) {
                    selHtml = tmp;
                    break;
                }
            }

            String splitTmp[] = selHtml.split("<br>");

            for(String str : splitTmp) {
                if(str.contains("?")) {
                    meditation.add(str);
                }
            }
        }

        private void elementSelect(Document doc, String cssQuery, int check) {
            Elements titles = doc.select(cssQuery);

            for(Element e : titles) {
                if(check == 0)
                    guideTitle.add(e.text().trim());
                else
                    guides.add(e.text().trim());
            }
        }

        private void prayerSelect(Document doc, String cssQuery) {
            Elements titles = doc.select(cssQuery);

            for(Element e : titles) {
                String str[] = e.text().trim().split(":");

                prayerTitle.add(str[0]);
                prayer.add(str[1]);
            }
        }

        private void textSetting() {
            for(int i=0, n=0; i<meditationTitle.size(); i++) {
                htmlContentInStringFormat += meditationTitle.get(i) + "\n";

                while(true) {
                    htmlContentInStringFormat += meditation.get(i+n) + "\n";
                    if( (i+n+1 < meditation.size()) && (meditation.get(i+n+1).contains("절") || !meditation.get(i+n+1).contains(".")))
                        n++;
                    else
                        break;
                }

                htmlContentInStringFormat += "\n";
            }

            templateText(guideTitle, guides);
            templateText(prayerTitle, prayer);
        }

        private void templateText(LinkedList<String> title, LinkedList<String> words) {
            if(title.size() == words.size()) {
                Iterator<String> TitleIt = title.iterator();
                Iterator<String> wordssIt = words.iterator();

                while(TitleIt.hasNext() && wordssIt.hasNext()) {
                    htmlContentInStringFormat += TitleIt.next() + "\n" + wordssIt.next() + "\n\n";
                }
            }
        }
    }
}