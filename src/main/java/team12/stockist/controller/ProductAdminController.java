package team12.stockist.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import team12.stockist.model.Product;
import team12.stockist.model.Supplier;
import team12.stockist.service.ProductService;
import team12.stockist.service.SupplierService;
import team12.stockist.validator.NewProductValidator;



@RequestMapping(value="/admin/product/")
@Controller
public class ProductAdminController {
	

	@Autowired
	private ProductService pservice;
	@Autowired
	private SupplierService sservice;
	@Autowired
	private NewProductValidator npValidator;
	@InitBinder("product")
	private void initProductBinder(WebDataBinder binder)
	{
		binder.addValidators(npValidator);
	}
	
	private static ArrayList<Supplier> ssList;
	
	
	
	//-------------------- CREATE --------------------------//
	@RequestMapping(value = "/create", method = RequestMethod.GET)    // Directing to createProduct page
	public ModelAndView createProduct()
	{
		ModelAndView mav = new ModelAndView("product-new", "product", new Product());
		ArrayList<Supplier> sList = (ArrayList<Supplier>) sservice.findAllSupplier();
		ssList = sList;
		mav.addObject("sList", sList);
		return mav;
	}
	
	@RequestMapping(value = "/create", method=RequestMethod.POST)
	public ModelAndView newStudentPage(@ModelAttribute @Valid Product pdt, BindingResult result, final RedirectAttributes redirectAttributes)
	{
		if(result.hasErrors())
			{
				return new ModelAndView("product-new", "sList", ssList);
			}
		else
		{
			// Setting additional variables
			pdt.setUnitsOnOrder(0);
			pdt.setDiscontinued(0);
			
			
			
			// Checking if product already exist.
			try
			{
				Product tempPdt = pservice.findProductById(pdt.getPartID());
				
				if (tempPdt != null)
				{
					ModelAndView mav = new ModelAndView("product-new", "sList", ssList);
					String s = "A product with the same PartID already exists. Please try again.";
					mav.addObject("msgAlert", s);
					return mav;
				}
				else
				{
					// Creating our product
					pservice.createProduct(pdt);
					
					// Preparing and directing to our browse page
					ModelAndView mav = new ModelAndView("product-new", "sList", ssList);
					String message = "New product " + pdt.getDescription() + " was successfully created.";
					redirectAttributes.addFlashAttribute("message", message);
					mav.addObject("msgAlert", message);
					return mav;
				}
			}
			catch (Exception e)
			{
				ModelAndView mav = new ModelAndView("product-new", "sList", ssList);
				String s = "There is an error creating the new product entry. Please try again.";
				mav.addObject("msgAlert", s);
				return mav;
			}
		}
	}
}
