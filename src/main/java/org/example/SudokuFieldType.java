package org.example;

public abstract class SudokuFieldType {
    public static final int size = 9;
    protected SudokuField[] fields;

    public SudokuFieldType(final SudokuField[] fields) {
        if (fields.length == size) {
            this.fields = fields;
        }
    }

    public boolean verify() {
        for (int i = 0; i < 9; i++) {
            for (int i2 = i + 1; i2 < 9; i2++) {
                if (fields[i].getFieldValue() == fields[i2].getFieldValue()) {
                    return false;
                }
            }
        }

        return true;
    }


}
