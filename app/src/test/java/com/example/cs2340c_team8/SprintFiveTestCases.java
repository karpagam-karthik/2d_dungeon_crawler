package com.example.cs2340c_team8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.cs2340c_team8.models.GameConfig;
import com.example.cs2340c_team8.models.Player;
import com.example.cs2340c_team8.models.enemies.BulletBill;
import com.example.cs2340c_team8.models.enemies.Goomba;
import com.example.cs2340c_team8.models.enemies.KoopaTroopa;
import com.example.cs2340c_team8.models.enemies.BlueShell;
import com.example.cs2340c_team8.models.enums.Difficulty;
import com.example.cs2340c_team8.models.interfaces.PlayerObserver;
import com.example.cs2340c_team8.models.powerups.FirePowerUp;
import com.example.cs2340c_team8.models.powerups.IcePowerUp;
import com.example.cs2340c_team8.models.powerups.StarPowerUp;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class SprintFiveTestCases {
    @Test
    public void testPlayerEnemyCollisions() {
        GameConfig.setDifficulty(Difficulty.EXPERT);
        Player test = Player.getInstance();
        test.setHealth(750); //boost health to test  damage
        BulletBill target1 = new BulletBill(null, 23,27,0,0);
        KoopaTroopa target2 = new KoopaTroopa(null, 27,27,false,0);
        Goomba target3 = new Goomba(null, 23,23,false,0);
        BlueShell target4 = new BlueShell(27,23,0,0);
        target1.attackPlayer();
        target2.attackPlayer();
        target3.attackPlayer();
        target4.attackPlayer();
        //System.out.println(test.getHealth());
        assertEquals("damage levels are not correct or differnt for difficuluy", 565, test.getHealth());
    }
    @Test
    public void testFireFlower() {
        GameConfig.setDifficulty(Difficulty.BEGINNER);
        BulletBill target1 = new BulletBill(null, 25, 25, 0, 0);
        Goomba target2 = new Goomba(null, 50, 50, false, 0);
        Player test = Player.getInstance();
        FirePowerUp target3 = new FirePowerUp(null, 100,100);
        test.setStartX(30);
        test.setStartY(40);
        target1.attackPlayer();
        assertTrue("The players fire attack did not increase", test.getHealth() <= 200);
    }

    @Test
    public void testIceFlower() {
        GameConfig.setDifficulty(Difficulty.BEGINNER);
        BulletBill target1 = new BulletBill(null, 25, 25, 0, 0);
        Goomba target3 = new Goomba(null, 50, 50, false, 0);
        Player test = Player.getInstance();
        IcePowerUp target2 = new IcePowerUp(null, 100,100);
        test.setStartX(30);
        test.setStartY(40);
        int prev1 = target1.getMovementSpeed();
        int prev2 = target3.getMovementSpeed();
        target3.attackPlayer();
        boolean rtn1 = target1.getMovementSpeed() == prev1 / 2;
        boolean rtn2 = target3.getMovementSpeed() == prev2 / 2;
        assertTrue("The enemies did not get frozen", rtn1 && rtn2);
    }

    @Test
    public void TestStarPower() {
        GameConfig.setDifficulty(Difficulty.BEGINNER);
        BulletBill target1 = new BulletBill(null, 25, 25, 0, 20);
        Goomba target2 = new Goomba(null, 50, 50, false, 10);
        Player test = Player.getInstance();
        StarPowerUp target3 = new StarPowerUp(null, 100,100);
        test.setStartX(30);
        test.setStartY(40);
        int healthPrev = test.getHealth();
        target1.attackPlayer();
        target2.attackPlayer();
        boolean rtn1 = test.getHealth() <= healthPrev;
        assertTrue("The player did not get shielded", rtn1);
    }

    @Test
    public void TestStarPowerupDuration() {
        GameConfig.setDifficulty(Difficulty.BEGINNER);
        BulletBill target1 = new BulletBill(null, 25, 25, 0, 20);
        Goomba target3 = new Goomba(null, 50, 50, false, 10);
        Player test = Player.getInstance();
        StarPowerUp target2 = new StarPowerUp(null, 100,100);
        int prevHealth = test.getHealth();//target3.attackPlayer();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        assertTrue("The player returned to its og health", prevHealth == test.getHealth());
    }

    @Test
    public void TestIcePowerupDuration() {
        GameConfig.setDifficulty(Difficulty.BEGINNER);
        BulletBill target1 = new BulletBill(null, 25, 25, 0, 20);
        Goomba target3 = new Goomba(null, 50, 50, false, 10);
        Player test = Player.getInstance();
        IcePowerUp target2 = new IcePowerUp(null, 100,100);
        test.setStartX(30);
        test.setStartY(40);
        int prev1 = target1.getMovementSpeed();
        int prev2 = target3.getMovementSpeed();
        //target3.attackPlayer();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        boolean rtn1 = target1.getMovementSpeed() == prev1 / 2;
        boolean rtn2 = target3.getMovementSpeed() == prev2 / 2;
        assertTrue("The enemies did not begin moving", !rtn1 && !rtn2);
    }
    @Test
    public void TestFirePowerupDuration() {
        GameConfig.setDifficulty(Difficulty.BEGINNER);
        BulletBill target1 = new BulletBill(null, 25, 25, 0, 20);
        Goomba target3 = new Goomba(null, 50, 50, false, 10);
        Player test = Player.getInstance();
        FirePowerUp target2 = new FirePowerUp(null, 100,100);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        assertTrue("The players fire attack range did not return to normal", target2.getEffect() == 500);
    }

    @Test
    public void testMovementMods() {
        GameConfig.setDifficulty(Difficulty.EXPERT);
        Player test = Player.getInstance();
        test.setHealth(750); //boost health to test  damage
        BulletBill target1 = new BulletBill(null, 23,27,0,10);
        KoopaTroopa target2 = new KoopaTroopa(null, 27,27,false,10);
        Goomba target3 = new Goomba(null, 23,23,false,10);
        BlueShell target4 = new BlueShell(27,23,0,10);
        target1.fastMovespeed();
        target2.slowMovespeed();
        target3.getMovementSpeed();
        target4.slowMovespeed();
        target4.fastMovespeed();
        assertTrue("Movement modifications work as they should", target1.getMovementSpeed() == 20 && target3.getMovementSpeed() == 10);

    }
    @Test
    public void testPlayerPowerup() {
        GameConfig.setDifficulty(Difficulty.BEGINNER);
        BulletBill target1 = new BulletBill(null, 25, 25, 0, 0);
        Goomba target2 = new Goomba(null, 50, 50, false, 0);
        Player test = Player.getInstance();
        FirePowerUp target3 = new FirePowerUp(null, 100,100);
        test.addObserver(target1);
        test.addObserver(target2);
        test.addObserver(target3);
        boolean found = false;
        for (PlayerObserver obv : test.getObservers()) {
            if (obv.getClass() == FirePowerUp.class) {
                found = true;
            }
        }
        assertTrue("could not find the powerup", found);

    }

    @Test
    public void testPlayerEnemy() {
        GameConfig.setDifficulty(Difficulty.BEGINNER);
        BulletBill target1 = new BulletBill(null, 25, 25, 0, 0);
        Goomba target2 = new Goomba(null, 50, 50, false, 0);
        Player test = Player.getInstance();
        FirePowerUp target3 = new FirePowerUp(null, 100,100);
        test.addObserver(target1);
        test.addObserver(target2);
        test.addObserver(target3);
        boolean found = false;
        for (PlayerObserver obv : test.getObservers()) {
            if (obv.getClass() == BulletBill.class) {
                found = true;
            }
        }
        assertTrue("could not find the enemies", found);

    }





}