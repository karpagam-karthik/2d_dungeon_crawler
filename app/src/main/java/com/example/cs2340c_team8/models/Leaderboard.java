package com.example.cs2340c_team8.models;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Leaderboard {
    private volatile static Leaderboard leaderboard;
    private LeaderboardNode root;
    private int size;

    private Leaderboard() {
        root = null;
        size = 0;
    }

    public static Leaderboard getLeaderboard() {
        if (leaderboard == null) {
            synchronized (Leaderboard.class) {
                if (leaderboard == null) {
                    leaderboard = new Leaderboard();
                }
            }
        }

        return leaderboard;
    }

    public void addScore(Score score) {
        if (score == null) {
            throw new IllegalArgumentException();
        }
        root = addScoreH(root, score);
    }

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

    public List<Score> getTopNScores(int limit) {
        ArrayList<Score> list = new ArrayList<>(size);
        getTopNScoresH(list, root, limit);
        return list;
    }

    private void getTopNScoresH(ArrayList<Score> list, LeaderboardNode node, int limit) {
        if (node != null && list.size() < limit) {
            getTopNScoresH(list, node.getRight(), limit);
            list.add(node.getScore());
            getTopNScoresH(list, node.getLeft(), limit);
        }
    }

    public Score getTopScore() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return getTopNScores(1).get(0);
    }

    public static long compareScores(Score score1, Score score2) {
        if (score1.getScore() - score2.getScore() == 0) {
            return score2.getTimeMilliseconds() - score1.getTimeMilliseconds();
        }
        return score1.getScore() - score2.getScore();
    }

    public LeaderboardNode getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }
}

class LeaderboardNode {
    private Score score;
    private LeaderboardNode left;
    private LeaderboardNode right;

    LeaderboardNode(Score score, LeaderboardNode right, LeaderboardNode left) {
        this.score = score;
        this.right = right;
        this.left = left;
    }

    LeaderboardNode(Score score) {
        this(score, null, null);
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public LeaderboardNode getLeft() {
        return left;
    }

    public void setLeft(LeaderboardNode left) {
        this.left = left;
    }

    public LeaderboardNode getRight() {
        return right;
    }

    public void setRight(LeaderboardNode right) {
        this.right = right;
    }
}