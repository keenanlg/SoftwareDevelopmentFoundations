package cpsc2150.extendedConnectX.models;
import java.util.*;

/**
 * This interface contains the boundaries of the game variables and the methods for certain situations of the game
 *
 * @author Keenan Grant
 * @section CPSC 2151 007
 * @initialization A game board of maxRow by maxColumn or smaller
 * @define minPlayer: Z, Minimum number of players required to play the game
 *         maxPlayer: Z, Maximum number of players that can play the game
 *         minRow: Z, Minimum number of rows the board needs
 *         maxRow: Z, Maximum number of rows the board can have
 *         minColumn: Z, Minimum number of columns the board needs
 *         maxColumn: Z, Maximum number of columns the board can have
 *         minWin: Z, Minimum number of tokens in a row needed for a player to win the game
 *         maxWin: Z, Maximum number of tokens in a row that can be reached for a player to win the game
 * @constraints minPlayer {@code <=} playerNum {@code <} maxPlayer
 *             minRow {@code <=} rowNum {@code <} maxRow
 *             minColumn {@code <=} columnNum {@code <} maxColumn
 *             minWin {@code <=} winNum {@code <} maxWin
 */

public interface IGameBoard {

    public static final int minPlayer = 2, maxPlayer = 10, minRow = 3, maxRow = 100, minColumn = 3, maxColumn = 100, minWin = 3, maxWin = 25;

    public int getNumRows();
    public int getNumColumns();
    public int getNumToWin();
    public char whatsAtPos(BoardPosition pos);
    public void placeToken(char p, int c);

    /**
     * Returns true if the player is at pos; otherwise, it returns false
     * @pre 0 {@code <=} pos.getRow() {@code <} maxRow AND 0 {@code <=} pos.getColumn() {@code <} maxColumn
     * @param pos The BoardPosition coordinate to be checked
     * @param player The player who is getting checked if that is their position
     * @post [True or false if the specified player is at pos] AND gameBoard = #gameBoard
     * @return True if the player does have a token in the spot that is getting checked, false otherwise
     */
    default public boolean isPlayerAtPos(BoardPosition pos, char player) {
        if (whatsAtPos(pos) == player) {
            return true;
        }
        return false;
    }

    /**
     * This function will check to see if the game has resulted in a
     * tie. A game is tied if there are no free board positions remaining.
     * You do not need to check for any potential wins because we can assume
     * that the players were checking for win conditions as they played the
     * game. It will return true if the game is tied and false otherwise.
     * @pre GameBoard instance exists and there has not been a winner
     * @post [True or false if the all positions on the board are empty] and board = #board
     * @return True if there are no free spaces on the board, false otherwise
     */
    default public boolean checkTie() {
        for (int col = 0; col < getNumColumns(); col++) {
            BoardPosition pos = new BoardPosition(getNumRows() - 1, col);
            if (whatsAtPos(pos) == ' ') {
                return false;
            }
        }
        if (!checkForWin(getNumColumns() - 1)) {
            return true;
        }
        return false;
    }

    /**
     * Checks to see if the last token placed (which was placed in
     * position pos by player p) resulted in the desired number in a row horizontally. Returns
     * true if it does, otherwise false
     * @pre [Player p was the last to place their token and it is at pos] AND 0 {@code <=} pos.getRow() {@code <} MAX_ROW AND 0 {@code <=} pos.getColumn() {@code <} MAX_COLUMN
     * @param pos The position of the placed token
     * @param p The player who placed the token
     * @post [True or false if player p places a token that equals the number in a row needed to win] AND board = #board
     * @return True if player p won horizontally, false otherwise
     */
    default public boolean checkHorizWin(BoardPosition pos, char p) {
        int count = 1;
        boolean reverse = false;
        for (int c = pos.getColumn() + 1; c < getNumColumns() && !reverse; c++) {
            BoardPosition test = new BoardPosition(pos.getRow(), c);
            if (isPlayerAtPos(test, p)) {
                count++;
                if (count == getNumToWin()) {
                    return true;
                }
            }
            else {
                reverse = true;
            }
        }
        //Left direction
        for (int c = pos.getColumn() - 1; c >= 0; c--) {
            BoardPosition test = new BoardPosition(pos.getRow(), c);
            if (isPlayerAtPos(test, p)) {
                count++;
                if (count == getNumToWin()) {
                    return true;
                }
            }
            else {
                return false;
            }
        }
        return false;
    }

    /**
     * Checks to see if the last token placed (which was placed in
     * position pos by player p) resulted in the desired number in a row vertically. Returns
     * true if it does, otherwise false
     * @pre [Player p was the last to place their token and it is at pos] AND 0 {@code <=} pos.getRow() {@code <} MAX_ROW AND 0 {@code <=} pos.getColumn() {@code <} MAX_COLUMN
     * @param pos The position of the placed token
     * @param p The player who placed the token
     * @post [True or false if player p places a token that equals the number in a row needed to win] AND board = #board
     * @return True if player p won vertically, false otherwise
     */
    default public boolean checkVertWin(BoardPosition pos, char p) {
        int count = 1;
        for (int r = pos.getRow() - 1; r >= 0; r--) {
            BoardPosition test = new BoardPosition(r, pos.getColumn());
            if (isPlayerAtPos(test, p)) {
                count++;
                if (count == getNumToWin()) {
                    return true;
                }
            }
            else {
                return false;
            }
        }
        return false;
    }

    /**
     * Checks to see if the last token placed (which was placed in
     * position pos by player p) resulted in the desired number in a row diagonally. Returns
     * true if it does, otherwise false
     * @pre  [Player p was the last to place their token and it is at pos] AND 0 {@code <=} pos.getRow() {@code <} MAX_ROW AND 0 {@code <=} pos.getColumn() {@code <} MAX_COLUMN
     * @param pos The position of the placed token
     * @param p The player who placed the token
     * @post [True or false if player p places a token that equals the number in a row needed to win] AND board = #board
     * @return True if player p won diagonally, false otherwise
     */
    default public boolean checkDiagWin(BoardPosition pos, char p) {
        int count = 1;
        boolean reverse = false, opposite = false;
        //Northeast
        for (int r = pos.getRow() + 1, c = pos.getColumn() + 1; r < getNumRows() && c < getNumColumns() && !reverse; r++, c++) {
            BoardPosition test = new BoardPosition(r, c);
            if (isPlayerAtPos(test, p)) {
                count++;
                if (count == getNumToWin()) {
                    return true;
                }
            }
            else {
                reverse = true;
            }
        }
        //Southwest
        for (int r = pos.getRow() - 1, c = pos.getColumn() - 1; r >= 0 && c >= 0 && !opposite; r--, c--) {
            BoardPosition test = new BoardPosition(r, c);
            if (isPlayerAtPos(test, p)) {
                count++;
                if (count == getNumToWin()) {
                    return true;
                }
            }
            else {
                opposite = true;
            }
        }
        //Resetting variables for the other diagonal to be checked
        count = 1;
        reverse = false;
        //Southeast
        for (int r = pos.getRow() - 1, c = pos.getColumn() + 1; r >= 0 && c < getNumColumns() && !reverse; r--, c++) {
            BoardPosition test = new BoardPosition(r, c);
            if (isPlayerAtPos(test, p)) {
                count++;
                if (count == getNumToWin()) {
                    return true;
                }
            }
            else {
                reverse = true;
            }
        }
        //Northwest
        for (int r = pos.getRow() + 1, c = pos.getColumn() - 1; r < getNumRows() && c >= 0; r++, c--) {
            BoardPosition test = new BoardPosition(r, c);
            if (isPlayerAtPos(test, p)) {
                count++;
                if (count == getNumToWin()) {
                    return true;
                }
            }
            else {
                return false;
            }
        }
        return false;
    }

    /**
     * This function will check to see if the last token placed in
     * column c resulted in the player winning the game. If so it will return
     * true, otherwise false.
     * @pre [GameBoard instance exists and a token has been placed in column c]
     * @param c [The column that the token was placed in]
     * @post [Returns true or false if checkHorizWin, checkVertWin, or checkDiagWin returns true] AND board = #board
     * @return True if the player who placed the token won, false otherwise
     */
    default public boolean checkForWin(int c) {
        for (int row = getNumRows() - 1; row >= 0; row--) {
            BoardPosition pos = new BoardPosition(row, c);
            if (whatsAtPos(pos) != ' ') {
                if (checkHorizWin(pos, whatsAtPos(pos)) || checkVertWin(pos, whatsAtPos(pos)) || checkDiagWin(pos, whatsAtPos(pos))) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if the column can accept another token; false
     * otherwise.
     * @pre 0 {@code <=} c {@code <} COLUMN
     * @param c The column that the token aims to be placed in
     * @post iff board[ROW - 1][c] = ' ' AND board = #board
     * @return True if the column is free, false otherwise
     */
    default public boolean checkIfFree(int c) {
        BoardPosition pos = new BoardPosition(getNumRows() - 1, c);
        if (whatsAtPos(pos) == ' ') {
            return true;
        }
        return false;
    }
}