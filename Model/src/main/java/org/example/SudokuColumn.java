package org.example;

import java.util.ArrayList;
import java.util.List;

public class SudokuColumn extends SudokuFieldType implements Cloneable {
    public SudokuColumn(List<SudokuField> fields) {
        super(fields);
    }


    @Override
    public SudokuColumn clone() {
        try {
            SudokuColumn result = (SudokuColumn) super.clone();
            for (int i = 0; i < size; i++) {
                result.fields.set(i, fields.get(i));
            }
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
