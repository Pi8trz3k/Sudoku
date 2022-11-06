package org.example;

public abstract class SudokuFieldType {
    public static final int size = 9;
    protected SudokuField[] fields = new SudokuField[size];

    public SudokuFieldType() {
        for (int i = 0; i < size; i++) {
            fields[i] = new SudokuField();
        }
    }

    public void setValues(SudokuField[] values) {
        System.arraycopy(values, 0, fields, 0, 9);
    }

    public int get(int index) {
        return fields[index].getFieldValue();
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
