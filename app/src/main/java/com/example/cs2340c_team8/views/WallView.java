package com.example.cs2340c_team8.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.cs2340c_team8.models.Wall;

public class WallView extends View {
    private Paint paint;
    private Wall wall;

    public WallView(Context context, Wall wall) {
        super(context);

        this.wall = wall;
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.argb(255, 192, 194, 201));
        canvas.drawRect(wall.getX(), wall.getY(), wall.getX() + wall.getWidth(),
                wall.getY() + wall.getHeight(), paint);
    }
}
