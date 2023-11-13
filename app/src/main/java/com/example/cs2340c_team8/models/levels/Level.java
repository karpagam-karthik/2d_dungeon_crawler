package com.example.cs2340c_team8.models.levels;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.view.View;

import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.enemies.BulletBill;
import com.example.cs2340c_team8.models.enemies.Goomba;
import com.example.cs2340c_team8.models.enemies.KoopaTroopa;
import com.example.cs2340c_team8.models.enemies.PiranhaPlant;
import com.example.cs2340c_team8.models.interfaces.Enemy;

import java.util.ArrayList;

public abstract class Level {
    protected final static Player player = Player.getInstance();
    protected int width;
    protected int height;
    protected float scale;
    protected View view;
    protected static Bitmap map;
    private static final int level1TileColor = Color.rgb(87, 140, 170);
    private static final int level2TileColor = Color.rgb(107, 68, 150);
    private static final int level3TileColor = Color.rgb(255, 119, 0);

    protected Level(View view, int mapID, int spawnX, int spawnY) {
        this.view = view;
        this.map = BitmapFactory.decodeResource(view.getResources(), mapID);

        player.setStartX(spawnX);
        player.setStartY(spawnY);

        this.width = view.getWidth();
        this.height = view.getHeight();
    }

    abstract void onLevelCompleted();
    abstract void createEnemyEntities();
    abstract ArrayList<Enemy> getEnemies();
    abstract ArrayList<Bitmap> getEnemyBitmaps();

    protected Matrix scaleMap() {
        scale = Math.min((float) width / map.getWidth(), (float) height / map.getHeight());
        Matrix mapMatrix = new Matrix();
        mapMatrix.setScale(scale, scale);
        return mapMatrix;
    }

    protected Matrix scaleGoomba(Goomba test) {
        Matrix enemyMatrix = new Matrix();
        enemyMatrix.setScale(scale, scale);
        enemyMatrix.postTranslate(test.getStartX() * scale, test.getStartY() * scale);
        return enemyMatrix;
    }

    protected Matrix scalePlayer(Player test) {
        Matrix enemyMatrix = new Matrix();
        enemyMatrix.setScale(scale, scale);
        enemyMatrix.postTranslate(test.getStartX() * scale, test.getStartY() * scale);
        return enemyMatrix;
    }

    protected Matrix scaleKoopa(KoopaTroopa test) {
        Matrix enemyMatrix = new Matrix();
        enemyMatrix.setScale(scale, scale);
        enemyMatrix.postTranslate(test.getStartX() * scale, test.getStartY() * scale);
        return enemyMatrix;
    }

    protected Matrix scaleBullet(BulletBill test) {
        Matrix enemyMatrix = new Matrix();
        enemyMatrix.setScale(scale, scale);
        enemyMatrix.postTranslate(test.getStartX() * scale, test.getStartY() * scale);
        return enemyMatrix;
    }

    protected Matrix scaleShell(PiranhaPlant test) {
        Matrix enemyMatrix = new Matrix();
        enemyMatrix.setScale(scale, scale);
        enemyMatrix.postTranslate(test.getStartX() * scale, test.getStartY() * scale);
        return enemyMatrix;
    }

    public static boolean isValidMoveX(double actuatorX) {
        if (actuatorX > 0) {
            int xth = player.getStartX() + 16;
            for (int y = player.getStartY(); y < player.getStartY() + 16; y++) {
                if (map.getPixel(xth, y) != level1TileColor && map.getPixel(xth, y)
                        != level2TileColor && map.getPixel(xth, y) != level3TileColor) {
                    return false;
                }
            }
            return true;
        } else if (actuatorX < 0) {
            int xth = player.getStartX() - 1;
            for (int y = player.getStartY(); y < player.getStartY() + 16; y++) {
                if (map.getPixel(xth, y) != level1TileColor && map.getPixel(xth, y)
                        != level2TileColor && map.getPixel(xth, y) != level3TileColor) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isValidMoveY(double actuatorY) {
        if (actuatorY > 0) {
            int yth = player.getStartY() + 16;
            for (int x = player.getStartX(); x < player.getStartX() + 16; x++) {
                if (map.getPixel(x, yth) != level1TileColor && map.getPixel(x, yth)
                        != level2TileColor && map.getPixel(x, yth) != level3TileColor) {
                    return false;
                }
            }
            return true;
        } else if (actuatorY < 0) {
            int yth = player.getStartY() - 1;
            for (int x = player.getStartX(); x < player.getStartX() + 16; x++) {
                if (map.getPixel(x, yth) != level1TileColor && map.getPixel(x, yth)
                        != level2TileColor && map.getPixel(x, yth) != level3TileColor) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
