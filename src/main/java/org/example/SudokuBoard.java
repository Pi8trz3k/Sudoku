package org.example;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.Arrays;
import java.util.List;

public class SudokuBoard {
    private final int size = 9;
    private final List<SudokuField> board;
    private final SudokuSolver solver;

    public SudokuBoard(SudokuSolver solver) {
        this.solver = solver;
        board = Arrays.asList(new SudokuField[size * size]);

        for (int i = 0; i < size * size; i += 9) {
            for (int j = 0; j < size; j++) {
                this.board.set(i + j, new SudokuField());
            }
        }
    }

    public void solveGame() {
        solver.solve(this);
    }

    public void set(int x, int y, int number) {
        this.board.get(x * 9 + y).setFieldValue(number);
    }

    public int get(int x, int y) {
        return this.board.get(x * 9 + y).getFieldValue();
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
        return board.get(x1 * 9 + y1).getFieldValue() == board.get(x2 * 9 + y2).getFieldValue();
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
                        if (board.get(i * 9 + j).getFieldValue() == a) {
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
            fields.set(i, board.get(y * 9 + i));
        }

        return new SudokuRow(fields);
    }

    public SudokuColumn getColumn(int x) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[SudokuFieldType.size]);
        for (int i = 0; i < size; i++) {
            fields.set(i, board.get(i * 9 + x));
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
                fields.set(fieldIndex++, board.get((x + i) * 9 + y + j));
            }
        }
        return new SudokuBox(fields);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("", board)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuBoard that = (SudokuBoard) o;
        return Objects.equal(board, that.board);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(board);
    }

}
