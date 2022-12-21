package org.example.exceptions;

public class SudokuBoardCloneFailureException extends CloneFailureException {
    private final String message;

    public SudokuBoardCloneFailureException() {
        this.message = "board clone failure";
    }

}
