package com.example.cs2340c_team8.viewModels;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.models.GameConfig;

public class CreditsViewModel extends BaseObservable {
    private final Activity activity;
    public CreditsViewModel(Activity activity) {
        this.activity = activity;

        GameConfig.getMainThemePlayer().pause();
        GameConfig.setMainThemePlayer(MediaPlayer.create(activity, R.raw.credits));
        GameConfig.getMainThemePlayer().setVolume((float) 0.7, (float) 0.7);
        GameConfig.getMainThemePlayer().setLooping(true);
        GameConfig.getMainThemePlayer().start();
    }

    public void exitGame(View view) {
        GameConfig.getMainThemePlayer().stop();
        Intent exit = new Intent(Intent.ACTION_MAIN);
        exit.addCategory(Intent.CATEGORY_HOME);
        exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(exit);
    }
}
