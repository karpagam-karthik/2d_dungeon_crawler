package com.example.cs2340c_team8;
import static com.example.cs2340c_team8.models.Player.isColliding;
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
<<<<<<< HEAD
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
        //test.setX(Player.getInstance().getX() - 2 * randomMove);
        //move up then down
        test.setY(Player.getInstance().getY() + randomMove);
        //test.setY(Player.getInstance().getY() - 2*randomMove);

        //if player can move properly from origin, they should be at -randomMove,-randomMove
        assertTrue(Player.getInstance().getX() == randomMove);
        assertTrue(Player.getInstance().getY() == randomMove);
    }
    @Test
    public void testNoWallGaps() {
    }
=======
>>>>>>> 9b3f04d5ddba2d5f8531885e83af05c52215cdf5
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
        int knockBack = 15 / 3;
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
        float randomMove = r.nextInt();

        //move right, then left
        float originalX = test.getX();
        float originalY = test.getY();
        test.setX(Player.getInstance().getX() + randomMove);
        //test.setX(Player.getInstance().getX() - 2 * randomMove);
        //move up then down
        test.setY(Player.getInstance().getY() + randomMove);
        //test.setY(Player.getInstance().getY() - 2*randomMove);

        System.out.println(randomMove);
        System.out.println(test.getX());
        System.out.println(test.getY());

        //if player can move properly from origin, they should be at -randomMove,-randomMove
<<<<<<< HEAD
        assertTrue(Player.getInstance().getX() == randomMove);
        assertTrue(Player.getInstance().getY() == randomMove);
    }
    @Test
    public void testWallCollisionsFalse() {

        Player player = Player.getInstance();
        player.setX(30);
        player.setY(30);
        player.setX(Player.getInstance().getX());
        player.setY(Player.getInstance().getX());

        Wall wall = new Wall(50, 50, 5, 5, null);
        assertFalse(isColliding(player, wall));
    }

    @Test
    public void testWallCollisionsOrigin() {

        Player player = Player.getInstance();

        player.setX(Player.getInstance().getX());
        player.setY(Player.getInstance().getX());
        //player.setX(55);
        //player.setY(25);

        Wall wall = new Wall(0, 0, 0, 0, null);
        //combining should happen at 0
        assertTrue(isColliding(player, wall));
    }

    @Test
    public void testGoingPastWalls() {
        //Player is a singleton class, must use get instance eot instantiate
        Player player = Player.getInstance();

        Wall wall = new Wall(4, 4, 4, 4, null);

        player.setX(Player.getInstance().getX() + 10);
        player.setY(Player.getInstance().getY() + 10);

        System.out.println(player.getX());
        System.out.println(player.getY());
        wall.setX(4);
        wall.setY(4);
        System.out.println(wall.getX());
        System.out.println(wall.getY());


        assertFalse(isColliding(player, wall));
=======
        assertTrue(Player.getInstance().getX() == Player.getInstance().getY());
>>>>>>> 9b3f04d5ddba2d5f8531885e83af05c52215cdf5
    }



}
