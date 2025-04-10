package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Grid {
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { // initialize and create a grid with all DOT objects
        this.size = size;
        grid = new Sprite[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = new Dot(col, size - 1 - row);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public Sprite[][] getGrid() {
        return grid;
    }

    public void placeSprite(Sprite s) { // place sprite in new spot
        int row = s.getRow(size);
        int col = s.getColumn(size);
        grid[row][col] = s;
    }

    public void placeSprite(Sprite s, String direction) { // place sprite in a new spot based on direction
        // clear the current position by placing a Dot
        int oldXVal = -1;
        int oldYVal = -1;
        // Move the sprite based on direction
        if (direction.equals("d")) {
            oldXVal = s.getX() - 1;
            oldYVal = s.getY();
        } else if (direction.equals("w")) {
            oldXVal = s.getX();
            oldYVal = s.getY() - 1;
        } else if (direction.equals("a")) {
            oldXVal = s.getX() + 1;
            oldYVal = s.getY();
        } else if (direction.equals("s")) {
            oldXVal = s.getX();
            oldYVal = s.getY() + 1;
        }
        // Place the sprite in its new position
        grid[size - 1 - oldYVal][oldXVal] = new Dot(oldXVal, oldYVal);
        placeSprite(s);
    }

    public void display() { // print out the current grid to the screen
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] instanceof Enemy) {
                    System.out.print("👿");
                } else if (grid[i][j] instanceof Player) {
                    System.out.print("🏃");
                } else if (grid[i][j] instanceof Dot) {
                    System.out.print("⬜");
                } else if (grid[i][j] instanceof Treasure && !(grid[i][j] instanceof Trophy)) {
                    System.out.print("💰");
                } else if (grid[i][j] instanceof Trophy) {
                    System.out.print("🏆");
                }
            }
            System.out.println();
        }
    }

    public void gameover(int r, int c) { // use this method to display a loss
        String[][] lose = new String[grid.length][grid.length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                lose[i][j] = "💀";
            }
        }
        lose[r][c] = "👦";
        for (int row = 0; row < lose.length; row++) {
            for (int column = 0; column < lose[0].length; column++) {
                System.out.print(lose[row][column]);
            }
            System.out.println();
        }
        System.out.println("You lose!");
    }

    public void win() { // use this method to display a win
        String[][] win = new String[grid.length][grid.length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                win[i][j] = "🏆";
            }
        }
        win[0][0] = "👦";
        for (int row = 0; row < win.length; row++) {
            for (int column = 0; column < win[0].length; column++) {
                System.out.print(win[row][column]);
            }
            System.out.println();
        }
        System.out.println("You win!");
    }
}