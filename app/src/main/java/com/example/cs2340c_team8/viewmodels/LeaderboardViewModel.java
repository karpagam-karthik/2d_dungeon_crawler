package com.example.cs2340c_team8.viewmodels;

import androidx.databinding.BaseObservable;

import com.example.cs2340c_team8.models.Leaderboard;
import com.example.cs2340c_team8.models.Score;

import java.util.List;

public class LeaderboardViewModel extends BaseObservable {
    private static Leaderboard leaderboard = Leaderboard.getLeaderboard();
    private int score;
    private String time;
    private String keys;
    private boolean success;

    public LeaderboardViewModel(int score, String time, String keys, boolean success) {
        this.score = score;
        this.time = time;
        this.keys = keys;
        this.success = success;
    }

    public static void addNewScore(String username, int score, long time) {
        leaderboard.addScore(new Score(username, score, time));
    }

    public Score getNthTopScore(int n) {
        List<Score> topScores = leaderboard.getTopNScores(5);
        if (n >= topScores.size()) {
            return null;
        }
        return topScores.get(n);
    }

    public int getScore() {
        return score;
    }

    public String getTime() {
        return time;
    }

    public String getKeys() {
        return keys;
    }

    public boolean getSuccess() {
        return success;
    }
}
