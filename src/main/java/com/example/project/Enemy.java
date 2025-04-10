package com.example.project;

//Enemy that reduces player's lives
public class Enemy extends Sprite { //Constructor
    public Enemy(int x, int y) {
        super(x, y);
    }
    @Override
    public String getCoords() { // returns "Enemy:"+coordinates
        return "Enemy:" + super.getCoords();
    }

    @Override
    public String getRowCol(int size) { // return "Enemy:"+row col
        return "Enemy:" + super.getRowCol(size);
    }
}
