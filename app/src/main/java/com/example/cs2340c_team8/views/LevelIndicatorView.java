package com.example.cs2340c_team8.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import com.example.cs2340c_team8.R;
import com.example.cs2340c_team8.models.enums.Character;

public class LevelIndicatorView extends View {
    private final int startX;
    private final int startY;
    private final Character character;
    private int level;
    private final Paint outerCirclePaint;
    private final Paint innerPaint;
    private final Paint shadowPaint;
    public LevelIndicatorView(Context context, int startX, int startY,
                              Character character, int level) {
        super(context);
        this.startX = startX;
        this.startY = startY;
        this.character = character;
        this.level = level;

        outerCirclePaint = new Paint();
        outerCirclePaint.setColor(Color.rgb(255, 255, 255));
        outerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        innerPaint = createCharacterColorPaint();

        shadowPaint = new Paint();
        shadowPaint.setColor(Color.rgb(220, 220, 220));
        shadowPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    private Bitmap createCharacterLevelIndicatorSpriteBitmap() {
        BitmapDrawable bitmapDrawable;
        switch (character) {
        case MARIO:
            bitmapDrawable = (BitmapDrawable)
                    getResources().getDrawable(R.drawable.mario_hat);
            return bitmapDrawable.getBitmap();
        case LUIGI:
            bitmapDrawable = (BitmapDrawable)
                    getResources().getDrawable(R.drawable.luigi_hat);
            return bitmapDrawable.getBitmap();
        default:
            bitmapDrawable = (BitmapDrawable)
                    getResources().getDrawable(R.drawable.princess_peach_crown);
            return bitmapDrawable.getBitmap();
        }
    }

    private int getSpriteOffsetX() {
        switch (character) {
        case MARIO:
        case LUIGI:
            return 53;
        default:
            return 76;
        }
    }

    private int getSpriteOffsetY() {
        switch (character) {
        case MARIO:
        case LUIGI:
            return 55;
        default:
            return 84;
        }
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

        canvas.drawBitmap(createCharacterLevelIndicatorSpriteBitmap(),
                startX + (100 * (level - 1)) - getSpriteOffsetX(),
                startY - getSpriteOffsetY(), outerCirclePaint);
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
}
