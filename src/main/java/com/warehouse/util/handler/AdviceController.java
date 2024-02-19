package com.warehouse.util.handler;

import com.warehouse.service.exception.CategoryNotFoundException;
import com.warehouse.service.exception.ProductNotFoundException;
import com.warehouse.util.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Defining a global exception handler
 */
@ControllerAdvice
public class AdviceController {
    /**
     * Handles exceptions of types ValidationException, ProductNotFoundException, CategoryNotFoundException.
     *
     * @param e exception or error object
     * @return a ResponseEntity object with the exception message and an HTTP status code of HttpStatus.BAD_REQUEST.
     */

    @ExceptionHandler({
            ValidationException.class,
            ProductNotFoundException.class,
            CategoryNotFoundException.class
    })
    public ResponseEntity<Object> handleException(Throwable e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles any other exceptions that are not specifically handled. It prints the stack trace of the exception.
     *
     * @param e exception or error object
     * @return a ResponseEntity object with the exception message and an HTTP status code of HttpStatus.INTERNAL_SERVER_ERROR.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnknownException(Throwable e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
