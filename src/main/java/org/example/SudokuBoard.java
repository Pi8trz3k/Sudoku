package org.example;

import java.util.Arrays;
import java.util.List;

public class SudokuBoard {
    private final int size = 9;
    private List<List<SudokuField>> board;
    private final SudokuSolver solver;

    public SudokuBoard(SudokuSolver solver) {
        this.solver = solver;
        board = Arrays.asList(new List[size]);

        for (int i = 0; i < size; i++) {
            board.set(i, Arrays.asList(new SudokuField[size]));
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board.get(i).set(j, new SudokuField());
            }
        }
    }

    public void solveGame() {
        solver.solve(this);
    }

    public void set(int x, int y, int number) {
        this.board.get(x).get(y).setFieldValue(number);
    }

    public int get(int x, int y) {
        return this.board.get(x).get(y).getFieldValue();
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

    private boolean comparisionBecauseLineIsTooLong(int x1, int y1, int x2, int y2) {
        return board.get(x1).get(y1).getFieldValue() == board.get(x2).get(y2).getFieldValue();
    }

    protected boolean checkHorizontal() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                for (int jj = x + 1; jj < size; jj++) {
                    if (comparisionBecauseLineIsTooLong(x, y, jj, y)) {
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
                    if (comparisionBecauseLineIsTooLong(x, y, x, jj)) {
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
                        if (board.get(i).get(j).getFieldValue() == a) {
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
        List<SudokuField> fields = Arrays.asList(new SudokuField[SudokuFieldType.size]);

        for (int i = 0; i < size; i++) {
            fields.set(i, board.get(y).get(i));
        }

        return new SudokuRow(fields);
    }

    public SudokuColumn getColumn(int x) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[SudokuFieldType.size]);
        for (int i = 0; i < size; i++) {
            fields.set(i, board.get(i).get(x));
        }

        return new SudokuColumn(fields);
    }

    public SudokuBox getBox(int x, int y) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[SudokuFieldType.size]);
        x = x - (x % 3);
        y = y - (y % 3);
        int fieldIndex = 0;
        for (int i = 0; i < SudokuBox.box_size; i++) {
            for (int j = 0; j < SudokuBox.box_size; j++) {
                fields.set(fieldIndex++, board.get(x + i).get(y + j));
            }
        }
        return new SudokuBox(fields);
    }
}
