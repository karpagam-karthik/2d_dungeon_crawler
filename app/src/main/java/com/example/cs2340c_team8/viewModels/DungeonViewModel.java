package com.example.cs2340c_team8.viewModels;

import static java.lang.System.currentTimeMillis;

import android.app.Activity;
import android.content.Intent;
import android.health.connect.datatypes.units.Power;
import android.media.metrics.PlaybackErrorEvent;
import android.widget.TextView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.models.GameConfig;
import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.enums.PowerUpType;
import com.example.cs2340c_team8.models.interfaces.PlayerObserver;
import com.example.cs2340c_team8.models.interfaces.PowerUp;
import com.example.cs2340c_team8.views.activities.LeaderboardActivity;

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
        return player.getPowerUps().containsKey(PowerUpType.FIRE);
    }

    public boolean isIcePowerUpActive() {
        return player.getPowerUps().containsKey(PowerUpType.ICE);
    }

    public boolean isStarPowerUpActive() {
        return player.getPowerUps().containsKey(PowerUpType.STAR);
    }

    public int getSpriteImage() {
        return GameConfig.fetchCharacterMiniSprite();
    }

    public String getDifficultyText() {
        switch (GameConfig.getDifficulty()) {
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
        return GameConfig.getUsername();
    }

    public String getSpriteText() {
        switch (GameConfig.getCharacter()) {
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
            player.clearObservers();

            TextView timeElapsedTextView = activity.findViewById(R.id.time_elapsed);
            TextView scoreTextView = activity.findViewById(R.id.score_indicator);
            int score = Integer.parseInt(((String) scoreTextView.getText()).split(" ")[1]);

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
