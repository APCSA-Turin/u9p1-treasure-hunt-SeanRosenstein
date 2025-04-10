package com.example.project;

//Trophy is the final goal after collecting all treasures
public class Trophy extends Treasure {
    public Trophy(int x, int y) {
        super(x, y);
    }

    @Override
    public String getCoords() {
        return "Trophy:" + super.getCoords();
    }

    @Override
    public String getRowCol(int size) {
        return "Trophy:" + super.getRowCol(size);
    }

    @Override
    public String toString() {
        return "🏆";
    }
}
