package com.example.cs2340c_team8.views;

import static java.lang.System.currentTimeMillis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.databinding.DungeonScreenBinding;
import com.example.cs2340c_team8.models.enums.Difficulty;
import com.example.cs2340c_team8.models.interfaces.PlayerObserver;
import com.example.cs2340c_team8.viewmodels.DungeonViewModel;
import com.example.cs2340c_team8.viewmodels.LeaderboardViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class DungeonActivity extends AppCompatActivity implements PlayerObserver {
    private long startTime;
    private int score;
    private long scoreSeconds;
    private TextView timeElapsedTextView;
    private TextView scoreTextView;
    private int currImg = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DungeonScreenBinding dungeonScreenBinding = DataBindingUtil
                .setContentView(this, R.layout.dungeon_screen);

        startTime = currentTimeMillis();
        String username = getIntent().getStringExtra("username");
        Difficulty difficulty = Difficulty.values()[getIntent()
                .getIntExtra("difficulty", 1)];
        String sprite = getIntent().getStringExtra("sprite");
        dungeonScreenBinding.setViewmodel(new DungeonViewModel(username, difficulty, sprite));

        dungeonScreenBinding.setLifecycleOwner(this);
        dungeonScreenBinding.executePendingBindings();

        timeElapsedTextView = findViewById(R.id.time_elapsed);
        scoreTextView = findViewById(R.id.score_indicator);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    runTimer();
                });
            }
        }, 0, 500);

        Button button = findViewById(R.id.end_level_button);
        button.setOnClickListener(v -> {
            LeaderboardViewModel.addNewScore(username, score, currentTimeMillis() - startTime);

            Intent end = new Intent(DungeonActivity.this, LeaderboardActivity.class);
            end.putExtra("score", Integer.parseInt(((String) scoreTextView.getText())
                    .split(" ")[1]));
            end.putExtra("time", timeElapsedTextView.getText());
            end.putExtra("keys", "2 of 3");
            end.putExtra("success", true);
            startActivity(end);
            finish();
        });

        ImageView startScreenMap = findViewById(R.id.mapImage);
        Button nextMapButton = findViewById(R.id.nextMapButton);
        nextMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View tempView) {
                if (currImg == 1) {
                    startScreenMap.setImageResource(R.drawable.map_two);
                    currImg = 2;
                } else if (currImg == 2) {
                    startScreenMap.setImageResource(R.drawable.map_three);
                    currImg = 3;
                } else if (currImg == 3) {
                    startScreenMap.setImageResource(R.drawable.map_one);
                    currImg = 1;
                }
            }
        });
    }

    protected void runTimer() {
        long runTime = currentTimeMillis() - startTime;
        long hours = runTime / (1000 * 60 * 60);
        long minutes = (runTime / (1000 * 60)) % 60;
        long seconds = (runTime / 1000) % 60;
        scoreSeconds = (runTime / 1000);

        score = 999 - (int) scoreSeconds;
        if (score < 0) {
            score = 0;
        }

        timeElapsedTextView.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        scoreTextView.setText(String.format("Score: %02d", score));
    }

    public long getScore() {
        return score;
    }

    public void setScoreSeconds(long num) {
        scoreSeconds = num;
    }

    @Override
    public void update(String str) {

    }
}
