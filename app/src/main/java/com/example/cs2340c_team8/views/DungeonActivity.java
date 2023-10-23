package com.example.cs2340c_team8.views;

import static java.lang.System.currentTimeMillis;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
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
    private int borderBlack = Color.BLACK;
    private int borderGreen = Color.GREEN;
    private BitmapDrawable mapOneBitmapDrawable;
    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private ImageView currentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DungeonScreenBinding dungeonScreenBinding = DataBindingUtil
                .setContentView(this, R.layout.dungeon_screen);

        startTime = currentTimeMillis();
        username = getIntent().getStringExtra("username");
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

        // Call to create initial map 1 settings
        printMap(1);

        Button goRight = findViewById(R.id.rightButton);
        goRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View tempView) {
                if (canMove("r")) {
                    for (int a = topLeftY; a < topLeftY + playerSize; a++) {
                        bitmap2.setPixel(topLeftX, a, tileColor);
                    }
                    topLeftX++;
                    for (int r = topLeftX; r < topLeftX + playerSize; r++) {
                        for (int c = topLeftY; c < topLeftY + playerSize; c++) {
                            bitmap2.setPixel(r, c, playerColor);
                        }
                    }
                    currentMap.setImageBitmap(bitmap2);
                    mapCompleted();
                }
            }
        });

        Button goLeft = findViewById(R.id.leftButton);
        goLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View tempView) {
                if (canMove("l")) {
                    for (int a = topLeftY; a < topLeftY + playerSize; a++) {
                        bitmap2.setPixel(topLeftX + playerSize - 1, a, tileColor);
                    }
                    topLeftX--;
                    for (int r = topLeftX; r < topLeftX + playerSize; r++) {
                        for (int c = topLeftY; c < topLeftY + playerSize; c++) {
                            bitmap2.setPixel(r, c, playerColor);
                        }
                    }
                    currentMap.setImageBitmap(bitmap2);
                    mapCompleted();
                }
            }
        });

        Button goUp = findViewById(R.id.upButton);
        goUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View tempView) {
                if (canMove("u")) {
                    for (int a = topLeftX; a < topLeftX + 16; a++) {
                        bitmap2.setPixel(a, topLeftY + playerSize - 1, tileColor);
                    }
                    topLeftY--;
                    for (int r = topLeftX; r < topLeftX + playerSize; r++) {
                        for (int c = topLeftY; c < topLeftY + playerSize; c++) {
                            bitmap2.setPixel(r, c, playerColor);
                        }
                    }
                    currentMap.setImageBitmap(bitmap2);
                    mapCompleted();
                }
            }
        });

        Button goDown = findViewById(R.id.downButton);
        goDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View tempView) {
                if (canMove("d")) {
                    for (int a = topLeftX; a < topLeftX + 16; a++) {
                        bitmap2.setPixel(a, topLeftY, tileColor);
                    }
                    topLeftY++;
                    for (int r = topLeftX; r < topLeftX + playerSize; r++) {
                        for (int c = topLeftY; c < topLeftY + playerSize; c++) {
                            bitmap2.setPixel(r, c, playerColor);
                        }
                    }
                    currentMap.setImageBitmap(bitmap2);
                    mapCompleted();
                }
            }
        });
    }

    // Method to print specific map to DungeonActivity screen
    public void printMap(int mapNumber) {
        if (mapNumber == 1) {
            mapOneBitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.map1);
            bitmap1 = mapOneBitmapDrawable.getBitmap();
            bitmap2 = Bitmap.createBitmap(bitmap1.getWidth(), bitmap1.getHeight(), Bitmap.Config.ARGB_8888);
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
            bitmap2 = Bitmap.createBitmap(bitmap1.getWidth(), bitmap1.getHeight(), Bitmap.Config.ARGB_8888);
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
            bitmap2 = Bitmap.createBitmap(bitmap1.getWidth(), bitmap1.getHeight(), Bitmap.Config.ARGB_8888);
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

    // Method to detect if wall is in front of character, returns whether or not it can proceed with the movement
    public boolean canMove(String direction) {
        switch (direction) {
            case "r": {
                int xth = topLeftX + 16;
                for (int y = topLeftY; y < topLeftY + 16; y++) {
                    if (bitmap2.getPixel(xth, y) != Color.WHITE) {
                        return false;
                    }
                }
                return true;
            }
            case "l": {
                int xth = topLeftX - 1;
                for (int y = topLeftY; y < topLeftY + 16; y++) {
                    if (bitmap2.getPixel(xth, y) != Color.WHITE) {
                        return false;
                    }
                }
                return true;
            }
            case "u": {
                int yth = topLeftY - 1;
                for (int x = topLeftX; x < topLeftX + 16; x++) {
                    if (bitmap2.getPixel(x, yth) != Color.WHITE) {
                        return false;
                    }
                }
                return true;
            }
            case "d": {
                int yth = topLeftY + 16;
                for (int x = topLeftX; x < topLeftX + 16; x++) {
                    if (bitmap2.getPixel(x, yth) != Color.WHITE) {
                        return false; // If any pixel is not white, return false
                    }
                }
                return true;
            }
        }
        return false;
    }

    // Method that's constantly being called to see if player has completed a specific map
    public void mapCompleted() {
        if (topLeftX >= 0 && topLeftX <= 20 && topLeftY >= 0 && topLeftY <= 20 && currImg == 1) {
            currImg = 2;
            topLeftY = 25;
            topLeftX = 25;
            printMap(2);
        } else if (topLeftX >= 0 && topLeftX <= 20 && topLeftY >= 0 && topLeftY <= 20 && currImg == 2) {
            currImg = 3;
            topLeftY = 25;
            topLeftX = 25;
            printMap(3);
        } else if (topLeftX >= 0 && topLeftX <= 20 && topLeftY >= 0 && topLeftY <= 20 && currImg == 3) {
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
