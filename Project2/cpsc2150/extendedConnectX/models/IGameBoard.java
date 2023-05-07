package cpsc2150.extendedConnectX.models;

public interface IGameBoard {

    public static final int MAX_ROW = 9, MAX_COLUMN = 7, WIN = 5;

    public int getNumRows();
    public int getNumColumns();
    public int getNumToWin();

    public boolean checkIfFree(int c);

    public char whatsAtPos(BoardPosition pos);

    public void placeToken(char p, int c);

    public boolean checkForWin(int c);

    /**
     * Returns true if the player is at pos; otherwise, it returns false
     * @pre 0 <= pos.getRow() < MAX_ROW AND 0 <= pos.getColumn() < MAX_COLUMN
     * @param pos The BoardPosition coordinate to be checked
     * @param player The player who is getting checked if that is their position
     * @post True or false if the specified player is at pos AND gameBoard = #gameBoard
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
     * @post True or false if the all positions on the board are empty
     * @return True if there are no free spaces on the board, false otherwise
     */
    default public boolean checkTie() {
        for (int col = 0; col < getNumColumns(); col++) {
            BoardPosition pos = new BoardPosition(getNumRows() - 1, col);
            if (whatsAtPos(pos) == ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks to see if the last token placed (which was placed in
     * position pos by player p) resulted in 5 in a row horizontally. Returns
     * true if it does, otherwise false
     * @pre [Player p was the last to place their token and it is at pos] AND 0 <= pos.getRow() < MAX_ROW AND 0 <= pos.getColumn() < MAX_COLUMN
     * @param pos The position of the placed token
     * @param p The player who placed the token
     * @post [True or false if player p places a token that equals the max number in a row needed to win horizontally] AND gameBoard = #gameBoard
     * @return True if player p won horizontally, false otherwise
     */
    default public boolean checkHorizWin(BoardPosition pos, char p) {
        int count = 1;
        boolean reverse = false, loop = true;
        //If pos is on the edge of the board
        if (pos.getColumn() == getNumColumns() - 1) {
            for (int c = getNumColumns() - 2; c >= 0; c--) {
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
        }
        //If pos is not on the edge of the board
        else {
            int c = pos.getColumn() + 1;
            BoardPosition test = new BoardPosition(pos.getRow(), c);
            while (loop) {
                if (isPlayerAtPos(test, p)) {
                    count++;
                    if (count == getNumToWin()) {
                        return true;
                    } else {
                        if (c == getNumColumns() - 1) {
                            reverse = true;
                            c = pos.getColumn() - 1;
                        } else {
                            if (!reverse) {
                                c++;
                            } else {
                                c--;
                            }
                        }
                        test = new BoardPosition(pos.getRow(), c);
                    }
                }
                else {
                    loop = false;
                }
            }
        }
        return false;
    }

    /**
     * Checks to see if the last token placed (which was placed in
     * position pos by player p) resulted in 5 in a row vertically. Returns
     * true if it does, otherwise false
     * @pre [Player p was the last to place their token and it is at pos] AND 0 <= pos.getRow() < MAX_ROW AND 0 <= pos.getColumn() < MAX_COLUMN
     * @param pos The position of the placed token
     * @param p The player who placed the token
     * @post [True or false if player p places a token that equals the max number in a row needed to win vertically] AND gameBoard = #gameBoard
     * @return True if player p won vertically, false otherwise
     */
    default public boolean checkVertWin(BoardPosition pos, char p) {
        int count = 1;
        boolean reverse = false, loop = true;
        //If pos is on the edge of the board
        if (pos.getRow() == getNumRows() - 1) {
            for (int r = getNumRows() - 2; r >= 0; r--) {
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
        }
        //If pos is not on the edge of the board
        else {
            int r = pos.getRow() + 1;
            BoardPosition test = new BoardPosition(r, pos.getColumn());
            while (loop) {
                if (isPlayerAtPos(test, p)) {
                    count++;
                    if (count == getNumToWin()) {
                        return true;
                    }
                    else {
                        if (r == getNumRows() - 1) {
                            reverse = true;
                            r = pos.getRow() - 1;
                        }
                        else {
                            if (!reverse) {
                                r++;
                            }
                            else {
                                r--;
                            }
                        }
                        test = new BoardPosition(r, pos.getColumn());
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks to see if the last token placed (which was placed in
     * position pos by player p) resulted in 5 in a row diagonally. Returns
     * true if it does, otherwise false
     * @pre  [Player p was the last to place their token and it is at pos] AND 0 <= pos.getRow() < MAX_ROW AND 0 <= pos.getColumn() < MAX_COLUMN
     * @param pos The position of the placed token
     * @param p The player who placed the token
     * @post [True or false if player p places a token that equals the max number in a row needed to win diagonally] AND gameBoard = #gameBoard
     * @return True if player p won diagonally, false otherwise
     */
    default public boolean checkDiagWin(BoardPosition pos, char p) {
        int count = 1;
        BoardPosition currPos = pos;
        while (count < getNumToWin()) {
            if (currPos.getRow() - 1 >= 0 && currPos.getColumn() + 1 < getNumColumns()) {
                currPos = new BoardPosition((currPos.getRow() - 1), (currPos.getColumn() + 1));
                if (whatsAtPos(currPos) == p) {
                    count++;
                    if (count == getNumToWin()) {
                        return true;
                    }
                }
                else break;
            }
            else break;
        }
        currPos = pos;
        while (count < getNumToWin()) {
            if (currPos.getRow() + 1 < getNumRows() && currPos.getColumn() - 1 >= 0) {
                currPos = new BoardPosition((currPos.getRow() + 1), (currPos.getColumn() - 1));
                if (whatsAtPos(currPos) == p) {
                    count++;
                    if (count == getNumToWin()) {
                        return true;
                    }
                }
                else break;
            }
            else break;
        }
        currPos = pos;
        count = 1;
        while (count < getNumToWin()) {
            if (currPos.getRow() - 1 < getNumRows() && currPos.getColumn() - 1 >= 0) {
                currPos = new BoardPosition((currPos.getRow() + 1), (currPos.getColumn() - 1));
                if (whatsAtPos(currPos) == p) {
                    count++;
                    if (count == getNumToWin()) {
                        return true;
                    }
                }
                else return false;
            }
            else return false;
        }
        currPos = pos;
        if (currPos.getRow() + 1 < getNumRows() && currPos.getColumn() + 1 >= 0) {
            currPos = new BoardPosition((currPos.getRow() + 1), (currPos.getColumn() - 1));
            if (whatsAtPos(currPos) == p) {
                count++;
                if (count == getNumToWin()) {
                    return true;
                }
            }
            else return false;
        }
        return false;
    }
}
