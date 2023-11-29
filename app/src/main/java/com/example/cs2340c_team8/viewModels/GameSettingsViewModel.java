// Package declaration for the 'viewModels' package within the 'com.example.cs2340c_team8' namespace
package com.example.cs2340c_team8.viewModels;

// Import statements for required Android classes and libraries
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

// Data Binding import
import androidx.databinding.BaseObservable;

// Project-specific imports
import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.models.GameConfig;
import com.example.cs2340c_team8.models.enums.Character;
import com.example.cs2340c_team8.models.enums.Difficulty;
import com.example.cs2340c_team8.views.activities.DungeonActivity;

// Declaration of the 'GameSettingsViewModel' class, extending 'BaseObservable'
public class GameSettingsViewModel extends BaseObservable {

    // Private member of the class
    private final Activity activity;

    // Constructor for the 'GameSettingsViewModel' class
    public GameSettingsViewModel(Activity activity) {
        this.activity = activity;
    }
  
    // Method to start the game, triggered by a button click
    public void startGame(View view) {
        // Get references to UI elements
        EditText usernameInput = activity.findViewById(R.id.usernameInput);
        RadioGroup difficultyRG = activity.findViewById(R.id.radioGroupDifficulty);
        RadioGroup spriteRG = activity.findViewById(R.id.radioGroupSprite);

        // Get and validate the entered username
        String username = usernameInput.getText().toString().trim();
        // Check if the username is empty and show a Toast if so
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(activity, "Enter a Username", Toast.LENGTH_SHORT).show();
            return;
        }
        // Set the username in the GameConfig
        GameConfig.setUsername(username);

        // Get and set the chosen difficulty from the RadioGroup
        RadioButton selectedDifficulty =
                activity.findViewById(difficultyRG.getCheckedRadioButtonId());
        // Set the difficulty in the 'GameConfig' class
        GameConfig.setDifficulty(deriveChosenDifficulty(selectedDifficulty.getText().toString()));

        // Get and set the chosen character from the RadioGroup
        RadioButton selectedSprite = activity.findViewById(spriteRG.getCheckedRadioButtonId());
        // Set the character in the 'GameConfig' class
        GameConfig.setCharacter(deriveChosenCharacter(selectedSprite.getText().toString()));

        // Create an intent to start the 'DungeonActivity'
        Intent gameIntent = new Intent(activity, DungeonActivity.class);
        // Start the 'DungeonActivity' and finish the current activity
        activity.startActivity(gameIntent);
        activity.finish();
    }
    
    // Private method to derive the chosen difficulty based on the selected text
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

    // Private method to derive the chosen character based on the selected text
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
