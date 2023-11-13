package com.example.cs2340c_team8.models;

import android.graphics.Color;
import android.graphics.Paint;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.models.enums.Character;
import com.example.cs2340c_team8.models.enums.Difficulty;
import com.example.cs2340c_team8.models.enums.MarioColor;

public class GameConfig {
    // Frequently accessed objects and settings
    public static final Player PLAYER = Player.getInstance();
    public static final int playerPixelsPerFrame = 5;
    public static String username;
    public static Difficulty difficulty;
    public static Character character;
    private static int level = 1;

    // UI Positioning Values
    public static int screenWidth;
    public static int screenHeight;
    public static int thumbstickX;
    public static int thumbstickY;
    public static int levelIndicatorX;
    public static int levelIndicatorY;
    public static final int levelIndicatorSpriteZ = 10;
    public static final int levelIndicatorSpriteOffset = 70;
    public static final int levelIndicatorLevelOffset = 102;

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

    public static int fetchCharacterSprite() {
        switch (character) {
            case MARIO:
                return R.drawable.mario_player;
            case LUIGI:
                return R.drawable.luigi_player;
            default:
                return R.drawable.princess_peach_player;
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
            case INTERMEDIATE:
                PLAYER.setHealth(150);
                break;
            case EXPERT:
                PLAYER.setHealth(100);
                break;
            default:
                PLAYER.setHealth(200);
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

    public static void setScreenWidth(int newScreenWidth) {
        screenWidth = newScreenWidth;
        levelIndicatorX = screenWidth - 248;
        thumbstickX = 500;
    }

    public static void setScreenHeight(int newScreenHeight) {
        screenHeight = newScreenHeight;
        levelIndicatorY = screenHeight - 8;
        thumbstickY = screenHeight - 250;
    }

    public static int getLevel() {
        return level;
    }

    public static int getStartingHealth() {
        switch (difficulty) {
            case INTERMEDIATE:
                return 150;
            case EXPERT:
                return 100;
            default:
                return 200;
        }
    }

    public static void incrementLevel() {
        level += 1;
    }

    public static void decrementLevel() {
        level -= 1;
    }
}
