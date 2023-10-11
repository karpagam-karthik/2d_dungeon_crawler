package com.example.cs2340c_team8.models.interfaces;

import com.example.cs2340c_team8.models.enums.PowerUpType;

public interface PowerUp {
    PowerUpType getPowerUpType();
    int getEffect();
    long getDuration();
    // Add other methods if needed
}
