package com.example.cs2340c_team8.models.interfaces;

public interface Enemy extends PlayerObserver {
    void moveEnemy();
    boolean isCollidingWithPlayer();
    void attackPlayer();
}
