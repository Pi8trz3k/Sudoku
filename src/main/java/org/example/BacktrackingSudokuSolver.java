package org.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BacktrackingSudokuSolver implements SudokuSolver {


    @Override
    public void solve(SudokuBoard board) {
        shuffleFirstRow(board);
        solveBoard(board, 0, 0);
    }

    private static void shuffleFirstRow(SudokuBoard board) {
        Integer[] firstRow = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> firstRowList = Arrays.asList(firstRow);
        Collections.shuffle(firstRowList);
        firstRowList.toArray(firstRow);
        for (int i = 0; i < 9; i++) {
            board.set(i, 0, firstRow[i]); //[i][0] = firstRow[i];
        }
    }

    private static boolean solveBoard(SudokuBoard board, int x, int y) {
        if (y == 8 && x == 9) {
            return true;
        }
        if (x == 9) {
            y++;
            x = 0;
        }
        if (board.get(x, y) != 0) {
            return solveBoard(board, x + 1, y);
        }

        for (int num = 1; num < 10; num++) {
            if (isCorrect(board, x, y, num)) {
                board.set(x, y, num);
                if (solveBoard(board, x + 1, y)) {
                    return true;
                }
            }
            board.set(x, y, 0);
        }
        return false;
    }


    private static boolean isCorrect(SudokuBoard board, int x, int y, int number) {
        for (int i = 0; i < 9; i++) {
            if (board.get(i, y) == number) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (board.get(x, i) == number) {
                return false;
            }
        }
        int a = x - x % 3;
        int b = y - y % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.get(a + i, b + j) == number) {
                    return false;
                }
            }
        }
        return true;
    }
}

