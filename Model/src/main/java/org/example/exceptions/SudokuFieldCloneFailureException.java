package org.example.exceptions;

public class SudokuFieldCloneFailureException extends CloneFailureException {

    public SudokuFieldCloneFailureException() {
        String message = "fieldCloneFailure";
    }

}
