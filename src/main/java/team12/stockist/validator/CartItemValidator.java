package team12.stockist.validator;

import java.util.regex.Pattern;

import org.hibernate.id.insert.IdentifierGeneratingInsert;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import team12.stockist.controller.CartItem;

@Component
public class CartItemValidator implements Validator{

	private static final String regex = "\\d+";
	
	@Override
	public boolean supports(Class<?> arg0) {
		return CartItem.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object object, Errors errors) {
		CartItem cartItem = (CartItem) object;
		ValidationUtils.rejectIfEmpty(errors, "quantity", "error.cartitem.quantity.empty");

		if(!Pattern.matches(regex, String.valueOf(cartItem.getQuantity()))) {
			errors.rejectValue("quantity", "Error! Please enter a valid integer", "Error! Please enter a valid integer");
		}

		if(cartItem.getQuantity() <= 0) {
			errors.rejectValue("quantity", "Error! Quantity must be MORE than 0", "Error! Quantity must be MORE than 0");
		}
		
		if(cartItem.getProduct().getUnitsInStock() < cartItem.getQuantity()) {
			errors.rejectValue("quantity","Error! Quantity must be LESS than available stock", "Error! Quantity must be LESS than available stock: " + cartItem.getProduct().getUnitsInStock());
		}

	}
}
