package com.example.cs2340c_team8.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Button;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.databinding.LeaderboardBinding;
import com.example.cs2340c_team8.viewModels.LeaderboardViewModel;

public class LeaderboardActivity extends AppCompatActivity {
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

        LeaderboardBinding leaderboardBinding = DataBindingUtil
                .setContentView(this, R.layout.leaderboard);

        LeaderboardViewModel leaderboardViewModel = new LeaderboardViewModel(LeaderboardActivity.this);
        leaderboardBinding.setViewModel(leaderboardViewModel);
        leaderboardBinding.setLifecycleOwner(this);
        leaderboardBinding.executePendingBindings();
    }
}