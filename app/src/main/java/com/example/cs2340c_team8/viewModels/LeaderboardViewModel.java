package com.example.cs2340c_team8.viewModels;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.example.cs2340c_team8.models.Leaderboard;
import com.example.cs2340c_team8.models.Score;
import com.example.cs2340c_team8.views.activities.CreditsActivity;
import com.example.cs2340c_team8.views.activities.TitleActivity;

import java.util.List;

public class LeaderboardViewModel extends BaseObservable {
    private static final Leaderboard LEADERBOARD = Leaderboard.getInstance();
    private final Activity activity;
    private final int score;
    private final String time;
    private final String keys;
    private final boolean success;

    public LeaderboardViewModel(Activity activity) {
        this.activity = activity;
        this.score = activity.getIntent().getIntExtra("score", 0);
        this.time = activity.getIntent().getStringExtra("time");
        this.keys = activity.getIntent().getStringExtra("keys");
        this.success = activity.getIntent().getBooleanExtra("success", false);
    }

    public static void addNewScore(String username, int score, long time) {
        LEADERBOARD.addScore(new Score(username, score, time));
    }

    public Score getNthTopScore(int n) {
        List<Score> topScores = LEADERBOARD.getTopNScores(5);
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

    public void playAgain(View view) {
        Intent settings = new Intent(activity, TitleActivity.class);
        activity.startActivity(settings);
        activity.finish();
    }

    public void exitGame(View view) {
        Intent endGame = new Intent(activity, CreditsActivity.class);
        activity.startActivity(endGame);
        activity.finish();
    }
}
