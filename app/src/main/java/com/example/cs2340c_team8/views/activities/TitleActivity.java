package com.example.cs2340c_team8.views.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.databinding.DataBindingUtil;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.databinding.TitleBinding;
import com.example.cs2340c_team8.viewModels.TitleViewModel;

public class TitleActivity extends AppCompatActivity {
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

        TitleBinding titleBinding = DataBindingUtil.setContentView(this, R.layout.title);

        TitleViewModel titleViewModel = new TitleViewModel(TitleActivity.this);
        titleBinding.setViewModel(titleViewModel);
        titleBinding.setLifecycleOwner(this);
        titleBinding.executePendingBindings();
    }
}
