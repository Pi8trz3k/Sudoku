package org.example;

import java.util.ArrayList;
import java.util.List;

public class SudokuRow extends SudokuFieldType implements Cloneable {
    public SudokuRow(List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public SudokuRow clone() {
        List<SudokuField> fieldsList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            fieldsList.add(this.fields.get(i).clone());
        }
        return new SudokuRow(fieldsList);
    }
}
