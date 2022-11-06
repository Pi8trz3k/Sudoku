package org.example;

public class SudokuField {
    private int value;

    public SudokuField() {
        this.value = 0;
    }

    public SudokuField(int value) {
        this.value = value;
    }

    public int getFieldValue() {
        return this.value;
    }

    public void setFieldValue(int number) {
        if (number <= 9 && number >= 0) {
            this.value = number;
        }
    }
}
