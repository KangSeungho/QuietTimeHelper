package com.example.kangseungho.quiettimehelper;

import java.util.LinkedList;

public class WordItem {
    public static WordItem instance = new WordItem();

    public boolean dataCheck = false;

    private String title, today;

    private String bible, chapter, passageStartNum, passageEndNum;
    private LinkedList<String> words = new LinkedList<>();
    private LinkedList<String> prayTitle = new LinkedList<>();
    private LinkedList<String> pray = new LinkedList<>();

    private LinkedList<String> meditationTitle = new LinkedList<>();
    private LinkedList<String> meditation = new LinkedList<>();
    private LinkedList<String> guideTitle = new LinkedList<>();
    private LinkedList<String> guides = new LinkedList<>();
    private LinkedList<String> prayerTitle = new LinkedList<>();
    private LinkedList<String> prayer = new LinkedList<>();

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



    public LinkedList<String> getMeditationTitle() {
        return meditationTitle;
    }
    public void setMeditationTitle(LinkedList<String> meditationTitle) {
        this.meditationTitle = meditationTitle;
    }

    public LinkedList<String> getMeditation() {
        return meditation;
    }
    public void setMeditation(LinkedList<String> meditation) {
        this.meditation = meditation;
    }

    public LinkedList<String> getGuideTitle() {
        return guideTitle;
    }
    public void setGuideTitle(LinkedList<String> guideTitle) {
        this.guideTitle = guideTitle;
    }

    public LinkedList<String> getGuides() {
        return guides;
    }
    public void setGuides(LinkedList<String> guides) {
        this.guides = guides;
    }

    public LinkedList<String> getPrayerTitle() {
        return prayerTitle;
    }
    public void setPrayerTitle(LinkedList<String> prayerTitle) {
        this.prayerTitle = prayerTitle;
    }

    public LinkedList<String> getPrayer() {
        return prayer;
    }
    public void setPrayer(LinkedList<String> prayer) {
        this.prayer = prayer;
    }
}
