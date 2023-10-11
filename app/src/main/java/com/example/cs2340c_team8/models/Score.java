package com.example.cs2340c_team8.models;

import static java.lang.System.currentTimeMillis;

import com.example.cs2340c_team8.models.enums.Difficulty;

import java.util.Date;

public class Score {
    private final int score;
    private final Player player;
    private final Difficulty difficulty;
    private final int level;
    private final long timeMilliseconds;
    private final Date date;
    public Score(int score, Player player, Difficulty difficulty, int level, int timeMilliseconds) {
        this.score = score;
        this.player = player;
        this.difficulty = difficulty;
        this.level = level;
        this.timeMilliseconds = timeMilliseconds;
        this.date = new Date(currentTimeMillis());
    }

    public int getScore() {
        return score;
    }

    public Player getPlayer() {
        return player;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getLevel() {
        return level;
    }

    public long getTimeMilliseconds() {
        return timeMilliseconds;
    }

    public Date getDate() {
        return date;
    }
}
