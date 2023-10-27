package com.example.cs2340c_team8.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.example.cs2340c_team8.models.GameConfig;
import com.example.cs2340c_team8.models.interfaces.PlayerObserver;

public class PlayerView extends View implements PlayerObserver {
    private int posX;
    private int posY;
    private Paint playerPaint;
    public PlayerView(Context context) {
        super(context);

        this.posX = 25;
        this.posY = 25;

        playerPaint = GameConfig.createCustomPaint();
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawCircle(posX, posY, 60, playerPaint);
    }

    @Override
    public void update(int posX, int posY) {
        //TODO: Validate Move
        this.posX = posX;
        this.posY = posY;

        this.invalidate();
    }
}
