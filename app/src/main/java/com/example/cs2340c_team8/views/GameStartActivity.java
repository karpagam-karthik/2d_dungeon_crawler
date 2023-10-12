package com.example.cs2340c_team8.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cs2340c_team8.R;

public class GameStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_start_screen);

        Button playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(v -> {
            Intent settings = new Intent(GameStartActivity.this, GameOptionsActivity.class);
            startActivity(settings);
            finish();
        });

        Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(v -> {
            Intent exit = new Intent(Intent.ACTION_MAIN);
            exit.addCategory(Intent.CATEGORY_HOME);
            exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(exit);
        });

    }
}
