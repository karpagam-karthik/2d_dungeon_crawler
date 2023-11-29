// Package declaration
package com.example.cs2340c_team8.models.levels;

// Android imports
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.view.View;

// Project-specific imports
import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.enemies.BulletBill;
import com.example.cs2340c_team8.models.enemies.Goomba;
import com.example.cs2340c_team8.models.enemies.KoopaTroopa;
import com.example.cs2340c_team8.models.enemies.BlueShell;
import com.example.cs2340c_team8.models.interfaces.Enemy;

// Java util import
import java.util.ArrayList;

// Declaration of the 'Level' abstract class
public abstract class Level {

    // Constants for tile colors in different levels
    private static final int LEVEL_1_TILE_COLOR = Color.rgb(87, 140, 170);
    private static final int LEVEL_2_TILE_COLOR = Color.rgb(107, 68, 150);
    private static final int LEVEL_3_TILE_COLOR = Color.rgb(255, 119, 0);

    // Protected members of the class
    protected static final Player PLAYER = Player.getInstance();
    protected int width;
    protected int height;
    protected float scale;
    protected View view;
    protected static Bitmap map;

    // Constructor for the 'Level' class
    protected Level(View view, int mapID, int spawnX, int spawnY) {
        this.view = view;
        this.map = BitmapFactory.decodeResource(view.getResources(), mapID);

        // Set player's initial position
        PLAYER.setStartX(spawnX);
        PLAYER.setStartY(spawnY);

        this.width = view.getWidth();
        this.height = view.getHeight();
    }

    // Abstract methods to be implemented by subclasses
    abstract void onLevelCompleted();
    abstract void createEnemyEntities();
    abstract ArrayList<Enemy> getEnemies();
    abstract ArrayList<Bitmap> getEnemyBitmaps();

    // Method to scale the map based on the current view size
    protected Matrix scaleMap() {
        scale = Math.min((float) width / map.getWidth(), (float) height / map.getHeight());
        Matrix mapMatrix = new Matrix();
        mapMatrix.setScale(scale, scale);
        return mapMatrix;
    }

    // Methods to scale different enemy types based on the current view size
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

    protected Matrix scaleShell(BlueShell test) {
        Matrix enemyMatrix = new Matrix();
        enemyMatrix.setScale(scale, scale);
        enemyMatrix.postTranslate(test.getStartX() * scale, test.getStartY() * scale);
        return enemyMatrix;
    }

    // Method to check if a horizontal move is valid for the player
    public static boolean isValidMoveX(double actuatorX) {
        if (actuatorX > 0) {
            int xth = PLAYER.getStartX() + 16;
            for (int y = PLAYER.getStartY(); y < PLAYER.getStartY() + 16; y++) {
                if (map.getPixel(xth, y) != LEVEL_1_TILE_COLOR && map.getPixel(xth, y)
                        != LEVEL_2_TILE_COLOR && map.getPixel(xth, y) != LEVEL_3_TILE_COLOR) {
                    return false;
                }
            }
            return true;
        } else if (actuatorX < 0) {
            int xth = PLAYER.getStartX() - 1;
            for (int y = PLAYER.getStartY(); y < PLAYER.getStartY() + 16; y++) {
                if (map.getPixel(xth, y) != LEVEL_1_TILE_COLOR && map.getPixel(xth, y)
                        != LEVEL_2_TILE_COLOR && map.getPixel(xth, y) != LEVEL_3_TILE_COLOR) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    // Method to check if a vertical move is valid for the player
    public static boolean isValidMoveY(double actuatorY) {
        if (actuatorY > 0) {
            int yth = PLAYER.getStartY() + 16;
            for (int x = PLAYER.getStartX(); x < PLAYER.getStartX() + 16; x++) {
                if (map.getPixel(x, yth) != LEVEL_1_TILE_COLOR && map.getPixel(x, yth)
                        != LEVEL_2_TILE_COLOR && map.getPixel(x, yth) != LEVEL_3_TILE_COLOR) {
                    return false;
                }
            }
            return true;
        } else if (actuatorY < 0) {
            int yth = PLAYER.getStartY() - 1;
            for (int x = PLAYER.getStartX(); x < PLAYER.getStartX() + 16; x++) {
                if (map.getPixel(x, yth) != LEVEL_1_TILE_COLOR && map.getPixel(x, yth)
                        != LEVEL_2_TILE_COLOR && map.getPixel(x, yth) != LEVEL_3_TILE_COLOR) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
