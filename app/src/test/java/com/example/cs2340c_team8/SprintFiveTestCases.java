package com.example.cs2340c_team8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.content.ContextWrapper;

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
import com.example.cs2340c_team8.models.powerups.FirePowerUp;
import com.example.cs2340c_team8.models.powerups.IcePowerUp;
import com.example.cs2340c_team8.models.powerups.StarPowerUp;
import com.example.cs2340c_team8.views.GameView;

import org.junit.Assert;
import org.junit.Test;
import java.lang.reflect.Constructor;
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
        PiranhaPlant target4 = new PiranhaPlant(27,23,0,0);
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
        Goomba target2 = new Goomba(null, 50, 50, false, 10);
        Player test = Player.getInstance();
        StarPowerUp target3 = new StarPowerUp(null, 100,100);
        test.setStartX(30);
        test.setStartY(40);
        int healthPrev = test.getHealth();
        boolean rtn1 = test.getHealth() == healthPrev;
        target1.attackPlayer();
        target2.attackPlayer();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        assertTrue("The player returned to its og health", rtn1);
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
        target3.attackPlayer();
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
        BulletBill target1 = new BulletBill(null, 25, 25, 0, 0);
        Goomba target2 = new Goomba(null, 50, 50, false, 0);
        Player test = Player.getInstance();
        FirePowerUp target3 = new FirePowerUp(null, 100,100);
        test.setStartX(30);
        test.setStartY(40);
        target1.attackPlayer();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        assertTrue("The players fire attack did not return crease", test.getHealth() <= 400);
    }

}