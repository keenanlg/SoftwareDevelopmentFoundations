package cpsc2150.extendedConnectX.models;

import cpsc2150.extendedConnectX.models.BoardPosition;

/**
 * This class keeps track of one cell of the cpsc2150.extendedConnectX.models.GameBoard. Has the rows and column variables.
 *
 * @author Keenan Grant
 * @section CPSC 2151 007
 * @invariant 0 <= row < MAX_NUM_ROWS AND 0 <= column < MAX_NUM_COLUMNS
 * @invariant No tokens can be placed in a full column
 * @invariant No tokens can be placed outside of the board
 * @invariant No gaps between non-space positions
 */
public class GameBoard extends AbsGameBoard implements IGameBoard{

    private char[][] board = new char[MAX_ROW][MAX_COLUMN];

    /**
     * Creates a 2-dimensional array of characters where position [0][0]
     * would be the bottom left of the board and [8][6] would be the top right of the board;
     * all with blank characters.
     * @post [board[MAX_ROW][MAX_COLUMN] is created with empty characters] AND row = MAX_ROW AND col = MAX_COLUMN
     */
    public GameBoard() {
        for (int row = 0; row < MAX_ROW; row++) {
            for (int col = 0; col < MAX_COLUMN; col++) {
                board[row][col] = ' ';
            }
        }
    }

    /**
     * Returns true if the column can accept another token; false
     * otherwise.
     * @pre 0 <= c < MAX_COLUMN_NUM
     * @param c The column that the token aims to be placed in
     * @post iff board[MAX_ROW_NUM - 1][c] = ' ' AND board = #board
     * @return True if the column is free, false otherwise
     */
    public boolean checkIfFree(int c) {
        if (board[getNumRows() - 1][c] == ' ') {
            return true;
        }
        return false;
    }

    /**
     * Returns what is in the GameBoard at position pos .
     * If no marker is there, it returns a blank space char.
     * @pre <0, 0> <= pos <= <8, 6>
     * @param pos The BoardPosition coordinate to be checked
     * @post [The token that is at pos is returned, ' ' if there isn't a token there]
     * @return X if there is an X there, O if there is a O there, and ' ' if there is nothing there
     */
    public char whatsAtPos(BoardPosition pos) {
        return board[pos.getRow()][pos.getColumn()];
    }

    /**
     * Places the character p in column c. The token will be placed in
     * the lowest available row in column c.
     * @pre GameBoard instance exists and checkIfFree returns true for a specified column
     * @param p The character token; either X or O
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
     * This function will check to see if the last token placed in
     * column c resulted in the player winning the game. If so it will return
     * true, otherwise false.
     * @pre [GameBoard instance exists and a token has been placed in column c]
     * @param c The column that the token was placed in
     * @post [Returns true or false if checkHorizWin, checkVertWin, or checkDiagWin returns true] AND board = #board
     * @return True if the player who placed the token won, false otherwise
     */
    public boolean checkForWin(int c) {
        if (checkIfFree(c)) {
            for (int row = getNumRows() - 2; row >= 0; row--) {
                if (board[row][c] != ' ') {
                    BoardPosition pos = new BoardPosition(row, c);
                    if (checkHorizWin(pos, whatsAtPos(pos)) || checkVertWin(pos, whatsAtPos(pos)) || checkDiagWin(pos, whatsAtPos(pos))) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            }
        }
        else {
            BoardPosition pos = new BoardPosition(getNumRows() - 1, c);
            if (checkHorizWin(pos, whatsAtPos(pos)) || checkVertWin(pos, whatsAtPos(pos)) || checkDiagWin(pos, whatsAtPos(pos))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the number of rows the board has
     * @post MAX_ROW = #MAX_ROW
     * @return MAX_ROW
     */
    public int getNumRows() {
        return MAX_ROW;
    }

    /**
     * Returns the number of columns the board has
     * @post MAX_COLUMN = #MAX_COLUMN
     * @return MAX_COLUMN
     */
    public int getNumColumns() {
        return MAX_COLUMN;
    }

    /**
     * Returns the number of tokens in a row needed to win
     * @post WIN = #WIN
     * @return WIN
     */
    public int getNumToWin() {
        return WIN;
    }
}
