package team12.stockist.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import team12.stockist.model.Product;



@Component
public class ProductUpdateValidator implements Validator {
	
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
		
		
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "partID" , "error.productId", "PartID");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "description" , "error.productDescription", "Description");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "unitPrice" , "error.productUnitprice", "Unit price");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "color" , "error.productColor", "Color");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "dimension" , "error.productDimension", "Dimension");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "manufacturer" , "error.productManufacturer", "Manufacturer ");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "reorderLevel" , "error.productReorderLevel", "Reorder Level");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "minReorderQty" , "error.productMinOrderQty", "Minimum");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "shelfLocation" , "error.productShelfLocation", "Shelf");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "supplierID" , "error.productSupplierId", "SupplierID");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "unitsOnOrder" , "error.productUnitsOnOrder", "Quantity");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "unitsInStock" , "error.productUnitsInStock", "UnitsInStock ");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "discontinued" , "error.productDiscontinued", "Discontinue");
		


		
			
		
		
		
		
			
		}
}
		
		
		
	
