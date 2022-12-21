package org.example;

import org.example.exceptions.SudokuFieldCloneFailureException;
import  org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuFieldTypeTest {

    private final BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
    private final SudokuBoard sudokuBoard = new SudokuBoard(solver);
    @Test
    public void constructorTest() {
        sudokuBoard.solveGame();
        SudokuRow row = sudokuBoard.getRow(0);
        assertTrue(verifyTest(row));
    }

    @Test
    public void valueInRowTest() {
        int r = 2;
        int c = 3;
        sudokuBoard.solveGame();
        SudokuRow row = sudokuBoard.getRow(r);
        assertEquals(row.get(c), sudokuBoard.get(r,c));
    }

    @Test
    public void valueInColTest() {
        int r = 4;
        int c = 5;
        sudokuBoard.solveGame();
        SudokuColumn col = sudokuBoard.getColumn(c);
        assertEquals(col.get(r), sudokuBoard.get(r,c));
    }

    @Test
    public void negativeVerifyTest() {
        sudokuBoard.solveGame();
        if(sudokuBoard.get(0,0) != 1) {
            sudokuBoard.set(0,0,1);
        } else {
            sudokuBoard.set(0,0,2);
        }
        SudokuRow row = sudokuBoard.getRow(0);
        assertFalse(verifyTest(row));
    }

    @Test
    public void positiveVerifyTest() {
        sudokuBoard.solveGame();
        SudokuRow row = sudokuBoard.getRow(0);
        assertTrue(verifyTest(row));
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

    @Test
    public void ColumnCloneTest() throws SudokuFieldCloneFailureException {
        SudokuColumn column = sudokuBoard.getColumn(1);
        column.fields.get(2).setFieldValue(2);
        SudokuColumn columnClone = column.clone();
        column.fields.get(2).setFieldValue(4);
        assertEquals(column.fields.get(2).getFieldValue(), 4);
        assertEquals(columnClone.fields.get(2).getFieldValue(), 2);
    }

    @Test
    public void RowCloneTest() throws SudokuFieldCloneFailureException {
        SudokuRow row = sudokuBoard.getRow(1);
        row.fields.get(2).setFieldValue(2);
        SudokuRow rowClone = row.clone();
        row.fields.get(2).setFieldValue(4);
        assertEquals(row.fields.get(2).getFieldValue(), 4);
        assertEquals(rowClone.fields.get(2).getFieldValue(), 2);
    }
    @Test
    public void BoxCloneTest() throws SudokuFieldCloneFailureException {
        SudokuBox box = sudokuBoard.getBox(1, 1);
        box.fields.get(2).setFieldValue(2);
        SudokuBox boxClone = box.clone();
        box.fields.get(2).setFieldValue(4);
        assertEquals(box.fields.get(2).getFieldValue(), 4);
        assertEquals(boxClone.fields.get(2).getFieldValue(), 2);
    }

}
