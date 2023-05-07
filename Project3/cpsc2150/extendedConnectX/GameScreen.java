package cpsc2150.extendedConnectX;

import cpsc2150.extendedConnectX.models.BoardPosition;
import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.GameBoardMem;

import java.util.*;

/**
 * @author Keenan Grant
 * @section CPSC 2150 001
 */

public class GameScreen {
    public static void main(String args[]) {
        Scanner scnr = new Scanner(System.in);
        int numInput, playerNum, rows, columns, win;
        char charInput = 'Y', gameType;
        String input;
        GameBoard test = new GameBoard(0, 0, 0);

        while (charInput == 'Y' || charInput == 'y') {
            playerNum = 0;
            rows = 0;
            columns = 0;
            win = 0;
            gameType = 'x';
            boolean gameOver = false;
            int loop = 0;

            //Testing player number input
            while (!(playerNum >= test.getMinPlayer() && playerNum <= test.getMaxPlayer())) {
                System.out.println("How many players?");
                input = scnr.nextLine();
                playerNum = Integer.parseInt(input);
                if (playerNum < test.getMinPlayer()) {
                    System.out.println("Must be at least two players");
                }
                else if (playerNum > test.getMaxPlayer()) {
                    System.out.println("Must be 10 players or fewer");
                }
            }

            //Creating player tokens
            char[] players = new char[playerNum];
            for (int i = 0; i < playerNum; i++) {
                System.out.println("Enter the character to represent player " + (i + 1));
                input = scnr.nextLine();
                players[i] = Character.toUpperCase(input.charAt(0));
                for (int k = 0; k < i; k++) {
                    if (players[i] == players[k]) {
                        System.out.println(players[i] + " is already taken as a player token!");
                        //Stops loop
                        k = i;
                        //Resets player for prompt
                        i--;
                    }
                }
            }

            //Getting rows
            while (rows < test.getMinRow() || rows > test.getMaxRow()) {
                System.out.println("How many rows should be on the board?");
                input = scnr.nextLine();
                rows = Integer.parseInt(input);
                if (rows < test.getMinRow()) {
                    System.out.println("Rows should be at least " + test.getMinRow());
                }
                else if (rows > test.getMaxRow()) {
                    System.out.println("Rows can not be greater than " + test.getMaxRow());
                }
            }

            //Getting columns
            while (columns < test.getMinColumn() || columns > test.getMaxColumn()) {
                System.out.println("How many columns should be on the board?");
                input = scnr.nextLine();
                columns = Integer.parseInt(input);
                if (columns < test.getMinColumn()) {
                    System.out.println("Columns should be at least " + test.getMinColumn());
                }
                else if (columns > test.getMaxColumn()) {
                    System.out.println("Columns can not be greater than " + test.getMaxColumn());
                }
            }

            //Getting win number
            while (win > test.getMaxWin() || win < test.getMinWin() || win > rows || win > columns) {
                System.out.println("How many in a row to win?");
                input = scnr.nextLine();
                win = Integer.parseInt(input);
                if (win > test.getMaxWin()) {
                    System.out.println("Number must be no greater than " + test.getMaxWin());
                }
                else if (win < test.getMinWin()) {
                    System.out.println("Number must be greater than " + test.getMinWin());
                }
                else if (win > rows) {
                    System.out.println("Number must be no greater than the rows of the board");
                }
                else if (win > columns) {
                    System.out.println("Number must be no greater than the columns of the board");
                }
            }

            //Choosing game type
            while (gameType != 'F' && gameType != 'f' && gameType != 'M' && gameType != 'm') {
                System.out.println("Would you like a Fast game (F/f) or a Memory Efficient game (M/m)?");
                input = scnr.nextLine();
                gameType = input.charAt(0);
                if (gameType != 'F' && gameType != 'f' && gameType != 'M' && gameType != 'm') {
                    System.out.println("Please enter F or M");
                }
            }

            //Fast game
            if (gameType == 'F' || gameType == 'f') {
                GameBoard fast = new GameBoard(rows, columns, win);
                while (!gameOver) {
                    //Resetting player cycle
                    if (loop == playerNum) {
                        loop = 0;
                    }

                    //Prints the board before placement
                    System.out.println(fast.toString());

                    //Prompts the player
                    System.out.println("Player " + players[loop] + ", what column do you want to place your marker in?");
                    input = scnr.nextLine();
                    numInput = Integer.parseInt(input);

                    //Testing column input
                    while (numInput < 0 || numInput >= fast.getNumColumns()) {
                        if (numInput < 0) {
                            System.out.println("Column cannot be less than 0");
                        }
                        else {
                            System.out.println("Column cannot be greater than " + (fast.getNumColumns() - 1));
                        }
                        System.out.println("Player " + players[loop] + ", what column do you want to place your marker in?");
                        input = scnr.nextLine();
                        numInput = Integer.parseInt(input);
                    }

                    //Token placement
                    if (fast.checkIfFree(numInput)) {
                        fast.placeToken(players[loop], numInput);
                    }
                    else {
                        System.out.println("Column is full");
                    }

                    //Check win
                    if (fast.checkForWin(numInput) || fast.checkTie()) {
                        gameOver = true;
                    }

                    //Next player
                    loop++;
                }

                //Prints the board and result after game ends
                System.out.println(fast.toString());
                if (!fast.checkTie()) {
                    System.out.println("Player " + players[loop - 1] + " Won!");
                }
                else {
                    System.out.println("This game is a tie!");
                }

            }

            //Memory efficient game
            else {
                GameBoardMem mem = new GameBoardMem(rows, columns, win);
                while (!gameOver) {
                    //Resetting player cycle
                    if (loop == playerNum) {
                        loop = 0;
                    }

                    //Prints the board before placement
                    System.out.println(mem.toString());
    
                    //Prompts the player
                    System.out.println("Player " + players[loop] + ", what column do you want to place your marker in?");
                    input = scnr.nextLine();
                    numInput = Integer.parseInt(input);
    
                    //Testing column input
                    while (numInput < 0 || numInput >= mem.getNumColumns()) {
                     if (numInput < 0) {
                         System.out.println("Column cannot be less than 0");
                     }
                     else {
                         System.out.println("Column cannot be greater than " + (mem.getNumColumns() - 1));
                     }
                     System.out.println("Player " + players[loop] + ", what column do you want to place your marker in?");
                     input = scnr.nextLine();
                     numInput = Integer.parseInt(input);
                    }
    
                    //Token placement
                    if (mem.checkIfFree(numInput)) {
                     mem.placeToken(players[loop], numInput);
                    }
                    else {
                     System.out.println("Column is full");
                    }

                    //Check win
                    if (mem.checkForWin(numInput) || mem.checkTie()) {
                     gameOver = true;
                    }

                    //Next player
                    loop++;
                }
    
                //Prints the board and result after game ends
                System.out.println(mem.toString());
                if (!mem.checkTie()) {
                    System.out.println("Player " + players[loop - 1] + " Won!");
                }
                else {
                    System.out.println("This game is a tie!");
                }

            }
            //Prompt for another game
            do {
                System.out.println("Would you like to play again? Y/N");
                input = scnr.nextLine();
                charInput = input.charAt(0);
            } while (charInput != 'Y' && charInput != 'y' && charInput != 'n' && charInput != 'N');
        }
    }
}