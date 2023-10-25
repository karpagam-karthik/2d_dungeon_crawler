package com.example.cs2340c_team8.models;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.example.cs2340c_team8.models.interfaces.MovementStrategy;
public class RightMovement implements MovementStrategy {
    private Player player;
    private Bitmap bitmap;
    private int tileColor = Color.WHITE;
    private int playerColor = Color.BLUE;
    private final int playerSize = Player.getSpriteSize();
    public RightMovement(Bitmap bitmap) {
        player = Player.getInstance();
        this.bitmap = bitmap;
    }
    @Override
    public Bitmap movePlayer() {
        for (int a = player.getY(); a < player.getY() + playerSize; a++) {
            bitmap.setPixel(player.getX(), a, tileColor);
        }

        player.setPosX(player.getX() + 1);

        for (int r = player.getX(); r < player.getX() + playerSize; r++) {
            for (int c = player.getY(); c < player.getY() + playerSize; c++) {
                bitmap.setPixel(r, c, playerColor);
            }
        }

        return bitmap;
    }

    @Override
    public boolean canPlayerMove() {
        int xth = player.getX() + 16;
        for (int y = player.getY(); y < player.getY() + 16; y++) {
            if (bitmap.getPixel(xth, y) != Color.WHITE) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int checkLevelCompleted(int currentMap) {
        if (player.getX() >= 0 && player.getX() <= 20 && player.getY() >= 0
                && player.getY() <= 20 && currentMap == 1) {
            player.setPosY(25);
            player.setPosX(25);
            return 2;
        } else if (player.getX() >= 0 && player.getX() <= 20 && player.getY() >= 0
                && player.getY() <= 20 && currentMap == 2) {
            player.setPosY(25);
            player.setPosX(25);
            return 3;
        } else if (player.getX() >= 0 && player.getX() <= 20 && player.getY() >= 0
                && player.getY() <= 20 && currentMap == 3) {
            return -1;
        }

        return currentMap;
    }
}
