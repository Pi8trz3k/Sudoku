package org.example;

public class SudokuBoard {
    private final int[][] board = new int[9][9];
    BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();

    public void solveGame() {
        solver.solve(this);
    }

    public void set(int x, int y, int number) {
        this.board[x][y] = number;
    }

    public int get(int x, int y) {
        return this.board[x][y];
    }


    public boolean checkBoard() {
        return (checkVertical() && checkHorizontal() && checkSquare());
    }

    public boolean checkVertical() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int jj = j + 1; jj < 9; jj++) {
                    if (board[i][j] == board[i][jj]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean checkHorizontal() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int jj = j + 1; jj < 9; jj++) {
                    if (board[j][i] == board[jj][i]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean checkSquare() {
        int powt = 0;
        for (int a = 1; a < 10; a++) {
            for (int z = 0; z < 9; z++) {
                for (int i = z % 3 * 3; i < z % 3 * 3 + 3; i++) {
                    for (int j = z / 3 * 3; j < z / 3 * 3 + 3; j++) {
                        if (board[i][j] == a) {
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
}
