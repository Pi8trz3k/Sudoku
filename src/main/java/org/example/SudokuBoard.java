package org.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SudokuBoard {
    private int[][] board = new int[9][9];

    public static void shuffleFirstRow(int[][] board) {
        Integer[] firstRow = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> firstRowList = Arrays.asList(firstRow);
        Collections.shuffle(firstRowList);
        firstRowList.toArray(firstRow);
        for (int i = 0; i < 9; i++) {
            board[i][0] = firstRow[i];
        }
    }

    public void fillBoard() {
        shuffleFirstRow(this.board);
        solveBoard(this.board, 0, 1, 1);
    }

    public static boolean solveBoard(int[][] board, int x, int y, int number) {
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
            if (solveBoard(board, points[i][0], points[i][1], number)) {
                return true;
            } else {
                unFillPoint(board, points[i][0], points[i][1]);
            }
        }
        return false;
    }

    public static boolean isEnd(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int findAllRepeats(int[][] board, int number) {
        int repeats = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == number) {
                    repeats++;
                }
            }
        }
        return repeats;
    }

    public static int findAllPossiblePoints(int[][] board, int number, int[][] points) {
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

    public static void fillPoint(int[][] board, int x, int y, int number) {
        board[x][y] = number;
    }

    public static void unFillPoint(int[][] board, int x, int y) {
        board[x][y] = 0;
    }

    public static boolean isCorrect(int[][] board, int x, int y, int number) {

        if (board[x][y] != 0) {
            return false;
        }

        for (int i = 0; i < 9; i++) {
            if (board[i][y] == number || board[x][i] == number) {
                return false;
            }
        }

        return !usedInBox(board, x - (x % 3), y - (y % 3), number);
    }

    public static boolean usedInBox(int[][] board, int startRow, int startCol, int number) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row + startRow][col + startCol] == number) {
                    return true;
                }
            }
        }
        return false;
    }


    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(" " + this.board[j][i] + " ");
            }
            System.out.println(" ");
        }
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[][] getBoard() {
        int[][] sudokuBoard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(board[i], 0, sudokuBoard[i], 0, 9);
        }
        return sudokuBoard;
    }

}
