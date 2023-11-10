package com.example.cs2340c_team8.viewModels;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.databinding.BaseObservable;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.models.GameConfig;
import com.example.cs2340c_team8.models.enums.Character;
import com.example.cs2340c_team8.models.enums.Difficulty;
import com.example.cs2340c_team8.views.activities.DungeonActivity;

public class GameSettingsViewModel extends BaseObservable {
    private final Activity activity;

    public GameSettingsViewModel(Activity activity) {
        this.activity = activity;
    }

    public void startGame(View view) {
        EditText usernameInput = activity.findViewById(R.id.usernameInput);
        RadioGroup difficultyRG = activity.findViewById(R.id.radioGroupDifficulty);
        RadioGroup spriteRG = activity.findViewById(R.id.radioGroupSprite);

        String username = usernameInput.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(activity, "Enter a Username", Toast.LENGTH_SHORT).show();
            return;
        }
        GameConfig.setUsername(username);

        RadioButton selectedDifficulty = activity.findViewById(difficultyRG.getCheckedRadioButtonId());
        GameConfig.setDifficulty(deriveChosenDifficulty(selectedDifficulty.getText().toString()));

        RadioButton selectedSprite = activity.findViewById(spriteRG.getCheckedRadioButtonId());
        GameConfig.setCharacter(deriveChosenCharacter(selectedSprite.getText().toString()));

        Intent gameIntent = new Intent(activity, DungeonActivity.class);
        activity.startActivity(gameIntent);
        activity.finish();
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
