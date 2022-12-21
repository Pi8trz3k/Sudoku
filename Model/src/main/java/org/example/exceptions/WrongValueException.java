package org.example.exceptions;

import java.util.Locale;
import org.example.Messages;

public class WrongValueException extends IllegalArgumentException {
    private final String message;

    public WrongValueException(String message) {
        this.message = message;
    }

    public String getLocalizedMessage(Locale locale) {
        return Messages.getMessage(message, locale);
    }
}
