package com.example.cs2340c_team8.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.databinding.GameSettingsBinding;
import com.example.cs2340c_team8.viewModels.GameSettingsViewModel;

public class GameSettingsActivity extends AppCompatActivity {
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

        GameSettingsBinding gameSettingsBinding = DataBindingUtil
                .setContentView(this, R.layout.game_settings);

        GameSettingsViewModel gameSettingsViewModel =
                new GameSettingsViewModel(GameSettingsActivity.this);
        gameSettingsBinding.setViewModel(gameSettingsViewModel);
        gameSettingsBinding.setLifecycleOwner(this);
        gameSettingsBinding.executePendingBindings();
    }
}

