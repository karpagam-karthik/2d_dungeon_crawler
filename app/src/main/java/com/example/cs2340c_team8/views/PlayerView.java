package com.example.cs2340c_team8.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.example.cs2340c_team8.models.GameConfig;
import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.interfaces.PlayerObserver;

public class PlayerView extends View implements PlayerObserver {
    private final Player player = Player.getInstance();
    private int posX;
    private int posY;
    private Paint playerPaint;
    public PlayerView(Context context) {
        super(context);

        this.posX = player.getX();
        this.posY = player.getY();

        playerPaint = GameConfig.createCustomPaint();
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawRect(posX, posY, posX + Player.getSpriteSizeX(),
                posY + Player.getSpriteSizeY(), playerPaint);
    }

    @Override
    public void updatePlayerPosition(int posX, int posY, int endX, int endY) {
        //TODO: Validate Move. Might need endX and endY, coordinates of bottom right corner
        this.posX = posX;
        this.posY = posY;

        this.invalidate();
    }
}
