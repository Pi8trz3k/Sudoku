package org.example.exceptions;

import java.util.Locale;
import org.example.Messages;

public class NullValueException extends NullPointerException {
    private final String message;

    public NullValueException(String message) {
        this.message = message;
    }

    public String getLocalizedMessage(Locale locale) {
        return Messages.getMessage(message, locale);
    }
}
