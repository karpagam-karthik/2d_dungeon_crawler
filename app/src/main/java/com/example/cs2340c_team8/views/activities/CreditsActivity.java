// Package declaration
package com.example.cs2340c_team8.views.activities;

// Import statements for required Android classes and libraries
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.databinding.DataBindingUtil;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.databinding.CreditsBinding;
import com.example.cs2340c_team8.viewModels.CreditsViewModel;

// Declaration of the 'CreditsActivity' class, extending AppCompatActivity
public class CreditsActivity extends AppCompatActivity {
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
        CreditsBinding creditsBinding = DataBindingUtil.setContentView(this, R.layout.credits);

        // Create and set up the CreditsViewModel
        CreditsViewModel creditsViewModel = new CreditsViewModel(CreditsActivity.this);
        creditsBinding.setViewModel(creditsViewModel);
        creditsBinding.setLifecycleOwner(this);
        creditsBinding.executePendingBindings();
    }
}
