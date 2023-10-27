package com.example.cs2340c_team8.views;

import android.content.Intent;
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
import com.example.cs2340c_team8.models.GameConfig;
import com.example.cs2340c_team8.models.enums.MarioColor;

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

        GameConfig.setScreenWidth(getResources().getDisplayMetrics().widthPixels);
        GameConfig.setScreenHeight(getResources().getDisplayMetrics().heightPixels);

        TextView richTextView = findViewById(R.id.gameTitle);
        SpannableString gameTitle = new SpannableString("Super Mario\nDungeons");
        themeGameTitle(gameTitle);
        richTextView.setText(gameTitle);

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

    private void themeGameTitle(SpannableString text) {
        text.setSpan(new ForegroundColorSpan(
                GameConfig.getMarioColor(MarioColor.BLUE)), 0, 1, 0); // Blue
        text.setSpan(new ForegroundColorSpan(
                GameConfig.getMarioColor(MarioColor.YELLOW)), 1, 2, 0); // Yellow
        text.setSpan(new ForegroundColorSpan(
                GameConfig.getMarioColor(MarioColor.RED)), 2, 3, 0); // Red
        text.setSpan(new ForegroundColorSpan(
                GameConfig.getMarioColor(MarioColor.GREEN)), 3, 4, 0); // Green
        text.setSpan(new ForegroundColorSpan(
                GameConfig.getMarioColor(MarioColor.YELLOW)), 4, 5, 0); // Yellow
        text.setSpan(new ForegroundColorSpan(
                GameConfig.getMarioColor(MarioColor.RED)), 6, 7, 0);
        text.setSpan(new ForegroundColorSpan(
                GameConfig.getMarioColor(MarioColor.GREEN)), 7, 8, 0);
        text.setSpan(new ForegroundColorSpan(
                GameConfig.getMarioColor(MarioColor.YELLOW)), 8, 9, 0);
        text.setSpan(new ForegroundColorSpan(
                GameConfig.getMarioColor(MarioColor.BLUE)), 9, 10, 0);
        text.setSpan(new ForegroundColorSpan(
                GameConfig.getMarioColor(MarioColor.GREEN)), 10, 11, 0);
    }
}
