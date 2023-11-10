package com.example.cs2340c_team8.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

import com.example.cs2340c_team8.R;

public class GameView extends View {

    private int currentMap;
    private int player_x; // Top left X coordinate of player
    private int player_y; // Top left Y coordinate of player
    private Bitmap player; // Player character
    private Bitmap map; // Map
    private Bitmap enemy1; // Enemy #1 (Bullet Bill)
    private int enemy1_x; // Top left X coordinate of enemy1
    private int enemy1_y; //Top left Y coordinate of enemy1
    private int enemy1_start_y, enemy1_end_y;
    private int enemy1_speed; // Movement speed for enemy1
    private boolean movingDown;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // Initialize variables here
        currentMap = 1;
        map = BitmapFactory.decodeResource(getResources(), R.drawable.map1);
        enemy1 = BitmapFactory.decodeResource(getResources(), R.drawable.bullet_bill);
        enemy1_x = 550;
        enemy1_y = 48;
        enemy1_start_y = enemy1_y;
        enemy1_end_y = 123;
        enemy1_speed = 1;
        movingDown = true;
    }

    // Method to create enemy
    public void createEnemy(Bitmap enemy, int x, int y) {

    }

    // Someone needs to make methods for enemy position that gets updated
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (currentMap == 1) {
            float scale = Math.min((float) getWidth() / map.getWidth(), (float) getHeight() / map.getHeight());
            Matrix mapMatrix = new Matrix();
            mapMatrix.setScale(scale, scale);

            canvas.drawBitmap(map, mapMatrix, null); // Loads map1

            Matrix enemyMatrix = new Matrix();
            enemyMatrix.setScale(scale, scale);
            enemyMatrix.postTranslate(enemy1_x * scale, enemy1_y * scale);

            canvas.drawBitmap(enemy1, enemyMatrix, null); // Loads enemy1

            if (movingDown) {
                enemy1_y = enemy1_y + enemy1_speed;
                if (enemy1_y >= enemy1_end_y) {
                    movingDown = false;
                }
            } else {
                enemy1_y = enemy1_y - enemy1_speed;
                if (enemy1_y <= enemy1_start_y) {
                    movingDown = true;
                }
            }
        }
        invalidate();
    }

    // This method sets View object to 800x800 pixels, do not change it!
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = resolveSize(800, widthMeasureSpec);
        int height = resolveSize(800, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

}

