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
import com.example.cs2340c_team8.models.GameConfig;
import com.example.cs2340c_team8.models.interfaces.PlayerMovement;
import com.example.cs2340c_team8.models.movements.DownPlayerMovement;
import com.example.cs2340c_team8.models.movements.LeftPlayerMovement;
import com.example.cs2340c_team8.models.movements.RightPlayerMovement;
import com.example.cs2340c_team8.models.movements.UpPlayerMovement;
import com.example.cs2340c_team8.viewmodels.DungeonViewModel;
import com.example.cs2340c_team8.viewmodels.LeaderboardViewModel;
import com.example.cs2340c_team8.views.enemies.BulletBillView;

import java.util.Timer;
import java.util.TimerTask;

public class DungeonActivity extends AppCompatActivity {
    private long startTime;
    private int score;
    private long scoreSeconds;
    private TextView timeElapsedTextView;
    private TextView scoreTextView;
    private int currImg = 1;
    private int playerSize = 16;
    private int playerColor = Color.BLUE;
    private PlayerMovement playerMovement;
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

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    runTimer();
                });
            }
        }, 0, 500);

        DungeonScreenBinding dungeonScreenBinding = DataBindingUtil
                .setContentView(this, R.layout.dungeon_screen);
        DungeonViewModel dungeonViewModel = new DungeonViewModel(DungeonActivity.this, timer);
        dungeonScreenBinding.setViewmodel(dungeonViewModel);
        Player.addObserver(dungeonViewModel);

        dungeonScreenBinding.setLifecycleOwner(this);
        dungeonScreenBinding.executePendingBindings();

        ConstraintLayout layout = findViewById(R.id.dungeonLayout);
        Thumbstick thumbstick = new Thumbstick(this,
                GameConfig.thumbstickX, GameConfig.thumbstickY);
        layout.addView(thumbstick);

        // TODO: Switch from blue box to a Player Sprite
//        Player player = Player.getInstance();
        PlayerView playerView = new PlayerView(this);
        Player.addObserver(playerView);
        layout.addView(playerView);

        BulletBillView bulletBillView = new BulletBillView(this);
        layout.addView(bulletBillView);

        // TODO: Abstract animation on level change to external methods
        // TODO: Remove Hard-coded Level Number
        int level = 1;

        LevelIndicatorView levelIndicatorView = new LevelIndicatorView(this,
                GameConfig.levelIndicatorX, GameConfig.levelIndicatorY, level);
        layout.addView(levelIndicatorView);

        int levelIndicatorSpriteX = GameConfig.levelIndicatorX - GameConfig.levelIndicatorSpriteOffset;
        int levelIndicatorSpriteY = GameConfig.levelIndicatorY - GameConfig.levelIndicatorSpriteOffset;
        int levelOffset = (level - 1) * GameConfig.levelIndicatorLevelOffset;

        ImageView spriteImageView = findViewById(R.id.level_indicator_sprite);
        spriteImageView.setX(levelIndicatorSpriteX);
        spriteImageView.setY(levelIndicatorSpriteY);
        spriteImageView.setZ(10);
        spriteImageView.animate().x(levelIndicatorSpriteX).setDuration(500);

        // TODO: Code to Animate Level Indicator while Updating the Level number
        /*
            spriteImageView.animate().x(levelIndicatorSpriteX + levelOffset).setDuration(3000);
            layout.removeView(levelIndicatorView);
            levelIndicatorView = new LevelIndicatorView(this, GameConfig.levelIndicatorX,
                GameConfig.levelIndicatorY, level + 1);
            layout.addView(levelIndicatorView);
            levelOffset = (level) * 102;
            spriteImageView.animate().x(levelIndicatorSpriteX + levelOffset).setDuration(3000);
        */

        startTime = currentTimeMillis();
        timeElapsedTextView = findViewById(R.id.time_elapsed);
        scoreTextView = findViewById(R.id.score_indicator);

        printMap(1);

        Button goRight = findViewById(R.id.rightButton);
        goRight.setOnClickListener(tempView -> {
            playerMovement = new RightPlayerMovement(bitmap2);
            movePlayer();
        });

        Button goLeft = findViewById(R.id.leftButton);
        goLeft.setOnClickListener(tempView -> {
            playerMovement = new LeftPlayerMovement(bitmap2);
            movePlayer();
        });

        Button goUp = findViewById(R.id.upButton);
        goUp.setOnClickListener(tempView -> {
            playerMovement = new UpPlayerMovement(bitmap2);
            movePlayer();
        });

        Button goDown = findViewById(R.id.downButton);
        goDown.setOnClickListener(tempView -> {
            playerMovement = new DownPlayerMovement(bitmap2);
            movePlayer();
        });
    }

    public void movePlayer() {
        if (playerMovement.canPlayerMove()) {
            currentMap.setImageBitmap(playerMovement.movePlayer());
            int newImg = playerMovement.checkLevelCompleted(currImg);

            if (newImg == -1) {
                loadEndingScreen();
            } else if (newImg != currImg) {
                currImg = newImg;
                printMap(newImg);
            }
        }
    }

    public void loadEndingScreen() {
        LeaderboardViewModel.addNewScore(GameConfig.username, score, currentTimeMillis() - startTime);

        Intent end = new Intent(DungeonActivity.this, LeaderboardActivity.class);
        end.putExtra("score", Integer.parseInt(((String) scoreTextView.getText())
                .split(" ")[1]));
        end.putExtra("time", timeElapsedTextView.getText());
        end.putExtra("keys", "3 of 3");
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
