package org.example;

import java.util.ArrayList;
import java.util.List;

public class SudokuBox extends SudokuFieldType implements Cloneable {
    public static final int box_size = 3;

    public SudokuBox(List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public SudokuBox clone() {
        try {
            SudokuBox result = (SudokuBox) super.clone();
            for (int i = 0; i < size; i++) {
                result.fields.set(i, fields.get(i));
            }
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
