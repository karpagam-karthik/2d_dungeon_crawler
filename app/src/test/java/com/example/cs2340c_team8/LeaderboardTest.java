package com.example.cs2340c_team8;

import static org.junit.Assert.assertTrue;

import com.example.cs2340c_team8.models.Leaderboard;
import com.example.cs2340c_team8.models.Score;

import org.junit.Test;
import java.util.List;

public class LeaderboardTest {
    @Test
    public void testEmptyLeaderboardGetSize() {
        // Akshara Test Case 1
        Leaderboard leaderboard = Leaderboard.getInstance();
        List<Score> returnedScores = leaderboard.getTopNScores(5);
        assertTrue(returnedScores.size() == 0);
    }

    @Test
    public void testLeaderboardGet() {
        // Akshara Test Case 2
        Leaderboard leaderboard = Leaderboard.getInstance();
        leaderboard.addScore(new Score("Test", 50, 12345));
        leaderboard.addScore(new Score("Test", 40, 23456));
        leaderboard.addScore(new Score("Test", 30, 34567));
        leaderboard.addScore(new Score("Test", 20, 45678));
        leaderboard.addScore(new Score("Test", 10, 56789));
        leaderboard.addScore(new Score("Test", 0, 0));

        List<Score> returnedScores = leaderboard.getTopNScores(5);
        assertTrue(returnedScores.size() == 5);

        assertTrue(returnedScores.get(0).getScore() == 50);
        assertTrue(returnedScores.get(1).getScore() == 40);
        assertTrue(returnedScores.get(2).getScore() == 30);
        assertTrue(returnedScores.get(3).getScore() == 20);
        assertTrue(returnedScores.get(4).getScore() == 10);
    }
}

