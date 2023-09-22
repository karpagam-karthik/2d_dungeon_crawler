package com.example.demo_2340;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class startGameActivity extends AppCompatActivity {
    private EditText etName;
    private RadioGroup rgDifficulty, rgSprite;
    private Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        etName = findViewById(R.id.etName);
        rgDifficulty = findViewById(R.id.rgDifficulty);
        rgSprite = findViewById(R.id.rgSprite);
        btnContinue = findViewById(R.id.btnContinue);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });
    }

    private void startGame() {
        // Validate name
        String name = etName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get selected difficulty
        int selectedDifficultyId = rgDifficulty.getCheckedRadioButtonId();
        RadioButton selectedDifficulty = findViewById(selectedDifficultyId);
        double difficulty = getDifficultyValue(selectedDifficulty.getText().toString());

        // Get selected sprite
        int selectedSpriteId = rgSprite.getCheckedRadioButtonId();
        RadioButton selectedSprite = findViewById(selectedSpriteId);
        String sprite = selectedSprite.getText().toString();

        // Start the game activity with the selected options
        Intent gameIntent = new Intent(this, GameActivity.class);
        gameIntent.putExtra("name", name);
        gameIntent.putExtra("difficulty", difficulty);
        gameIntent.putExtra("sprite", sprite);
        startActivity(gameIntent);
    }

    private double getDifficultyValue(String difficultyText) {
        // Implement logic to map difficulty text to a numeric value
        double difficulty = 1.0; // Default value
        switch (difficultyText) {
            case "Easy":
                difficulty = 0.5;
                break;
            case "Medium":
                difficulty = 0.75;
                break;
            case "Hard":
                difficulty = 1.0;
                break;
        }
        return difficulty;
    }
}

