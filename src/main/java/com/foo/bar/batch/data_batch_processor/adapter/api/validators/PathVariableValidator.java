package com.foo.bar.batch.data_batch_processor.adapter.api.validators;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PathVariableValidator implements ConstraintValidator<ValidNumber, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value >= 0 && value <= 100;
    }
}
