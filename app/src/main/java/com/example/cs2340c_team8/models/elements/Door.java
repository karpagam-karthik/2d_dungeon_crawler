package com.example.cs2340c_team8.models.elements;

import com.example.cs2340c_team8.models.interfaces.Element;

public class Door implements Element {
    @Override
    public int getEffectMagnitude() {
        return -1;
    }
    @Override
    public String getEffect() {
        return "Door";
    }
}
