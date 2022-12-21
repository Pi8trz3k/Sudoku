package org.example.exceptions;

public class CloneFailureException extends CloneNotSupportedException {
    private final String message;

    public CloneFailureException() {
        this.message = "clone failure";
    }
}
