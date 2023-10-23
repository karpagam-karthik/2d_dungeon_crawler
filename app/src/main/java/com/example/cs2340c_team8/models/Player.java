package com.example.cs2340c_team8.models;

import com.example.cs2340c_team8.models.enums.PowerUpType;
import com.example.cs2340c_team8.models.interfaces.Consumable;
import com.example.cs2340c_team8.models.interfaces.Key;
import com.example.cs2340c_team8.models.interfaces.Level;
import com.example.cs2340c_team8.models.interfaces.Obstacle;
import com.example.cs2340c_team8.models.interfaces.PlayerObserver;
import com.example.cs2340c_team8.models.interfaces.Point;
import com.example.cs2340c_team8.models.interfaces.PowerUp;
import com.example.cs2340c_team8.models.interfaces.Weapon;

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
    private int level;
    private List<PlayerObserver> observers;

    private float x; //for x positioning
    private float y; //for y positioning

    private Player() {
        health = 100;
        time = 0;
        weapons = new ArrayList<>();
        keys = new ArrayList<>();
        points = 0;
        powerUps = new ArrayList<>();
        consumables = new ArrayList<>();
        x = 0; //likely need to change based on grid positioning
        y = 0; //likely need to change based on grid positioning
        level = 1;
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
        return level;
    }

    public int nextLevel(int level) {
        this.level += 1;
        return level + 1;
    }

    @Override
    public String getLayout() {
        // Implement level layout logic
        if (level < 4) {
            return "Default Layout";
        } else {
            return "Final Level";
        }
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

    public float getX() { //for positioning
        return x;
    }
    public float getY() { //for positioning
        return y;
    }

    public void setX(float X) { //for positioning
        this.x = x;
    }
    public void setY(float Y) { //for positioning
        this.y = y;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public int getHealth() {
        return this.health;
    }


    public void updateObservers() {
        String str = getInstance().toString();
        for (PlayerObserver observer : observers) {
            observer.update(str);
        }
    }
    @Override
    public String toString() {
        String rtn = getLevelNumber() + getHealth()
                + getX() + getY() + getType();
        return rtn;
    }

    /**
     * isCollding will check if player and wall collide based on their positions.
     * @param player represents the player (controlled by user).
     * @param wall represents the list of walls.
     */
    public static boolean isColliding(Player player, Wall wall) {
        double distanceX = wall.getX() - player.getX();
        double distanceY = wall.getY() - player.getY();
        double distanceXFormula = Math.pow(distanceX, 2);
        double distanceYFormula = Math.pow(distanceY, 2);
        //Below line finds the distance between the player and wall
        double distance = Math.sqrt(distanceXFormula + distanceYFormula);

        if (distance < 5) { //just putting '5' for now b/c '1' might cause player to phase past wall
            return true;
        } else {
            return false;
        }
    } //isColliding

    public void movementInteraction(Obstacle obstacle) {
        Player player = getInstance();
        if (obstacle.getEffect() == "Damage") { //strategy if a player encounters an enemy
            player.setHealth(player.getHealth() - obstacle.getEffectMagnitude()); //deal damage
        } else if (obstacle.getEffect() == "Knock-back") { //strategy if a player encounters a trap
            player.setX(player.getX() + obstacle.getEffectMagnitude());
            player.setY(player.getY() - obstacle.getEffectMagnitude()); //for now all traps will knock player in same direction for simplicity
        } else if (obstacle.getEffect() == "Door") {
            player.nextLevel(player.getLevelNumber()); //strategy if a player encounters a door
        } else {
            //else do nothing to change movement, check if obstacle is a wall
        }
    }


    // Implement other methods from the interfaces as needed
}

