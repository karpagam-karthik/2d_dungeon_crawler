package com.example.cs2340c_team8.models.elements;

public class Wall {
    private float x;
    private float y;
    private int height;
    private int width;
    private Wall[] neighbors;

    public Wall(float x, float y, int height, int width, Wall[] neighbors) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.neighbors = neighbors;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Wall getLeftNeighbor() {
        return neighbors[0];
    }

    public Wall getFrontNeighbor() {
        return neighbors[1];
    }

    public Wall getRightNeighbor() {
        return neighbors[2];
    }

    public Wall getBackNeighbor() {
        return neighbors[3];
    }

    public void setLeftNeighbor(Wall wall) {
        neighbors[0] = wall;
    }

    public void setFrontNeighbor(Wall wall) {
        neighbors[1] = wall;
    }

    public void setRightNeighbor(Wall wall) {
        neighbors[2] = wall;
    }

    public void setBackNeighbor(Wall wall) {
        neighbors[3] = wall;
    }
}
