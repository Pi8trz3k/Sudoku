package org.example;

public class SudokuBoard {
    private final int size = 9;
    private final SudokuField[][] board = new SudokuField[size][size];
    private final SudokuSolver solver;

    public SudokuBoard(SudokuSolver solver) {
        this.solver = solver;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = new SudokuField();
            }
        }
    }

    public void solveGame() {
        solver.solve(this);
    }

    public void set(int x, int y, int number) {
        this.board[x][y].setFieldValue(number);
    }

    public int get(int x, int y) {
        return this.board[x][y].getFieldValue();
    }


    protected boolean checkBoard() {
        for (int i = 0; i < size; i++) {
            if (!getRow(i).verify() || !getColumn(i).verify()) {
                return false;
            }
        }

        for (int i = 0; i < (size / 3); i++) {
            for (int j = 0; j < (size / 3); j++) {
                if (!getBox(i, j).verify()) {
                    return false;
                }
            }

        }

        return true;
    }

    protected boolean checkHorizontal() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                for (int jj = x + 1; jj < size; jj++) {
                    if (board[x][y].getFieldValue() == board[jj][y].getFieldValue()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    protected boolean checkVertical() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                for (int jj = y + 1; jj < size; jj++) {
                    if (board[x][y].getFieldValue() == board[x][jj].getFieldValue()) {
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
        SudokuRow row = new SudokuRow();
        SudokuField[] fields = new SudokuField[SudokuFieldType.size];
        System.arraycopy(board[y], 0, fields, 0, 9);

        row.setValues(fields);
        return row;
    }

    public SudokuColumn getColumn(int x) {
        SudokuColumn column = new SudokuColumn();
        SudokuField[] fields = new SudokuField[SudokuFieldType.size];
        for (int i = 0; i < size; i++) {
            fields[i] = board[i][x];
        }

        column.setValues(fields);
        return column;
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
        SudokuBox box = new SudokuBox();
        box.setValues(fields);
        return box;
    }
}
