package com.example.project;

public class Sprite {
    private int x, y;

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int newX) {
        x = newX;
    }

    public void setY(int newY) {
        y = newY;
    }

    public String getCoords() { // returns the coordinates of the sprite ->"(x,y)"
        return "(" + x + "," + y + ")";
    }

    public String getRowCol(int size) { // returns the row and column of the sprite -> "[row][col]"
        int column = x;
        int row = size - y - 1;
        return "[" + row + "]" + "[" + column + "]";
    }

    public int getRow(int size) {
        return size - y - 1;
    }

    public int getColumn(int size) {
        return x;
    }

    public boolean isValid(int size, String direction) { // check grid boundaries
        if (direction.equals("w")) {
            if (getY() == size - 1) {
                return false;
            } else {
                return true;
            }
        }
        if (direction.equals("a")) {
            if (getX() == 0) {
                return false;
            } else {
                return true;
            }
        }
        if (direction.equals("s")) {
            if (getY() == 0) {
                return false;
            } else {
                return true;
            }
        }
        if (direction.equals("d")) {
            if (getX() == size - 1) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public void move(String direction) {
    }

    public void interact() {
    }
}