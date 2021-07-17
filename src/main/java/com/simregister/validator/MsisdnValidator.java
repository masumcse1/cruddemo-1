package com.simregister.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class MsisdnValidator implements ConstraintValidator<Msisdn, String> {
	
	

    @Override
    public boolean isValid(String msisdn, ConstraintValidatorContext context) {
    	
    	 String countryCode = "TH";
    	 PhoneNumberUtil numberUtil = PhoneNumberUtil.getInstance();

		  PhoneNumber phoneNumber = null;
		try {
			phoneNumber = numberUtil.parse(msisdn, countryCode);
		} catch (NumberParseException e) {
		
		}
		
	
		return  numberUtil.isValidNumber(phoneNumber);

       
    }

}
