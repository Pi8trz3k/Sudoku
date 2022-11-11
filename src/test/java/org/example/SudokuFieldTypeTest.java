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
        row = sudokuBoard.getRow(0);
        Assertions.assertTrue(verifyTest(row));
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
        SudokuFieldType col = new SudokuColumn();
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
        Assertions.assertFalse(verifyTest(row));
    }

    public boolean verifyTest(SudokuFieldType sudokuField) {
        for(int i = 0; i < 9; i++) {
            for(int j = i + 1; j < 9; j++) {
                if(sudokuField.get(i) == sudokuField.get(j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
