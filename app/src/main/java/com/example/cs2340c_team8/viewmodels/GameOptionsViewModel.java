package com.example.cs2340c_team8.viewmodels;

import androidx.databinding.BaseObservable;

public class GameOptionsViewModel extends BaseObservable {

    private int getDifficultyValue(String difficultyText) {
        int difficulty;
        switch (difficultyText) {
        case "Intermediate":
            difficulty = 1;
            break;
        case "Expert":
            difficulty = 2;
            break;
        default:
            difficulty = 0;
        }
        return difficulty;
    }
}
