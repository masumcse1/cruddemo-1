package com.simregister.validator;

import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.simregister.model.Sim;

public class BeanValidator {

	public static Validator createValidator() {
		Configuration<?> config = Validation.byDefaultProvider().configure();
		ValidatorFactory factory = config.buildValidatorFactory();
		Validator validator = factory.getValidator();
		factory.close();
		return validator;
	}

	private static void printError(ConstraintViolation<?> violation) {
		Sim sim = (Sim)violation.getRootBean();
		System.out.println("Registration Rejected for ------->"+sim.getMsisdn());
		System.out.println(sim.getMsisdn()+"-------->"+violation.getPropertyPath() + " " + violation.getMessage());
		
		
	}

	public static boolean checkValidator(Sim sim) {
		boolean isValid=false;
		Validator validator = BeanValidator.createValidator();
		Set<ConstraintViolation<Sim>> violations = validator.validate(sim);
		if (violations.size() == 0) {
			//System.out.println("No violations.");
			isValid=true;
		} else {
			//System.out.printf("%s violations:%n", violations.size());
			violations.stream().forEach(BeanValidator::printError);
			isValid=false;
		}
		
		return isValid;

	}

}
