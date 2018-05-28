package team12.stockist.controller;

import java.util.ArrayList;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import team12.stockist.model.Supplier;
import team12.stockist.service.ProductService;
import team12.stockist.service.SupplierService;
import team12.stockist.validator.SupplierValidator;

@RequestMapping(value = "admin/supplier")
@Controller
public class SupplierController {

	@Autowired
	SupplierService supplierService;
	@Autowired
	private SupplierValidator supplierValidator;
	@Autowired
	ProductService productService;

	@InitBinder("supplier")
	private void initSupplierBinder(WebDataBinder binder) {
		binder.addValidators(supplierValidator);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView userListPage() {
		ModelAndView mav = new ModelAndView("supplier-list");
		ArrayList<Supplier> supplierList = (ArrayList<Supplier>) supplierService.findAllSupplier();
		mav.addObject("supplierList", supplierList);
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView newSupplierPage() {
		ModelAndView mav = new ModelAndView("supplier-new");
		mav.addObject("supplier", new Supplier());
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createSupplierPage(@ModelAttribute @Valid Supplier supplier, BindingResult result,final RedirectAttributes redirectAttribute) {

		
		ModelAndView mav = new ModelAndView();

		if(result.hasErrors()) {
			mav.setViewName("redirect:/admin/supplier/create");
			redirectAttribute.addFlashAttribute("supplieralreadyexists", "All fields must be completed");
			return mav;
		}
		
		if(supplierService.supplierAlreadyExists(supplier)) {
			mav.setViewName("redirect:/admin/supplier/create");
			redirectAttribute.addFlashAttribute("supplieralreadyexists", "Supplier already exists");
			return mav;
		}
		else {
			supplierService.createSupplierRecord(supplier);
			mav.setViewName("redirect:/admin/supplier/list");
			return mav;
		}
	}

	@RequestMapping(value = "/edit/{supplierID}", method = RequestMethod.GET)
	public ModelAndView editSupplierPage(@PathVariable String supplierID) {
		ModelAndView mav = new ModelAndView("supplier-edit");
		Supplier supplier = supplierService.findSupplierById(supplierID);
		mav.addObject("supplier", supplier);
		return mav;
	}

	@RequestMapping(value = "/edit/{supplierID}", method = RequestMethod.POST)
	public ModelAndView editSupplierPage(@ModelAttribute @Valid Supplier suppliers, BindingResult result,
			@PathVariable String supplierID) {
		if (result.hasErrors())
			return new ModelAndView("supplier-edit");

		ModelAndView mav = new ModelAndView("redirect:/admin/supplier/list");

		supplierService.updateSupplierRecord(suppliers);

		return mav;
	}

	@RequestMapping(value = "/delete/{supplierID}", method = RequestMethod.GET)
	public ModelAndView deleteSupplier(@PathVariable String supplierID) {
		ModelAndView mav = new ModelAndView("redirect:/admin/supplier/list");
		Supplier supplier = supplierService.findSupplierById(supplierID);
		
		if (!productService.findProductBySupplier(supplierID).isEmpty()) {
			mav.addObject("supplierdeleteerror", "Cannot delete Supplier while it exists in Usage Records.");
			return mav;
		}
		supplierService.deleteSupplierRecord(supplier);
		return mav;
	}
}