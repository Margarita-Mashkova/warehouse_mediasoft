package com.warehouse.util.validation;

import java.util.Set;


/**
 * A custom exception that can be thrown when errors occur during the data validation process.
 */
public class ValidationException extends RuntimeException{
    /**
     * Combines lines from a set of errors into a single line separated by a newline character.
     * The error message will be used as an error message for the exception.
     * @param errors set of errors
     */
    public <T> ValidationException(Set<String> errors) {
        super(String.join("\n", errors));
    }
}
