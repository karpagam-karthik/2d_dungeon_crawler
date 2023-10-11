package com.example.cs2340c_team8;

import java.util.ArrayList;
import java.util.List;

public class Player implements Weapon, PowerUp, Level, Key, Point {
    private static Player instance;

    private int health;
    private long time;
    private List<Weapon> weapons;
    private List<Key> keys;
    private int points;
    private List<PowerUp> powerUps;
    private List<Consumable> consumables;

    private Player() {
        health = 100;
        time = 0;
        weapons = new ArrayList<>();
        keys = new ArrayList<>();
        points = 0;
        powerUps = new ArrayList<>();
        consumables = new ArrayList<>();
    }

    public static  Player getInstance() {
        if (instance == null) {
            synchronized (Player.class) {
                if (instance == null) {
                    instance = new Player();
                }
            }
        }
        return instance;
    }

    // Implement methods from the interfaces

    @Override
    public String getType() {
        // Implement weapon type logic
        return "Default";
    }

    @Override
    public int getDamage() {
        // Implement weapon damage logic
        return 10;
    }

    public PowerUpType getPowerUpType() {
        // Implement power-up type logic
        return PowerUpType.HEALTH_BOOST;
    }

    public int getEffect() {
        // Implement power-up effect logic
        return 5;
    }

    public long getDuration() {
        // Implement power-up duration logic
        return 10;
    }
    public int getLevelNumber() {
        // Implement level number logic
        return 1;
    }

    public String getLayout() {
        // Implement level layout logic
        return "Default Layout";
    }

    public int getMatchedPoints() {
        // Implement matched points logic
        return 0;
    }

    @Override
    public boolean isMatchedDoor() {
        // Implement matched door logic
        return false;
    }

    @Override
    public int getAmount() {
        // Implement point amount logic
        return points;
    }

    // Implement other methods from the interfaces as needed
}

