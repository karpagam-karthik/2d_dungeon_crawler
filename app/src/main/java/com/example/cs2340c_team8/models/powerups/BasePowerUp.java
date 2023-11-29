package com.example.cs2340c_team8.models.powerups;

import android.content.Context;
import android.widget.ImageView;

import com.example.cs2340c_team8.models.GameConfig;
import com.example.cs2340c_team8.models.interfaces.Enemy;
public class BasePowerUp implements Enemy {
    private Context context;
    private int playerStartX;
    private int playerStartY;
    private int playerEndX;
    private int playerEndY;

    // Enemy Attributes
    private final int spriteSizeX = 16;
    private final int spriteSizeY = 16;
    private final double pixelsPerFrame = GameConfig.PLAYER_PIXELS_PER_FRAME;
    private ImageView sprite;
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    public BasePowerUp(Context context, int startX, int startY) {
        this.startX = startX;
        this.endX = startX + spriteSizeX;

        this.startY = startY;
        this.endY = startY + spriteSizeY;
        this.context = context;
    }

    @Override
    public void moveEnemy() {
        if (isCollidingWithPlayer()) {
            attackPlayer();
        }
    }

    @Override
    public boolean isCollidingWithPlayer() {
        int overlapWidth = Math.min(endX, playerEndX) - Math.max(startX, playerStartX);
        int overlapHeight = Math.min(endY, playerEndY) - Math.max(startY, playerStartY);

        return (overlapWidth > 3 && overlapHeight > 3);
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

    @Override //for our purposes will actually inflict a buff,
    //within the base class it will simply disappear
    public void attackPlayer() {
        com.example.cs2340c_team8.views.GameView.removePowerUp(this.context);
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
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
