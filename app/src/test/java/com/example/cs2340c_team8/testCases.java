package com.example.cs2340c_team8;

import static com.example.cs2340c_team8.models.Player.isColliding;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static java.lang.System.currentTimeMillis;

import com.example.cs2340c_team8.models.elements.Door;
import com.example.cs2340c_team8.models.Enemy;
import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.Score;
import com.example.cs2340c_team8.models.elements.Trap;
import com.example.cs2340c_team8.models.elements.Wall;
import com.example.cs2340c_team8.models.interfaces.Element;

import org.junit.Test;

import java.util.Random;

public class testCases {
    @Test
    public void testPlayerSameDirection() {
        Player test = Player.getInstance();
        Random r = new Random();
        int randomMove = r.nextInt();

        //move right, then left
        test.setStartX(Player.getInstance().getStartX() + randomMove);
        //move up then down
        test.setStartY(Player.getInstance().getStartY() + randomMove);

        //if player can move properly from origin, they should be at randomMove,randomMove
        assertTrue(test.getStartX() == test.getStartY());
    }

//    @Test
//    public void testEnemyCollision() {
//        Player test = Player.getInstance();
//        Element enemy = new Enemy();
//        int health = test.getHealth();
//        test.movementInteraction(enemy);
//        assertTrue(test.getHealth() == health - 10);
//    }

    @Test
    public void testPlayerStrategy() {
        //using the strategy method, we want to make sure
        // that interactable components are all of the same interface
        Trap test1 = new Trap(10);
        Enemy test2 = new Enemy();
        Door test3 = new Door();
        assertTrue(test1 instanceof Element);
        assertTrue(test2 instanceof Element);
        assertTrue(test3 instanceof Element);
    }

//    @Test
//    public void testTrapCollision() {
//        Player test = Player.getInstance();
//        int knockBack = 15;
//        Element trap = new Trap(knockBack);
//        test.movementInteraction(trap);
//        assertTrue(test.getX() == 30 && test.getY() == 20);
//    }
//
//    @Test
//    public void testFinalLevel() {
//        Player test = Player.getInstance();
//        for (int i = 0; i < 4; i++) {
//            test.nextLevel(1);
//        }
//        assertTrue(test.getLevelNumber() > 3);
//        assertTrue(test.getLayout() == "Final Level");
//    }

    @Test
    public void testFutureDate() {
        Score test = new Score("testingPlayer", 0, 0);
        assertTrue(test.getTimeMilliseconds() <= currentTimeMillis());
    }

    @Test
    public void testPlayerOppositeDirection() {
        //Player is a singleton class, must use get instance eot instantiate
        Player test = Player.getInstance();
        Random r = new Random();
        int randomMove = r.nextInt();

        //move right, then left
        test.setStartX(test.getStartX() + randomMove);
        test.setStartX(test.getStartX() - 2 * randomMove);
        //move up then down
        test.setStartY(test.getStartY() + randomMove);
        test.setStartY(test.getStartY() - 2 * randomMove);

        //if player can move properly from origin, they should be at -randomMove,-randomMove
        assertTrue(test.getStartX() == test.getStartY());
    }

    @Test
    public void testWallCollisionsFalse() {

        Player player = Player.getInstance();
        player.setStartX(30);
        player.setStartY(30);
        player.setStartX(Player.getInstance().getStartX());
        player.setStartY(Player.getInstance().getStartX());

        Wall wall = new Wall(50, 50, 5, 5, null);
        assertFalse(isColliding(player, wall));
    }

    @Test
    public void testWallCollisionsOrigin() {
        Player player = Player.getInstance();

        player.setStartX(Player.getInstance().getStartX());
        player.setStartY(Player.getInstance().getStartX());

        Wall wall = new Wall(25, 25, 0, 0, null);
        //combining should happen at 0
        assertTrue(isColliding(player, wall));
    }

    @Test
    public void testGoingPastWalls() {
        //Player is a singleton class, must use get instance eot instantiate
        Player player = Player.getInstance();

        Wall wall = new Wall(4, 4, 4, 4, null);

        player.setStartX(Player.getInstance().getStartX() + 10);
        player.setStartY(Player.getInstance().getStartY() + 10);

        wall.setX(4);
        wall.setY(4);

        assertFalse(isColliding(player, wall));
    }
}
