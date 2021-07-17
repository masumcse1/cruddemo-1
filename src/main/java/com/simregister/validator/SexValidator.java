package com.simregister.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SexValidator implements ConstraintValidator<Sex, String> {

    @Override
    public boolean isValid(String gender, ConstraintValidatorContext context) {
        if (gender == null) {
            return false;
        }

        return gender.equals("M") || gender.equals("F");
    }

}
