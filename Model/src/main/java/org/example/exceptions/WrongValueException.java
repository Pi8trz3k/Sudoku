package org.example.exceptions;

public class WrongValueException extends IllegalArgumentException {
    private final String message;

    public WrongValueException(String message) {
        this.message = message;
    }
}
