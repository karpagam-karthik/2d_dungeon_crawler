package com.example.cs2340c_team8.viewmodels;

import android.content.Intent;

import androidx.databinding.BaseObservable;

import com.example.cs2340c_team8.models.Leaderboard;
import com.example.cs2340c_team8.views.GameOverActivity;
import com.example.cs2340c_team8.views.LeaderboardActivity;

public class LeaderboardViewModel extends BaseObservable {
    private Leaderboard leaderboard;
    private int score;
    private String time;
    private String keys;
    private String successString;

    public LeaderboardViewModel(int score, String time, String keys, boolean success) {
        leaderboard = Leaderboard.getLeaderboard();
        this.score = score;
        this.time = time;
        this.keys = keys;
        this.successString = success ? "Passed" : "Failed";
    }

    public Leaderboard getLeaderboard() {
        return leaderboard;
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

    public String getSuccessString() {
        return successString;
    }
}
