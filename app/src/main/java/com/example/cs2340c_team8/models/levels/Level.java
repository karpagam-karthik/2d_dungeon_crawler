package com.example.cs2340c_team8.models.levels;

import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.interfaces.Enemy;
import com.example.cs2340c_team8.models.interfaces.PowerUp;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Level {
    protected final Player player = Player.getInstance();
    protected int spawnX;
    protected int spawnY;
    protected Level(int spawnX, int spawnY) {
        this.spawnX = spawnX;
        this.spawnY = spawnY;
    }

    abstract void onLevelCompleted();

    abstract ArrayList<Enemy> createEnemyEntities();

    // TODO: Complete Implementation
    protected void doSomethingWithEnemies() {
    }
}
