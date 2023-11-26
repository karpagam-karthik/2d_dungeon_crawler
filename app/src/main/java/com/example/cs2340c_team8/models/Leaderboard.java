// Package declaration
package com.example.cs2340c_team8.models;

// Import statements for required Java classes
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

// Declaration of the 'Leaderboard' class
public class Leaderboard {
    // Singleton instance of the Leaderboard class
    private static volatile Leaderboard leaderboard;
    
    // Root node of the binary search tree representing the leaderboard
    private LeaderboardNode root;
    
    // Number of scores in the leaderboard
    private int size;

    // Private constructor to initialize the leaderboard
    private Leaderboard() {
        root = null;
        size = 0;
    }

    // Singleton pattern: method to get an instance of the Leaderboard class
    public static Leaderboard getInstance() {
        if (leaderboard == null) {
            synchronized (Leaderboard.class) {
                if (leaderboard == null) {
                    leaderboard = new Leaderboard();
                }
            }
        }

        return leaderboard;
    }

    // Method to add a score to the leaderboard
    public void addScore(Score score) {
        if (score == null) {
            throw new IllegalArgumentException();
        }
        root = addScoreH(root, score);
    }

    // Helper method for recursively adding a score to the leaderboard
    private LeaderboardNode addScoreH(LeaderboardNode node, Score score) {
        if (node == null) {
            size++;
            return new LeaderboardNode(score);
        } else {
            long comparison = compareScores(score, node.getScore());
            if (comparison < 0) {
                node.setLeft(addScoreH(node.getLeft(), score));
            } else if (comparison > 0) {
                node.setRight(addScoreH(node.getRight(), score));
            }
            return node;
        }
    }

    // Method to retrieve the top N scores from the leaderboard
    public List<Score> getTopNScores(int limit) {
        ArrayList<Score> list = new ArrayList<>(size);
        getTopNScoresH(list, root, limit);
        return list;
    }

    // Helper method for recursively retrieving the top N scores from the leaderboard
    private void getTopNScoresH(ArrayList<Score> list, LeaderboardNode node, int limit) {
        if (node != null && list.size() < limit) {
            getTopNScoresH(list, node.getRight(), limit);
            list.add(node.getScore());
            getTopNScoresH(list, node.getLeft(), limit);
        }
    }

    // Method to retrieve the top score from the leaderboard
    public Score getTopScore() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return getTopNScores(1).get(0);
    }

    // Static method to compare two scores based on score value and timestamp
    public static long compareScores(Score score1, Score score2) {
        if (score1.getScore() - score2.getScore() == 0) {
            return score2.getTimeMilliseconds() - score1.getTimeMilliseconds();
        }
        return score1.getScore() - score2.getScore();
    }

    // Getter method to retrieve the root node of the leaderboard
    public LeaderboardNode getRoot() {
        return root;
    }

    // Getter method to retrieve the size (number of scores) of the leaderboard
    public int getSize() {
        return size;
    }
}
