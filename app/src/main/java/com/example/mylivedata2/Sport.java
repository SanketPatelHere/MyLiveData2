package com.example.mylivedata2;

public class Sport {
    private String title, info;
    private int imageResource;
    public Sport(String title, String info, int imageResource) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
    }
    String getTitle() {
        return title;
    }
    String getInfo() {
        return info;
    }
    public int getImageResource() {
        return imageResource;
    }
}
