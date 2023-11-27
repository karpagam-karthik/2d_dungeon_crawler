package com.example.cs2340c_team8.viewModels;

// Android imports
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.TextView;

// Data Binding imports
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

// Project-specific imports
import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.models.GameConfig;
import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.enums.PowerUpType;
import com.example.cs2340c_team8.models.interfaces.PlayerObserver;
import com.example.cs2340c_team8.views.activities.LeaderboardActivity;

// Java utility import
import java.util.Timer;

// Declaration of the 'DungeonViewModel' class, extending 'BaseObservable' and implementing 'PlayerObserver'
public class DungeonViewModel extends BaseObservable implements PlayerObserver {

    // Private members of the class
    private final Activity activity;
    private final Player player = Player.getInstance();
    private final Timer timer;
    private boolean stopUpdating;
    private int health = player.getHealth();

    // Constructor for 'DungeonViewModel' class
    public DungeonViewModel(Activity activity, Timer timer) {
        this.activity = activity;
        this.timer = timer;
        this.stopUpdating = false;

        GameConfig.setLevelPlayer(MediaPlayer.create(activity, R.raw.level));
        GameConfig.getMainThemePlayer().setVolume((float) 0.33, (float) 0.33);
    }

    // Methods to check if specific power-ups are active
    public boolean isFirePowerUpActive() {
        return player.getPowerUps().containsKey(PowerUpType.FIRE);
    }

    public boolean isIcePowerUpActive() {
        return player.getPowerUps().containsKey(PowerUpType.ICE);
    }

    public boolean isStarPowerUpActive() {
        return player.getPowerUps().containsKey(PowerUpType.STAR);
    }

    // Getter method for the sprite image
    public int getSpriteImage() {
        return GameConfig.fetchCharacterMiniSprite();
    }

    // Getter method for the difficulty text
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

    // Getter method for the health as a bindable property
    @Bindable
    public String getHealth() {
        return Integer.toString(health);
    }

    // Getter method for the username
    public String getUsername() {
        return GameConfig.getUsername();
    }

    // Getter method for the sprite text
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

    // Override method from 'PlayerObserver' interface to update player position
    @Override
    public void updatePlayerPosition(int startX, int startY, int endX, int endY) {
        health = player.getHealth();

        // Check if player health is zero or less and stop updating if it is
        if (health <= 0 && !stopUpdating) {
            this.stopUpdating = true;
            player.clearObservers();

            GameConfig.setLevelPlayer(MediaPlayer.create(activity, R.raw.dead));
            GameConfig.getLevelPlayer().start();
            GameConfig.getLevelPlayer().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    // Get references to UI elements  
                    TextView timeElapsedTextView = activity.findViewById(R.id.time_elapsed);
                    TextView scoreTextView = activity.findViewById(R.id.score_indicator);
                    int score = Integer.parseInt(((String) scoreTextView.getText()).split(" ")[1]);

                    // Create an intent to navigate to the LeaderboardActivity
                    Intent end = new Intent(activity, LeaderboardActivity.class);
                    end.putExtra("score", score);
                    end.putExtra("time", timeElapsedTextView.getText());
                    end.putExtra("keys", "0 of 3");
                    end.putExtra("success", false);
                    
                    // Start the LeaderboardActivity and finish the current activity
                    activity.startActivity(end);
                    activity.finish();
                }
            });

            return;
        }

        // Notify Data Binding that the 'health' property has changed
        notifyPropertyChanged(BR.health);
    }
}
