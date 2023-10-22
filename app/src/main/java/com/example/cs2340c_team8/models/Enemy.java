package com.example.cs2340c_team8.models;

import com.example.cs2340c_team8.models.interfaces.Obstacle;

public class Enemy implements Obstacle {
    @Override
    public int getEffectMagnitude() {
        return 10;
    }
    @Override
    public String getEffect() {
        return "Damage";
    }
}
