package com.example.plzfind;

import java.io.Serializable;

// PostItem.java
public class PostItem implements Serializable {
    private String title;
    private String content;
    private String regDate;
    private String place;
    private String imageName;

    public PostItem(String title, String content, String regDate, String place, String imageName) {
        this.title = title;
        this.content = content;
        this.regDate = regDate;
        this.place = place;
        this.imageName = imageName;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getRegDate() {
        return regDate;
    }

    public String getPlace() {
        return place;
    }

    public String getImageName() {
        return imageName;
    }


    // 각 이미지에 대한 메소드 추가
    public static String getAirpotImage() {
        return "airpot";
    }

    public static String getMoneyImage() {
        return "money";
    }

    public static String getBearImage() {
        return "bear";
    }

    public static String getPhoneImage() {
        return "phone";
    }

    public static String getCardImage() {
        return "card";
    }

    public static String getChangerImage() {
        return "changer";
    }

    public static String getBagImage() {
        return "bag";
    }
    public static String getBirdImage() {
        return "bird";
    }
    public static String getAndroidImage() {
        return "android";
    }
    public static String getCardKeyImage() {
        return "cardkey";
    }
    public static String getBuriImage() {
        return "buri";
    }

}
