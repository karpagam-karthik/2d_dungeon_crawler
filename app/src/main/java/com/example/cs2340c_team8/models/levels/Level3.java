package com.example.cs2340c_team8.models.levels;

import android.graphics.Bitmap;
import android.view.View;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.models.interfaces.Enemy;

import java.util.ArrayList;

public class Level3 extends Level {
    private ArrayList<Enemy> enemies;
    private ArrayList<Bitmap> enemyBitmaps;
    public Level3(View view, int spawnX, int spawnY) {
        super(view, R.drawable.map3, spawnX, spawnY);
    }

    @Override
    void onLevelCompleted() {

    }

    public void createEnemyEntities() {
    }

    @Override
    ArrayList<Enemy> getEnemies() {
        return null;
    }

    @Override
    ArrayList<Bitmap> getEnemyBitmaps() {
        return null;
    }
}