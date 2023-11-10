package com.example.cs2340c_team8.models;

import com.example.cs2340c_team8.models.interfaces.Element;

public class Enemy implements Element {
    @Override
    public int getEffectMagnitude() {
        return 10;
    }
    @Override
    public String getEffect() {
        return "Damage";
    }
}
