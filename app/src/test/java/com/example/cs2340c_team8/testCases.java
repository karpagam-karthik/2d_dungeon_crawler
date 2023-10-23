package com.example.cs2340c_team8;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static java.lang.System.currentTimeMillis;

import com.example.cs2340c_team8.models.Door;
import com.example.cs2340c_team8.models.Enemy;
import com.example.cs2340c_team8.models.Leaderboard;
import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.Score;
import com.example.cs2340c_team8.models.Trap;
import com.example.cs2340c_team8.models.Wall;
import com.example.cs2340c_team8.models.interfaces.Obstacle;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.Random;

public class testCases {
    @Test
    public void testEnemyCollision() {
        Player test = Player.getInstance();
        Obstacle enemy = new Enemy();
        int health = test.getHealth();
        test.movementInteraction(enemy);
        assertTrue(test.getHealth() == health - 10);
    }
    @Test
    public void testPlayerStrategy() {
        //using the strategy method, we want to make sure
        // that interactable components are all of the same interface
        Trap test1 = new Trap(10);
        Enemy test2 = new Enemy();
        Door test3 = new Door();
        assertTrue(test1 instanceof Obstacle);
        assertTrue(test2 instanceof Obstacle);
        assertTrue(test3 instanceof Obstacle);
    }
    @Test
    public void testTrapCollision() {
        Player test = Player.getInstance();
        int knockBack = 15;
        Obstacle trap = new Trap(knockBack);
        test.movementInteraction(trap);
        assertTrue(test.getX() == test.getY());
    }
    @Test
    public void testFinalLevel() {
        Player test = Player.getInstance();
        for(int i = 0; i < 4; i++) {
            test.nextLevel(1);
        }
        assertTrue(test.getLevelNumber() > 3);
        assertTrue(test.getLayout() == "Final Level");
    }
    @Test
    public void testFutureDate() {
        Score test = new Score("testingPlayer",0,0);
        assertTrue(test.getTimeMilliseconds() <= currentTimeMillis());
    }
    @Test
    public void testPlayerDirection() {
        //Player is a singleton class, must use get instance eot instantiate
        Player test = Player.getInstance();
        Random r = new Random();
        int randomMove = r.nextInt();

        //move right, then left
        float originalX = test.getX();
        float originalY = test.getY();
        test.setX(Player.getInstance().getX() + randomMove);
        test.setX(Player.getInstance().getX() - 2 * randomMove);
        //move up then down
        test.setY(Player.getInstance().getY() + randomMove);
        test.setY(Player.getInstance().getY() - 2*randomMove);

        //if player can move properly from origin, they should be at -randomMove,-randomMove
        assertTrue(Player.getInstance().getX() == Player.getInstance().getY());
    }


}
