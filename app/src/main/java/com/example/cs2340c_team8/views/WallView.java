// Package declaration
package com.example.cs2340c_team8.views;

// Import statements for required Android classes
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.cs2340c_team8.models.elements.Wall;

// Declaration of the 'WallView' class, extending the 'View' class
public class WallView extends View {
    // Paint object for drawing the wall
    private Paint paint;
    
    // Instance of the 'Wall' class
    private Wall wall;

    // Constructor for the 'WallView' class
    public WallView(Context context, Wall wall) {
        super(context);

        // Initialize the wall instance
        this.wall = wall;
        
        // Initialize the Paint object
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
    }

    // Override the 'onDraw' method to customize the drawing of the wall
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        // Set the color for drawing the wall
        paint.setColor(Color.argb(255, 192, 194, 201));
        
        // Draw the wall rectangle on the canvas
        canvas.drawRect(
            wall.getX(),
            wall.getY(),
            wall.getX() + wall.getWidth(),
            wall.getY() + wall.getHeight(),
            paint
        );
    }
}
