package cpsc2150.extendedConnectX.models;

import java.util.*;

/**
 * This class determines what characters are at certain positions and places tokens into the board using a map for memory efficiency.
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
 * @correspondence self = Map<Character, List<BoardPosition>> board
 */

public class GameBoardMem extends AbsGameBoard implements IGameBoard{

    private int ROW = 0, COLUMN = 0, WIN = 0;
    private Map<Character, List<BoardPosition>> board;

    /**
     * The key field of our Map will be a Character that represents the player so
     * that each player will get their own entry in the Map. The value associated with that player will be a
     * List of BoardPositions that the player occupies on the board. If a player has not placed a token
     * yet, then there will be no key or List in the Map to represent that player. 
     * @param r The number of rows the board will have
     * @param c The number of columns the board will have
     * @param w The number of tokens in a row needed to win
     * @post [board map is created with key value pair of characters and lists of BoardPositions] AND ROW = r, COLUMN = c, WIN = w
     */
    public GameBoardMem(int r, int c, int w) {
      board = new HashMap<>();
      ROW = r;
      COLUMN = c;
      WIN = w;
    }

    /**
     * Returns what is in the GameBoard at position pos .
     * If no marker is there, it returns a blank space char.    
     * @pre 0, 0 {@code <=} pos {@code <=} ROW - 1, COLUMN - 1
     * @param pos The BoardPosition coordinate to be checked
     * @post [The token that is at pos is returned, ' ' if there isn't a token there]
     * @return Character token of pos or ' ' if there is nothing there
     */
    public char whatsAtPos(BoardPosition pos) {
        for (Map.Entry<Character, List<BoardPosition>> m: board.entrySet()) {
            for (int i = 0; i < m.getValue().size(); i++) {
                if (pos.equals(m.getValue().get(i))) {
                    return m.getKey();
                }
            }
        }
        return ' ';
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
        for (int r = 0; r < getNumColumns(); r++) {
            BoardPosition pos = new BoardPosition(r, c);
            if (whatsAtPos(pos) == ' ') {
                if (board.containsKey(p)) {
                    board.get(p).add(pos);
                }
                else {
                    List<BoardPosition> newList = new ArrayList<>();
                    newList.add(pos);
                    board.put(p, newList);
                }
                //Breaks the loop
                r = getNumColumns();
            }
        }
    }

    /**
     * Returns true if the player is at pos; otherwise, it returns false
     * @pre 0 {@code <=} pos.getRow() {@code <} MAX_ROW AND 0 {@code <=} pos.getColumn() {@code <} MAX_COLUMN
     * @param pos The BoardPosition coordinate to be checked
     * @param player The player who is getting checked if that is their position
     * @post True or false if the specified player is at pos AND gameBoard = #gameBoard
     * @return True if the player does have a token in the spot that is getting checked, false otherwise
     */
    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        if (board.containsKey(player)) {
            if (board.get(player).contains(pos)) {
                return true;
            }
        }
        return false;
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
     * Returns the number of columns the board has
     * @post COLUMN = #COLUMN
     * @return COLUMN
     */
    public int getNumColumns() {
        return COLUMN;
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