package com.example.cs2340c_team8.views;

import static java.lang.System.currentTimeMillis;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.databinding.DataBindingUtil;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.databinding.DungeonScreenBinding;
import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.enums.Character;
import com.example.cs2340c_team8.models.enums.Difficulty;
import com.example.cs2340c_team8.models.interfaces.MovementStrategy;
import com.example.cs2340c_team8.models.interfaces.PlayerObserver;
import com.example.cs2340c_team8.models.DownMovement;
import com.example.cs2340c_team8.models.LeftMovement;
import com.example.cs2340c_team8.models.RightMovement;
import com.example.cs2340c_team8.models.UpMovement;
import com.example.cs2340c_team8.viewmodels.DungeonViewModel;
import com.example.cs2340c_team8.viewmodels.LeaderboardViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class DungeonActivity extends AppCompatActivity {
    private long startTime;
    private int score;
    private String username;
    private long scoreSeconds;
    private TextView timeElapsedTextView;
    private TextView scoreTextView;
    private int currImg = 1;
    private int topLeftY = 25;
    private int topLeftX = 25;
    private int playerSize = 16;
    private int playerColor = Color.BLUE;
    private int tileColor = Color.WHITE;
    private MovementStrategy movementStrategy;
    private BitmapDrawable mapOneBitmapDrawable;
    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private ImageView currentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        WindowInsetsControllerCompat windowInsetsController =
                ViewCompat.getWindowInsetsController(getWindow().getDecorView());
        if (windowInsetsController == null) {
            return;
        }
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());

        DungeonScreenBinding dungeonScreenBinding = DataBindingUtil
                .setContentView(this, R.layout.dungeon_screen);

        startTime = currentTimeMillis();
        username = getIntent().getStringExtra("username");
        Difficulty difficulty = Difficulty.values()[getIntent()
                .getIntExtra("difficulty", 1)];
        String sprite = getIntent().getStringExtra("sprite");
        DungeonViewModel dungeonViewModel = new DungeonViewModel(username, difficulty, sprite);
        dungeonScreenBinding.setViewmodel(dungeonViewModel);

        dungeonScreenBinding.setLifecycleOwner(this);
        dungeonScreenBinding.executePendingBindings();

        ConstraintLayout layout = findViewById(R.id.dungeonLayout);

        // TODO: Joystick functionality testing
//        Thumbstick thumbstick = new Thumbstick(this, getCharacterFromSpriteString(sprite));
//        layout.addView(thumbstick);

        // TODO: Switch from blue box to a Player Sprite
//        Player player = Player.getInstance();
//        PlayerView playerView = new PlayerView(this, getCharacterFromSpriteString(sprite));
//        player.addObserver(playerView);
//        layout.addView(playerView);

        // TODO: Abstract animation on level change to external methods
//        int level = 1;                                                                            // Test Level Number
//        int levelIndicatorStartX = 100;                                                           // TODO: Remove Hard-coded value
//        int levelIndicatorStartY = getResources().getDisplayMetrics().heightPixels - 32;          // TODO: Remove Hard-coded value

//        LevelIndicatorView levelIndicatorView =
//                new LevelIndicatorView(this, levelIndicatorStartX, levelIndicatorStartY,
//                        getCharacterFromSpriteString(sprite), level);                             // Creates a new LevelIndicatorView
//        layout.addView(levelIndicatorView);                                                       // Displays the View on the Layout

//        int levelIndicatorSpriteOffset = 70;                                                      // TODO: Create a constant for this value
//        int levelIndicatorSpriteStartX = levelIndicatorStartX - levelIndicatorSpriteOffset;       // Relative Offset for Sprite's X Coordinate
//        int levelIndicatorSpriteStartY = levelIndicatorStartY - levelIndicatorSpriteOffset;       // Relative Offset for Sprite's Y Coordinate
//        int levelOffset = (level - 1) * 102;                                                      // Horizontal Offset to position Sprite on the appropriate level circle. TODO: Create a constant for the value 102
//
//        ImageView spriteImageView = findViewById(R.id.level_indicator_sprite);
//        spriteImageView.setX(levelIndicatorSpriteStartX);
//        spriteImageView.setY(levelIndicatorSpriteStartY);
//        spriteImageView.setZ(20);

//        spriteImageView.animate().x(levelIndicatorSpriteStartX).setDuration(500);                 // Brings the Sprite onto the First Level
//        spriteImageView.animate().x(levelIndicatorSpriteStartX + levelOffset).setDuration(3000);  // Moves the Sprite to Test Level

        // IMP: Code to Animate Level Indicator while Updating the Level number

        /*
            layout.removeView(levelIndicatorView);
            levelIndicatorView = new LevelIndicatorView(this, levelIndicatorStartX,
                levelIndicatorStartY, getCharacterFromSpriteString(sprite), level + 1);
            layout.addView(levelIndicatorView);
            levelOffset = (level) * 102;
            spriteImageView.animate().x(levelIndicatorSpriteStartX + levelOffset).setDuration(3000);
        */

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

        printMap(1);

        Button goRight = findViewById(R.id.rightButton);
        goRight.setOnClickListener(tempView -> {
            movementStrategy = new RightMovement(bitmap2);
            movePlayer();
        });

        Button goLeft = findViewById(R.id.leftButton);
        goLeft.setOnClickListener(tempView -> {
            movementStrategy = new LeftMovement(bitmap2);
            movePlayer();
        });

        Button goUp = findViewById(R.id.upButton);
        goUp.setOnClickListener(tempView -> {
            movementStrategy = new UpMovement(bitmap2);
            movePlayer();
        });

        Button goDown = findViewById(R.id.downButton);
        goDown.setOnClickListener(tempView -> {
            movementStrategy = new DownMovement(bitmap2);
            movePlayer();
        });
    }

    private Character getCharacterFromSpriteString(String spriteString) {
        switch (spriteString) {
        case "Mario":
            return Character.MARIO;
        case "Luigi":
            return Character.LUIGI;
        default:
            return Character.PRINCESS_PEACH;
        }
    }

    public void movePlayer() {
        if (movementStrategy.canPlayerMove()) {
            currentMap.setImageBitmap(movementStrategy.movePlayer());
            int newImg = movementStrategy.checkLevelCompleted(currImg);

            if (newImg == -1) {
                loadEndingScreen();
            } else if (newImg != currImg) {
                currImg = newImg;
                printMap(newImg);
            }
        }
    }

    public void loadEndingScreen() {
        LeaderboardViewModel.addNewScore(username, score, currentTimeMillis() - startTime);

        Intent end = new Intent(DungeonActivity.this, LeaderboardActivity.class);
        end.putExtra("score", Integer.parseInt(((String) scoreTextView.getText())
                .split(" ")[1]));
        end.putExtra("time", timeElapsedTextView.getText());
        end.putExtra("keys", "0 of 3");
        end.putExtra("success", true);
        startActivity(end);
        finish();
    }

    // Method to print specific map to DungeonActivity screen
    public void printMap(int mapNumber) {
        if (mapNumber == 1) {
            mapOneBitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.map1);
            bitmap1 = mapOneBitmapDrawable.getBitmap();
            bitmap2 = Bitmap.createBitmap(bitmap1.getWidth(),
                    bitmap1.getHeight(), Bitmap.Config.ARGB_8888);

            for (int i = 0; i < bitmap1.getWidth(); i++) {
                for (int j = 0; j < bitmap1.getHeight(); j++) {
                    int pixel = bitmap1.getPixel(i, j);
                    bitmap2.setPixel(i, j, pixel);
                }
            }

            currentMap = findViewById(R.id.mapImage);
            currentMap.setImageBitmap(bitmap2);
            for (int r = 25; r < 25 + playerSize; r++) {
                for (int c = 25; c < 25 + playerSize; c++) {
                    bitmap2.setPixel(r, c, playerColor);
                }
            }
            currentMap.setImageBitmap(bitmap2);

        } else if (mapNumber == 2) {
            mapOneBitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.map2);
            bitmap1 = mapOneBitmapDrawable.getBitmap();
            bitmap2 = Bitmap.createBitmap(bitmap1.getWidth(),
                    bitmap1.getHeight(), Bitmap.Config.ARGB_8888);
            for (int i = 0; i < bitmap1.getWidth(); i++) {
                for (int j = 0; j < bitmap1.getHeight(); j++) {
                    int pixel = bitmap1.getPixel(i, j);
                    bitmap2.setPixel(i, j, pixel);
                }
            }
            currentMap = findViewById(R.id.mapImage);
            currentMap.setImageBitmap(bitmap2);
            for (int r = 25; r < 25 + playerSize; r++) {
                for (int c = 25; c < 25 + playerSize; c++) {
                    bitmap2.setPixel(r, c, playerColor);
                }
            }
            currentMap.setImageBitmap(bitmap2);
        } else if (mapNumber == 3) {
            mapOneBitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.map3);
            bitmap1 = mapOneBitmapDrawable.getBitmap();
            bitmap2 = Bitmap.createBitmap(bitmap1.getWidth(),
                    bitmap1.getHeight(), Bitmap.Config.ARGB_8888);
            for (int i = 0; i < bitmap1.getWidth(); i++) {
                for (int j = 0; j < bitmap1.getHeight(); j++) {
                    int pixel = bitmap1.getPixel(i, j);
                    bitmap2.setPixel(i, j, pixel);
                }
            }
            currentMap = findViewById(R.id.mapImage);
            currentMap.setImageBitmap(bitmap2);
            for (int r = 25; r < 25 + playerSize; r++) {
                for (int c = 25; c < 25 + playerSize; c++) {
                    bitmap2.setPixel(r, c, playerColor);
                }
            }
            currentMap.setImageBitmap(bitmap2);
        }
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
}
