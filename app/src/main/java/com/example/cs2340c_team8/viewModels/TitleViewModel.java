package com.example.cs2340c_team8.viewModels;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.models.GameConfig;
import com.example.cs2340c_team8.models.enums.MarioColor;
import com.example.cs2340c_team8.views.activities.GameSettingsActivity;

public class TitleViewModel extends BaseObservable {
    private final Activity activity;

    public TitleViewModel(Activity activity) {
        this.activity = activity;

        GameConfig.setMainThemePlayer(MediaPlayer.create(activity, R.raw.main));
        GameConfig.setScreenWidth(activity.getResources().getDisplayMetrics().widthPixels);
        GameConfig.setScreenHeight(activity.getResources().getDisplayMetrics().heightPixels);

        GameConfig.getMainThemePlayer().setLooping(true);
        GameConfig.getMainThemePlayer().start();
    }

    public SpannableString createGameTitle() {
        SpannableString gameTitle = new SpannableString("Super Mario\nDungeons");
        themeGameTitle(gameTitle);
        return gameTitle;
    }

    public void playGame(View view) {
        Intent settings = new Intent(activity, GameSettingsActivity.class);
        activity.startActivity(settings);
        activity.finish();
    }

    public void exitGame(View view) {
        GameConfig.getMainThemePlayer().stop();
        Intent exit = new Intent(Intent.ACTION_MAIN);
        exit.addCategory(Intent.CATEGORY_HOME);
        exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(exit);
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
