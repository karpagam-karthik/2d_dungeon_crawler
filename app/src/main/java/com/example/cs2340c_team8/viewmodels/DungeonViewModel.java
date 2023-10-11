package com.example.cs2340c_team8.viewmodels;

import androidx.databinding.BaseObservable;

import com.example.cs2340c_team8.DungeonActivity;
import com.example.cs2340c_team8.models.enums.Difficulty;

public class DungeonViewModel extends BaseObservable {
    private String username;
    private Difficulty difficulty;
    private String sprite;

    public DungeonViewModel(String username, Difficulty difficulty, String sprite) {
        this.username = username;
        this.difficulty = difficulty;
        this.sprite = sprite;
    }

    public String getUsername() {
        return username;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public String getSprite() {
        return sprite;
    }
}
