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
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private final Paint playerPaint;
    public PlayerView(Context context) {
        super(context);

        this.startX = player.getStartX();
        this.startY = player.getStartY();
        this.endX = player.getEndX();
        this.endY = player.getEndY();

        playerPaint = GameConfig.createCustomPaint();
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawRect(startX, startY, endX, endY, playerPaint);
    }

    @Override
    public void updatePlayerPosition(int startX, int startY, int endX, int endY) {
        //TODO: Validate Move. Might need endX and endY, coordinates of bottom right corner
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;

        this.invalidate();
    }
}
