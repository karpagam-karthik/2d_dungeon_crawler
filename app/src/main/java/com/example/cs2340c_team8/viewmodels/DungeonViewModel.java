package com.example.cs2340c_team8.viewmodels;

import android.graphics.Bitmap;

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
        case "Wizard":
            return R.drawable.wizard_sprite;
        case "Elf":
            return R.drawable.elf_sprite;
        default:
            return R.drawable.knight_sprite;
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
