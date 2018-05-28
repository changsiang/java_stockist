
package team12.stockist.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import team12.stockist.model.Product;



@Component
public class NewProductValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> arg0) 
	{
		// TODO Auto-generated method stub
		return Product.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1)
	{
		// TODO Auto-generated method stub
		
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "partID" , "error.studentName", "PartID is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "description" , "error.studentNickName", "Description is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "unitPrice" , "error.studentNickName", "Unit price is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "color" , "error.studentNickName", "Color is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "dimension" , "error.studentNickName", "Dimension is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "manufacturer" , "error.studentNickName", "Manufacturer is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "reorderLevel" , "error.studentNickName", "Reorder Level is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "minReorderQty" , "error.studentNickName", "Minimum Reorder Quantity is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "shelfLocation" , "error.studentNickName", "Shelf Location is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "supplierID" , "error.studentNickName", "SupplierID is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "UnitsInStock" , "error.studentNickName", "Quantity is required");
	}
}
