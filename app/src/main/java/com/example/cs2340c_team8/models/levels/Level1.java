package com.example.cs2340c_team8.models.levels;

import com.example.cs2340c_team8.models.interfaces.Enemy;

import java.util.ArrayList;

public class Level1 extends Level {
    private ArrayList<Enemy> enemies;

    public Level1(int spawnX, int spawnY) {
        super(spawnX, spawnY);
    }

    public void onLevelCompleted() {

    }

    // TODO: Complete Implementation
    public ArrayList<Enemy> createEnemyEntities() {
        this.enemies = new ArrayList<>();
        return this.enemies;
    }
}
