package team12.stockist.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

import team12.stockist.exception.EmptyCartException;
import team12.stockist.model.Product;
import team12.stockist.model.UsageRecord;
import team12.stockist.model.UsageRecordDetail;
import team12.stockist.service.CustomUserDetails;
import team12.stockist.service.ProductService;
import team12.stockist.service.UsageRecordDetailService;
import team12.stockist.service.UsageRecordService;
import team12.stockist.service.UserService;
import team12.stockist.validator.CartItemValidator;
import team12.stockist.validator.CartValidator;

@RequestMapping(value = "/mechanic/usagerecord/")
@Controller
public class UsageRecordController {

	@Autowired
	private UsageRecordService usageRecordService;

	@Autowired
	private UsageRecordDetailService usageRecordDetailService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CartItemValidator ciValidator;

	// temporary
	@Autowired
	UserService userService;

	@InitBinder("cartItem")
	private void initCartItemValidator(WebDataBinder binder) {
		binder.addValidators(ciValidator);
	}

	@RequestMapping(value = "/viewcart", method = RequestMethod.GET)
	public ModelAndView ViewCart(Object object, HttpSession session, Authentication authentication) {
		ModelAndView modelAndView = new ModelAndView("view-cart");

		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		Cart cart = (Cart) session.getAttribute("cart");
		
		cart.setUser(userDetails);
		cart.setDateUsed(new Date());

		session.setAttribute("cart", cart);
		modelAndView.addObject("cart", cart);
		return modelAndView;
	}

	@RequestMapping(value = "/viewcart", method = RequestMethod.POST)
	public ModelAndView Checkout(@ModelAttribute Cart model, BindingResult result, HttpSession session,
			final RedirectAttributes redirectAttributes) throws EmptyCartException {

		UsageRecord usageRecord = new UsageRecord();
		ArrayList<UsageRecordDetail> usageRecordDetails = new ArrayList<UsageRecordDetail>();
		ModelAndView modelAndView = new ModelAndView();

		Cart cart = (Cart) session.getAttribute("cart");

		usageRecord.setTransID(cart.getCartId());

		CartValidator cartValidator = new CartValidator();
		cartValidator.validate(model, result);

		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("redirect:/mechanic/usagerecord/viewcart/");
			redirectAttributes.addFlashAttribute("customerNameError", "Error! Customer Name cannot be empty");
			return mav;
		}

		if (!usageRecordService.checkStockAvailable(cart).isEmpty()) {
			ModelAndView mav = new ModelAndView("redirect:/mechanic/usagerecord/viewcart/");
			ArrayList<String> noStockCartItem = new ArrayList<String>();
			ArrayList<CartItem> noStockList = usageRecordService.checkStockAvailable(cart);
			for (CartItem cartItem : noStockList) {
				StringBuilder stockAlert = new StringBuilder("Stock Error for ");
				stockAlert.append(cartItem.getProduct().getDescription());
				stockAlert.append(" Remaining stock left is: ");
				stockAlert.append(Integer
						.toString(productService.findProductById(cartItem.getProduct().getPartID()).getUnitsInStock()));
				noStockCartItem.add(stockAlert.toString());
			}
			redirectAttributes.addFlashAttribute("noStockCartItem", noStockCartItem);
			return mav;
		}

		usageRecord.setCustomerName(model.getCustomerName());
		usageRecord.setUserId(cart.getUser().getId());
		usageRecord.setDateUsed(cart.getDateUsed());
		usageRecordService.createUsageRecord(usageRecord);
		usageRecordDetails = usageRecordService.checkoutCartDetails(cart);
		usageRecordDetailService.addUsageRecordDetailList(usageRecordDetails);

		String checkoutOK = "Cart ID: " + cart.getCartId() + " Usage Recorded Successfully!";
		redirectAttributes.addFlashAttribute("checkoutSuccess", checkoutOK);
		
		Cart cartNew = new Cart();
		cartNew.setCartId(Long.toString(new Date().getTime()));
		session.setAttribute("cart", cartNew);

		modelAndView.setViewName("redirect:/");

		return modelAndView;

	}

	@RequestMapping(value = "viewcart/edit/{index}", method = RequestMethod.GET)
	public ModelAndView editCartItem(@PathVariable int index, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("edit-cartitem");
		Cart cart = (Cart) session.getAttribute("cart");
		ArrayList<CartItem> cartItemList = cart.getCartItemList();
		CartItem cartItem = cartItemList.get(index);

		modelAndView.addObject("cartItem", cartItem);

		return modelAndView;
	}

	@RequestMapping(value = "viewcart/edit/{index}", method = RequestMethod.POST)
	public ModelAndView updateCartItem(@PathVariable int index, @ModelAttribute @Valid CartItem cartItem,
			BindingResult result, HttpSession session) {

		if (result.hasErrors())
			return new ModelAndView("edit-cartitem");

		ModelAndView modelAndView = new ModelAndView();
		Cart cart = (Cart) session.getAttribute("cart");
		ArrayList<CartItem> cartItemList = cart.getCartItemList();

		cartItemList.set(index, cartItem);
		cart.setCartItemList(cartItemList);

		session.setAttribute("cart", cart);
		modelAndView.setViewName("redirect:/mechanic/usagerecord/viewcart/");

		return modelAndView;
	}

	@RequestMapping(value = "viewcart/delete/{index}", method = RequestMethod.GET)
	public ModelAndView deleteCartItem(@PathVariable int index, HttpSession session) {

		ModelAndView modelAndView = new ModelAndView();
		Cart cart = (Cart) session.getAttribute("cart");
		ArrayList<CartItem> cartItemList = cart.getCartItemList();
		cartItemList.remove(index);
		cart.setCartItemList(cartItemList);

		session.setAttribute("cart", cart);
		modelAndView.setViewName("forward:/mechanic/usagerecord/viewcart/");

		return modelAndView;
	}
}
