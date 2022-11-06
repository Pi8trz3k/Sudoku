package org.example;

public class SudokuField {
    private int value;

    public SudokuField() {

    }

    public SudokuField(int value) {
        this.value = value;
    }

    public int getFieldValue() {
        return this.value;
    }

    public void setFieldValue(int number) {
        if (number > 0 && number < 10) {
            this.value = number;
        }
    }
}
