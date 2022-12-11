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

    @Test
    void toStringMethodTest() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        String toStringMethodResult = board.toString();
        StringBuilder valueToCompare = new StringBuilder("SudokuBoard{=[");

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(i == 8 && j == 8) {
                    valueToCompare.append(board.get(i, j));
                    continue;
                }
                valueToCompare.append(board.get(i, j)).append(", ");
            }
        }
        valueToCompare.append("]}");
        assertEquals(valueToCompare.toString(), toStringMethodResult);
    }

    @Test
    void equalsMethodTest() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        SudokuBoard board2 = new SudokuBoard(solver);
        SudokuBoard board3 = new SudokuBoard(solver);
        SudokuBoard board4 = null;
        Integer a = 1;
        board.solveGame();
        board2.solveGame();
        board = board3;

        assertNotEquals(board, board2);
        assertNotEquals(board, board4);
        assertNotEquals(board, a);
        assertEquals(board, board3);
    }

    @Test
    void hashCodeMethodTest() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        SudokuBoard board2;
        board2 = board;
        assertEquals(board.hashCode(), board2.hashCode());
    }

    @Test
    void isHashCodeTheSameWhenObjectAreEquals() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        SudokuBoard board2 = new SudokuBoard(solver);


        if(board.equals(board2)) {
            assertEquals(board.hashCode(), board2.hashCode());
        }
    }

    @Test
    void cloneTest() throws CloneNotSupportedException {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        SudokuBoard boardClone = board.clone();

        int a = boardClone.get(1, 1);

        board.set(1,1, (a+1)%9);

        assertEquals(board.get(1,1), (a+1)%9);
        assertEquals(boardClone.get(1,1), a);
        assertNotEquals(board.get(1,1), boardClone.get(1,1));
        assertNotEquals(board, boardClone);
    }
}
