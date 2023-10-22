package com.example.cs2340c_team8.models.interfaces;

import com.example.cs2340c_team8.models.Player;

public interface Obstacle {
    //This interface will return
    // the type effect on the player
    // as well as teh magnitude
    int getEffectMagnitude();
    String getEffect();
}
