package com.example.bequiet;

public class Profile {
    private String day;
    private int fromHour;
    private int fromMinute;
    private int toHour;
    private int toMinute;

    private int imageResource;
    private String title;
    private String description;


    public Profile(String day, int fromHour, int fromMinute, int toHour, int toMinute, int imageResource, String title, String description) {
        this.imageResource = imageResource;
        this.title = title;
        this.description = description;
        this.fromHour = fromHour;
        this.fromMinute = fromMinute;
        this.toHour = toHour;
        this.toMinute = toMinute;
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public int getFromHour() {
        return fromHour;
    }

    public int getFromMinute() {
        return fromMinute;
    }

    public int getToHour() {
        return toHour;
    }

    public int getToMinute() {
        return toMinute;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
