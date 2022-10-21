package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


class SudokuBoardTest {
    BacktrackingSudokuSolver BTSS = new BacktrackingSudokuSolver();
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
    void setMethodTest() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        Assertions.assertEquals(sudokuBoard.get(0, 0), 0);
        sudokuBoard.set(0, 0, 3);
        Assertions.assertEquals(sudokuBoard.get(0, 0), 3);
    }

    @Test
    void checkMethodTest() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        Assertions.assertFalse(sudokuBoard.checkBoard());
        BTSS.solveGame(sudokuBoard);

        Assertions.assertEquals(sudokuBoard.checkBoard(), checkVertical(sudokuBoard));
        Assertions.assertEquals(sudokuBoard.checkBoard(), checkHorizontal(sudokuBoard));
        Assertions.assertEquals(sudokuBoard.checkBoard(), checkBox(sudokuBoard));
        //verticalTest
        sudokuBoard.set(0,0, 1 );
        sudokuBoard.set(0, 1, 1);
        Assertions.assertEquals(sudokuBoard.checkBoard(), checkVertical(sudokuBoard));

        BTSS.solve(sudokuBoard);
        //horizontalTest
        sudokuBoard.set(0, 0, 1);
        sudokuBoard.set(1, 0, 1);
        Assertions.assertEquals(sudokuBoard.checkBoard(), checkHorizontal(sudokuBoard));

        BTSS.solve(sudokuBoard);
        //boxTest
        sudokuBoard.set(0,0, 1);
        sudokuBoard.set(2, 2, 1);
        Assertions.assertEquals(sudokuBoard.checkBoard(), checkBox(sudokuBoard));

    }
}
