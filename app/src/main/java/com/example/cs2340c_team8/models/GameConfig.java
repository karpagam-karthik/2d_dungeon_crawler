package com.example.cs2340c_team8.models;

import android.graphics.Color;
import android.graphics.Paint;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.models.enums.Character;
import com.example.cs2340c_team8.models.enums.Difficulty;
import com.example.cs2340c_team8.models.enums.MarioColor;

public class GameConfig {
    public static final Player player = Player.getInstance();
    public static String username;
    public static Difficulty difficulty;
    public static Character character;

    public static int fetchCharacterMiniSprite() {
        switch (character) {
        case MARIO:
            return R.drawable.mario_hat;
        case LUIGI:
            return R.drawable.luigi_hat;
        default:
            return R.drawable.princess_peach_crown;
        }
    }

    public static Paint createCustomPaint() {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        switch (character) {
        case MARIO:
            paint.setColor(getMarioColor(MarioColor.RED));
            break;
        case LUIGI:
            paint.setColor(getMarioColor(MarioColor.GREEN));
            break;
        default:
            paint.setColor(getMarioColor(MarioColor.PINK));
        }

        return paint;
    }

    public static int getMarioColor(MarioColor marioColor) {
        switch (marioColor) {
        case RED:
            return Color.rgb(229, 37, 33);
        case GREEN:
            return Color.rgb(67, 176, 71);
        case BLUE:
            return Color.rgb(4, 156, 216);
        case YELLOW:
            return Color.rgb(251, 208, 0);
        case PINK:
            return Color.rgb(255, 0, 170);
        default:
            return Color.rgb(255, 255, 255);
        }
    }

    private static void setStartingHealth() {
        switch (difficulty) {
        case BEGINNER:
            player.setHealth(200);
        case INTERMEDIATE:
            player.setHealth(150);
        case EXPERT:
            player.setHealth(100);
        default:
            player.setHealth(200);
        }
    }

    public static void setUsername(String newUsername) {
        username = newUsername;
    }

    public static void setDifficulty(Difficulty newDifficulty) {
        difficulty = newDifficulty;
        setStartingHealth();
    }

    public static void setCharacter(Character newCharacter) {
        character = newCharacter;
    }
}
