package com.example.kangseungho.quiettimehelper;

import java.util.LinkedList;

public class WordItem {
    public static WordItem instance = new WordItem();

    public boolean dataCheck = false;

    private String date, title, today;

    private String bible, chapter, passageStartNum, passageEndNum;
    private LinkedList<String> words = new LinkedList<>();
    private LinkedList<String> prayTitle = new LinkedList<>();
    private LinkedList<String> pray = new LinkedList<>();

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getToday() {
        return today;
    }
    public void setToday(String today) {
        this.today = today;
    }

    public String getBible() {
        return bible;
    }
    public void setBible(String bible) {
        this.bible = bible;
    }

    public String getChapter() {
        return chapter;
    }
    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getPassageStartNum() {
        return passageStartNum;
    }
    public void setPassageStartNum(String passageStartNum) {
        this.passageStartNum = passageStartNum;
    }

    public String getPassageEndNum() {
        return passageEndNum;
    }
    public void setPassageEndNum(String passageEndNum) {
        this.passageEndNum = passageEndNum;
    }

    public LinkedList<String> getWords() {
        return words;
    }
    public void setWords(LinkedList<String> words) {
        this.words = words;
    }

    public LinkedList<String> getPrayTitle() {
        return prayTitle;
    }
    public void setPrayTitle(LinkedList<String> prayTitle) {
        this.prayTitle = prayTitle;
    }

    public LinkedList<String> getPray() {
        return pray;
    }
    public void setPray(LinkedList<String> pray) {
        this.pray = pray;
    }
}
