package org.example.exceptions;

import java.util.Locale;
import org.example.Messages;

public class CloneFailureException extends CloneNotSupportedException {

    String message;

    public CloneFailureException() {
        String message = "cloneFailure";
    }

    public String getLocalizedMessage(Locale locale) {
         return Messages.getMessage(message, locale);
    }
}
