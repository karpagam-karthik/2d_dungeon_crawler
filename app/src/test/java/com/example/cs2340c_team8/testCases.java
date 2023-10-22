package com.example.cs2340c_team8;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static java.lang.System.currentTimeMillis;

import com.example.cs2340c_team8.models.Leaderboard;
import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.Score;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.Random;

public class testCases {
    //test game ending scenario where the player wins,
    //test if a player moves off screen or out of bounds what happens
    //test putting in a date which has not occured as a score date
    //test walls have no gaps in between them, neighbors list has at least 2 walls
    //test getting to next room
    //test gameover leaderboard scenario where a player has a negative score or two duplicate scores
    //test players movement is strategy pattern
    //test players movement is observer pattern
    //test final exit exists/level
    //test directiosn of player movement work as intended
    @Test
    public void testPlayerOutOfBounds() {
        Player test = Player.getInstance();
        Random r = new Random();
        int randomMove = r.nextInt();

        //move right, then left
        test.setX(Player.getInstance().getX() + randomMove);
        test.setX(Player.getInstance().getX() - 2 * randomMove);
        //move up then down
        test.setY(Player.getInstance().getY() + randomMove);
        test.setY(Player.getInstance().getY() - 2*randomMove);

        //if player can move properly from origin, they should be at -randomMove,-randomMove
        assertTrue(Player.getInstance().getX() == -randomMove);
        assertTrue(Player.getInstance().getY() == -randomMove);
    }
    @Test
    public void testNoWallGaps() {
        new
    }
    @Test
    public void testNextLevel() {
    }
    @Test
    public void testPlayerStrategy() {
    }
    @Test
    public void testPlayerObserver() {
    }
    @Test
    public void testGameOverNegativeNDuplicate() {
    }
    @Test
    public void testFinalLevel() {
    }
    @Test
    public void testPlayerWins() {
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
        test.setX(Player.getInstance().getX() + randomMove);
        test.setX(Player.getInstance().getX() - 2 * randomMove);
        //move up then down
        test.setY(Player.getInstance().getY() + randomMove);
        test.setY(Player.getInstance().getY() - 2*randomMove);

        //if player can move properly from origin, they should be at -randomMove,-randomMove
        assertTrue(Player.getInstance().getX() == -randomMove);
        assertTrue(Player.getInstance().getY() == -randomMove);
    }


}
