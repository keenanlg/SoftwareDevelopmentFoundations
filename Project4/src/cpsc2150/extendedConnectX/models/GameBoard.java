package cpsc2150.extendedConnectX.models;

import java.util.*;
import cpsc2150.extendedConnectX.models.BoardPosition;

/**
 * This class determines what characters are at certain positions and places tokens into the board using a 2D array for run speed.
 *
 * @author Keenan Grant
 * @section CPSC 2151 007
 * @invariant 0 {@code <=} row {@code <} MAX_NUM_ROWS AND 0 {@code <=} column {@code <=} MAX_NUM_COLUMNS
 * @invariant No tokens can be placed in a full column
 * @invariant No tokens can be placed outside of the board
 * @invariant No gaps between non-space positions
 * @correspondence minRow {@code <=} ROW {@code <=} maxRow
 * @correspondence minColumn {@code <=} COLUMN {@code <=} maxColumn
 * @correspondence minWin {@code <=} WIN {@code <=} maxWin
 * @correspondence self = board[0...maxRow][0...maxColumn]
 */
public class GameBoard extends AbsGameBoard implements IGameBoard{

    private int ROW = 0, COLUMN = 0, WIN = 0;
    private char[][] board;

    /**
     * Creates a 2-dimensional array of characters where position [0][0]
     * would be the bottom left of the board and [ROW - 1][COLUMN - 1] would be the top right of the board;
     * all with blank characters.
     * @param r The number of rows the board will have
     * @param c The number of columns the board will have
     * @param w The number of tokens in a row needed to win
     * @post [board[ROW][COLUMN] is created with empty characters] AND ROW = r, COLUMN = c, WIN = w
     */
    public GameBoard(int r, int c, int w) {
        ROW = r;
        COLUMN = c;
        WIN = w;
        board = new char[ROW][COLUMN];
        for (int row = 0; row < getNumRows(); row++) {
            for (int col = 0; col < getNumColumns(); col++) {
                board[row][col] = ' ';
            }
        }
    }

    /**
     * Returns what is in the GameBoard at position pos .
     * If no marker is there, it returns a blank space char.
     * @pre 0, 0 {@code <=} pos {@code <=} ROW - 1, COLUMN - 1
     * @param pos The BoardPosition coordinate to be checked
     * @post [The token that is at pos is returned, ' ' if there isn't a token there]
     * @return The character at position pos or ' ' if there is nothing there
     */
    public char whatsAtPos(BoardPosition pos) {
        return board[pos.getRow()][pos.getColumn()];
    }

    /**
     * Places the character p in column c. The token will be placed in
     * the lowest available row in column c.
     * @pre GameBoard instance exists and checkIfFree returns true for a specified column
     * @param p The character token
     * @param c The column that the token will be placed in
     * @post A valid token is placed in the lowest available row of the column
     */
    public void placeToken(char p, int c) {
        for (int row = 0; row < getNumRows(); row++) {
            if (board[row][c] == ' ') {
                board[row][c] = p;
                //Breaks the loop
                row = getNumRows();
            }
        }
    }

    /**
     * Returns the number of rows the board has
     * @post ROW = #ROW
     * @return ROW
     */
    public int getNumRows() {
        return ROW;
    }

    /**
     * Returns the minimum number of rows needed to play
     * @post minRow = #minRow
     * @return minRow
     */
    public int getMinRow() {
        return minRow;
    }

    /**
     * Returns the maximum number of rows that the board can have
     * @post maxRow = #maxRow
     * @return maxRow
     */
    public int getMaxRow() {
        return maxRow;
    }

    /**
     * Returns the number of columns the board has
     * @post COLUMN = #COLUMN
     * @return COLUMN
     */
    public int getNumColumns() {
        return COLUMN;
    }

    /**
     * Returns the minimum number of columns needed to play
     * @post minColumn = #minColumn
     * @return minColumn
     */
    public int getMinColumn() {
        return minColumn;
    }

    /**
     * Returns the maximum number of columns that the board can have
     * @post maxColumn = #maxColumn
     * @return maxColumn
     */
    public int getMaxColumn() {
        return maxColumn;
    }

    /**
     * Returns the number of tokens in a row needed to win
     * @post WIN = #WIN
     * @return WIN
     */
    public int getNumToWin() {
        return WIN;
    }

    /**
     * Returns the minimum number of tokens in a row to win
     * @post minWin = #minWin
     * @return minWin
     */
    public int getMinWin() {
        return minWin;
    }

    /**
     * Returns the maximum number of tokens in a row to win
     * @post maxWin = #maxWin
     * @return maxWin
     */
    public int getMaxWin() {
        return maxWin;
    }

    /**
     * Returns the minimum number of players needed to play
     * @post minPlayer = #minPlayer
     * @return minPlayer
     */
    public int getMinPlayer() {
        return minPlayer;
    }

    /**
     * Returns the maximum number of players that can play
     * @post maxPlayer = #maxPlayer
     * @return maxPlayer
     */
    public int getMaxPlayer() {
        return maxPlayer;
    }
}