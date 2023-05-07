package cpsc2150.extendedConnectX.models;
import java.util.*;

/**
 * This class keeps track of a single position on a game board
 * 
 * @author Keenan Grant
 * @section CPSC 2150 001
 */

public class BoardPosition {

    private int ROW, COLUMN;

    /**
     * Creates a BoardPosition coordinate object
     *
     * @param r the row coordinate of the object
     * @param c the column coordinate of the object
     * @post ROW = r AND COLUMN = c
     */
    public BoardPosition(int r, int c) {
        ROW = r;
        COLUMN = c;
    }

    /**
     * Returns the row
     * @return The row coordinate of the object
     * @pre BoardPosition instance is created
     * @post ROW = #ROW
     */
    public int getRow() {
        return ROW;
    }

    /**
     * Returns the column
     * @return The column coordinate of the object
     * @pre BoardPosition instance is created
     * @post COLUMN = #COLUMN
     */
    public int getColumn() {
        return COLUMN;
    }

    /**
     * Compares two BoardPosition objects for coordinate equality
     * @param pos The BoardPosition object that is getting compared to the current object
     * @return True if the position parameter and the current coordinate equal each other, false otherwise
     * @post True or false if BoardPosition object coordinates equal each other
     */
    @Override
    public boolean equals(Object pos) {
        if (this == pos) {
            return true;
        }
        else if (pos == null || getClass() != pos.getClass()) {
            return false;
        }
        BoardPosition pos2 = (BoardPosition) pos;
        return pos2.getRow() == ROW && pos2.getColumn() == COLUMN;
    }

    /**
     * Converts the object to a string of coordinates
     * @return The coordinates of the BoardPosition object in the format of row,column
     * @pre A BoardPosition object is using the method
     * @post The BoardPosition object can be seen as coordinates row,column
     */
    @Override
    public String toString() {
        return getRow() + "," + getColumn();
    }
}