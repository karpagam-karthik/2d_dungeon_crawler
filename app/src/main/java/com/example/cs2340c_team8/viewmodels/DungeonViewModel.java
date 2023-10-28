package com.example.cs2340c_team8.viewmodels;

import static java.lang.System.currentTimeMillis;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.models.GameConfig;
import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.interfaces.PlayerObserver;
import com.example.cs2340c_team8.views.LeaderboardActivity;

import androidx.databinding.library.baseAdapters.BR;

import java.util.Timer;

public class DungeonViewModel extends BaseObservable implements PlayerObserver {
    private final Activity activity;
    private final Player player = Player.getInstance();
    private final Timer timer;
    private boolean stopUpdating;
    private int health = player.getHealth();
    public DungeonViewModel (Activity activity, Timer timer) {
        this.activity = activity;
        this.timer = timer;
        this.stopUpdating = false;
    }
    public boolean isFirePowerUpActive() {
        return true; //TODO Check if Player has collected the Power-Up
    }

    public boolean isIcePowerUpActive() {
        return false; //TODO Check if Player has collected the Power-Up
    }

    public boolean isStarPowerUpActive() {
        return false; //TODO Check if Player has collected the Power-Up
    }

    public int getSpriteImage() {
        return GameConfig.fetchCharacterMiniSprite();
    }

    public String getDifficultyText() {
        switch (GameConfig.difficulty) {
        case INTERMEDIATE:
            return "Intermediate";
        case EXPERT:
            return "Expert";
        default:
            return "Beginner";
        }
    }

    @Bindable
    public String getHealth() {
        return Integer.toString(health);
    }

    public String getUsername() {
        return GameConfig.username;
    }

    public String getSpriteText() {
        switch (GameConfig.character) {
        case LUIGI:
            return "Luigi";
        case PRINCESS_PEACH:
            return "Princess Peach";
        default:
            return "Mario";
        }
    }

    @Override
    public void updatePlayerPosition(int startX, int startY, int endX, int endY) {
        health = player.getHealth();

        if (health <= 0 && !stopUpdating) {
            this.stopUpdating = true;

            TextView timeElapsedTextView = activity.findViewById(R.id.time_elapsed);
            TextView scoreTextView = activity.findViewById(R.id.score_indicator);
            int score = Integer.parseInt(((String) scoreTextView.getText()).split(" ")[1]);

            LeaderboardViewModel.addNewScore(GameConfig.username, score, currentTimeMillis() - 100);

            System.out.println("Uh-oh");

            Intent end = new Intent(activity, LeaderboardActivity.class);
            end.putExtra("score", score);
            end.putExtra("time", timeElapsedTextView.getText());
            end.putExtra("keys", "0 of 3");
            end.putExtra("success", false);

            activity.startActivity(end);
            activity.finish();

            return;
        }

        notifyPropertyChanged(BR.health);
    }
}
