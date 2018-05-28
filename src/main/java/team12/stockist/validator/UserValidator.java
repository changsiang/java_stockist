package team12.stockist.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import team12.stockist.model.User;

import team12.stockist.service.UsageRecordService;

import team12.stockist.service.UserService;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserService userService;
	
	@Autowired
	UsageRecordService usageRecordService;
	


	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
	        
		ValidationUtils.rejectIfEmpty(errors, "id", "User ID cannot be empty");
		
		ValidationUtils.rejectIfEmpty(errors, "username", "User name cannot be empty");
		ValidationUtils.rejectIfEmpty(errors, "password", "Password cannot be empty");
		ValidationUtils.rejectIfEmpty(errors, "userRole", "User role cannot be empty");
		 
		System.out.println(user.toString());
	}

}
