package com.example.cs2340c_team8.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.cs2340c_team8.models.GameConfig;

public class LevelIndicatorView extends View {
    private final int startX;
    private final int startY;
    private int level;
    private final Paint outerCirclePaint;
    private final Paint innerPaint;
    private final Paint shadowPaint;

    public LevelIndicatorView(Context context, int startX, int startY,
                              int level) {
        super(context);
        this.startX = startX;
        this.startY = startY;
        this.level = level;

        outerCirclePaint = new Paint();
        outerCirclePaint.setColor(Color.rgb(255, 255, 255));
        outerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        innerPaint = GameConfig.createCustomPaint();

        shadowPaint = new Paint();
        shadowPaint.setColor(Color.rgb(220, 220, 220));
        shadowPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int circleRadius = 34;
        int innerCircleRadius = circleRadius - 8;
        int shadowCircleRadius = innerCircleRadius + 3;

        int rectLength = 100;
        int rectWidth = 14;
        int shadowRectWidth = rectWidth + 6;

        canvas.drawCircle(startX, startY, circleRadius, outerCirclePaint);

        for (int i = 1; i < level; i++) {
            canvas.drawRect(startX + (rectLength * (i - 1)), startY - rectWidth,
                    startX + (rectLength * i), startY + rectWidth, outerCirclePaint);
            canvas.drawCircle(startX + (rectLength * i), startY,
                    circleRadius, outerCirclePaint);

            canvas.drawCircle(startX + (rectLength * (i - 1)), startY,
                    shadowCircleRadius, shadowPaint);
            canvas.drawRect(startX + (rectLength * (i - 1)), startY - (shadowRectWidth / 2),
                    startX + (rectLength * i), startY + (shadowRectWidth / 2), shadowPaint);
        }

        for (int i = 1; i < level; i++) {
            canvas.drawCircle(startX + (rectLength * (i - 1)), startY,
                    innerCircleRadius, innerPaint);
            canvas.drawRect(startX + (rectLength * (i - 1)), startY - (rectWidth / 2),
                    startX + (rectLength * i), startY + (rectWidth / 2), innerPaint);
        }

        for (int i = level; i < 4; i++) {
            canvas.drawCircle(startX + (rectLength * (i - 1)), startY,
                    circleRadius, outerCirclePaint);
            canvas.drawRect(startX + (rectLength * (i - 1)), startY - rectWidth,
                    startX + (rectLength * i), startY + rectWidth, outerCirclePaint);
        }
        canvas.drawCircle(startX + (rectLength * 3), startY, circleRadius, outerCirclePaint);
    }
}
