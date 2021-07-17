package com.simregister.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SimTypeValidator implements ConstraintValidator<SimType, String> {

    @Override
    public boolean isValid(String simType, ConstraintValidatorContext context) {
        if (simType == null) {
            return false;
        }

        return simType.equals("PREPAID") || simType.equals("POSTPAID");
    }

}
