package cpsc2150.extendedConnectX.models;
import java.util.*;

/**
 * Abstract class simply for converting GameBoard or GameBoardMem objects to strings
 * 
 * @author Keenan Grant
 * @section CPSC 2150 001
 */

abstract public class AbsGameBoard implements IGameBoard {
    /**
     * Converts a GameBoard or GameBoardMem object to a string format
     * 
     * @return The game board in a grid format in the form of a string
     * @pre A GameBoard or GameBoardMem object has to be created for this to be called and a new turn has started
     * @post The GameBoard or GameBoardMem object can be seen in a grid format with the columns labeled at the top
     *       toString = [String representation of the game board] and self = #self
     */
    @Override
    public String toString() {
        String test = "|";

        //Column numbers
        for (int column = 0; column < getNumColumns(); column++) {
            if (column < 10) {
                test = test.concat(" " + column + "|");
            }
            else {
                test = test.concat(column + "|");
            }
        }
        test = test.concat("\n");

        //Board
        for (int row = getNumRows() - 1; row >= 0; row--) {
            test = test.concat("|");
            for (int column = 0; column < getNumColumns(); column++) {
                BoardPosition pos = new BoardPosition(row, column);
                test = test.concat(whatsAtPos(pos) + " |");
            }
            test = test.concat("\n");
        }
        return test;
    }
}