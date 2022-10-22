package org.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BacktrackingSudokuSolver implements SudokuSolver {


    @Override
    public void solve(SudokuBoard board) {
        shuffleFirstRow(board);
        solveBoard(board, 1);
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

    private static boolean solveBoard(SudokuBoard board, int number) {
        if (isEnd(board)) {
            return true;
        }
        if (findAllRepeats(board, number) >= 9) {
            number++;
        }
        int[][] points = new int[81][2];
        int numberOfPoints = findAllPossiblePoints(board, number, points);
        for (int i = 0; i < numberOfPoints; i++) {
            fillPoint(board, points[i][0], points[i][1], number);
            if (solveBoard(board, number)) {
                return true;
            } else {
                unFillPoint(board, points[i][0], points[i][1]);
            }
        }
        return false;
    }

    private static boolean isEnd(SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i, j) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int findAllRepeats(SudokuBoard board, int number) {
        int repeats = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i, j) == number) {
                    repeats++;
                }
            }
        }
        return repeats;
    }

    private static int findAllPossiblePoints(SudokuBoard board, int number, int[][] points) {
        int numberOfPoints = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (isCorrect(board, i, j, number)) {
                    points[numberOfPoints][0] = i;
                    points[numberOfPoints][1] = j;
                    numberOfPoints++;
                }
            }
        }
        return numberOfPoints;
    }

    private static void fillPoint(SudokuBoard board, int x, int y, int number) {
        board.set(x, y, number);//[x][y] = number;
    }

    private static void unFillPoint(SudokuBoard board, int x, int y) {
        board.set(x, y, 0);//[x][y] = 0;
    }

    private static boolean isCorrect(SudokuBoard board, int x, int y, int number) {

        if (board.get(x, y) != 0) {
            return false;
        }

        for (int i = 0; i < 9; i++) {
            if (board.get(i, y) == number || board.get(x, i) == number) {
                return false;
            }
        }

        return !usedInBox(board, x - (x % 3), y - (y % 3), number);
    }

    private static boolean usedInBox(SudokuBoard board, int startRow, int startCol, int number) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.get(row + startRow, col + startCol) == number) {
                    return true;
                }
            }
        }
        return false;
    }

}
