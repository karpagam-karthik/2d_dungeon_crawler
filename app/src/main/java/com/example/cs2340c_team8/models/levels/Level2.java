package com.example.cs2340c_team8.models.levels;

import android.graphics.Bitmap;
import android.view.View;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.models.GameConfig;
import com.example.cs2340c_team8.models.enemies.BulletBill;
import com.example.cs2340c_team8.models.interfaces.Enemy;

import java.util.ArrayList;

public class Level2 extends Level {
    private ArrayList<Enemy> enemies;
    private ArrayList<Bitmap> enemyBitmaps;
    public Level2(View view, int spawnX, int spawnY) {
        super(view, R.drawable.map2, spawnX, spawnY);
        this.enemies = new ArrayList<>();
    }

    @Override
    void onLevelCompleted() {

    }

    public void createEnemyEntities() {
    }

    @Override
    ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    @Override
    ArrayList<Bitmap> getEnemyBitmaps() {
        return this.enemyBitmaps;
    }
}