package com.mgg;


/**
 * 
 * A class that models a product, and uses inheritance on the class Item
 *
 */
public class Product extends Item{

	private double basePrice;
	private double quantity;
	
	public Product(String code, String type, String name, double basePrice) {
		super(code, type, name);
		this.basePrice = basePrice;
	}
	public Product(Product p, double quantity) {
		super(p.getCode(), p.getType(), p.getName());
		this.basePrice = p.basePrice;
		this.quantity = quantity;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public double getQuantity() {
		return quantity;
	}
	@Override
	public String toString() {
		return getCode() + ", " + getType() + ", " + getName() + "Product [basePrice=" + basePrice + "]";
	}
	
	public void addQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	

}
