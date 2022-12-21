package org.example.exceptions;

import java.io.IOException;

public class DaoException extends IOException {
    private final String message;

    public DaoException(Throwable cause) {
        super(cause);
        this.message = "dao failure";
    }
}
