package cpsc2150.extendedConnectX.models;

public class BoardPosition {

/**
 * This class keeps track of one cell of the cpsc2150.extendedConnectX.models.GameBoard. Has the rows and column variables.
 *
 * @author Keenan Grant
 * @section CPSC 2151 007
 * @invariant ROW >= 0 AND COLUMN >= 0
 */

    private int ROW, COLUMN;

    /**
     * Creates a coordinate object
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
     * @pre A BoardPosition object is testing comparison
     * @post True or false if BoardPosition object coordinates equal each other
     */
    @Override
    public boolean equals(Object pos) {
        BoardPosition pos2 = (BoardPosition) pos;
        if (pos2.getRow() == ROW && pos2.getColumn() == COLUMN) {
            return true;
        }
        return false;
    }

    /**
     * Converts the object to a string of coordinates
     * @return The coordinates of the BoardPosition object in the format of <row>, <column>
     * @pre A BoardPosition object is using the method
     * @post The BoardPosition object can be seen as coordinates <row>, <column>
     */
    @Override
    public String toString() {
        return ROW + ", " + COLUMN;
    }
}
