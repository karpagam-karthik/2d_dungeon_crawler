package com.example.cs2340c_team8;

import static org.junit.Assert.assertTrue;

import com.example.cs2340c_team8.models.Leaderboard;
import com.example.cs2340c_team8.models.Score;

import org.junit.Test;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testCompareScoresUnequal() {
        // Aidan Test Case 1
        Score score1 = new Score("Test", 50, 12345);
        Score score2 = new Score("Test", 40, 12345);
        assertTrue(Leaderboard.compareScores(score1, score2) > 0);
    }

    @Test
    public void testCompareScoresEqual() {
        // Aidan Test Case 2
        Score score1 = new Score("Test", 50, 23456);
        Score score2 = new Score("Test", 50, 12345);
        assertTrue(Leaderboard.compareScores(score1, score2) < 0);
    }
}