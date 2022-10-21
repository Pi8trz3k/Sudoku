package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class BacktrackingSudokuSolverTest {
    private boolean checkVertical(SudokuBoard board){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int jj = j + 1; jj < 9; jj++) {
                    if (board.get(i, j) == board.get(i, jj)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    private boolean checkHorizontal(SudokuBoard board){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int jj = j + 1; jj < 9; jj++) {
                    if (board.get(j,i) == board.get(jj, i)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean checkBox(SudokuBoard board){
        int powt = 0;
        for (int a = 1; a < 10; a++) {
            for (int z = 0; z < 9; z++) {
                for (int i = z % 3 * 3; i < z % 3 * 3 + 3; i++) {
                    for (int j = z / 3 * 3; j < z / 3 * 3 + 3; j++) {
                        if (board.get(i, j) == a) {
                            powt++;
                        }
                    }
                }
                if (powt > 1) {
                    return false;
                }
                powt = 0;
            }
        }
        return true;
    }


    @Test
    void solveTest() {
        SudokuBoard sudoku = new SudokuBoard();
        BacktrackingSudokuSolver BTSS = new BacktrackingSudokuSolver();
        BTSS.solve(sudoku);
        Assertions.assertTrue(checkHorizontal(sudoku));
        Assertions.assertTrue(checkVertical(sudoku));
        Assertions.assertTrue(checkBox(sudoku));
    }

    @Test
    void solveRepeatTest() {
        SudokuBoard sudoku = new SudokuBoard();
        SudokuBoard sudoku2 = new SudokuBoard();
        BacktrackingSudokuSolver BTSS = new BacktrackingSudokuSolver();
        BTSS.solve(sudoku);
        BTSS.solve(sudoku2);
        Assertions.assertNotEquals(sudoku, sudoku2);
    }
}