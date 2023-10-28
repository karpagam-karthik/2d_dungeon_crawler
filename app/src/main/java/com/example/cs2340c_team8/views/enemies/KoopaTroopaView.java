package com.example.cs2340c_team8.views.enemies;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.enemies.KoopaTroopa;

// TODO: Remove after testing. Instead use the ImageView sprite property of KoopaTroopa
public class KoopaTroopaView extends View {
    private final KoopaTroopa koopaTroopa = new KoopaTroopa(200, 200);
    private int posX;
    private int posY;
    private Paint paint;

    public KoopaTroopaView(Context context) {
        super(context);

        Player.addObserver(koopaTroopa);

        this.posX = koopaTroopa.getStartX();
        this.posY = koopaTroopa.getStartY();

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawRect(posX, posY, posX + koopaTroopa.getSpriteSizeX(),
                posY + koopaTroopa.getSpriteSizeY(), paint);
    }
}
