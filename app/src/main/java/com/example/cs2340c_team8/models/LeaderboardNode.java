package com.example.cs2340c_team8.models;

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
