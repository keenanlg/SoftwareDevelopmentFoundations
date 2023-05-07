package cpsc2150.extendedConnectX.models;

abstract public class AbsGameBoard implements IGameBoard {
    /**
     * Converts a GameBoard object to a string format
     * @return The coordinates of the BoardPosition object in the format of <row>,<column>
     * @pre A BoardPosition object has to be created for this to be called
     * @post The BoardPosition object can be seen in a grid format with the columns labeled at the top
     */
    @Override
    public String toString() {
        String test = "|";

        //Column numbers
        for (int column = 0; column < getNumColumns(); column++) {
            test = test.concat(column + "|");
        }
        test = test.concat("\n");

        //Board
        for (int row = getNumRows() - 1; row >= 0; row--) {
            test = test.concat("|");
            for (int column = 0; column < getNumColumns(); column++) {
                BoardPosition pos = new BoardPosition(row, column);
                test = test.concat(whatsAtPos(pos) + "|");
            }
            test = test.concat("\n");
        }
        return test;
    }
}
