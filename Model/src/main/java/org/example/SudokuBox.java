package org.example;

import java.util.List;

public class SudokuBox extends SudokuFieldType {
    public static final int box_size = 3;

    public SudokuBox(List<SudokuField> fields) {
        super(fields);
    }
}
