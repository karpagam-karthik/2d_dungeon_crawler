package com.example.cs2340c_team8;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import com.example.cs2340c_team8.models.Leaderboard;
import com.example.cs2340c_team8.models.Player;

import org.junit.Test;
import java.lang.reflect.Constructor;

public class SingletonTests {
    @Test
    public void testLeaderboardSingleton() {
        //Tahsin's unit test 1, make sure the leader board is a
        // singleton to avoid errors with multiple leaderboards
        //checks constructor is private
        try {
            Class test = Class.forName("Leaderboard");
            Constructor<Leaderboard>[] constructors = test.getDeclaredConstructors();
            if (constructors.length != 1 || !constructors[0].isAccessible()) {
                throw new ClassNotFoundException();
            }
            //check private instance
            test.getField("instance");
            Object test1 = constructors[0].newInstance();
            Object test2 = constructors[0].newInstance();
            assertTrue("Singletons cannot have more than one instance.", test1 == test2);
        } catch (Exception e) {
            assertFalse("Singletons must have private constructors, " +
                    "public static getInstance(), and private static instances",false);
        }
    }
    @Test
    public void testPlayerSingleton() {
        //Tahsin's unit test 2, make sure the player is a
        // singleton to avoid errors with multiple leaderboards
        //checks constructor is private
        try {
            Class test = Class.forName("Player");
            Constructor<Player>[] constructors = test.getDeclaredConstructors();
            if (constructors.length != 1 || !constructors[0].isAccessible()) {
                throw new ClassNotFoundException();
            }
            //check private instance
            test.getField("instance");
            Object test1 = constructors[0].newInstance();
            Object test2 = constructors[0].newInstance();
            assertTrue("Singletons cannot have more than one instance.", test1 == test2);
        } catch (Exception e) {
            assertFalse("Singletons must have private constructors, " +
                    "public static getInstance(), and private static instances",false);
        }
    }
}

