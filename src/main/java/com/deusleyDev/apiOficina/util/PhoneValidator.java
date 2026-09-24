package com.deusleyDev.apiOficina.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    private static final String PHONE_REGEX = "^\\(\\d{2}\\)\\d{9}$";


    @Override
    public void initialize(Phone constraintAnnotation) {


    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        return Pattern.matches(PHONE_REGEX, value);

    }
}