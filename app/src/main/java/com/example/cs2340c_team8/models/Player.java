package com.example.cs2340c_team8.models;

import com.example.cs2340c_team8.models.elements.Wall;
import com.example.cs2340c_team8.models.interfaces.Consumable;
import com.example.cs2340c_team8.models.interfaces.Key;
import com.example.cs2340c_team8.models.interfaces.Level;
import com.example.cs2340c_team8.models.interfaces.Element;
import com.example.cs2340c_team8.models.interfaces.PlayerObserver;
import com.example.cs2340c_team8.models.interfaces.PowerUp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player implements PowerUp, Level, Key {
    private static final int spriteSizeX = 25;
    private static final int spriteSizeY = 25;
    private static Player instance;
    private int health;
    private int level;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private List<Key> keys;
    private List<PowerUp> powerUps;
    private List<Consumable> consumables;
    private static List<PlayerObserver> observers;

    private Player() {
        keys = new ArrayList<>();
        powerUps = new ArrayList<>();
        consumables = new ArrayList<>();
        observers = new ArrayList<>();

        level = 1;
        startX = 25;
        startY = 25;
        endX = startX + spriteSizeX;
        endY = startY + spriteSizeY;
    }

    public static Player getInstance() {
        if (instance == null) {
            synchronized (Player.class) {
                if (instance == null) {
                    instance = new Player();
                }
            }
        }
        return instance;
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

    public int getX() {
        return startX;
    }

    public int getY() {
        return startY;
    }

    public void setStartX(int startX) {
        this.startX = startX;
        endX = startX + spriteSizeX;
        updateObservers();
    }

    public void setStartY(int startY) {
        this.startY = startY;
        endY = startY + spriteSizeY;
        updateObservers();
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return this.health;
    }

    public static int getSpriteSizeX() {
        return spriteSizeX;
    }

    public static int getSpriteSizeY() {
        return spriteSizeY;
    }

    public static void addObserver(PlayerObserver observer) {
        observers.add(observer);
    }

    public static void removeObserver(PlayerObserver observer) {
        observers.remove(observer);
    }

    public void updateObservers() {
        for (PlayerObserver observer : observers) {
            observer.updatePlayerPosition(startX, startY, endX, endY);
        }
    }

    @Override
    public String toString() {
        String rtn = String.valueOf(getLevelNumber() + getHealth()
                + getX() + getY());
        return rtn;
    }

    /**
     * isColliding will check if player and wall collide based on their positions.
     *
     * @param player represents the player (controlled by user).
     * @param wall   represents the list of walls.
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

    public void movementInteraction(Element obstacle) {
        Player player = getInstance();
        if (Objects.equals(obstacle.getEffect(), "Damage")) {
            player.setHealth(player.getHealth() - obstacle.getEffectMagnitude()); //deal damage
        } else if (Objects.equals(obstacle.getEffect(), "Knock-back")) {
            player.setStartX(player.getX() + obstacle.getEffectMagnitude());
            player.setStartY(player.getY() - obstacle.getEffectMagnitude());
        } else if (Objects.equals(obstacle.getEffect(), "Door")) {
            player.nextLevel(player.getLevelNumber());
        }
    }
}

