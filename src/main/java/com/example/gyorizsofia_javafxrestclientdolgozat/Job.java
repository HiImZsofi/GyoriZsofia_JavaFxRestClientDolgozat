package com.example.gyorizsofia_javafxrestclientdolgozat;

import com.google.gson.annotations.Expose;

public class Job {
    private int id;
    @Expose
    private String title;
    @Expose
    private String location;
    @Expose
    private String name;
    @Expose
    private int score;
    @Expose
    private boolean isworking;

    public Job(int id, String title, String location, String name, int score, boolean isworking) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.name = name;
        this.score = score;
        this.isworking = isworking;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isIsworking() {
        return isworking;
    }

    public void setIsworking(boolean isworking) {
        this.isworking = isworking;
    }
}
