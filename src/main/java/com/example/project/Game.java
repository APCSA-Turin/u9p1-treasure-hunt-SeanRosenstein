package com.example.project;

import java.util.Scanner;

public class Game {
    private Grid grid;
    private Player player;
    private Enemy[] enemies; // instance variables
    private Treasure[] treasures;
    private Trophy trophy;
    private int size;

    public Game(int size) { // sets up the game by calling initialize and play

        this.size = size;
        grid = new Grid(size);
        initialize();
        play();
    }

    public static void clearScreen() { // clears the screen
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() { //method that actually runs the game
        Scanner scanner = new Scanner(System.in);
        boolean keepPlaying = true;

        while (keepPlaying && player.getLives() > 0 && !player.getWin()) { //if the player is continuing to play and lives are valid 

            clearScreen();
            grid.display();
            System.out.println("Player:" + player.getCoords());
            System.out.println("Player:" + player.getRowCol(size)); //These print statemnts get the coords and row col of player
            System.out.println("Treasure Collected: " + player.getTreasureCount() + "/" + treasures.length);
            System.out.println("Lives remaining: " + player.getLives());

            System.out.print("Enter a direction (w, a, s, d) or 'q' to exit: ");  //user input to move player
            String move = scanner.nextLine().toLowerCase();

            if (move.equals("q")) {
                keepPlaying = false;
            } else {
                boolean isValidInput = move.matches("[wasd]"); //makes sure the player entered a valid character to move
                boolean isValidMove = isValidInput && player.isValid(size, move);

                if (!isValidInput) {//asks the user to pick again if is invalid
                    System.out.println("Invalid input! Use w, a, s, or d.");
                } else if (!isValidMove) {
                    System.out.println("Can't move that direction!"); //If player tries to move off screen
                }

                if (isValidInput && isValidMove) {//gets new position of player
                    int newX = player.getX();
                    int newY = player.getY();

                    if (move.equals("a")) {
                        newX--;
                    } else if (move.equals("d")) {
                        newX++;                                  //changing coords of player depending on input
                    } else if (move.equals("w")) {
                        newY++;
                    } else if (move.equals("s")) {
                        newY--;
                    }

                    int row = size - newY - 1;
                    int col = newX;
                    Sprite target = grid.getGrid()[row][col];

                    if (target instanceof Trophy && player.getTreasureCount() != treasures.length) { //checks if user has all treasure and has touched trophy
                        System.out.println("You need all treasures first!"); 
                    } else {
                        player.interact(size, move, treasures.length, target);
                        player.move(move);
                        grid.placeSprite(player, move);

                        if (player.getWin()) { //checks if player has won

                            clearScreen();
                            grid.win();
                            keepPlaying = false;
                        } else if (player.getLives() <= 0) { //checks user lives
                            clearScreen();
                            int playerRow = player.getRow(size);
                            int playerCol = player.getColumn(size);
                            grid.gameover(playerRow, playerCol);
                            keepPlaying = false;
                        }
                    }
                }
                try {
                    Thread.sleep(1000); //pause to display message
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        scanner.close();
    }

    public void initialize() { //initializes grid
        player = new Player(0, 0);
        trophy = new Trophy(size - 1, size - 1); //places player and trophy

        treasures = new Treasure[2]; //places treasure
        treasures[0] = new Treasure(2, 2);
        treasures[1] = new Treasure(5, 7);

        enemies = new Enemy[2]; //places enemies
        enemies[0] = new Enemy(3, 5);
        enemies[1] = new Enemy(6, 2);

        grid.placeSprite(player);
        grid.placeSprite(trophy);
        for (Treasure t : treasures) {  //places all objects on grid
            grid.placeSprite(t);
        }
        for (Enemy e : enemies) {
            grid.placeSprite(e);
        }
    }

    public static void main(String[] args) {
        Game game = new Game(10); //method to run the game with a 10x10 grid
    }
}