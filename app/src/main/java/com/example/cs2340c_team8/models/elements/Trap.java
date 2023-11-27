package com.example.cs2340c_team8.models.elements;

import com.example.cs2340c_team8.models.interfaces.Element;

public class Trap implements Element {
    private int magnitude = 0;
    public Trap(int magnitude) {
        this.magnitude = magnitude;
    }
    @Override
    public int getEffectMagnitude() {
        return 5;
    }
    @Override
    public String getEffect() {
        return "Knock-back";
    }
}
