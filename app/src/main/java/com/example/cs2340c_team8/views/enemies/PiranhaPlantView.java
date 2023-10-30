package com.example.cs2340c_team8.views.enemies;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.enemies.PiranhaPlant;

// TODO: Remove after testing. Instead use the ImageView sprite property of PiranhaPlant
public class PiranhaPlantView extends View {
    private final Player player = Player.getInstance();
    private final PiranhaPlant piranhaPlant = new PiranhaPlant(200, 200);
    private int posX;
    private int posY;
    private Paint paint;

    public PiranhaPlantView(Context context) {
        super(context);

        player.addObserver(piranhaPlant);

        this.posX = piranhaPlant.getStartX();
        this.posY = piranhaPlant.getStartY();

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawRect(posX, posY, posX + piranhaPlant.getSpriteSizeX(),
                posY + piranhaPlant.getSpriteSizeY(), paint);
    }
}
