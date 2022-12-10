package org.example;

import java.util.ArrayList;
import java.util.List;

public class SudokuRow extends SudokuFieldType implements Cloneable {
    public SudokuRow(List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public SudokuRow clone() {
        try {
            SudokuRow result = (SudokuRow) super.clone();
            for (int i = 0; i < size; i++) {
                result.fields.set(i, fields.get(i));
            }
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
