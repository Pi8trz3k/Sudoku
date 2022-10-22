package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class BacktrackingSudokuSolverTest {

    private final SudokuBoard sudoku = new SudokuBoard();
    @Test
    void solveTest() {
        sudoku.solveGame();
        Assertions.assertTrue(sudoku.checkHorizontal());
        Assertions.assertTrue(sudoku.checkVertical());
        Assertions.assertTrue(sudoku.checkSquare());
    }

    @Test
    void solveRepeatTest() {
        SudokuBoard sudoku2 = new SudokuBoard();
        sudoku.solveGame();
        sudoku2.solveGame();
        Assertions.assertNotEquals(sudoku, sudoku2);
    }
}