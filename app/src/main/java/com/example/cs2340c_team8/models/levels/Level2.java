package com.example.cs2340c_team8.models.levels;

import com.example.cs2340c_team8.models.GameConfig;
import com.example.cs2340c_team8.models.enemies.BulletBill;
import com.example.cs2340c_team8.models.interfaces.Enemy;

import java.util.ArrayList;

public class Level2 extends Level {
    private ArrayList<Enemy> enemies;
    public Level2(int spawnX, int spawnY) {
        super(spawnX, spawnY);
        this.enemies = new ArrayList<>();
    }

    @Override
    void onLevelCompleted() {

    }

    // TODO: Complete Implementation
    // eg: enemies.add(new BulletBill(200, 200));
    public ArrayList<Enemy> createEnemyEntities() {
        return enemies;
    }
}