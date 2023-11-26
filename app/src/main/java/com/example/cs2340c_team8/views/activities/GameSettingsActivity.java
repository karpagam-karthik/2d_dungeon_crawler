// Package declaration
package com.example.cs2340c_team8.views.activities;

// Import statements for required AndroidX and application-specific classes
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

// Declaration of the 'GameSettingsActivity' class, extending AppCompatActivity
public class GameSettingsActivity extends AppCompatActivity {

    // Method called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configure the window to not fit the system windows
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        // Get the window's insets controller and hide system bars
        WindowInsetsControllerCompat windowInsetsController =
                ViewCompat.getWindowInsetsController(getWindow().getDecorView());
        if (windowInsetsController == null) {
            return;
        }
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());

        // Set up data binding for the layout
        GameSettingsBinding gameSettingsBinding = DataBindingUtil
                .setContentView(this, R.layout.game_settings);

        // Create an instance of the ViewModel for game settings
        GameSettingsViewModel gameSettingsViewModel =
                new GameSettingsViewModel(GameSettingsActivity.this);

        // Bind the ViewModel to the layout
        gameSettingsBinding.setViewModel(gameSettingsViewModel);
        gameSettingsBinding.setLifecycleOwner(this);
        gameSettingsBinding.executePendingBindings();
    }
}
