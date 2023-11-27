// Package declaration
package com.example.cs2340c_team8.views.activities;

// Import statements for required Android classes and libraries
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.databinding.LeaderboardBinding;
import com.example.cs2340c_team8.viewModels.LeaderboardViewModel;

// Declaration of the 'LeaderboardActivity' class, extending AppCompatActivity
public class LeaderboardActivity extends AppCompatActivity {
    // Method called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set system window decorations and hide system bars
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        WindowInsetsControllerCompat windowInsetsController =
                ViewCompat.getWindowInsetsController(getWindow().getDecorView());
        if (windowInsetsController == null) {
            return;
        }
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());

        // Use DataBindingUtil to set content view and bind ViewModel
        LeaderboardBinding leaderboardBinding = DataBindingUtil
                .setContentView(this, R.layout.leaderboard);

        // Create and set up the LeaderboardViewModel
        LeaderboardViewModel leaderboardViewModel =
                new LeaderboardViewModel(LeaderboardActivity.this);
        leaderboardBinding.setViewModel(leaderboardViewModel);
        leaderboardBinding.setLifecycleOwner(this);
        leaderboardBinding.executePendingBindings();
    }
}
