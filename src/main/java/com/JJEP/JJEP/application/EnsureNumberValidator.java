package com.JJEP.JJEP.application;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnsureNumberValidator implements ConstraintValidator<EnsureNumber, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String regex = "-?[0-9]+(\\.[0-9]+)?";
        String data = String.valueOf(value);
        return data.matches(regex);
    }
}