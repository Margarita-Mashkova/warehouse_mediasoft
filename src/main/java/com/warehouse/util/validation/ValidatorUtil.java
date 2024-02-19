package com.warehouse.util.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Used to perform object validation using a validator.
 */
@Component
public class ValidatorUtil {
    private final Validator validator;

    public ValidatorUtil() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * Performs validation of this object using a validator.
     * If the error set is not empty, a ValidationException is thrown.
     * The ValidationException exception passes a set of error messages received from Constraint Violation.
     * @param object the validated object
     */
    public <T> void validate(T object) {
        final Set<ConstraintViolation<T>> errors = validator.validate(object);
        if(!errors.isEmpty()){
            throw new ValidationException(errors.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet()));
        }
    }
}
