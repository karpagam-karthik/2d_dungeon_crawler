// Package declaration
package com.example.cs2340c_team8.models;

// Import statement to include the 'Element' interface
import com.example.cs2340c_team8.models.interfaces.Element;

// Declaration of the 'Enemy' class that implements the 'Element' interface
public class Enemy implements Element {

    // Implementation of the 'getEffectMagnitude' method from the 'Element' interface
    @Override
    public int getEffectMagnitude() {
        return 10;
    }

    // Implementation of the 'getEffect' method from the 'Element' interface
    @Override
    public String getEffect() {
        return "Damage";
    }
}
