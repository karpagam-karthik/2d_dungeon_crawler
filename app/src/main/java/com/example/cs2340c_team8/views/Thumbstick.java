// Package declaration
package com.example.cs2340c_team8.views;

// Import statements for required Android and Java classes
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.GameConfig;

// Declaration of the 'Thumbstick' class, extending the 'View' class
public class Thumbstick extends View {
    // Instance of the Player class
    private final Player player = Player.getInstance();
    
    // Coordinates for the center of the thumbstick
    private int centerX;
    private int centerY;
    
    // Radii for the thumbstick's outer and inner circles
    private final int outerRadius = 90;
    private final int innerRadius = 80;
    
    // Paint objects for drawing the thumbstick's circles
    private Paint outerCirclePaint;
    private Paint innerCirclePaint;
    private Paint highlightPaint;
    
    // Coordinates for the center of the inner circle of the thumbstick
    private int innerCircleCenterX;
    private int innerCircleCenterY;
    
    // Flag to indicate whether the thumbstick is pressed
    private boolean isPressed;
    
    // Actuator values for X and Y axes
    private double actuatorX;
    private double actuatorY;

    // Constructor for the Thumbstick class
    public Thumbstick(Context context, int centerX, int centerY) {
        super(context);

        // Initialize thumbstick coordinates
        this.centerX = centerX;
        this.centerY = centerY;
        innerCircleCenterX = centerX;
        innerCircleCenterY = centerY;

        // Initialize Paint objects for drawing
        outerCirclePaint = new Paint();
        outerCirclePaint.setColor(Color.argb(210, 255, 255, 255));
        outerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        innerCirclePaint = GameConfig.createCustomPaint();

        highlightPaint = new Paint();
        highlightPaint.setColor(Color.argb(255, 255, 255, 255));
        highlightPaint.setStyle(Paint.Style.STROKE);
        highlightPaint.setStrokeWidth(4);
    }

    // Override the 'draw' method to customize the drawing of the thumbstick
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

    // Method to check if the thumbstick is pressed based on touch coordinates
    public boolean isPressed(double touchX, double touchY) {
        return distanceBetweenCenterAndTouch(touchX, touchY) < outerRadius;
    }

    // Setter method for the 'isPressed' flag
    public void setIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    // Getter method for the 'isPressed' flag
    public boolean getIsPressed() {
        return isPressed;
    }

    // Method to set the actuator values based on touch coordinates
    public void setActuator(double touchX, double touchY) {
        // Calculate deltas and distance between center and touch
        double deltaX = touchX - centerX;
        double deltaY = touchY - centerY;
        double deltaDistance = distanceBetweenCenterAndTouch(touchX, touchY);
        
        // Normalize actuator values
        if (deltaDistance < outerRadius) {
            actuatorX = deltaX / outerRadius;
            actuatorY = deltaY / outerRadius;
        } else {
            actuatorX = deltaX / deltaDistance;
            actuatorY = deltaY / deltaDistance;
        }

        // Update player's position and notify observers
        update();
        if (GameView.isValidMoveX(getActuatorX())) {
            double velocityX = actuatorX * player.getPixelsPerFrame();
            player.setStartX((int) (player.getStartX() + velocityX));
        }

        if (GameView.isValidMoveY(getActuatorY())) {
            double velocityY = actuatorY * player.getPixelsPerFrame();
            player.setStartY((int) (player.getStartY() + velocityY));
        }

        player.updateObservers();
    }

    // Method to reset actuator values
    public void resetActuator() {
        actuatorX = 0;
        actuatorY = 0;
        update();
    }

    // Helper method to calculate the distance between thumbstick center and touch
    private double distanceBetweenCenterAndTouch(double touchX, double touchY) {
        return Math.sqrt((Math.pow(touchX - centerX, 2) + Math.pow(touchY - centerY, 2)));
    }

    // Method to update the position of the thumbstick
    public void update() {
        innerCircleCenterX = (int) (centerX + actuatorX * outerRadius);
        innerCircleCenterY = (int) (centerY + actuatorY * outerRadius);

        // Request a redraw
        this.invalidate();
    }

    // Override the 'onTouchEvent' method to handle touch events
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println(event.getAction());
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

    // Getter method for the Y-axis actuator value
    public double getActuatorY() {
        return this.actuatorY;
    }

    // Getter method for the X-axis actuator value
    public double getActuatorX() {
        return this.actuatorX;
    }
}
