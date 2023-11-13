package com.example.cs2340c_team8.models.movements;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.interfaces.PlayerMovement;

public class UpPlayerMovement implements PlayerMovement {
    private Player player;
    private Bitmap bitmap;
    private int tileColor = Color.WHITE;
    private int playerColor = Color.BLUE;
    public UpPlayerMovement(Bitmap bitmap) {
        player = Player.getInstance();
        this.bitmap = bitmap;
    }

    @Override
    public Bitmap movePlayer() {
        for (int a = player.getStartX(); a < player.getStartX() + 16; a++) {
            bitmap.setPixel(a, player.getEndY() - 1, tileColor);
        }
        player.setStartY(player.getStartY() - 1);

        for (int r = player.getStartX(); r < player.getEndX(); r++) {
            for (int c = player.getStartY(); c < player.getEndY(); c++) {
                bitmap.setPixel(r, c, playerColor);
            }
        }

        return bitmap;
    }

    @Override
    public boolean canPlayerMove() {
        int yth = player.getStartY() - 1;
        for (int x = player.getStartX(); x < player.getStartX() + 16; x++) {
            if (bitmap.getPixel(x, yth) != Color.WHITE) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int checkLevelCompleted(int currentMap) {
        if (player.getStartX() >= 0 && player.getStartX() <= 20 && player.getStartY() >= 0
                && player.getStartY() <= 20 && currentMap == 1) {
            player.setStartY(25);
            player.setStartX(25);
            return 2;
        } else if (player.getStartX() >= 0 && player.getStartX() <= 20 && player.getStartY() >= 0
                && player.getStartY() <= 20 && currentMap == 2) {
            player.setStartY(25);
            player.setStartX(25);
            return 3;
        } else if (player.getStartX() >= 0 && player.getStartX() <= 20 && player.getStartY() >= 0
                && player.getStartY() <= 20 && currentMap == 3) {
            return -1;
        }

        return currentMap;
    }
}
