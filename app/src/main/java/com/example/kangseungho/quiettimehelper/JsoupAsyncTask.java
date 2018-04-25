package com.example.kangseungho.quiettimehelper;

import android.os.AsyncTask;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

    private String htmlPageUrl;
    private TextView textView;

    private String htmlContentInStringFormat = "";

    private LinkedList<String> meditationTitle = new LinkedList<>();
    private LinkedList<String> meditation = new LinkedList<>();
    private LinkedList<String> guideTitle = new LinkedList<>();
    private LinkedList<String> guides = new LinkedList<>();
    private LinkedList<String> prayerTitle = new LinkedList<>();
    private LinkedList<String> prayer = new LinkedList<>();

    JsoupAsyncTask(String htmlPageUrl, TextView textView) {
        this.htmlPageUrl = htmlPageUrl;
        this.textView = textView;
    }

    JsoupAsyncTask(String htmlPageUrl) {
        this.htmlPageUrl = htmlPageUrl;
        this.textView = null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void result) {
        if(textView != null) {
            textSetting();
            textView.setText(htmlContentInStringFormat);
        }

        WordItem.instance.dataCheck = true;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Document doc = Jsoup.connect(htmlPageUrl).get();

            // 오늘의 말씀
            todaySaySelect(doc, "#qtDayText2");

            if(textView != null) {
                // 성경 구절
                wordsSelect(doc, "div.qtBox li");

                // 내용 관찰 제목
                meditationTitleSelect(doc, "span.box2Title");

                // 내용 관찰
                meditationSelect(doc, "#content > p");

                // 말씀나누기, 은혜나누기, 함께기도하기
                elementSelect(doc, "div.bx2 div.box2Title", 0);

                // 각 컨텐츠
                elementSelect(doc, "div.bx2 div.box2Content", 1);

                // 골방, 중보, 열방
                prayerSelect(doc, "div.box2 div.box2Content li");
            }
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

            String qtTitle = buffer.substring(start, end);

            WordItem.instance.setBible(qtTitle.split("\\s+")[0]);
            String tmp = qtTitle.split("\\s+")[1];

            WordItem.instance.setChapter(tmp.split(":")[0]);
            String tmp2 = tmp.split(":")[1];

            WordItem.instance.setPassageStartNum(tmp2.split("~")[0]);
            WordItem.instance.setPassageEndNum(tmp2.split("~")[1]);

            htmlContentInStringFormat += WordItem.instance.getBible() + " " + WordItem.instance.getChapter() + "장 " + WordItem.instance.getPassageStartNum() + "~" + WordItem.instance.getPassageEndNum() + "절\n\n";
        }
    }

    private void wordsSelect(Document doc, String cssQuery) {
        Elements titles = doc.select(cssQuery);

        String wordsTmp = "";

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
        String htmlStr = "";

        for(Element e : titles) {
            if(e.ownText().trim().matches(".+"))
                htmlStr = e.ownText().trim();
        }

        String tmp[] = htmlStr.split("[0-9]\\.");

        for(int i=1; i<tmp.length; i++) {
            meditation.add(i + ". " + tmp[i]);
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
            Iterator<String> wordsIt = words.iterator();

            while(TitleIt.hasNext() && wordsIt.hasNext()) {
                htmlContentInStringFormat += TitleIt.next() + "\n" + wordsIt.next() + "\n\n";
            }
        }
    }

    public String getText() {
        return htmlContentInStringFormat;
    }

    /*
    private void todaySaySelect(Document doc, String cssQuery) {
        Elements titles = doc.select(cssQuery);

        for(Element e : titles) {
            String buffer = e.html().trim();
            int start = buffer.indexOf("(") + 1;
            int end = buffer.indexOf(")");

            String qtTitle = buffer.substring(start, end);

            bible = qtTitle.split("\\s+")[0];
            String tmp = qtTitle.split("\\s+")[1];

            chapter = tmp.split(":")[0];
            String tmp2 = tmp.split(":")[1];

            passageStartNum = tmp2.split("~")[0];
            passageEndNum = tmp2.split("~")[1];

            htmlContentInStringFormat += bible + " " + chapter + "장 " + passageStartNum + "~" + passageEndNum + "절\n\n";
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
        String htmlStr = "";

        for(Element e : titles) {
            if(e.ownText().trim().matches(".+"))
                htmlStr = e.ownText().trim();
        }

        String tmp[] = htmlStr.split("[0-9]\\.");

        for(int i=1; i<tmp.length; i++) {
            meditation.add(i + ". " + tmp[i]);
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
            Iterator<String> wordsIt = words.iterator();

            while(TitleIt.hasNext() && wordsIt.hasNext()) {
                htmlContentInStringFormat += TitleIt.next() + "\n" + wordsIt.next() + "\n\n";
            }
        }
    }
    */
}