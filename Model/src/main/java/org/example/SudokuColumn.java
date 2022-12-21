package org.example;

import java.util.ArrayList;
import java.util.List;
import org.example.exceptions.SudokuFieldCloneFailureException;

public class SudokuColumn extends SudokuFieldType implements Cloneable {
    public SudokuColumn(List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public SudokuColumn clone() throws SudokuFieldCloneFailureException {
        try {
            List<SudokuField> fieldsList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                fieldsList.add(this.fields.get(i).clone());
            }
            return new SudokuColumn(fieldsList);
        } catch (CloneNotSupportedException ex) {
            throw new SudokuFieldCloneFailureException();
        }
    }
}
