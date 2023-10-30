package com.example.cs2340c_team8.viewModels;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.databinding.BaseObservable;

public class CreditsViewModel extends BaseObservable {
    private final Activity activity;
    public CreditsViewModel(Activity activity) {
        this.activity = activity;
    }

    public void exitGame(View view) {
        Intent exit = new Intent(Intent.ACTION_MAIN);
        exit.addCategory(Intent.CATEGORY_HOME);
        exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(exit);
    }
}
