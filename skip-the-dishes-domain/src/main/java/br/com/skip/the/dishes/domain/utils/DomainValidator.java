package br.com.skip.the.dishes.domain.utils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public final class DomainValidator {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> void validate(T instance){
        Set<ConstraintViolation<T>> validate = validator.validate(instance);

        if (!validate.isEmpty()) {
            throw new ConstraintViolationException(validate);
        }
    }

    private DomainValidator() { }

}
