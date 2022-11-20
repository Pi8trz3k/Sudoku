package org.example;

import  org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuFieldTest {

    private final SudokuField field = new SudokuField();
    private final int value1 = 5;

    @Test
    void constructorTestWithNumber() {
        SudokuField field1 = new SudokuField(value1);
        assertEquals(field1.getFieldValue(), value1);
    }

    @Test
    void constructorTestWithoutNumber() {
        SudokuField field2 = new SudokuField();
        assertEquals(field2.getFieldValue(), 0);
    }

    @Test
    void setGetTest() {
        field.setFieldValue(value1);
        assertEquals(field.getFieldValue(),value1);
    }

    @Test
    void setBadPositiveValueTest() {
        final int value2 = 10;
        field.setFieldValue(value1);
        field.setFieldValue(value2);
        assertEquals(field.getFieldValue(),value1);
    }

    @Test
    void setBadNegativeValueTest() {
        final int value3 = -3;
        field.setFieldValue(value1);
        field.setFieldValue(value3);
        assertEquals(field.getFieldValue(),value1);
    }

    @Test
    void toStringMethodTest() {
        String number = "5";
        field.setFieldValue(5);
        assertEquals(field.toString(), number);
    }

    @Test
    void equalsMethodTest() {
        SudokuField field = new SudokuField(4);
        SudokuField field2 = new SudokuField(5);
        SudokuField field3 = new SudokuField();
        SudokuField field4 = new SudokuField(4);
        SudokuField field5 = null;

        assertFalse(field.equals(field2));
        assertFalse(field.equals(field3));
        assertFalse(field.equals(field5));
        assertTrue(field.equals(field4));
        assertTrue(field.equals(field));
    }

    @Test
    void hashCodeMethodTest() {
        SudokuField field = new SudokuField(1);
        SudokuField field2 = new SudokuField(2);
        SudokuField field3 = new SudokuField();
        SudokuField field4 = new SudokuField(1);

        assertNotEquals(field.hashCode(), field2.hashCode());
        assertNotEquals(field.hashCode(), field3.hashCode());
        assertEquals(field.hashCode(), field4.hashCode());
    }
}
