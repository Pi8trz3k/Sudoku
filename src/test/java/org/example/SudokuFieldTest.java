package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class SudokuFieldTest {

    private final SudokuField field = new SudokuField();
    private final int value1 = 5;

    @Test
    void constructorTestWithNumber() {
        SudokuField field1 = new SudokuField(value1);
        Assertions.assertEquals(field1.getFieldValue(), value1);
    }

    @Test
    void constructorTestWithoutNumber() {
        SudokuField field2 = new SudokuField();
        Assertions.assertEquals(field2.getFieldValue(), 0);
    }

    @Test
    void setGetTest() {
        field.setFieldValue(value1);
        Assertions.assertEquals(field.getFieldValue(),value1);
    }

    @Test
    void setBadPositiveValueTest() {
        final int value2 = 10;
        field.setFieldValue(value1);
        field.setFieldValue(value2);
        Assertions.assertEquals(field.getFieldValue(),value1);
    }

    @Test
    void setBadNegativeValueTest() {
        final int value3 = -3;
        field.setFieldValue(value1);
        field.setFieldValue(value3);
        Assertions.assertEquals(field.getFieldValue(),value1);
    }

}
