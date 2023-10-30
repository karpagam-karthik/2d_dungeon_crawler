package com.example.cs2340c_team8.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.databinding.DataBindingUtil;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.databinding.CreditsBinding;
import com.example.cs2340c_team8.databinding.GameSettingsBinding;
import com.example.cs2340c_team8.viewModels.CreditsViewModel;
import com.example.cs2340c_team8.viewModels.GameSettingsViewModel;

public class CreditsActivity extends AppCompatActivity {
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

        CreditsBinding creditsBinding = DataBindingUtil.setContentView(this, R.layout.credits);

        CreditsViewModel creditsViewModel = new CreditsViewModel(CreditsActivity.this);
        creditsBinding.setViewModel(creditsViewModel);
        creditsBinding.setLifecycleOwner(this);
        creditsBinding.executePendingBindings();
    }
}