package com.example.cs2340c_team8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class LevelScoreActivity extends AppCompatActivity {

    private TextView scoreTextView;
    private TextView timeElapsedTextView;
    private TextView keysCollectedTextView;
    private TextView levelPassedTextView;
    private Button endGameButton;
    private int score;
    private String timeElapsed;
    private String keysCollected;
    private boolean levelPassed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_screen);

        score = getIntent().getIntExtra("score", 0);
        timeElapsed = getIntent().getStringExtra("time");
        keysCollected = getIntent().getStringExtra("keys");
        levelPassed = getIntent().getBooleanExtra("success", false);

        scoreTextView = findViewById(R.id.scoreValueText);
        scoreTextView.setText(String.format("%d", score));

        timeElapsedTextView = findViewById(R.id.timeElapsedScoreText);
        timeElapsedTextView.setText(timeElapsed);

        keysCollectedTextView = findViewById(R.id.keyPassedText);
        keysCollectedTextView.setText(keysCollected);

        levelPassedTextView = findViewById(R.id.levelPassedText);
        levelPassedTextView.setText((levelPassed) ? "Passed" : "Failed");

        endGameButton = findViewById(R.id.end_game_button);
        endGameButton.setOnClickListener(v -> {
            Intent endGame = new Intent(LevelScoreActivity.this, LeaderboardActivity.class);
            startActivity(endGame);
            finish();
        });
    }
}