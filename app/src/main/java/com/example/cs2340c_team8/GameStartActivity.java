package com.example.cs2340c_team8;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class GameStartActivity extends AppCompatActivity {
    private int currImg = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

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

        ImageView startScreenMap = findViewById(R.id.mapOne);
        Button tempChangeMap = findViewById(R.id.tempNextButton);

        tempChangeMap.setOnClickListener(new View.OnClickListener() {
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
}
