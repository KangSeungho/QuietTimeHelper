package com.example.kangseungho.quiettimehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kangseungho.quiettimehelper.sqlite.DataBaseHelper;
import com.example.kangseungho.quiettimehelper.sqlite.DatabasesTables;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

public class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

    private SQLiteDatabase db;
    private DataBaseHelper DBHelper;
    private Context context;

    private String htmlPageUrl;

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static String date = dateFormat.format(new Date());

    private LinkedList<String> words = new LinkedList<>();
    private LinkedList<String> prayTitle = new LinkedList<>();
    private LinkedList<String> pray = new LinkedList<>();


    private LinkedList<String> meditationTitle = new LinkedList<>();
    private LinkedList<String> meditation = new LinkedList<>();
    private LinkedList<String> guideTitle = new LinkedList<>();
    private LinkedList<String> guides = new LinkedList<>();
    private LinkedList<String> prayerTitle = new LinkedList<>();
    private LinkedList<String> prayer = new LinkedList<>();
    private int check;

    public JsoupAsyncTask(String htmlPageUrl, int check, Context context) {
        this.htmlPageUrl = htmlPageUrl;
        this.check = check;
        this.context = context;

        DBHelper = new DataBaseHelper(context);
        db = DBHelper.getWritableDatabase();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void result) {
        textSetting();
        WordItem.instance.dataCheck = true;

        insertDB();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Document doc = Jsoup.connect(htmlPageUrl).get();

            // 제목 가져오기
            titleSelect(doc, "#qtDayText2 span.qtbigText2");

            // 오늘의 말씀
            todaySaySelect(doc, "#qtDayText2", check);

            if(check == 0) {

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

    private void titleSelect(Document doc, String cssQuery) {
        Elements titles = doc.select(cssQuery);

        WordItem.instance.setTitle(titles.text().trim());
    }

    private void todaySaySelect(Document doc, String cssQuery, int check) {
        Elements titles = doc.select(cssQuery);

        if(check == 0) {
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
            }
        }
        else if(check == 1) {
            String buffer = titles.html().trim();
            int start = buffer.indexOf("(") + 1;
            int end = buffer.indexOf(")");

            WordItem.instance.setToday(buffer.substring(start, end));
        }
    }

    private void wordsSelect(Document doc, String cssQuery) {
        Elements titles = doc.select(cssQuery);

        for(Element e : titles) {
            words.add(e.text().trim().split("\\.")[1]);
        }
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
            if(i==5) {
                tmp[i] = tmp[i].replace("? ", "?\n");
            }
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
        String tmp="";

        WordItem.instance.setDate(date);
        WordItem.instance.setWords(words);

        for(int i=0, n=0; i<meditationTitle.size(); i++) {
            prayTitle.add(meditationTitle.get(i));

            while(true) {
                tmp += meditation.get(i+n);
                if( (i+n+1 < meditation.size()) && (meditation.get(i+n+1).contains("절") || !meditation.get(i+n+1).contains("."))) {
                    tmp += "\n";
                    n++;
                }
                else {
                    pray.add(tmp);
                    tmp = "";
                    break;
                }
            }
        }

        templateText(guideTitle, guides);
        templateText(prayerTitle, prayer);

        WordItem.instance.setPrayTitle(prayTitle);
        WordItem.instance.setPray(pray);
    }

    private void templateText(LinkedList<String> title, LinkedList<String> words) {
        if(title.size() == words.size()) {
            Iterator<String> TitleIt = title.iterator();
            Iterator<String> wordsIt = words.iterator();

            while(TitleIt.hasNext() && wordsIt.hasNext()) {
                prayTitle.add(TitleIt.next());
                pray.add(wordsIt.next());
            }
        }
    }

    private void insertDB() {
        try {
            wordInfoInsert(date);
            wordInsert(date);
            prayInsert(date);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void wordInfoInsert(String date) {
        String strWordInfo =
                "INSERT INTO " + DatabasesTables.CreateDB_wordInfomationTable._TABLENAME + "("
                        + DatabasesTables.CreateDB_wordInfomationTable._DATE + ", "
                        + DatabasesTables.CreateDB_wordInfomationTable._TITLE + ", "
                        + DatabasesTables.CreateDB_wordInfomationTable._TODAY + ", "
                        + DatabasesTables.CreateDB_wordInfomationTable._BIBLE + ", "
                        + DatabasesTables.CreateDB_wordInfomationTable._CHAPTER + ", "
                        + DatabasesTables.CreateDB_wordInfomationTable._PASSAGESTARTNUM + ", "
                        + DatabasesTables.CreateDB_wordInfomationTable._PASSAGEENDNUM + ")"
                        + " VALUES('"
                        + WordItem.instance.getDate() + "', '"
                        + WordItem.instance.getTitle() + "', '"
                        + WordItem.instance.getToday() + "', '"
                        + WordItem.instance.getBible() + "', '"
                        + WordItem.instance.getChapter() + "', "
                        + WordItem.instance.getPassageStartNum() + ", "
                        + WordItem.instance.getPassageEndNum() + ");";

        db.execSQL(strWordInfo);
    }

    private void wordInsert(String date) {
        Iterator<String> wordIt = words.iterator();
        int i=0;

        while(wordIt.hasNext()) {
            String tmp =
                    "INSERT INTO " + DatabasesTables.CreateDB_wordTable._TABLENAME + "("
                    + DatabasesTables.CreateDB_wordTable._DATE + ", "
                    + DatabasesTables.CreateDB_wordTable._PASSAGE + ", "
                    + DatabasesTables.CreateDB_wordTable._WORD + ")"
                    + " VALUES('"
                    + WordItem.instance.getDate() + "', "
                    + (Integer.parseInt(WordItem.instance.getPassageStartNum()) + i) + ", '"
                    + wordIt.next() + "');";

            db.execSQL(tmp);
            i++;
        }
    }

    private void prayInsert(String date) {
        Iterator<String> prayTitleIt = prayTitle.iterator();
        Iterator<String> prayIt = pray.iterator();

        while(prayTitleIt.hasNext() && prayIt.hasNext()) {
            String tmp =
                    "INSERT INTO " + DatabasesTables.CreateDB_prayTable._TABLENAME + "("
                            + DatabasesTables.CreateDB_prayTable._DATE + ", "
                            + DatabasesTables.CreateDB_prayTable._PRAYTITLE + ", "
                            + DatabasesTables.CreateDB_prayTable._PRAY + ")"
                            + " VALUES('"
                            + WordItem.instance.getDate() + "', '"
                            + prayTitleIt.next() + "', '"
                            + prayIt.next() + "');";

            db.execSQL(tmp);
        }
    }
}