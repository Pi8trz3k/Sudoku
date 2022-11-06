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
    void checkBoardTest() {
        sudokuBoard.solveGame();
        Assertions.assertTrue(sudokuBoard.checkBoard());
    }

    @Test
    void negativeCheckBoardTestOne() {
        sudokuBoard.solveGame();
        if(sudokuBoard.get(0,0) != 1) {
            sudokuBoard.set(0,0,1);
        } else {
            sudokuBoard.set(0,0,2);
        }
        Assertions.assertFalse(sudokuBoard.checkBoard());
    }

    @Test
    void negativeCheckBoardTestTwo() {
        sudokuBoard.solveGame();
        int value = sudokuBoard.get(0,5);
        sudokuBoard.set(0,5, sudokuBoard.get(0,6));
        sudokuBoard.set(0,6,value);
        Assertions.assertFalse(sudokuBoard.checkBoard());
    }

    @Test
    public void getRowTest() {
        Assertions.assertNotNull(sudokuBoard.getRow(2));
    }

    @Test
    public void getColumnTest() {
        Assertions.assertNotNull(sudokuBoard.getColumn(2));
    }

    @Test
    public void getBoxTest() {
        Assertions.assertNotNull(sudokuBoard.getBox(1, 1));
    }

    @Test
    public void negativeRowTest() {
        sudokuBoard.solveGame();
        if(sudokuBoard.get(0,0) != 1) {
            sudokuBoard.set(0,0,1);
        } else {
            sudokuBoard.set(0,0,2);
        }

        Assertions.assertFalse(sudokuBoard.checkHorizontal());
    }

    @Test
    public void negativeColumnTest() {
        sudokuBoard.solveGame();
        if(sudokuBoard.get(0,0) != 1){
            sudokuBoard.set(0,0,1);
        } else {
            sudokuBoard.set(0,0,2);
        }

        Assertions.assertFalse(sudokuBoard.checkVertical());
    }

    @Test
    public void negativeBoxTest() {
        sudokuBoard.solveGame();
        sudokuBoard.set(0,0,1);
        Assertions.assertFalse(sudokuBoard.checkSquare());
    }
}
