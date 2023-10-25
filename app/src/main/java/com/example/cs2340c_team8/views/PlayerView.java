package com.example.cs2340c_team8.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.cs2340c_team8.models.enums.Character;
import com.example.cs2340c_team8.models.interfaces.PlayerObserver;

public class PlayerView extends View implements PlayerObserver {
    private int posX;
    private int posY;
    private Character character;
    private Paint playerPaint;
    public PlayerView(Context context, Character character) {
        super(context);

        this.posX = 25;
        this.posY = 25;
        this.character = character;

        playerPaint = createCharacterColorPaint();
    }

    private Paint createCharacterColorPaint() {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        switch (character) {
            case MARIO:
                paint.setColor(Color.rgb(229, 37, 33));
                break;
            case LUIGI:
                paint.setColor(Color.rgb(67, 176, 71));
                break;
            default:
                paint.setColor(Color.rgb(255, 0, 170));
        }

        return paint;
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
