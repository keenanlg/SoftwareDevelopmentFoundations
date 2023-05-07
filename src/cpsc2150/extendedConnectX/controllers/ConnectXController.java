package cpsc2150.extendedConnectX.controllers;

import cpsc2150.extendedConnectX.models.*;
import cpsc2150.extendedConnectX.views.*;

/**
 * The controller class will handle communication between our View and our Model ({@link IGameBoard})
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your {@link BoardPosition} class, {@link IGameBoard} interface
 * and both of the {@link IGameBoard} implementations from Project 4.
 * If your code was correct you will not need to make any changes to your {@link IGameBoard} implementation class.
 *
 * @version 2.0
 */
public class ConnectXController {

    /**
     * <p>
     * The current game that is being played
     * </p>
     */
    private IGameBoard curGame;

    /**
     * <p>
     * The screen that provides our view
     * </p>
     */
    private ConnectXView screen;

    /**
     * <p>
     * Constant for the maximum number of players.
     * </p>
     */
    public static final int MAX_PLAYERS = 10;
    
    /**
     * <p>
     * The number of players for this game. Note that our player tokens are hard coded.
     * </p>
     */
    private int numPlayers;

    private boolean reset, full;
    private int turn;
    private char[] players;

    /**
     * <p>
     * This creates a controller for running the Extended ConnectX game
     * </p>
     * 
     * @param model
     *      The board implementation
     * @param view
     *      The screen that is shown
     * @param np
     *      The number of players for this game.
     * 
     * @post [ the controller will respond to actions on the view using the model. ]
     */
    public ConnectXController(IGameBoard model, ConnectXView view, int np) {
        this.curGame = model;
        this.screen = view;
        this.numPlayers = np;

        // Some code is needed here.
        players = new char[]{'X', 'O', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        reset = false;
        turn = 0;
    }

    /**
     * <p>
     * This processes a button click from the view.
     * </p>
     * 
     * @param col 
     *      The column of the activated button
     * 
     * @post [ will allow the player to place a token in the column if it is not full, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button ]
     */
    public void processButtonClick(int col) {
        // Your code goes here.
        full = false;
        //Resets the game
        if (reset) {
            reset = false;
            newGame();
        }
        else {
            //Places the token
            if (curGame.checkIfFree(col)) {
                //Retrieving the row of the token
                int row = 0;
                BoardPosition pos = new BoardPosition(row, col);
                while (curGame.whatsAtPos(pos) != ' ') {
                    pos = new BoardPosition(++row, col);
                }
                //Token placed and screen market set
                curGame.placeToken(players[turn], col);
                screen.setMarker(pos.getRow(), col, players[turn]);
                //Checking win and tie
                if (curGame.checkForWin(col)) {
                    screen.setMessage("Player " + players[turn] + " Won!");
                    reset = true;
                }
                else if (curGame.checkTie()) {
                    screen.setMessage("This game is a tie!");
                    reset = true;
                }
                turn++;
                //Resets the player array for token placement
                if (turn == numPlayers) {
                    turn = 0;
                }
            }
            else {
                screen.setMessage("Column is full");
                full = true;
            }
        }
        if (!reset && !full) {
            screen.setMessage("It is " + players[turn] + "'s turn. ");
        }
    }

    /**
     * <p>
     * This method will start a new game by returning to the setup screen and controller
     * </p>
     * 
     * @post [ a new game gets started ]
     */
    private void newGame() {
        //close the current screen
        screen.dispose();
        
        //start back at the set up menu
        SetupView screen = new SetupView();
        SetupController controller = new SetupController(screen);
        screen.registerObserver(controller);
    }
}