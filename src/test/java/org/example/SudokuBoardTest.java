package org.example;

import  org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    private final BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
    private final SudokuBoard sudokuBoard = new SudokuBoard(solver);

    @Test
    void setGetMethodTest() {
        // get test in addition
        assertEquals(sudokuBoard.get(0, 0), 0);
        sudokuBoard.set(0, 0, 3);
        assertEquals(sudokuBoard.get(0, 0), 3);
        //test
    }

    @Test
    void checkBoardTest() {
        sudokuBoard.solveGame();
        assertTrue(sudokuBoard.checkBoard());
    }

    @Test
    void negativeCheckBoardTestOne() {
        sudokuBoard.solveGame();
        if(sudokuBoard.get(0,0) != 1) {
            sudokuBoard.set(0,0,1);
        } else {
            sudokuBoard.set(0,0,2);
        }
        assertFalse(sudokuBoard.checkBoard());
    }

    @Test
    void negativeCheckBoardTestTwo() {
        sudokuBoard.solveGame();
        int value = sudokuBoard.get(0,5);
        sudokuBoard.set(0,5, sudokuBoard.get(0,6));
        sudokuBoard.set(0,6,value);
        assertFalse(sudokuBoard.checkBoard());
    }

    @Test
    void negativeCheckBoardTestThree() {
        int a=0;
        int c;
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                c=a+j;
                if(c>8){
                    c=c%9;
                }
                sudokuBoard.set(c, i, j);
            }
            a++;
        }

        assertFalse(sudokuBoard.checkBoard());
    }


    @Test
    public void getRowTest() {
        assertNotNull(sudokuBoard.getRow(2));
    }

    @Test
    public void getColumnTest() {
        assertNotNull(sudokuBoard.getColumn(2));
    }

    @Test
    public void getBoxTest() {
        assertNotNull(sudokuBoard.getBox(1, 1));
    }

    @Test
    public void negativeRowTest() {
        sudokuBoard.solveGame();
        if(sudokuBoard.get(0,0) != 1) {
            sudokuBoard.set(0,0,1);
        } else {
            sudokuBoard.set(0,0,2);
        }

        assertFalse(sudokuBoard.checkHorizontal());
    }

    @Test
    public void negativeColumnTest() {
        sudokuBoard.solveGame();
        if(sudokuBoard.get(0,0) != 1){
            sudokuBoard.set(0,0,1);
        } else {
            sudokuBoard.set(0,0,2);
        }

        assertFalse(sudokuBoard.checkVertical());
    }

    @Test
    public void negativeBoxTest() {
        sudokuBoard.solveGame();
        sudokuBoard.set(0,0,1);
        sudokuBoard.set(2, 2, 1);
        assertFalse(sudokuBoard.checkSquare());
    }
}
