package com.example.cs2340c_team8.views.enemies;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.enemies.BulletBill;

// TODO: Remove after testing. Instead use the ImageView sprite property of BulletBill
public class BulletBillView extends View {
    private final Player player = Player.getInstance();
    private final BulletBill bulletBill = new BulletBill(200, 200);
    private int posX;
    private int posY;
    private Paint paint;

    public BulletBillView(Context context) {
        super(context);

        player.addObserver(bulletBill);

        this.posX = bulletBill.getStartX();
        this.posY = bulletBill.getStartY();

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawRect(posX, posY, posX + bulletBill.getSpriteSizeX(),
                posY + bulletBill.getSpriteSizeY(), paint);
    }
}
