package org.example;

import  org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

class BacktrackingSudokuSolverTest {


    private final BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
    private final SudokuBoard sudoku = new SudokuBoard(solver);
    @Test
    void solveTest() {
        sudoku.solveGame();
        assertTrue(sudoku.checkHorizontal());
        assertTrue(sudoku.checkVertical());
        assertTrue(sudoku.checkSquare());
    }

    @Test
    void solveRepeatTest() {
        int [][] tablica = new int[9][9];
        int [][] tablica2 = new int[9][9];
        for(int i = 0; i<9; i++){
            for(int j =0; j<9; j++){
                tablica[i][j] = sudoku.get(i, j);
            }
        }
        sudoku.solveGame();
        for(int i = 0; i<9; i++){
            for(int j =0; j<9; j++){
                tablica2[i][j] = sudoku.get(i, j);
            }
        }
        assertFalse(Arrays.deepEquals(tablica, tablica2));
    }
}