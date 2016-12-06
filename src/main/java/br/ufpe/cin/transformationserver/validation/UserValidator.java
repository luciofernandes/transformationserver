package br.ufpe.cin.transformationserver.validation;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.ufpe.cin.transformationserver.domain.User;

public class UserValidator implements Validator{

	@Autowired
	private javax.validation.Validator beanValidator;
	private Set<Validator> springValidators;
	
	public UserValidator(){
		springValidators = new HashSet<Validator>();
	}
	
	public void setSpringValidators(Set<Validator>springValidators) {
		this.springValidators = springValidators;
	}
	
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Set<ConstraintViolation<Object>> constraintViolations = beanValidator.validate(target);
		
		for (ConstraintViolation<Object> constraintViolation :constraintViolations) {
			String propertyPath	= constraintViolation.getPropertyPath().toString();
			String message = constraintViolation.getMessage();
			errors.rejectValue(propertyPath, "", message);
		}
		
		for(Validator validator: springValidators) {
			validator.validate(target, errors);
		}
	}

}
