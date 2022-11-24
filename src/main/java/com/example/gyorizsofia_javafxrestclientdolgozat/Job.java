package com.example.gyorizsofia_javafxrestclientdolgozat;

import com.google.gson.annotations.Expose;

public class Job {
    private int id;
    @Expose
    private String jobTitle;
    @Expose
    private String jobLocation;
    @Expose
    private String jobName;
    @Expose
    private int jobScore;
    @Expose
    private boolean isWorking;

    public Job(int id, String title, String location, String name, int score, boolean isWorking) {
        this.id = id;
        this.jobTitle = title;
        this.jobLocation = location;
        this.jobName = name;
        this.jobScore = score;
        this.isWorking = isWorking;
    }

    public int getId() {
        return id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getJobScore() {
        return jobScore;
    }

    public void setJobScore(int jobScore) {
        this.jobScore = jobScore;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        this.isWorking = working;
    }
}
