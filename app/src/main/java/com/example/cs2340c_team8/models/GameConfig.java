package com.example.cs2340c_team8.models;

import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.models.enums.Character;
import com.example.cs2340c_team8.models.enums.Difficulty;
import com.example.cs2340c_team8.models.enums.MarioColor;

public class GameConfig {
    // Frequently accessed objects and settings
    public static final Player PLAYER = Player.getInstance();
    public static final int PLAYER_PIXELS_PER_FRAME = 5;
    private static MediaPlayer mainThemePlayer;
    private static MediaPlayer levelPlayer;
    private static String username;
    private static Difficulty difficulty;
    private static Character character;
    private static int level = 1;

    // UI Positioning Values
    private static int screenWidth;
    private static int screenHeight;
    private static int thumbstickX;
    private static int thumbstickY;
    private static int levelIndicatorX;
    private static int levelIndicatorY;
    private static final int LEVEL_INDICATOR_SPRITE_Z = 10;
    private static final int LEVEL_INDICATOR_SPRITE_OFFSET = 70;
    private static final int LEVEL_INDICATOR_LEVEL_OFFSET = 102;

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

    public static void setLevel(int level) {
        GameConfig.level = level;
    }

    public static int getThumbstickX() {
        return thumbstickX;
    }

    public static int getThumbstickY() {
        return thumbstickY;
    }

    public static String getUsername() {
        return username;
    }

    public static Difficulty getDifficulty() {
        return difficulty;
    }

    public static Character getCharacter() {
        return character;
    }

    public static int getLevelIndicatorX() {
        return levelIndicatorX;
    }

    public static int getLevelIndicatorY() {
        return levelIndicatorY;
    }

    public static int getLevelIndicatorSpriteOffset() {
        return LEVEL_INDICATOR_SPRITE_OFFSET;
    }

    public static int getLevelIndicatorSpriteZ() {
        return LEVEL_INDICATOR_SPRITE_Z;
    }

    public static int getLevelIndicatorLevelOffset() {
        return LEVEL_INDICATOR_LEVEL_OFFSET;
    }

    public static MediaPlayer getMainThemePlayer() {
        return mainThemePlayer;
    }

    public static void setMainThemePlayer(MediaPlayer mainThemePlayer) {
        GameConfig.mainThemePlayer = mainThemePlayer;
    }

    public static MediaPlayer getLevelPlayer() {
        return levelPlayer;
    }

    public static void setLevelPlayer(MediaPlayer levelPlayer) {
        GameConfig.levelPlayer = levelPlayer;
    }
}
