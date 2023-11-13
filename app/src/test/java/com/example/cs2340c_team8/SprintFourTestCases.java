package com.example.cs2340c_team8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.cs2340c_team8.models.Enemy;
import com.example.cs2340c_team8.models.GameConfig;
import com.example.cs2340c_team8.models.Leaderboard;
import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.enemies.BulletBill;
import com.example.cs2340c_team8.models.enemies.Goomba;
import com.example.cs2340c_team8.models.enemies.KoopaTroopa;
import com.example.cs2340c_team8.models.enemies.PiranhaPlant;
import com.example.cs2340c_team8.models.enums.Difficulty;
import com.example.cs2340c_team8.models.levels.Level;
import com.example.cs2340c_team8.models.levels.Level1;
import com.example.cs2340c_team8.models.levels.Level2;
import com.example.cs2340c_team8.models.levels.Level3;

import org.junit.Assert;
import org.junit.Test;
import java.lang.reflect.Constructor;

public class SprintFourTestCases {
    @Test
    public void testPlayerEnemyCollision() {
        GameConfig.difficulty = Difficulty.BEGINNER;
        GameConfig.setDifficulty(Difficulty.INTERMEDIATE);
        BulletBill target1 = new BulletBill(25,25);
        Goomba target2 = new Goomba(50,50);
        Player test = Player.getInstance();
        test.setStartX(30);
        test.setStartY(40);
        boolean rtn1 = target1.isCollidingWithPlayer();
        boolean rtn2 = target2.isCollidingWithPlayer();
        assertTrue("The player and bullet bill are not colliding " +
                "OR the player and goomba are colliding", rtn1 || !rtn2);
    }

    @Test
    public void testPlayerEnemyHealth() {
        GameConfig.difficulty = Difficulty.BEGINNER;
        Player test = Player.getInstance();
        test.setStartX(25);
        test.setStartY(25);
        int healthInit = test.getHealth();
        KoopaTroopa target1 = new KoopaTroopa(25,25);
        target1.attackPlayer();
        //System.out.println(target1.getStartX());
        assertTrue("player and koopa colliding", !target1.isCollidingWithPlayer());
        assertTrue("no damage was taken",test.getHealth() < healthInit);
    }
    @Test
    public void testEnemyPlayerObserver() {
        GameConfig.difficulty = Difficulty.BEGINNER;
        Player test = Player.getInstance();
        test.clearObservers();
        BulletBill target1 = new BulletBill(10,27);
        KoopaTroopa target2 = new KoopaTroopa(27,27);
        Goomba target3 = new Goomba(23,23);
        PiranhaPlant target4 = new PiranhaPlant(27,23);
        assertFalse(Enemy.class.isAssignableFrom(BulletBill.class));
        assertFalse(Enemy.class.isAssignableFrom(KoopaTroopa.class));
        assertFalse(Enemy.class.isAssignableFrom(Goomba.class));
        assertFalse(Enemy.class.isAssignableFrom(PiranhaPlant.class));
        test.updateObservers();
        assertEquals(false,target1.isCollidingWithPlayer());
    }
    @Test
    public void testPlayerHealthZero() {
        GameConfig.difficulty = Difficulty.EXPERT;
        Player test = Player.getInstance();
        test.setHealth(100);
        PiranhaPlant target1 = new PiranhaPlant(25,25);
        for (int i = 0; i < 5; i++) {
            target1.attackPlayer();
        }
        assertTrue("health did not go to zero", test.getHealth() <= 0);
    }
    @Test
    public void testDifficultyEasy() {
        GameConfig.difficulty = Difficulty.BEGINNER;
        Player test = Player.getInstance();
        test.setHealth(250); //boost health to test  damage
        //int healthInit = test.getHealth();
        BulletBill target1 = new BulletBill(23,27);
        KoopaTroopa target2 = new KoopaTroopa(27,27);
        Goomba target3 = new Goomba(23,23);
        PiranhaPlant target4 = new PiranhaPlant(27,23);
        target1.attackPlayer();
        //System.out.println(test.getHealth()/ healthInit);
        target2.attackPlayer();
        target3.attackPlayer();
        target4.attackPlayer();
        assertEquals("damage levels are not correct or differnt for difficuluy", 90, test.getHealth());
    }
    @Test
    public void testDifficultyMedium() {
        GameConfig.difficulty = Difficulty.INTERMEDIATE;
        Player test = Player.getInstance();
        test.setHealth(500); //boost health to test  damage
        BulletBill target1 = new BulletBill(23,27);
        KoopaTroopa target2 = new KoopaTroopa(27,27);
        Goomba target3 = new Goomba(23,23);
        PiranhaPlant target4 = new PiranhaPlant(27,23);
        target1.attackPlayer();
        target2.attackPlayer();
        target3.attackPlayer();
        target4.attackPlayer();
        //System.out.println(test.getHealth());
        assertEquals("damage levels are not correct or differnt for difficuluy", 313, test.getHealth());
    }
    @Test
    public void testDifficultyHard() {
        GameConfig.difficulty = Difficulty.EXPERT;
        Player test = Player.getInstance();
        test.setHealth(750); //boost health to test  damage
        BulletBill target1 = new BulletBill(23,27);
        KoopaTroopa target2 = new KoopaTroopa(27,27);
        Goomba target3 = new Goomba(23,23);
        PiranhaPlant target4 = new PiranhaPlant(27,23);
        target1.attackPlayer();
        target2.attackPlayer();
        target3.attackPlayer();
        target4.attackPlayer();
        //System.out.println(test.getHealth());
        assertEquals("damage levels are not correct or differnt for difficuluy", 565, test.getHealth());
    }
    @Test
    public void testEnemyPlayerFactory() {
        GameConfig.difficulty = Difficulty.BEGINNER;
        Player test = Player.getInstance();
        test.updateObservers();
        assertTrue(Level.class.isAssignableFrom(Level.class));
        assertTrue(Level.class.isAssignableFrom(Level1.class));
        assertTrue(Level.class.isAssignableFrom(Level2.class));
        assertTrue(Level.class.isAssignableFrom(Level3.class));
    }
    @Test
    public void testPlayerEnemyDamageNotChange() {
        GameConfig.difficulty = Difficulty.BEGINNER;
        Player test = Player.getInstance();
        test.setHealth(60);
        KoopaTroopa target1 = new KoopaTroopa(25,25); //damage SHOULD not change
        target1.attackPlayer();
        assertEquals(40,test.getHealth());
        GameConfig.difficulty = Difficulty.INTERMEDIATE;
        target1.attackPlayer();
        assertEquals(20,test.getHealth());
        GameConfig.difficulty = Difficulty.EXPERT;
        target1.attackPlayer();
        assertEquals(0,test.getHealth());
    }
    @Test
    public void testEnemyMovementDifferent() {
        GameConfig.difficulty = Difficulty.INTERMEDIATE;
        BulletBill target1 = new BulletBill(23,27);
        KoopaTroopa target2 = new KoopaTroopa(27,27);
        Goomba target3 = new Goomba(23,23);
        PiranhaPlant target4 = new PiranhaPlant(27,23);
        target1.moveEnemy();
        target2.moveEnemy();
        target3.moveEnemy();
        target4.moveEnemy();
        boolean rtnX = (target1.getStartX() == target2.getStartX() || target3.getStartX() == target4.getStartX());
        boolean rtnY = (target1.getStartY() == target2.getStartY() || target3.getStartY() == target4.getStartY());
        assertTrue(!rtnX || !rtnY);
    }

}

