package com.example.kangseungho.quiettimehelper;

import java.util.LinkedList;

public class WordItem {
    public static WordItem instance = new WordItem();

    private String bible, chapter, passageStartNum, passageEndNum;
    private LinkedList<String> wordNum = new LinkedList<>();
    private LinkedList<String> words = new LinkedList<>();
    private LinkedList<String> meditationTitle = new LinkedList<>();
    private LinkedList<String> meditation = new LinkedList<>();
    private LinkedList<String> guideTitle = new LinkedList<>();
    private LinkedList<String> guides = new LinkedList<>();
    private LinkedList<String> prayerTitle = new LinkedList<>();
    private LinkedList<String> prayer = new LinkedList<>();

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

    public LinkedList<String> getWordNum() {
        return wordNum;
    }
    public void setWordNum(LinkedList<String> wordNum) {
        this.wordNum = wordNum;
    }

    public LinkedList<String> getWords() {
        return words;
    }
    public void setWords(LinkedList<String> words) {
        this.words = words;
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
