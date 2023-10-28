package com.example.cs2340c_team8.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.models.GameConfig;
import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.enums.Character;
import com.example.cs2340c_team8.models.enums.Difficulty;

public class GameOptionsActivity extends AppCompatActivity {
    private EditText usernameInput;
    private RadioGroup rgDifficulty;
    private RadioGroup rgSprite;
    private Button continueButton;

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

        setContentView(R.layout.game_options_screen);

        usernameInput = findViewById(R.id.usernameInput);
        rgDifficulty = findViewById(R.id.radioGroupDifficulty);
        rgSprite = findViewById(R.id.radioGroupSprite);
        continueButton = findViewById(R.id.continueButton);

        continueButton.setOnClickListener(view -> startGame());
    }

    private void startGame() {
        String username = usernameInput.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Enter a Username", Toast.LENGTH_SHORT).show();
            return;
        }
        GameConfig.setUsername(username);

        RadioButton selectedDifficulty = findViewById(rgDifficulty.getCheckedRadioButtonId());
        GameConfig.setDifficulty(deriveChosenDifficulty(selectedDifficulty.getText().toString()));

        RadioButton selectedSprite = findViewById(rgSprite.getCheckedRadioButtonId());
        GameConfig.setCharacter(deriveChosenCharacter(selectedSprite.getText().toString()));

        Intent gameIntent = new Intent(this, DungeonActivity.class);
        startActivity(gameIntent);
        finish();
    }

    private Difficulty deriveChosenDifficulty(String difficultyText) {
        switch (difficultyText) {
        case "Intermediate":
            return Difficulty.INTERMEDIATE;
        case "Expert":
            return Difficulty.EXPERT;
        default:
            return Difficulty.BEGINNER;
        }
    }

    private Character deriveChosenCharacter(String characterText) {
        switch (characterText) {
        case "Luigi":
            return Character.LUIGI;
        case "Princess Peach":
            return Character.PRINCESS_PEACH;
        default:
            return Character.MARIO;
        }
    }
}

