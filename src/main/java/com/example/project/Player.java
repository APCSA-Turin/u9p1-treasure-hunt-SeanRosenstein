package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite {
    private int treasureCount;
    private int numLives;
    private boolean win;

    public Player(int x, int y) { // set treasureCount = 0 and numLives = 2
        super(x, y);
        treasureCount = 0;
        numLives = 2;
        win = false;
    }

    public int getTreasureCount() {
        return treasureCount;
    }

    public int getLives() {
        return numLives;
    }

    public void setLives(int val) { // getters and setters
        numLives = val;
    }

    public boolean getWin() {
        return win;
    }

    public void setWin(boolean b) {
        win = b;
    }

    @Override
    public String getCoords() { // returns "Player:"+coordinates
        return "Player:" + super.getCoords();
    }

    @Override
    public String getRowCol(int size) { // return "Player:"+row col
        return "Player:" + super.getRowCol(size);
    }

    @Override
    // move method should override parent class, sprite
    public void move(String direction) { // move the (x,y) coordinates of the player
        if (direction.equals("w")) {
            setY(getY() + 1);
        }
        if (direction.equals("s")) {
            setY(getY() - 1);
        }
        if (direction.equals("a")) {
            setX(getX() - 1);
        }
        if (direction.equals("d")) {
            setX(getX() + 1);
        }
    }

    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with object in the
                                                                                     // place you moved to
        if (isValid(size, direction)) {
            if (obj instanceof Enemy) {
                numLives--;
            } else if (obj instanceof Treasure) {
                if (obj instanceof Trophy) {
                    if (treasureCount == numTreasures) {
                        win = true;
                    }
                } else {
                    treasureCount++;
                }
            }
        }
    }

    public boolean isValid(int size, String direction) { // check grid boundaries
        if (direction.equals("w")) {
            if (getY() < size - 1) {
                return true;
            } else {
                return false;
            }
        }
        if (direction.equals("a")) {
            if (getX() > 0) {
                return true;
            } else {
                return false;
            }
        }
        if (direction.equals("s")) {
            if (getY() > 0) { // makes sure player doesn't go off screen
                return true;
            } else {
                return false;
            }
        }
        if (direction.equals("d")) {
            if (getX() < size - 1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
