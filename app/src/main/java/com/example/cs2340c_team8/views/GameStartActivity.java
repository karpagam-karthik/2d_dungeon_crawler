package com.example.cs2340c_team8.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.TextView;

import com.example.cs2340c_team8.R;

public class GameStartActivity extends AppCompatActivity {
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

        setContentView(R.layout.game_start_screen);

        TextView richTextView = findViewById(R.id.gameTitle);
        SpannableString text = new SpannableString("Super Mario\nDungeons");
        text.setSpan(new ForegroundColorSpan(Color.rgb(4, 156, 216)), 0, 1, 0); // Blue
        text.setSpan(new ForegroundColorSpan(Color.rgb(251, 208, 0)), 1, 2, 0); // Yellow
        text.setSpan(new ForegroundColorSpan(Color.rgb(229, 37, 33)), 2, 3, 0); // Red
        text.setSpan(new ForegroundColorSpan(Color.rgb(67, 176, 71)), 3, 4, 0); // Green
        text.setSpan(new ForegroundColorSpan(Color.rgb(251, 208, 0)), 4, 5, 0); // Yellow
        text.setSpan(new ForegroundColorSpan(Color.rgb(229, 37, 33)), 6, 7, 0);
        text.setSpan(new ForegroundColorSpan(Color.rgb(67, 176, 71)), 7, 8, 0);
        text.setSpan(new ForegroundColorSpan(Color.rgb(251, 208, 0)), 8, 9, 0);
        text.setSpan(new ForegroundColorSpan(Color.rgb(4, 156, 216)), 9, 10, 0);
        text.setSpan(new ForegroundColorSpan(Color.rgb(67, 176, 71)), 10, 11, 0);
        richTextView.setText(text);

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
