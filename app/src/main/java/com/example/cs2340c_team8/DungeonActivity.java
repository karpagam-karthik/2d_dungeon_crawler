package com.example.cs2340c_team8;

import static java.lang.System.currentTimeMillis;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.example.cs2340c_team8.databinding.DungeonScreenBinding;
import com.example.cs2340c_team8.models.Wall;
import com.example.cs2340c_team8.models.enums.Difficulty;
import com.example.cs2340c_team8.viewmodels.DungeonViewModel;
import com.example.cs2340c_team8.viewmodels.LeaderboardViewModel;
import com.example.cs2340c_team8.views.LeaderboardActivity;
import com.example.cs2340c_team8.views.WallView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class DungeonActivity extends AppCompatActivity {
    private long startTime;
    private long scoreNum;
    private long scoreSeconds;
    private TextView timeElapsedTextView;
    private TextView difficultyTextView;
    private TextView numericalScoreTextView;
    private TextView healthTextView;
    private TextView spriteTextView;
    private ImageView spriteImageView;
    private Map<Wall, WallView> wallViewMap = new HashMap<>();
    private List<Wall> wallList = new ArrayList<>();
    private ConstraintLayout dungeonLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DungeonScreenBinding dungeonScreenBinding = DataBindingUtil.setContentView(this, R.layout.dungeon_screen);
        String username = getIntent().getStringExtra("username");
        Difficulty difficulty = Difficulty.values()[getIntent().getIntExtra("difficulty", 1)];
        String sprite = getIntent().getStringExtra("sprite");
        dungeonScreenBinding.setViewmodel(new DungeonViewModel(username, difficulty, sprite));

        dungeonScreenBinding.setLifecycleOwner(this);
        dungeonScreenBinding.executePendingBindings();

        dungeonLayout = findViewById(R.id.dungeonLayout);

        timeElapsedTextView = findViewById(R.id.time_elapsed);
        difficultyTextView = findViewById(R.id.difficulty_indicator);
        numericalScoreTextView = findViewById(R.id.numericalScore);
        healthTextView = findViewById(R.id.health_count);
        spriteTextView = findViewById(R.id.spriteTextView);
        spriteImageView = findViewById(R.id.spriteImageView);

        startTime = currentTimeMillis();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    runTimer();
                });
            }
        }, 0, 500);

        setDifficultyAndHealth(difficulty);
        setSprite(sprite);

        Button button = findViewById(R.id.end_level_button);
        button.setOnClickListener(v -> {
            Intent end = new Intent(DungeonActivity.this, LeaderboardActivity.class);
            end.putExtra("score", 8265);
            end.putExtra("time", timeElapsedTextView.getText());
            end.putExtra("keys", "2 of 3");
            end.putExtra("success", true);
            startActivity(end);
            finish();
        });

        drawDungeon();
    }

    protected void setDifficultyAndHealth(Difficulty difficulty) {
        switch (difficulty) {
        case BEGINNER:
            difficultyTextView.setText("Beginner");
            healthTextView.setText(String.format("Health: %d", 200));
            break;
        case INTERMEDIATE:
            difficultyTextView.setText("Intermediate");
            healthTextView.setText(String.format("Health: %d", 150));
            break;
        case EXPERT:
            difficultyTextView.setText("Expert");
            healthTextView.setText(String.format("Health: %d", 100));
            break;
        default:
            difficultyTextView.setText("N/A");
            break;
        }
    }

    protected void setSprite(String sprite) {
        spriteTextView.setText(sprite);
        switch (sprite) {
        case "Wizard":
            spriteImageView.setImageResource(R.drawable.wizard_sprite);
            break;
        case "Elf":
            spriteImageView.setImageResource(R.drawable.elf_sprite);
            break;
        case "Knight":
            spriteImageView.setImageResource(R.drawable.knight_sprite);
            break;
        default:
            spriteImageView.setImageResource(R.drawable.knight_sprite);
            break;
        }
    }

    protected void runTimer() {
        long runTime = currentTimeMillis() - startTime;
        long hours = runTime / (1000 * 60 * 60);
        long minutes = (runTime / (1000 * 60)) % 60;
        long seconds = (runTime / 1000) % 60;
        scoreSeconds = (runTime / 1000);

        scoreNum = 999 - scoreSeconds;
        if (scoreNum < 0) {
            scoreNum = 0;
        }

        timeElapsedTextView.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        numericalScoreTextView.setText(String.format("%02d", scoreNum));

    }

    public long getScoreNum() {
        return scoreNum;
    }

    public void setScoreSeconds(long num) {
        scoreSeconds = num;
    }

    private void drawDungeon() {
        //TODO Add dependency on difficulty and level
        int[][] dungeonMap = {
                {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1}
        };

        int[] layoutCoordinates = {30, 390};
        int size = 36;
        for (int i = 0; i < dungeonMap.length; i++) {
            for (int j = 0; j < dungeonMap[i].length; j++) {
                if (dungeonMap[i][j] == 1) {
                    Wall wall = new Wall(layoutCoordinates[0] + (j * (size + 5)),
                            layoutCoordinates[1] + (i * (size + 5)), size, size, null);
                    WallView wallView = new WallView(this, wall);

                    dungeonLayout.addView(wallView);
                    wallViewMap.put(wall, wallView);
                    wallList.add(wall);
                }
            }
        }
    }
}
