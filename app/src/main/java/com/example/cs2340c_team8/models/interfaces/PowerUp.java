package com.example.cs2340c_team8.models.interfaces;

public interface PowerUp {
    com.example.cs2340c_team8.models.enums.PowerUp getPowerUpType();
    int getEffect();
    long getDuration();
    // Add other methods if needed
}
