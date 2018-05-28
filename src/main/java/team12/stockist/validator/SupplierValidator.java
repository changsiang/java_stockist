package team12.stockist.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import team12.stockist.model.Supplier;

@Component
public class SupplierValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Supplier.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Supplier suppliers = (Supplier) target;
		ValidationUtils.rejectIfEmpty(errors, "supplierID", "Supplier ID cannot be empty");
		ValidationUtils.rejectIfEmpty(errors, "companyName", "Company name cannot be empty");
		ValidationUtils.rejectIfEmpty(errors, "contactNumber", "Contact number cannot be empty");
		ValidationUtils.rejectIfEmpty(errors, "address", "Address cannot be empty");
		System.out.println(suppliers.toString());
	}

}
