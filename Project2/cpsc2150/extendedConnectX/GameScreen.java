package cpsc2150.extendedConnectX;

import cpsc2150.extendedConnectX.models.GameBoard;

import java.util.*;

public class GameScreen {
    public static void main(String args[]) {
        Scanner scnr = new Scanner(System.in);
        int numInput, loop = 1;
        //Integer numInput;
        char charInput = 'Y', thePlayer;
        String input;
        boolean won = false;
        GameBoard game = new GameBoard();

    while (charInput == 'Y' || charInput == 'y') {
        while (!won) {
            //Determines which player will be prompted
            if (loop % 2 == 0) {
                thePlayer = 'O';
            } else {
                thePlayer = 'X';
            }
            loop++;

            //Prints the board before placement
            System.out.println(game.toString());

            //Prompts the player
            System.out.println("Player " + thePlayer + ", what column do you want to place your marker in?");
            input = scnr.nextLine();
            numInput = Integer.parseInt(input);

            //Testing
            while (numInput < 0 || numInput >= game.getNumColumns()) {
                if (numInput < 0) {
                    System.out.println("Column cannot be less than 0");
                } else {
                    System.out.println("Column cannot be greater than " + (game.getNumColumns() - 1));
                }
                System.out.println("Player " + thePlayer + ", what column do you want to place your marker in?");
                input = scnr.nextLine();
                numInput = Integer.parseInt(input);
            }

            if (game.checkIfFree(numInput)) {
                game.placeToken(thePlayer, numInput);
                if (game.checkForWin(numInput)) {
                    won = true;
                } else if (game.checkTie()) {
                    won = true;
                }
            } else {
                System.out.println("Column is full");
            }

            //Prints the board after placement
            game.toString();

        }
        if (loop % 2 == 0) {
            thePlayer = 'O';
        } else {
            thePlayer = 'X';
        }

        if (!game.checkTie()) {
            System.out.println("Player " + thePlayer + " Won!");
        }
        else {
            System.out.println("This game is a tie!");
        }

        System.out.println("Would you like to play again? Y/N");
        input = scnr.nextLine();
        charInput = input.charAt(0);
        }
    }
}
