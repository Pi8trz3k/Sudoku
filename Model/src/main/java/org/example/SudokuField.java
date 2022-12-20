package org.example;

import com.google.common.base.Objects;
import java.io.Serializable;

public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {
    private int value;
    private boolean empty;

    public SudokuField() {
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public boolean isEmpty() {
        return empty;
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

    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuField that = (SudokuField) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public SudokuField clone() {
        try {
            return (SudokuField) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public int compareTo(SudokuField o) {
        return Integer.compare(getFieldValue(), o.getFieldValue());
    }
}
