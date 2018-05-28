package team12.stockist.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import team12.stockist.controller.Cart;

@Component
public class CartValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return Cart.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Cart cart = (Cart) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerName", "Error! Customer Name cannot be empty.", "Error! Customer Name cannot be empty.");
		// "error.cart.customerName.emptyOrWhitespace",
		
	}

}
