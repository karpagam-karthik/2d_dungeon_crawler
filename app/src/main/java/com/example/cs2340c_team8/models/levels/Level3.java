package com.example.cs2340c_team8.models.levels;

import com.example.cs2340c_team8.models.interfaces.Enemy;

import java.util.ArrayList;

public class Level3 extends Level {
    private ArrayList<Enemy> enemies;
    public Level3(int spawnX, int spawnY) {
        super(spawnX, spawnY);
    }

    @Override
    void onLevelCompleted() {

    }

    // TODO: Complete Implementation
    public ArrayList<Enemy> createEnemyEntities() {
        this.enemies = new ArrayList<>();
        return this.enemies;
    }
}