package com.foo.bar.batch.data_batch_processor.adapter.api.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PathVariableValidator.class)
public @interface ValidNumber {
    String message() default "Number must be between 0 and 100";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
