package org.example;

import java.util.ArrayList;
import java.util.List;

public class SudokuBox extends SudokuFieldType implements Cloneable {
    public static final int box_size = 3;

    public SudokuBox(List<SudokuField> fields) {
        super(fields);
    }

    @Override
    protected SudokuBox clone() throws CloneNotSupportedException {
        List<SudokuField> fieldsList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            fieldsList.add(this.fields.get(i).clone());
        }
        return new SudokuBox(fieldsList);

    }
}
