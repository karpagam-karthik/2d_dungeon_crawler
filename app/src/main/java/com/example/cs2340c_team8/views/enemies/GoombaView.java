package com.example.cs2340c_team8.views.enemies;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.enemies.Goomba;

// TODO: Remove after testing. Instead use the ImageView sprite property of Goomba
public class GoombaView extends View {
    private final Player player = Player.getInstance();
    private final Goomba goomba = new Goomba(200, 200);
    private int posX;
    private int posY;
    private Paint paint;

    public GoombaView(Context context) {
        super(context);

        player.addObserver(goomba);

        this.posX = goomba.getStartX();
        this.posY = goomba.getStartY();

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawRect(posX, posY, posX + goomba.getSpriteSizeX(),
                posY + goomba.getSpriteSizeY(), paint);
    }
}
