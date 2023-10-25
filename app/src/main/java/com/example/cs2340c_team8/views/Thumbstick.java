package com.example.cs2340c_team8.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.enums.Character;

public class Thumbstick extends View {
    private final int X_CENTER = 300;   // TODO: Remove Hard-coded value
    private final int Y_CENTER = 300;   // TODO: Remove Hard-coded value
    private final int OUTER_RADIUS = 90;
    private final int INNER_RADIUS = 80;
    private final Player player = Player.getInstance();
    private Character character;
    private Paint outerCirclePaint;
    private Paint innerCirclePaint;
    private Paint highlightCirclePaint;
    private int innerCircleCenterX = X_CENTER;
    private int innerCircleCenterY = Y_CENTER;
    private boolean isPressed;
    private double actuatorX;
    private double actuatorY;

    public Thumbstick(Context context, Character character) {
        super(context);
        this.character = character;

        outerCirclePaint = new Paint();
        outerCirclePaint.setColor(Color.argb(210, 255, 255, 255));
        outerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        innerCirclePaint = createCharacterColorPaint();

        highlightCirclePaint = new Paint();
        highlightCirclePaint.setColor(Color.argb(255, 255, 255, 255));
        highlightCirclePaint.setStyle(Paint.Style.STROKE);
        highlightCirclePaint.setStrokeWidth(4);
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
    public void draw(Canvas canvas) {
        super.draw(canvas);
        // Draw Thumb-stick base
        canvas.drawCircle(X_CENTER, Y_CENTER, OUTER_RADIUS + 35, outerCirclePaint);

        // Draw Thumb-stick
        canvas.drawCircle(innerCircleCenterX, innerCircleCenterY, OUTER_RADIUS, outerCirclePaint);
        canvas.drawCircle(innerCircleCenterX, innerCircleCenterY, INNER_RADIUS, innerCirclePaint);
        canvas.drawCircle(innerCircleCenterX, innerCircleCenterY, INNER_RADIUS - 8, highlightCirclePaint);
    }

    public boolean isPressed(double touchX, double touchY) {
        return distanceBetweenCenterAndTouch(touchX, touchY) < OUTER_RADIUS;
    }

    public void setIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public boolean getIsPressed() {
        return isPressed;
    }

    public void setActuator(double touchX, double touchY) {
        double deltaX = touchX - X_CENTER;
        double deltaY = touchY - Y_CENTER;
        double deltaDistance = distanceBetweenCenterAndTouch(touchX, touchY);
        if (deltaDistance < OUTER_RADIUS) {
            actuatorX = deltaX / OUTER_RADIUS;
            actuatorY = deltaY / OUTER_RADIUS;
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
        return Math.sqrt((Math.pow(touchX - X_CENTER, 2) + Math.pow(touchY - Y_CENTER, 2)));
    }

    public void update() {
        innerCircleCenterX = (int) (X_CENTER + actuatorX * OUTER_RADIUS);
        innerCircleCenterY = (int) (Y_CENTER + actuatorY * OUTER_RADIUS);

        double velocityX = actuatorX * 10;
        double velocityY = actuatorY * 10;

        player.setPosX((int) (player.getX() + velocityX));
        player.setPosY((int) (player.getY() + velocityY));
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
        }

        return true;
    }
}
