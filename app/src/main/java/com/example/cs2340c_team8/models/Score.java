package com.example.cs2340c_team8.models;

import static java.lang.System.currentTimeMillis;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Score {
    private final String username;
    private final int score;
    private final long timeMilliseconds;
    private final Date date;
    public Score(String username, int score, long timeMilliseconds) {
        this.username = username;
        this.score = score;
        this.timeMilliseconds = timeMilliseconds;
        this.date = new Date(currentTimeMillis());
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public long getTimeMilliseconds() {
        return timeMilliseconds;
    }

    public Date getDate() {
        return date;
    }

    public String getDateString() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd hh:mm:ss");
        return dateFormat.format(getDate());
    }
}
