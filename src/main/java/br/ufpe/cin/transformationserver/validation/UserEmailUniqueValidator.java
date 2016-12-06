package br.ufpe.cin.transformationserver.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.ufpe.cin.transformationserver.domain.User;
import br.ufpe.cin.transformationserver.service.UserService;

@Component
public class UserEmailUniqueValidator implements Validator{
	
	@Autowired
	private UserService userService;
	
	public boolean supports(Class<?> paramClass) {
		return User.class.equals(paramClass);
	}

	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "valid.email");
		User user = (User) obj;
		if(user.getId() == 0L){
			User userTest = userService.findByEmail(user.getEmail());

			if(userTest != null){
				errors.rejectValue("email", "User.email.Unique");
			}
		}
	}
}
