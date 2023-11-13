package com.example.cs2340c_team8.models.enemies;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.widget.ImageView;

import com.example.cs2340c_team8.models.GameConfig;
import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.interfaces.Enemy;

public class KoopaTroopa implements Enemy {
    private Player player = Player.getInstance();
    private int playerStartX;
    private int playerStartY;
    private int playerEndX;
    private int playerEndY;

    // Enemy Attributes
    private final int spriteSizeX = 16;
    private final int spriteSizeY = 16;
    private final double pixelsPerFrame = GameConfig.playerPixelsPerFrame * 1.1;
    private ImageView sprite;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private final int damage;

    // New
    private Bitmap koopaTroopa;
    private boolean movingDown;
    private int movementSpeed;
    private int endingX;
    private int startingX;

    public KoopaTroopa(Bitmap koopaTroopa, int startX, int startY, boolean movingDown, int movementSpeed) {
        this.startX = startX;
        this.endX = startX + spriteSizeX;

        this.startY = startY;
        this.endY = startY + spriteSizeY;

        //
        this.koopaTroopa = koopaTroopa;
        this.movingDown = movingDown;
        this.movementSpeed = movementSpeed;
        this.endingX = startX + 100;
        this.startingX = startX;

        int startingHealth = GameConfig.getStartingHealth();
        switch (GameConfig.difficulty) {
            case INTERMEDIATE:
                damage = (int) (0.2 * startingHealth);
                break;
            case EXPERT:
                damage = (int) (0.3 * startingHealth);
                break;
            default:
                damage = (int) (0.1 * startingHealth);
        }
        // TODO: Uncomment after shifting away from BulletBillView class
//        Player.addObserver(this);
    }

    // TODO: Implement
    @Override
    public void moveEnemy() {
        if (movingDown) {
            setStartX(startX + movementSpeed);
            if (startX >= endingX) {
                movingDown = false;
            }
        } else {
            setStartX(startX - movementSpeed);
            if (startX <= startingX) {
                movingDown = true;
            }
        }

        if (isCollidingWithPlayer()) {
            attackPlayer();
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

        switch (GameConfig.difficulty) {
            case INTERMEDIATE:
                player.setPixelsPerFrame(player.getPixelsPerFrame() * 0.8);
                break;
            case EXPERT:
                player.setPixelsPerFrame(player.getPixelsPerFrame() * 0.6);
                break;
            default:
                player.setPixelsPerFrame(player.getPixelsPerFrame() * 0.9);
        }
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
