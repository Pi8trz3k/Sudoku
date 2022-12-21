package org.example.exceptions;

public class SudokuFieldCloneFailureException extends CloneFailureException {
    private final String message;

    public SudokuFieldCloneFailureException() {
        this.message = "field clone failure";
    }

}
