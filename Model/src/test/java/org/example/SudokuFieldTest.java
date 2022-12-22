package org.example;

import org.example.exceptions.SudokuFieldCloneFailureException;
import org.example.exceptions.SudokuFieldNullValueException;
import org.example.exceptions.SudokuFieldWrongValueException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuFieldTest {

    private final SudokuField field = new SudokuField();
    private final int value1 = 5;
    private static final Logger logger = LoggerFactory.getLogger(SudokuFieldTest.class);

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
        try {
            field.setFieldValue(value2);
        } catch (SudokuFieldWrongValueException ex) {
            logger.info("Caught too high value exception");
            logger.info(ex.getLocalizedMessage(new Locale("en")));
            logger.info(ex.getLocalizedMessage(new Locale("pl")));
        }
        assertEquals(field.getFieldValue(),value1);
    }

    @Test
    void setBadNegativeValueTest() {
        final int value3 = -3;
        field.setFieldValue(value1);
        try {
            field.setFieldValue(value3);
        } catch (SudokuFieldWrongValueException ex) {
            logger.info("Caught too low value exception");
            logger.info(ex.getLocalizedMessage(new Locale("en")));
            logger.info(ex.getLocalizedMessage(new Locale("pl")));
        }
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
        Integer a = 5;

        assertNotEquals(field, field2);
        assertNotEquals(field, field3);
        assertNotEquals(field, field5);
        assertNotEquals(field, a);
        assertEquals(field, field4);
        assertEquals(field, field);
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

    @Test
    public void cloneTest() {
        SudokuField sudokuField = new SudokuField(8);
        SudokuField sudokuFieldClone = null;

        try {
            sudokuFieldClone = sudokuField.clone();
        } catch(SudokuFieldCloneFailureException ex) {
            System.out.println(ex.getMessage());
        }
        sudokuField.setFieldValue(2);
        assertEquals(sudokuField.getFieldValue(), 2);
        try {
            assert sudokuFieldClone != null;
            assertEquals(sudokuFieldClone.getFieldValue(), 8);
        } catch (SudokuFieldNullValueException ex) {
            logger.info("Caught null value exception");
            logger.info(ex.getLocalizedMessage(new Locale("en")));
            logger.info(ex.getLocalizedMessage(new Locale("en")));
        }
        assertNotEquals(sudokuField, sudokuFieldClone);
    }

    @Test
    public void compareToTest() {
        SudokuField field = new SudokuField(4);
        SudokuField field3 = null;
        assertThrows(SudokuFieldNullValueException.class,() -> field.compareTo(field3));

        SudokuField field2 = null;

        field2 = new SudokuField();
        field.setFieldValue(5);

        field2.setFieldValue(5);
        assertEquals(field.compareTo(field2), 0);

        field2.setFieldValue(6);
        assertEquals(field.compareTo(field2), -1);

        field2.setFieldValue(4);
        assertEquals(field.compareTo(field2), 1);
    }
}
