package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


class SudokuBoardTest {

    private final SudokuBoard sudokuBoard = new SudokuBoard();

    @Test
    void setMethodTest() {
        // get test in addition
        Assertions.assertEquals(sudokuBoard.get(0, 0), 0);
        sudokuBoard.set(0, 0, 3);
        Assertions.assertEquals(sudokuBoard.get(0, 0), 3);
    }

    @Test
    void checkBoardMethodTestTrue() {
        sudokuBoard.solveGame();
        Assertions.assertTrue(sudokuBoard.checkBoard());
    }

    @Test
    void checkBoardMethodTestFalse() {
        sudokuBoard.set(1,1,1);
        Assertions.assertFalse(sudokuBoard.checkBoard());
    }

    @Test
    void verticalLineTestTrue() {
        sudokuBoard.solveGame();
        Assertions.assertTrue(sudokuBoard.checkVertical());
    }

    @Test
    void verticalLineTestFalse() {
        sudokuBoard.solveGame();
        sudokuBoard.set(0,0, 1 );
        sudokuBoard.set(0, 1, 1);
        Assertions.assertFalse(sudokuBoard.checkVertical());
    }

    @Test
    void horizontalLineTestTrue() {
        sudokuBoard.solveGame();
        Assertions.assertTrue(sudokuBoard.checkHorizontal());
    }

    @Test
    void horizontalLineTestFalse() {
        sudokuBoard.solveGame();
        sudokuBoard.set(0, 0, 1);
        sudokuBoard.set(1, 0, 1);
        Assertions.assertFalse(sudokuBoard.checkHorizontal());
    }

    @Test
    void squareTestTrue() {
        sudokuBoard.solveGame();
        Assertions.assertTrue(sudokuBoard.checkSquare());
    }

    @Test
    void squareTestFalse() {
        sudokuBoard.solveGame();
        sudokuBoard.set(0,1, 1);
        sudokuBoard.set(1, 0, 1);
        Assertions.assertFalse(sudokuBoard.checkSquare());
    }


}
