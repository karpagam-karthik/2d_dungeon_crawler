package com.example.cs2340c_team8.viewmodels;

import androidx.databinding.BaseObservable;

import com.example.cs2340c_team8.models.GameConfig;

public class DungeonViewModel extends BaseObservable {
    public boolean isFirePowerUpActive() {
        return true; //TODO Check if Player has collected the Power-Up
    }

    public boolean isIcePowerUpActive() {
        return false; //TODO Check if Player has collected the Power-Up
    }

    public boolean isStarPowerUpActive() {
        return false; //TODO Check if Player has collected the Power-Up
    }

    public int getSpriteImage() {
        return GameConfig.fetchCharacterMiniSprite();
    }

    public String getDifficultyText() {
        switch (GameConfig.difficulty) {
        case INTERMEDIATE:
            return "Intermediate";
        case EXPERT:
            return "Expert";
        default:
            return "Beginner";
        }
    }

    public String getHealth() {
        switch (GameConfig.difficulty) {
        case INTERMEDIATE:
            return String.format("%d", 150);
        case EXPERT:
            return String.format("%d", 100);
        default:
            return String.format("%d", 200);
        }
    }

    public String getUsername() {
        return GameConfig.username;
    }

    public String getSpriteText() {
        switch (GameConfig.character) {
        case LUIGI:
            return "Luigi";
        case PRINCESS_PEACH:
            return "Princess Peach";
        default:
            return "Mario";
        }
    }
}
