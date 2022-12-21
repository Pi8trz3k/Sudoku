package org.example.exceptions;

public class CloneFailureException extends CloneNotSupportedException {

    public CloneFailureException() {
        String message = "cloneFailure";
    }
}
