package com.example.cs2340c_team8.viewmodels;

import androidx.databinding.BaseObservable;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.models.enums.Difficulty;

public class DungeonViewModel extends BaseObservable {
    private String username;
    private Difficulty difficulty;
    private String spriteText;

    public DungeonViewModel(String username, Difficulty difficulty, String spriteText) {
        this.username = username;
        this.difficulty = difficulty;
        this.spriteText = spriteText;
    }

    public int getSpriteImage() {
        switch (spriteText) {
        case "Mario":
            return R.drawable.mario_hat;
        case "Luigi":
            return R.drawable.luigi_hat;
        default:
            return R.drawable.princess_peach_crown;
        }
    }

    public String getDifficultyText() {
        switch (difficulty) {
        case INTERMEDIATE:
            return "Intermediate";
        case EXPERT:
            return "Expert";
        default:
            return "Beginner";
        }
    }

    public String getHealth() {
        switch (difficulty) {
        case INTERMEDIATE:
            return String.format("Health: %d", 150);
        case EXPERT:
            return String.format("Health: %d", 100);
        default:
            return String.format("Health: %d", 200);
        }
    }

    public String getUsername() {
        return username;
    }

    public String getSpriteText() {
        return spriteText;
    }
}
