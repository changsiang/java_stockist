package team12.stockist.service;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import team12.stockist.controller.Cart;
import team12.stockist.controller.CartItem;
import team12.stockist.model.Product;
import team12.stockist.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService {
	
	@Resource
	private ProductRepository prepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see team12.stockist.service.ProductService#findAllProduct()
	 */
	@Override
	@Transactional
	public ArrayList<Product> findAllProduct() {
		ArrayList<Product> sl = (ArrayList<Product>) prepo.findAll();
		return sl;
	}

	@Override
	@Transactional
	public Product findProductById(Integer productId) {
		return prepo.findOne(productId);
	}

	@Override
	@Transactional
	public Product createProduct(Product product) {
		return prepo.saveAndFlush(product);
	}

	@Override
	@Transactional
	public Product updateProduct(Product product) {
		return prepo.saveAndFlush(product);
	}

	@Override
	@Transactional
	public void deleteProduct(Product product) {
		prepo.delete(product);

	}

	@Override
	@Transactional
	public ArrayList<Product> findProductBySupplier(String supplierID) {
		return prepo.findProductBySupplier(supplierID);
	}
	
	
	
	
	
	
	@Override
	@Transactional
	public ArrayList<Product> findAllProductByFilter(ArrayList<String> mfrFilters)         
	{
		String temp = mfrFilters.get(0);
		ArrayList<Product> output = (ArrayList<Product>) prepo.findAllByFilter(temp);
		
		// If there are more than one filters
		if (mfrFilters.size() >= 1)
		{
			for (int i = 1; i < mfrFilters.size(); i++) {
				
				temp = mfrFilters.get(i);
				output.addAll((ArrayList<Product>) prepo.findAllByFilter(temp));
			}
			
			try   // Remove any repeated entries
			{
				output = DuplicateRemover(output);
			}
			catch(NullPointerException e)
	        {
	            System.out.print("NullPointerException caught");
	        }
		}
		
		return output;
	}
	
	
	
	
	
	
	
	//-------------------- ADD TO CART --------------------------//

	@Override
	@Transactional        
	public String addToCart(String cartPId, String qty, HttpSession session)
	{
		Cart sessCart = (Cart) session.getAttribute("cart");
		boolean check = true;
		int cartQty;   
		int pdtID;
		int tempQty = 0;
		String msg = "";

		
		// Checking if it can be parse into an int first
		try {
			cartQty = Integer.parseInt(qty);
			pdtID = Integer.parseInt(cartPId);
		} catch (Exception ex) {
			
			// If exception is caught, then input is not an integer.
			msg = "You have entered an invalid field in the cart. Please try again.";
			return msg;  // If error, will throw error msg.
		}
		
		
		// Determine current stock in the inventory
		Product tempPdt = prepo.findOne(pdtID);     // Based on the PartID that the user has selected
		int currentQty = tempPdt.getUnitsInStock();
		
		
		// Det all our needed arrays
		ArrayList<CartItem> cartArray = sessCart.getCartItemList();
		ArrayList<CartItem> tempArray = new ArrayList<CartItem>();
		
		
		
		if (tempPdt.getDiscontinued().equals(1))
		{
			return "Sorry, this item has been discontinued. Please contact administrator.";
		}
		
		if (currentQty >= cartQty && cartQty > 0)
		{
			// Add our details as our cart items
			CartItem tempItem = new CartItem();
			
			// Adding cart items to our cart
			if (sessCart.getCartItemList() == null)
			{
				tempItem.setProduct(tempPdt);
				tempItem.setQuantity(cartQty);


				tempArray.add(tempItem);
			}
			else 
			{
				for (int i = 0; i < cartArray.size(); i++) {
					
					Product temp = cartArray.get(i).getProduct();
					if (tempPdt.getPartID().equals(temp.getPartID()))
					{
						tempQty = cartArray.get(i).getQuantity();   // This will det the qty of the existing pdt in the cart
						tempQty = tempQty + cartQty;
						
						
						// Check if the qty selected, combined with the existing qty inside the cart is more than the existing inventory
						if (tempQty > currentQty)
						{
							msg = "Quantity selected has exceeded existing stock.";
							return msg;  // If more than, will throw error msg.
						}
						else
						{
							tempItem.setProduct(tempPdt);
							tempItem.setQuantity(tempQty);

							// Removes the existing ArrayList in the cart
							cartArray.remove(i);
							
							
							// Creates a new arrayList for that specific product and add the new qty to it
							tempArray.addAll(cartArray);
							tempArray.add(tempItem);
						}
						check = false;
						break;
					}
				}
				
				if (check == true)
				{
					tempItem.setProduct(tempPdt);
					tempItem.setQuantity(cartQty);
					
					tempArray.addAll(cartArray);
					tempArray.add(tempItem);
				}
			}
			
			// Set the new ArrayList<CartItem>
			sessCart.setCartItemList(tempArray);
			
			// Setting our object back to our session
			session.setAttribute("cart", sessCart);
			msg = "Successfully added to cart.";
			
		}
		else 
		{
			msg = "You have entered an invalid value in the cart. Please try again.";
		}
		
		
		
		return msg;
	}
	
	
	


	
	
	
	//-------------------- SEARCH --------------------------//

	@Override
	@Transactional        // General search without filters
	public ArrayList<Product> searchProduct(String input)
	{
		ArrayList<Product> output;
		input = StringConversion(input);
		
		// Check if user input is an integer
		boolean checkInt = isInteger(input);
		
		
		if (checkInt)
		{
			output = (ArrayList<Product>) prepo.searchAllPartID(input);
			return output;
		}
		else
		{
			output = (ArrayList<Product>) prepo.searchAllDescription(input);
			output.addAll((ArrayList<Product>) prepo.searchAllColor(input));
			output.addAll((ArrayList<Product>) prepo.searchAllManufacturer(input));
			output.addAll((ArrayList<Product>) prepo.searchAllShelfLocation(input));
			output.addAll((ArrayList<Product>) prepo.searchSupplierName(input));
			
			output = DuplicateRemover(output);
			return output;
		}
	}


	@Override
	@Transactional        // Advanced Search - Using filters
	public ArrayList<Product> searchProductByFilters(String input, ArrayList<String> mfrFilters)
	{
		ArrayList<Product> output = null;
		input = StringConversion(input);
		String tempA = mfrFilters.get(0);
		
		// Check if user input is an integer
		boolean checkInt = isInteger(input);
		
		if (checkInt)
		{
			output = (ArrayList<Product>) prepo.searchAllPartIDByFilterManufacturer(input, tempA);
			
			if (mfrFilters.size() >= 1)
			{
				for (int i = 1; i < mfrFilters.size(); i++) {
					
					tempA = mfrFilters.get(i);
					output.addAll((ArrayList<Product>) prepo.searchAllPartIDByFilterManufacturer(input, tempA));
				}
			}
		}
		else
		{
			output = (ArrayList<Product>) prepo.searchAllDescriptionByFilterManufacturer(input, tempA);
			output.addAll((ArrayList<Product>) prepo.searchAllColorByFilterManufacturer(input, tempA));
			output.addAll((ArrayList<Product>) prepo.searchAllShelfLocationByFilterManufacturer(input, tempA));
			output.addAll((ArrayList<Product>) prepo.searchSupplierNameByFilterManufacturer(input, tempA));
			
			if (mfrFilters.size() >= 1)
			{
				for (int i = 1; i < mfrFilters.size(); i++) {
					
					tempA = mfrFilters.get(i);
					output.addAll((ArrayList<Product>) prepo.searchAllDescriptionByFilterManufacturer(input, tempA));
					output.addAll((ArrayList<Product>) prepo.searchAllColorByFilterManufacturer(input, tempA));
					output.addAll((ArrayList<Product>) prepo.searchAllShelfLocationByFilterManufacturer(input, tempA));
					output.addAll((ArrayList<Product>) prepo.searchSupplierNameByFilterManufacturer(input, tempA));
				}
			}
		}
		
		try
		{
			output = DuplicateRemover(output);
		}
		catch(NullPointerException e)
        {
            System.out.print("NullPointerException caught");
        }
		return output;
	}
	
	
	
	
	
	
	
	
	
	//-------------------- SUPPORT METHODS --------------------------//
	
	// Making any given string to be easily manipulated and managed
	private static String StringConversion(String x) {
		// Converts input into lower chars and trims any spaces behind and back
		String y = x.toLowerCase().trim();
		y = y.replaceAll("\\s", ""); // Removing all whitespaces no matter where it is.
		return y;
	}
	// Checking if input is an integer
	private static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			// If can be parse, input is an integer
			return true;
		} catch (NumberFormatException ex) {
			// If exception is caught, then input is not an integer.
			return false;
		}
	}
	// Removes duplicate entries [Separate into two methods, cos the Arraylist will have to be cycled through two 
		// times for any duplicate entries to be completely removed. If you want to know why exactly this is so, ask me :) ]
	private ArrayList<Product> DuplicateRemover(ArrayList<Product> ipt)
	{
		ipt = RemoverProcesses(ipt);
		ipt = RemoverProcesses(ipt);
		return ipt;
	}
	private ArrayList<Product> RemoverProcesses(ArrayList<Product> ipt)
	{
		int temp1;
		int temp2;
		for (int i = 0; i < ipt.size(); i++) {
			temp1 = ipt.get(i).getPartID();
			
			for (int j = i + 1; j < ipt.size(); j++) {
				temp2 = ipt.get(j).getPartID();
				
				if (temp1 == temp2)
				{
					System.out.println(temp1);
					System.out.println(temp2);
					ipt.remove(j);
				}
			}
		}
		return ipt;
	}


}
