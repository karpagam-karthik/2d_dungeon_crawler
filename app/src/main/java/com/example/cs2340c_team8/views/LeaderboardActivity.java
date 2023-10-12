package com.example.cs2340c_team8.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.viewmodels.LeaderboardViewModel;
import com.example.cs2340c_team8.databinding.LeaderboardScreenBinding;
public class LeaderboardActivity extends AppCompatActivity {
    private Button endGameButton;
    private Button playAgainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LeaderboardScreenBinding leaderboardScreenBinding = DataBindingUtil.setContentView(this, R.layout.leaderboard_screen);

        int score = getIntent().getIntExtra("score", 0);
        String timeElapsed = getIntent().getStringExtra("time");
        String keysCollected = getIntent().getStringExtra("keys");
        boolean levelPassed = getIntent().getBooleanExtra("success", false);
        leaderboardScreenBinding.setViewmodel(new LeaderboardViewModel(score, timeElapsed, keysCollected, levelPassed));

        leaderboardScreenBinding.setLifecycleOwner(this);
        leaderboardScreenBinding.executePendingBindings();

        endGameButton = findViewById(R.id.end_game_button);
        endGameButton.setOnClickListener(v -> {
            Intent endGame = new Intent(LeaderboardActivity.this, GameOverActivity.class);
            startActivity(endGame);
            finish();
        });

        playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setOnClickListener(v -> {
            Intent settings = new Intent(LeaderboardActivity.this, GameStartActivity.class);
            startActivity(settings);
            finish();
        });
    }
}