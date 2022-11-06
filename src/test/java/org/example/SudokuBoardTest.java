package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


class SudokuBoardTest {

    private final BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
    private final SudokuBoard sudokuBoard = new SudokuBoard(solver);

    @Test
    void setGetMethodTest() {
        // get test in addition
        Assertions.assertEquals(sudokuBoard.get(0, 0), 0);
        sudokuBoard.set(0, 0, 3);
        Assertions.assertEquals(sudokuBoard.get(0, 0), 3);
        //test
    }

    @Test
    void checkBoardMethodTest() {
        sudokuBoard.solveGame();
        Assertions.assertTrue(sudokuBoard.checkBoard());
    }
    /* public void getRowTest() {
        Assertions.assertNotNull(sudokuBoard.getRow(2));
    }

    @Test
    public void getColumnTest() {
        Assertions.assertNotNull(sudokuBoard.getColumn(2));
    }

    @Test
    public void getBoxTest() {
        Assertions.assertNotNull(sudokuBoard.getBox(1, 1));
    }*/
}
