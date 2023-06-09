package org.example;

import com.google.common.base.Objects;
import java.io.Serializable;
import org.example.exceptions.SudokuFieldCloneFailureException;
import org.example.exceptions.SudokuFieldNullValueException;
import org.example.exceptions.SudokuFieldWrongValueException;

public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {
    private int value;
    private boolean editable;

    public SudokuField() {
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isEditable() {
        return editable;
    }

    public SudokuField(int value) {
        this.value = value;
    }

    public int getFieldValue() {
        return this.value;
    }

    public void setFieldValue(int number) {
        if (number > 9) {
            throw new SudokuFieldWrongValueException("valueBig");
        } else if (number < 0) {
            throw new SudokuFieldWrongValueException("valueSmall");
        } else {
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
    public SudokuField clone() throws SudokuFieldCloneFailureException {
        try {
            return (SudokuField) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new SudokuFieldCloneFailureException();
        }
    }

    @Override
    public int compareTo(SudokuField o) {
        if (o != null) {
            return Integer.compare(getFieldValue(), o.getFieldValue());
        } else {
            throw new SudokuFieldNullValueException("nullGiven");
        }
    }
}
