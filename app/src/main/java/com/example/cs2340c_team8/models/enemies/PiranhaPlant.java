package com.example.cs2340c_team8.models.enemies;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.widget.ImageView;

import com.example.cs2340c_team8.models.GameConfig;
import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.interfaces.Enemy;

public class PiranhaPlant implements Enemy {
    private Player player = Player.getInstance();
    private int playerStartX;
    private int playerStartY;
    private int playerEndX;
    private int playerEndY;

    // Enemy Attributes
    private final int spriteSizeX = 25;
    private final int spriteSizeY = 25;
    private final double pixelsPerFrame = GameConfig.playerPixelsPerFrame;
    private ImageView sprite;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private final int damage;

    // New
    private int startingY;
    private int endingY;
    private int movementSpeed;

    public PiranhaPlant(int startX, int startY, int endingY, int movementSpeed) {
        this.startX = startX;
        this.endX = startX + spriteSizeX;

        this.startY = startY;
        this.endY = startY + spriteSizeY;

        // New
        this.startingY = startY;
        this.endingY = endingY;
        this.movementSpeed = movementSpeed;

        int startingHealth = GameConfig.getStartingHealth();
        switch (GameConfig.difficulty) {
            case INTERMEDIATE:
                damage = (int) (0.1 * startingHealth);
                break;
            case EXPERT:
                damage = (int) (0.2 * startingHealth);
                break;
            default:
                damage = (int) (0.05 * startingHealth);
        }

        // TODO: Uncomment after shifting away from BulletBillView class
//        Player.addObserver(this);
    }

    // TODO: Implement
    @Override
    public void moveEnemy() {
        startY += movementSpeed;
        if (startY >= endingY) {
            startY = startingY;
        }
    }

    @Override
    public boolean isCollidingWithPlayer() {
        int overlapWidth = Math.min(endX, playerEndX) - Math.max(startX, playerStartX);
        int overlapHeight = Math.min(endY, playerEndY) - Math.max(startY, playerStartY);

        return (overlapWidth > 0 && overlapHeight > 0);
    }

    @Override
    public void updatePlayerPosition(int posX, int posY, int endX, int endY) {
        this.playerStartX = posX;
        this.playerStartY = posY;
        this.playerEndX = endX;
        this.playerEndY = endY;

        if (isCollidingWithPlayer()) {
            attackPlayer();
        }
    }

    @Override
    public void attackPlayer() {
        player.setHealth(player.getHealth() - damage);

        player.setStartY(playerStartY - 40);
    }

    public ImageView getSprite() {
        return sprite;
    }

    public double getPixelsPerFrame() {
        return pixelsPerFrame;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getSpriteSizeX() {
        return spriteSizeX;
    }

    public int getSpriteSizeY() {
        return spriteSizeY;
    }

    public void setSprite(ImageView sprite) {
        this.sprite = sprite;
    }

    public void setStartX(int startX) {
        this.startX = startX;
        this.endX = startX + spriteSizeX;
    }

    public void setStartY(int startY) {
        this.startY = startY;
        this.endY = startY + spriteSizeY;
    }
}
