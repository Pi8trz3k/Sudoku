package org.example;

public class SudokuBoard {
    private final SudokuField[][] board = new SudokuField[9][9];
    private final SudokuSolver solver;

    public SudokuBoard(SudokuSolver solver) {
        this.solver = solver;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board[i][j] = new SudokuField();
            }
        }
    }

    public void solveGame() {
        solver.solve(this);
    }

    public void set(int row, int column, int number) {
        this.board[row][column].setFieldValue(number);
    }

    public int get(int row, int column) {
        return this.board[row][column].getFieldValue();
    }


    public boolean checkBoard() {
        return (checkVertical() && checkHorizontal() && checkSquare());
    }

    protected boolean checkVertical() {
        for (int column = 0; column < 9; column++) {
            for (int row = 0; row < 9; row++) {
                for (int jj = row + 1; jj < 9; jj++) {
                    if (board[column][row].getFieldValue() == board[column][jj].getFieldValue()) {
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
                    if (board[column][row].getFieldValue() == board[jj][row].getFieldValue()) {
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
                        if (board[i][j].getFieldValue() == a) {
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

    public SudokuRow getRow(int y) {
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
        int fieldIndex = 0;
        for (int i = 0; i < SudokuBox.box_size; i++) {
            for (int j = 0; j < SudokuBox.box_size; j++) {
                fields[fieldIndex++] = board[x * 3 + i][y * 3 + j];
            }
        }

        return new SudokuBox(fields);
    }
}
