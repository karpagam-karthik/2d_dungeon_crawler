package com.example.cs2340c_team8.models;

import com.example.cs2340c_team8.models.enums.PowerUp;
import com.example.cs2340c_team8.models.interfaces.Consumable;
import com.example.cs2340c_team8.models.interfaces.Key;
import com.example.cs2340c_team8.models.interfaces.Level;
import com.example.cs2340c_team8.models.interfaces.Obstacle;
import com.example.cs2340c_team8.models.interfaces.PlayerObserver;
import com.example.cs2340c_team8.models.interfaces.Point;
import com.example.cs2340c_team8.models.interfaces.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Player implements Weapon, com.example.cs2340c_team8.models.interfaces.PowerUp, Level, Key, Point {
    private static Player instance;
    private static final int SPRITE_SIZE = 16;
    private int health;
    private long time;
    private List<Weapon> weapons;
    private List<Key> keys;
    private int points;
    private List<com.example.cs2340c_team8.models.interfaces.PowerUp> powerUps;
    private List<Consumable> consumables;
    private int level;
    private List<PlayerObserver> observers;
    private int posX; //for x positioning
    private int posY; //for y positioning

    private Player() {
        health = 100;
        time = 0;
        weapons = new ArrayList<>();
        keys = new ArrayList<>();
        points = 0;
        powerUps = new ArrayList<>();
        consumables = new ArrayList<>();
        observers = new ArrayList<>();
        posX = 25; // likely need to change based on grid positioning
        posY = 25; // likely need to change based on grid positioning
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

    public PowerUp getPowerUpType() {
        // Implement power-up type logic
        return PowerUp.HEALTH_BOOST;
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

    public int getX() { //for positioning
        return posX;
    }
    public int getY() { //for positioning
        return posY;
    }

    public void setPosX(int posX) { //for positioning
        this.posX = posX;
    }
  
    public void setPosY(int posY) { //for positioning
        this.posY = posY;
    }

    public void setHealth(int health) {
        this.health = health;
    }
  
    public int getHealth() {
        return this.health;
    }

    public static int getSpriteSize() {
        return SPRITE_SIZE;
    }

    public void addObserver(PlayerObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(PlayerObserver observer) {
        this.observers.remove(observer);
    }

    public void updateObservers() {
        for (PlayerObserver observer : observers) {
            observer.update(posX, posY);
        }
    }
  
    @Override
    public String toString() {
        String rtn = getLevelNumber() + getHealth()
                + getX() + getY() + getType();
        return rtn;
    }

    /**
     * isColliding will check if player and wall collide based on their positions.
     * @param player represents the player (controlled by user).
     * @param wall represents the list of walls.
     * @return returns if a player is colliding with a Wall
     */
    public static boolean isColliding(Player player, Wall wall) {
        double distanceX = wall.getX() - player.getX();
        double distanceY = wall.getY() - player.getY();
        double distanceXFormula = Math.pow(distanceX, 2);
        double distanceYFormula = Math.pow(distanceY, 2);
        //Below line finds the distance between the player and wall
        double distance = Math.sqrt(distanceXFormula + distanceYFormula);

        //just putting '5' for now b/c '1' might cause player to phase past wall
        return distance < 5;
    } //isColliding

    public void movementInteraction(Obstacle obstacle) {
        Player player = getInstance();
        if (obstacle.getEffect() == "Damage") { //strategy if a player encounters an enemy
            player.setHealth(player.getHealth() - obstacle.getEffectMagnitude()); //deal damage
        } else if (obstacle.getEffect() == "Knock-back") { //strategy if a player encounters a trap
            player.setPosX(player.getX() + obstacle.getEffectMagnitude());
            player.setPosY(player.getY() - obstacle.getEffectMagnitude());
        } else if (obstacle.getEffect() == "Door") {
            player.nextLevel(player.getLevelNumber()); //strategy if a player encounters a door
        }
    }
}

