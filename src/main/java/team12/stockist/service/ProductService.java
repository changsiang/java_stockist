package team12.stockist.service;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import team12.stockist.model.Product;

public interface ProductService 
{
	ArrayList<Product> findAllProduct();
	
	Product findProductById(Integer productId);
	
	Product createProduct(Product product);
	
	Product updateProduct(Product product);
	
	void deleteProduct(Product product);
	
	ArrayList<Product> findProductBySupplier(String supplierID);
	
	String addToCart(String cartPId, String qty, HttpSession session);
	
	
	
	ArrayList<Product> findAllProductByFilter(ArrayList<String> mfrFilters);
	
	ArrayList<Product> searchProduct(String input);
	
	ArrayList<Product> searchProductByFilters(String input, ArrayList<String> mfrFilters);
}