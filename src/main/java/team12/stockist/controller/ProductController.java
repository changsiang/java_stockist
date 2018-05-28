package team12.stockist.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import team12.stockist.model.Product;
import team12.stockist.model.Supplier;
import team12.stockist.model.UsageRecord;
import team12.stockist.model.UsageRecordDetail;
import team12.stockist.service.ProductService;
import team12.stockist.service.SupplierService;
import team12.stockist.service.UsageRecordDetailService;
import team12.stockist.service.UsageRecordService;
import team12.stockist.validator.ProductUpdateValidator;



@RequestMapping(value="/mechanic/product/")
@Controller
public class ProductController 
{
	@Autowired
	private ProductService pservice;
	@Autowired
	private SupplierService sservice;
	@Autowired
	private UsageRecordDetailService uservice;
	@Autowired
	private UsageRecordService uRservice;
	@Autowired
	ProductUpdateValidator uValidator;
	
	@InitBinder("product")
	private void initStudentBinder(WebDataBinder binder)
	{
		binder.addValidators(uValidator);
	}
	
	// Used for the edit page
	private static ArrayList<Supplier> ssList;
	

	
	
	//-------------------- MAIN BROWSE CATALOGUE PAGE --------------------------//
	
	// Retrieve entire list of products
	@RequestMapping(value = "/browse", method = RequestMethod.GET)
	public ModelAndView productListPage() 
	{
		ModelAndView mav = new ModelAndView("product-list", "command", new SearchFilters());
		String msg = "";
		mav = mavSupport(mav, msg);
		return mav;
	}
	
	@RequestMapping(value = "/addtocart")    // Directing to createProduct page
	public ModelAndView addToCart1(@RequestParam String cartPId, @RequestParam String qty, HttpServletRequest req)
	{
		HttpSession session = (HttpSession) req.getSession();
		String msg;
		
		// Sending to the service layer for processing
		msg = pservice.addToCart(cartPId, qty, session);
		
		// Redirecting to the main page
		ModelAndView mav = new ModelAndView("product-list", "command", new SearchFilters());
		mav = mavSupport(mav, msg);
		
		// Uncomment to check if your items are captured
		//mav.addObject("searchValue", secretValue);
		return mav;
	}//
	

	// A simple support method to minimize code repeat of calling the diff services (populate the list)
	private ModelAndView mavSupport(ModelAndView temp, String msg)
	{
		ArrayList<Product> pList = (ArrayList<Product>) pservice.findAllProduct();
		ArrayList<Supplier> sList = (ArrayList<Supplier>) sservice.findAllSupplier();
		temp.addObject("productList", pList);
		temp.addObject("supplierList", sList);

		temp.addObject("msgAlert", msg);
		return temp;
	}
	
	
	
	
	
	
	
	
	
	
	
	// .................. UPDATE PRODUCT PAGE ........................//

	@RequestMapping(value = "/edit/{partID}", method = RequestMethod.GET)
	public ModelAndView editProductPage(@PathVariable Integer partID) {
		// Redirect to product-edit page
		ModelAndView mav = new ModelAndView("product-edit");
		// calling the findProductById service method(ProductsServiceImpl class method).
		// Return the specific product based on partID
		// result is stored in variable 'product' of type Product
		Product product = pservice.findProductById(partID);
		mav.addObject("product", product);
		
		// Used to populate the supplier drop down list
		ArrayList<Supplier> sList = (ArrayList<Supplier>) sservice.findAllSupplier();
		ssList = sList;
		mav.addObject("sList", sList);
		
		return mav;
	}

	@RequestMapping(value = "/edit/{partID}", method = RequestMethod.POST)
	public ModelAndView editProduct(@ModelAttribute @Valid Product product, BindingResult result, @PathVariable Integer partID,
			final RedirectAttributes redirectAttributes) {
		// Redirect to product-list page
		
		if(result.hasErrors())
			return new ModelAndView("product-edit", "sList", ssList);
		
		ModelAndView mav = new ModelAndView();
		

		//1/ModelAndView mav = new ModelAndView("redirect:/mechanic/product/browse");
		// calling ProductServiceImpl service class method updateProduct.
		// update the product
		pservice.updateProduct(product);
		String message = "Product is updated";
		redirectAttributes.addFlashAttribute("message", message);
		mav.setViewName("redirect:/mechanic/product/browse");

		return mav;
	}
	
	
	//-------------------- DETAILS PAGE --------------------------//
	@RequestMapping(value = "/details/{partID}", method = RequestMethod.GET)
	public ModelAndView getProductDetailsPage(@PathVariable Integer partID) {
		// Redirect to product-details-transactionHistory page
		ModelAndView mav = new ModelAndView("product-details-transactionHistory");
		// Retrieve Product object
		Product product = pservice.findProductById(partID);
		// add to ModelView Object
		mav.addObject("pList", product);
		// ArrayList transactionListFromUsageRecordDetails contains UsageRecordDetails
		// table records based on querry condition.
		ArrayList<UsageRecordDetail> transactionListFromUsageRecorDetails = (ArrayList<UsageRecordDetail>) uservice
				.findTransactionHistoryByProductId(partID);
		// ArrayList transactionListFromUsageRecord contains UsageRecord table records
		// details based on querry condition.
		ArrayList<UsageRecord> transactionListFromUsageRecord = (ArrayList<UsageRecord>) uRservice
				.findUsageRecordHistory(partID);
		// Add tp ModelView Attribute
		mav.addObject("tList", transactionListFromUsageRecorDetails);
		mav.addObject("rList", transactionListFromUsageRecord);
		
		// Add msg object
		String msg = "";
		mav.addObject("msgAlert", msg);

		return mav;
	}
	
	
	//-------------------------Filter By Date Range--------------------------------------------------//

	@RequestMapping(value = "/details/filter")
	public ModelAndView getProductDetailsPage(@RequestParam String startdate, @RequestParam String enddate, 
			@RequestParam String pid) {
		
		// Redirect to product-details-transactionHistory page
		ArrayList<UsageRecordDetail> transactionListFromUsageRecorDetails;
		ArrayList<UsageRecord> transactionListFromUsageRecord;
		
		ModelAndView mav = new ModelAndView("product-details-transactionHistory");
		String msg = "";
				
		int temp = Integer.parseInt(pid);
		
		Product product = pservice.findProductById(temp);
		mav.addObject("pList", product);
		
		Date stDate = null;
		Date enDate = null;
		SimpleDateFormat sD = new SimpleDateFormat("yyyy-MM-dd");
		
		
		try
		{
			stDate = sD.parse(startdate);
			enDate = sD.parse(enddate);
		} catch (ParseException e)
		{
			transactionListFromUsageRecorDetails = (ArrayList<UsageRecordDetail>) uservice.findTransactionHistoryByProductId(temp);
			transactionListFromUsageRecord = (ArrayList<UsageRecord>) uRservice.findUsageRecordHistory(temp);
			msg = "";
			mav.addObject("tList", transactionListFromUsageRecorDetails);
			mav.addObject("rList", transactionListFromUsageRecord);
			mav.addObject("msgAlert", msg);
			return mav;
		}
		
		
		// Formatting to date
		
		if (stDate.before(enDate))
		{
			transactionListFromUsageRecorDetails = uservice.findTransactionHistoryByProductId(temp);
			transactionListFromUsageRecord = (ArrayList<UsageRecord>) uRservice.findUsageRecordHistoryByDate(temp, startdate, enddate);
			
			msg = "";
		}
		else
		{
			transactionListFromUsageRecorDetails = (ArrayList<UsageRecordDetail>) uservice.findTransactionHistoryByProductId(temp);
			transactionListFromUsageRecord = (ArrayList<UsageRecord>) uRservice.findUsageRecordHistory(temp);
			msg = "Please specify a correct date range.";
		}
		
		
		mav.addObject("tList", transactionListFromUsageRecorDetails);
		mav.addObject("rList", transactionListFromUsageRecord);
		mav.addObject("msgAlert", msg);
		
		return mav;
	}
	
	
	
	
	
	
	
	
	
	
	
	

	
	//-------------------- SEARCH --------------------------//
	
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchListPage(@RequestParam String inputField, @ModelAttribute SearchFilters sft) 
	{
		// Retrieving all our filters
		//ArrayList<String> tempA = sft.getColorFilters();
		ArrayList<String> tempA = sft.getManufacturerFilters();
		

		// When something is entered into the search bar
		if (inputField != null && !inputField.isEmpty())
		{
			ModelAndView mav = new ModelAndView("product-list", "command", sft);
			
			// And there are no filters [General search]
			if (tempA.size() == 0)
			{
				ArrayList<Product> pList = (ArrayList<Product>) pservice.searchProduct(inputField);
				ArrayList<Supplier> sList = (ArrayList<Supplier>) sservice.findAllSupplier();
				mav.addObject("productList", pList);
				mav.addObject("supplierList", sList);
				
				mav.addObject("searchValue", inputField);
				return mav;
			}
			else  // When there are filters [Advanced Search]
			{
				ArrayList<Product> pList = (ArrayList<Product>) pservice.searchProductByFilters(inputField, tempA);
				ArrayList<Supplier> sList = (ArrayList<Supplier>) sservice.findAllSupplier();
				mav.addObject("productList", pList);
				mav.addObject("supplierList", sList);
				
				mav.addObject("searchValue", inputField);
				return mav;
			}
			
		}
		// When nothing is entered into the search bar
		else
		{
			if (tempA.size() != 0)  // But there are filters selected [Semi-advanced Search]
			{
				ModelAndView mav = new ModelAndView("product-list", "command", sft);
				ArrayList<Product> pList = (ArrayList<Product>) pservice.findAllProductByFilter(tempA);
				ArrayList<Supplier> sList = (ArrayList<Supplier>) sservice.findAllSupplier();
				mav.addObject("productList", pList);
				mav.addObject("supplierList", sList);
				
				mav.addObject("searchValue", inputField);
				return mav;
			}
			else  // No filters selected
			{
				ModelAndView mav = new ModelAndView("redirect:/mechanic/product/browse");
				return mav;
			}
		}
	}
	
}
