package org.example;

public class SudokuBoard {
    private final int[][] board = new int[9][9];
    private final SudokuSolver solver;

    public SudokuBoard(SudokuSolver solver) {
        this.solver = solver;
        /*for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board[i][j] = new SudokuField();
            }
        }*/
    }

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

    protected boolean checkHorizontal() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                for (int jj = x + 1; jj < 9; jj++) {
                    if (board[x][y] == board[jj][y]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    protected boolean checkVertical() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                for (int jj = y + 1; jj < 9; jj++) {
                    if (board[x][y] == board[x][jj]) {
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

   /* public SudokuRow getRow(int y) {
        SudokuField[] fields = new SudokuField[SudokuFieldType.size];
        System.arraycopy(board[y], 0, fields, 0, 9);

        return new SudokuRow(fields);
    }

    public SudokuColumn getColumn(int x) {
        SudokuField[] fields = new SudokuField[SudokuFieldType.size];
        for (int i = 0; i < 9; i++) {
            fields[i] = board[i][x];
        }

        return new SudokuColumn(fields);
    }

    public SudokuBox getBox(int x, int y) {
        SudokuField[] fields = new SudokuField[SudokuFieldType.size];
        x = x - (x % 3);
        y = y - (y % 3);
        int fieldIndex = 0;
        for (int i = 0; i < SudokuBox.box_size; i++) {
            for (int j = 0; j < SudokuBox.box_size; j++) {
                fields[fieldIndex++] = board[x + i][y + j];
            }
        }

        return new SudokuBox(fields);
    }*/
}
