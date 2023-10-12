package com.example.cs2340c_team8.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cs2340c_team8.R;

public class GameOptionsActivity extends AppCompatActivity {
    private EditText usernameInput;
    private RadioGroup rgDifficulty;
    private RadioGroup rgSprite;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_options_screen);

        usernameInput = findViewById(R.id.usernameInput);
        rgDifficulty = findViewById(R.id.radioGroupDifficulty);
        rgSprite = findViewById(R.id.radioGroupSprite);
        continueButton = findViewById(R.id.continueButton);

        continueButton.setOnClickListener(view -> startGame());
    }

    private void startGame() {
        // Validate name
        String username = usernameInput.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Enter a Username", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedDifficultyId = rgDifficulty.getCheckedRadioButtonId();
        RadioButton selectedDifficulty = findViewById(selectedDifficultyId);
        int difficulty = getDifficultyValue(selectedDifficulty.getText().toString());

        // Get selected sprite
        int selectedSpriteId = rgSprite.getCheckedRadioButtonId();
        RadioButton selectedSprite = findViewById(selectedSpriteId);
        String sprite = selectedSprite.getText().toString();

        // Start the game activity with the selected options
        Intent gameIntent = new Intent(this, DungeonActivity.class);
        gameIntent.putExtra("username", username);
        gameIntent.putExtra("difficulty", difficulty);
        gameIntent.putExtra("sprite", sprite);
        startActivity(gameIntent);
        finish();
    }

    private int getDifficultyValue(String difficultyText) {
        int difficulty;
        switch (difficultyText) {
        case "Beginner":
            difficulty = 0;
            break;
        case "Intermediate":
            difficulty = 1;
            break;
        case "Expert":
            difficulty = 2;
            break;
        default:
            difficulty = 0;
        }
        return difficulty;
    }
}

