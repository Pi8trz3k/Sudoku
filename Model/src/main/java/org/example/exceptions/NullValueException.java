package org.example.exceptions;

public class NullValueException extends NullPointerException {
    private final String message;

    public NullValueException(String message) {
        this.message = message;
    }
}
