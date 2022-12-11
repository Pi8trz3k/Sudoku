package org.example;

import java.util.Collections;
import java.util.List;

public abstract class SudokuFieldType implements Cloneable {
    public static final int size = 9;
    protected List<SudokuField> fields;

    public SudokuFieldType(List<SudokuField> fields) {
        this.fields = fields;
    }


    public int get(int index) {
        return fields.get(index).getFieldValue();
    }


    public boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int i2 = i + 1; i2 < 9; i2++) {
                if (fields.get(i).getFieldValue() == fields.get(i2).getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }


}
