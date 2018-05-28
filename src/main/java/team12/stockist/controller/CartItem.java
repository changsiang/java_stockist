package team12.stockist.controller;

import team12.stockist.model.Product;

public class CartItem {
	
	Product product;
	int quantity;
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartItem(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	

}
