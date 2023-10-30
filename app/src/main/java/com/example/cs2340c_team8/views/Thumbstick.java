package com.example.cs2340c_team8.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.GameConfig;

public class Thumbstick extends View {
    private static final int pixelsPerFrame = 5;
    private int centerX;
    private int centerY;
    private final int outerRadius = 90;
    private final int innerRadius = 80;
    private final Player player = Player.getInstance();
    private Paint outerCirclePaint;
    private Paint innerCirclePaint;
    private Paint highlightPaint;
    private int innerCircleCenterX;
    private int innerCircleCenterY;
    private boolean isPressed;
    private double actuatorX;
    private double actuatorY;

    public Thumbstick(Context context, int centerX, int centerY) {
        super(context);

        this.centerX = centerX;
        this.centerY = centerY;
        innerCircleCenterX = centerX;
        innerCircleCenterY = centerY;

        outerCirclePaint = new Paint();
        outerCirclePaint.setColor(Color.argb(210, 255, 255, 255));
        outerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        innerCirclePaint = GameConfig.createCustomPaint();

        highlightPaint = new Paint();
        highlightPaint.setColor(Color.argb(255, 255, 255, 255));
        highlightPaint.setStyle(Paint.Style.STROKE);
        highlightPaint.setStrokeWidth(4);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        // Draw Thumb-stick base
        canvas.drawCircle(centerX, centerY, outerRadius + 35, outerCirclePaint);

        // Draw Thumb-stick
        canvas.drawCircle(innerCircleCenterX, innerCircleCenterY, outerRadius, outerCirclePaint);
        canvas.drawCircle(innerCircleCenterX, innerCircleCenterY, innerRadius, innerCirclePaint);
        canvas.drawCircle(innerCircleCenterX, innerCircleCenterY, innerRadius - 8, highlightPaint);
    }

    public boolean isPressed(double touchX, double touchY) {
        return distanceBetweenCenterAndTouch(touchX, touchY) < outerRadius;
    }

    public void setIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public boolean getIsPressed() {
        return isPressed;
    }

    public void setActuator(double touchX, double touchY) {
        double deltaX = touchX - centerX;
        double deltaY = touchY - centerY;
        double deltaDistance = distanceBetweenCenterAndTouch(touchX, touchY);
        if (deltaDistance < outerRadius) {
            actuatorX = deltaX / outerRadius;
            actuatorY = deltaY / outerRadius;
        } else {
            actuatorX = deltaX / deltaDistance;
            actuatorY = deltaY / deltaDistance;
        }
        update();
    }

    public void resetActuator() {
        actuatorX = 0;
        actuatorY = 0;
        update();
    }

    private double distanceBetweenCenterAndTouch(double touchX, double touchY) {
        return Math.sqrt((Math.pow(touchX - centerX, 2) + Math.pow(touchY - centerY, 2)));
    }

    public void update() {
        innerCircleCenterX = (int) (centerX + actuatorX * outerRadius);
        innerCircleCenterY = (int) (centerY + actuatorY * outerRadius);

        double velocityX = actuatorX * pixelsPerFrame;
        double velocityY = actuatorY * pixelsPerFrame;

        player.setStartX((int) (player.getStartX() + velocityX));
        player.setStartY((int) (player.getStartY() + velocityY));
        player.updateObservers();

        this.invalidate();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (isPressed(event.getX(), event.getY())) {
                    setIsPressed(true);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if (getIsPressed()) {
                    setActuator(event.getX(), event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
                setIsPressed(false);
                resetActuator();
                return true;
            default:
                return true;
        }
    }
}
