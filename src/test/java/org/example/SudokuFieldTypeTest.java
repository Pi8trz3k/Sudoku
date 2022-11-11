package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SudokuFieldTypeTest {

    private final BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
    private final SudokuBoard sudokuBoard = new SudokuBoard(solver);
    private SudokuFieldType row = new SudokuRow();

    @Test
    public void constructorTest() {
        sudokuBoard.solveGame();
        boolean a = true;
        row = sudokuBoard.getRow(0);
        for (int i = 0; i < 9; i++) {
            for (int i2 = i + 1; i2 < 9; i2++) {
                if (row.get(i) == row.get(i2)) {
                    a = false;
                }
            }
        }
        Assertions.assertTrue(a);
    }

    @Test
    public void valueInRowTest() {
        int r = 2;
        int c = 3;
        sudokuBoard.solveGame();
        row = sudokuBoard.getRow(r);
        Assertions.assertEquals(row.get(c), sudokuBoard.get(r,c));
    }

    @Test
    public void valueInColTest() {
        int r = 4;
        int c = 5;
        SudokuFieldType col;
        sudokuBoard.solveGame();
        col = sudokuBoard.getColumn(c);
        Assertions.assertEquals(col.get(r), sudokuBoard.get(r,c));
    }

    @Test
    public void negativeVerifyTest() {
        sudokuBoard.solveGame();
        if(sudokuBoard.get(0,0) != 1) {
            sudokuBoard.set(0,0,1);
        } else {
            sudokuBoard.set(0,0,2);
        }
        row = sudokuBoard.getRow(0);
       boolean a = true;
        for (int i = 0; i < 9; i++) {
            for (int i2 = i + 1; i2 < 9; i2++) {
                if (row.get(i) == row.get(i2)) {
                    a = false;
                }
            }
        }
        Assertions.assertFalse(a);
    }
}
