package org.example;

public class SudokuBoard {
    private final int[][] board = new int[9][9];
    private final SudokuSolver solver;

    public SudokuBoard(SudokuSolver solver) {
        this.solver = solver;
    }

    public void solveGame() {
        solver.solve(this);
    }

    public void set(int row, int column, int number) {
        this.board[row][column] = number;
    }

    public int get(int row, int column) {
        return this.board[row][column];
    }


    public boolean checkBoard() {
        return (checkVertical() && checkHorizontal() && checkSquare());
    }

    protected boolean checkVertical() {
        for (int column = 0; column < 9; column++) {
            for (int row = 0; row < 9; row++) {
                for (int jj = row + 1; jj < 9; jj++) {
                    if (board[column][row] == board[column][jj]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    protected boolean checkHorizontal() {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                for (int jj = column + 1; jj < 9; jj++) {
                    if (board[column][row] == board[jj][row]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    protected boolean checkSquare() {
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
