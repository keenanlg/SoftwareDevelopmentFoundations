package cpsc2150.extendedConnectX.models;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestGameBoardMem {
    private IGameBoard makeBoard(int r, int c, int w) {
        return new GameBoardMem(r, c, w);
    }
    private String makeString(char[][] array) {
        String test = "|";

        //Column numbers
        for (int column = 0; column < array[array.length - 1].length; column++) {
            if (column < 10) {
                test = test.concat(" " + column + "|");
            }
            else {
                test = test.concat(column + "|");
            }
        }
        test = test.concat("\n");

        //Board
        for (int row = array.length - 1; row >= 0; row--) {
            test = test.concat("|");
            for (int column = 0; column < array[row].length; column++) {
                test = test.concat(array[row][column] + " |");
            }
            test = test.concat("\n");
        }
        return test;

    }

    //Constructor tests
    @Test
    public void testGameBoard_max() {
        IGameBoard gb = makeBoard(100, 100, 25);
        assertEquals(gb.getNumRows(), 100);
        assertEquals(gb.getNumColumns(), 100);
        assertEquals(gb.getNumToWin(), 25);
    }
    @Test
    public void testGameBoard_min() {
        IGameBoard gb = makeBoard(3, 3, 3);
        assertEquals(gb.getNumRows(), 3);
        assertEquals(gb.getNumColumns(), 3);
        assertEquals(gb.getNumToWin(), 3);
    }
    @Test
    public void testGameBoard_middle() {
        IGameBoard gb = makeBoard(9, 7, 5);
        assertEquals(gb.getNumRows(), 9);
        assertEquals(gb.getNumColumns(), 7);
        assertEquals(gb.getNumToWin(), 5);
    }

    //checkIfFree tests
    @Test
    public void testCheckIfFree_finalSpace() {
        IGameBoard gb = makeBoard(3, 3, 3);

        gb.placeToken('o', 0);
        gb.placeToken('x', 0);
        gb.placeToken('o', 1);
        gb.placeToken('x', 1);
        gb.placeToken('o', 1);
        gb.placeToken('x', 2);

        assertTrue(gb.checkIfFree(0));
    }
    @Test
    public void testCheckIfFree_full() {
        IGameBoard gb = makeBoard(3, 3, 3);

        gb.placeToken('o', 0);
        gb.placeToken('x', 0);
        gb.placeToken('o', 1);
        gb.placeToken('x', 1);
        gb.placeToken('o', 1);
        gb.placeToken('x', 2);
        gb.placeToken('o', 0);

        assertFalse(gb.checkIfFree(0));
    }
    @Test
    public void testCheckIfFree_empty() {
        IGameBoard gb = makeBoard(3, 3, 3);

        gb.placeToken('o', 0);
        gb.placeToken('x', 0);
        gb.placeToken('o', 1);
        gb.placeToken('x', 1);
        gb.placeToken('o', 1);
        gb.placeToken('x', 0);

        assertTrue(gb.checkIfFree(2));
    }

    //checkHorizWin tests
    @Test
    public void testCheckHorizWin_both() {
        IGameBoard gb = makeBoard(3, 3, 3);

        gb.placeToken('x', 0);
        gb.placeToken('o', 0);
        gb.placeToken('o', 0);
        gb.placeToken('x', 2);
        gb.placeToken('x', 1);

        assertTrue(gb.checkHorizWin(new BoardPosition(0, 1), 'x'));
    }
    @Test
    public void testCheckHorizWin_false() {
        IGameBoard gb = makeBoard(3, 3, 3);

        gb.placeToken('x', 0);
        gb.placeToken('o', 0);
        gb.placeToken('x', 1);

        assertFalse(gb.checkHorizWin(new BoardPosition(0, 1), 'x'));
    }
    @Test
    public void testCheckHorizWin_left() {
        IGameBoard gb = makeBoard(4, 4, 3);

        gb.placeToken('x', 0);
        gb.placeToken('o', 0);
        gb.placeToken('x', 1);
        gb.placeToken('o', 0);
        gb.placeToken('x', 2);

        assertTrue(gb.checkHorizWin(new BoardPosition(0, 2), 'x'));
    }
    @Test
    public void testCheckHorizWin_right() {
        IGameBoard gb = makeBoard(4, 4, 3);

        gb.placeToken('x', 3);
        gb.placeToken('o', 3);
        gb.placeToken('x', 2);
        gb.placeToken('o', 3);
        gb.placeToken('x', 1);

        assertTrue(gb.checkHorizWin(new BoardPosition(0, 1), 'x'));
    }

    //checkVertWin tests
    @Test
    public void testCheckVertWin_noFull() {
        IGameBoard gb = makeBoard(4, 3, 3);

        gb.placeToken('x', 0);
        gb.placeToken('o', 1);
        gb.placeToken('x', 0);
        gb.placeToken('o', 2);
        gb.placeToken('x', 0);

        assertTrue(gb.checkVertWin(new BoardPosition(2, 0), 'x'));
    }
    @Test
    public void testCheckVertWin_false() {
        IGameBoard gb = makeBoard(3, 3, 3);

        gb.placeToken('x', 0);
        gb.placeToken('o', 1);
        gb.placeToken('x', 0);
        gb.placeToken('o', 2);

        assertFalse(gb.checkHorizWin(new BoardPosition(1, 0), 'x'));
    }
    @Test
    public void testCheckVertWin_interrupt() {
        IGameBoard gb = makeBoard(4, 4, 3);

        gb.placeToken('x', 0);
        gb.placeToken('o', 1);
        gb.placeToken('x', 0);
        gb.placeToken('o', 0);
        gb.placeToken('x', 0);

        assertFalse(gb.checkVertWin(new BoardPosition(3, 0), 'x'));
    }
    @Test
    public void testCheckVertWin_full() {
        IGameBoard gb = makeBoard(3, 3, 3);

        gb.placeToken('x', 0);
        gb.placeToken('o', 1);
        gb.placeToken('x', 0);
        gb.placeToken('o', 2);
        gb.placeToken('x', 0);

        assertTrue(gb.checkVertWin(new BoardPosition(2, 0), 'x'));
    }

    //checkDiagWin tests
    @Test
    public void testCheckDiagWin_sw() {
        IGameBoard gb = makeBoard(3, 3, 3);

        gb.placeToken('x', 0);
        gb.placeToken('o', 1);
        gb.placeToken('x', 1);
        gb.placeToken('o', 0);
        gb.placeToken('x', 2);
        gb.placeToken('o', 2);
        gb.placeToken('x', 2);

        assertTrue(gb.checkDiagWin(new BoardPosition(2, 2), 'x'));
    }
    @Test
    public void testCheckDiagWin_se() {
        IGameBoard gb = makeBoard(3, 3, 3);

        gb.placeToken('x', 0);
        gb.placeToken('o', 1);
        gb.placeToken('x', 1);
        gb.placeToken('o', 0);
        gb.placeToken('x', 2);
        gb.placeToken('o', 2);
        gb.placeToken('x', 0);

        assertTrue(gb.checkDiagWin(new BoardPosition(2, 0), 'x'));
    }
    @Test
    public void testCheckDiagWin_nw() {
        IGameBoard gb = makeBoard(3, 3, 3);

        gb.placeToken('x', 0);
        gb.placeToken('o', 1);
        gb.placeToken('x', 1);
        gb.placeToken('o', 0);
        gb.placeToken('x', 0);
        gb.placeToken('o', 1);
        gb.placeToken('x', 2);

        assertTrue(gb.checkDiagWin(new BoardPosition(0, 2), 'x'));
    }
    @Test
    public void testCheckDiagWin_ne() {
        IGameBoard gb = makeBoard(3, 3, 3);

        gb.placeToken('x', 2);
        gb.placeToken('o', 1);
        gb.placeToken('x', 1);
        gb.placeToken('o', 2);
        gb.placeToken('x', 2);
        gb.placeToken('o', 1);
        gb.placeToken('x', 0);

        assertTrue(gb.checkDiagWin(new BoardPosition(0, 0), 'x'));
    }
    @Test
    public void testCheckDiagWin_nw2se() {
        IGameBoard gb = makeBoard(3, 4, 3);

        gb.placeToken('x', 0);
        gb.placeToken('o', 1);
        gb.placeToken('x', 2);
        gb.placeToken('x', 3);
        gb.placeToken('o', 0);
        gb.placeToken('x', 1);
        gb.placeToken('o', 2);
        gb.placeToken('o', 3);
        gb.placeToken('x', 0);

        assertTrue(gb.checkDiagWin(new BoardPosition(1, 1), 'x'));
    }
    @Test
    public void testCheckDiagWin_sw2ne() {
        IGameBoard gb = makeBoard(3, 4, 3);

        gb.placeToken('x', 3);
        gb.placeToken('o', 2);
        gb.placeToken('x', 1);
        gb.placeToken('o', 3);
        gb.placeToken('x', 0);
        gb.placeToken('o', 1);
        gb.placeToken('x', 3);
        gb.placeToken('o', 0);
        gb.placeToken('x', 2);

        assertTrue(gb.checkDiagWin(new BoardPosition(1, 2), 'x'));
    }
    @Test
    public void testCheckDiagWin_interrupt() {
        IGameBoard gb = makeBoard(3, 4, 3);

        gb.placeToken('x', 0);
        gb.placeToken('o', 0);
        gb.placeToken('x', 1);
        gb.placeToken('o', 1);
        gb.placeToken('x', 1);
        gb.placeToken('o', 2);
        gb.placeToken('x', 3);
        gb.placeToken('o', 3);
        gb.placeToken('x', 3);
        gb.placeToken('o', 2);

        assertFalse(gb.checkDiagWin(new BoardPosition(1, 2), 'o'));
    }

    //checkTie tests
    @Test
    public void testCheckTie_true() {
        IGameBoard gb = makeBoard(3, 4, 4);

        gb.placeToken('x', 0);
        gb.placeToken('o', 0);
        gb.placeToken('x', 0);
        gb.placeToken('o', 1);
        gb.placeToken('x', 1);
        gb.placeToken('o', 1);
        gb.placeToken('o', 2);
        gb.placeToken('x', 2);
        gb.placeToken('o', 2);
        gb.placeToken('x', 3);
        gb.placeToken('x', 3);
        gb.placeToken('o', 3);

        assertTrue(gb.checkTie());
    }
    @Test
    public void testCheckTie_false() {
        IGameBoard gb = makeBoard(3, 3, 3);

        gb.placeToken('x', 0);
        gb.placeToken('o', 0);
        gb.placeToken('x', 0);
        gb.placeToken('o', 1);
        gb.placeToken('x', 1);
        gb.placeToken('x', 1);
        gb.placeToken('o', 2);
        gb.placeToken('o', 2);

        assertFalse(gb.checkTie());
    }
    @Test
    public void testCheckTie_win() {
        IGameBoard gb = makeBoard(3, 3, 3);

        gb.placeToken('x', 0);
        gb.placeToken('o', 0);
        gb.placeToken('x', 0);
        gb.placeToken('o', 1);
        gb.placeToken('x', 1);
        gb.placeToken('x', 1);
        gb.placeToken('o', 2);
        gb.placeToken('o', 2);
        gb.placeToken('x', 2);

        assertFalse(gb.checkTie());
    }
    @Test
    public void testCheckTie_column() {
        IGameBoard gb = makeBoard(3, 3, 3);

        gb.placeToken('x', 0);
        gb.placeToken('o', 0);
        gb.placeToken('x', 0);
        gb.placeToken('o', 1);
        gb.placeToken('x', 1);
        gb.placeToken('o', 1);

        assertFalse(gb.checkTie());
    }

    //whatsAtPos tests
    @Test
    public void testWhatsAtPos_blank() {
        IGameBoard gb = makeBoard(3, 3, 3);

        gb.placeToken('x', 0);
        gb.placeToken('o', 0);
        gb.placeToken('x', 0);
        gb.placeToken('o', 1);
        gb.placeToken('x', 1);
        gb.placeToken('x', 1);
        gb.placeToken('o', 2);
        gb.placeToken('o', 2);

        assertEquals(gb.whatsAtPos(new BoardPosition(2, 2)), ' ');
    }
    @Test
    public void testWhatsAtPos_nw() {
        IGameBoard gb = makeBoard(5, 5, 3);

        gb.placeToken('x', 0);
        gb.placeToken('o', 0);
        gb.placeToken('s', 0);
        gb.placeToken('w', 0);
        gb.placeToken('o', 0);
        gb.placeToken('x', 1);
        gb.placeToken('s', 1);
        gb.placeToken('w', 1);

        assertEquals(gb.whatsAtPos(new BoardPosition(4, 0)), 'o');
    }
    @Test
    public void testWhatsAtPos_sw() {
        IGameBoard gb = makeBoard(5, 5, 3);

        gb.placeToken('x', 0);
        gb.placeToken('o', 0);
        gb.placeToken('x', 0);
        gb.placeToken('o', 1);
        gb.placeToken('x', 1);
        gb.placeToken('x', 1);
        gb.placeToken('o', 2);
        gb.placeToken('o', 2);

        assertEquals(gb.whatsAtPos(new BoardPosition(0, 0)), 'x');
    }
    @Test
    public void testWhatsAtPos_ne() {
        IGameBoard gb = makeBoard(5, 5, 3);

        gb.placeToken('x', 4);
        gb.placeToken('o', 4);
        gb.placeToken('s', 4);
        gb.placeToken('w', 4);
        gb.placeToken('o', 4);
        gb.placeToken('x', 3);
        gb.placeToken('s', 3);
        gb.placeToken('w', 3);

        assertEquals(gb.whatsAtPos(new BoardPosition(4, 4)), 'o');
    }
    @Test
    public void testWhatsAtPos_se() {
        IGameBoard gb = makeBoard(5, 5, 3);

        gb.placeToken('x', 4);
        gb.placeToken('o', 4);
        gb.placeToken('s', 4);
        gb.placeToken('w', 4);
        gb.placeToken('o', 4);
        gb.placeToken('x', 3);
        gb.placeToken('s', 3);
        gb.placeToken('w', 3);

        assertEquals(gb.whatsAtPos(new BoardPosition(0, 4)), 'x');
    }

    //isPlayerAtPos tests
    @Test
    public void testIsPlayerAtPos_middle() {
        IGameBoard gb = makeBoard(5, 5, 3);

        gb.placeToken('x', 4);
        gb.placeToken('o', 4);
        gb.placeToken('s', 4);
        gb.placeToken('w', 4);
        gb.placeToken('o', 4);
        gb.placeToken('x', 3);
        gb.placeToken('s', 3);
        gb.placeToken('w', 3);

        assertFalse(gb.isPlayerAtPos(new BoardPosition(2, 0), 'x'));
    }
    @Test
    public void testIsPlayerAtPos_se() {
        IGameBoard gb = makeBoard(5, 5, 3);

        gb.placeToken('x', 4);
        gb.placeToken('o', 4);
        gb.placeToken('s', 4);
        gb.placeToken('w', 4);
        gb.placeToken('o', 4);
        gb.placeToken('x', 3);
        gb.placeToken('s', 3);
        gb.placeToken('w', 3);

        assertTrue(gb.isPlayerAtPos(new BoardPosition(0, 4), 'x'));
    }
    @Test
    public void testIsPlayerAtPos_ne() {
        IGameBoard gb = makeBoard(5, 5, 3);

        gb.placeToken('x', 4);
        gb.placeToken('o', 4);
        gb.placeToken('s', 4);
        gb.placeToken('w', 4);
        gb.placeToken('o', 4);
        gb.placeToken('x', 3);
        gb.placeToken('s', 3);
        gb.placeToken('w', 3);

        assertFalse(gb.isPlayerAtPos(new BoardPosition(4, 4), 'x'));
    }
    @Test
    public void testIsPlayerAtPos_sw() {
        IGameBoard gb = makeBoard(5, 5, 3);

        gb.placeToken('x', 0);
        gb.placeToken('o', 0);
        gb.placeToken('s', 0);
        gb.placeToken('w', 0);
        gb.placeToken('o', 0);
        gb.placeToken('x', 1);
        gb.placeToken('s', 1);
        gb.placeToken('w', 1);

        assertTrue(gb.isPlayerAtPos(new BoardPosition(0, 0), 'x'));
    }
    @Test
    public void testIsPlayerAtPos_nw() {
        IGameBoard gb = makeBoard(5, 5, 3);

        gb.placeToken('x', 0);
        gb.placeToken('o', 0);
        gb.placeToken('s', 0);
        gb.placeToken('w', 0);
        gb.placeToken('o', 0);
        gb.placeToken('x', 1);
        gb.placeToken('s', 1);
        gb.placeToken('w', 1);

        assertFalse(gb.isPlayerAtPos(new BoardPosition(4, 0), 'x'));
    }

    //placeToken tests
    @Test
    public void testPlaceToken_empty() {
        IGameBoard gb = makeBoard(5, 5, 3);
        char[][] array = new char[5][5];
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                array[r][c] = ' ';
            }
        }

        gb.placeToken('x', 0);
        gb.placeToken('o', 0);
        gb.placeToken('s', 0);
        gb.placeToken('w', 0);
        gb.placeToken('o', 0);
        gb.placeToken('x', 1);
        gb.placeToken('s', 1);
        gb.placeToken('w', 1);
        gb.placeToken('x', 4);

        array[0][0] = 'x';
        array[1][0] = 'o';
        array[2][0] = 's';
        array[3][0] = 'w';
        array[4][0] = 'o';
        array[0][1] = 'x';
        array[1][1] = 's';
        array[2][1] = 'w';
        array[0][4] = 'x';

        assertEquals(gb.toString(), makeString(array));
    }
    @Test
    public void testPlaceToken_full() {
        IGameBoard gb = makeBoard(5, 5, 3);
        char[][] array = new char[5][5];
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                array[r][c] = ' ';
            }
        }

        gb.placeToken('x', 0);
        gb.placeToken('o', 0);
        gb.placeToken('s', 0);
        gb.placeToken('w', 0);
        gb.placeToken('o', 0);
        gb.placeToken('x', 1);
        gb.placeToken('s', 1);
        gb.placeToken('w', 1);
        gb.placeToken('x', 0);

        array[0][0] = 'x';
        array[1][0] = 'o';
        array[2][0] = 's';
        array[3][0] = 'w';
        array[4][0] = 'o';
        array[0][1] = 'x';
        array[1][1] = 's';
        array[2][1] = 'w';

        assertEquals(gb.toString(), makeString(array));
    }
    @Test
    public void testPlaceToken_lastMiddle() {
        IGameBoard gb = makeBoard(5, 5, 3);
        char[][] array = new char[5][5];
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                array[r][c] = ' ';
            }
        }

        gb.placeToken('x', 0);
        gb.placeToken('o', 0);
        gb.placeToken('s', 0);
        gb.placeToken('w', 0);
        gb.placeToken('x', 0);
        gb.placeToken('o', 1);
        gb.placeToken('s', 1);
        gb.placeToken('w', 1);
        gb.placeToken('x', 1);
        gb.placeToken('o', 1);

        array[0][0] = 'x';
        array[1][0] = 'o';
        array[2][0] = 's';
        array[3][0] = 'w';
        array[4][0] = 'x';
        array[0][1] = 'o';
        array[1][1] = 's';
        array[2][1] = 'w';
        array[3][1] = 'x';
        array[4][1] = 'o';

        assertEquals(gb.toString(), makeString(array));
    }
    @Test
    public void testPlaceToken_lastLeft() {
        IGameBoard gb = makeBoard(5, 5, 3);
        char[][] array = new char[5][5];
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                array[r][c] = ' ';
            }
        }

        gb.placeToken('x', 0);
        gb.placeToken('o', 0);
        gb.placeToken('s', 0);
        gb.placeToken('w', 0);
        gb.placeToken('o', 1);
        gb.placeToken('s', 1);
        gb.placeToken('w', 1);
        gb.placeToken('x', 1);
        gb.placeToken('x', 4);
        gb.placeToken('o', 0);

        array[0][0] = 'x';
        array[1][0] = 'o';
        array[2][0] = 's';
        array[3][0] = 'w';
        array[4][0] = 'o';
        array[0][1] = 'o';
        array[1][1] = 's';
        array[2][1] = 'w';
        array[3][1] = 'x';
        array[0][4] = 'x';

        assertEquals(gb.toString(), makeString(array));
    }
    @Test
    public void testPlaceToken_lastRight() {
        IGameBoard gb = makeBoard(5, 5, 3);
        char[][] array = new char[5][5];
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                array[r][c] = ' ';
            }
        }

        gb.placeToken('x', 4);
        gb.placeToken('o', 4);
        gb.placeToken('s', 4);
        gb.placeToken('w', 4);
        gb.placeToken('o', 3);
        gb.placeToken('s', 3);
        gb.placeToken('w', 3);
        gb.placeToken('x', 3);
        gb.placeToken('x', 0);
        gb.placeToken('o', 4);

        array[0][4] = 'x';
        array[1][4] = 'o';
        array[2][4] = 's';
        array[3][4] = 'w';
        array[4][4] = 'o';
        array[0][3] = 'o';
        array[1][3] = 's';
        array[2][3] = 'w';
        array[3][3] = 'x';
        array[0][0] = 'x';

        assertEquals(gb.toString(), makeString(array));
    }
}