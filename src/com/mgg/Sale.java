package com.mgg;

import java.util.List;

/**
 * 
 * A class that models a Sale and takes in the store, customer, salesPerson, salesCode, a list of items
 * and a grand total for the items in the list.
 *
 */

public class Sale {

	private Person customer;
	private Store store;
	private Person salesPerson;
	private String salesCode;
	private List<Item> item;
	private Double grandTotal;
	
	//update string
	public Sale(String salesCode, Store store, Person customer, Person salesPerson, List<Item> item) {
		super();
		this.salesCode = salesCode;
		this.store = store;
		this.customer = customer;
		this.salesPerson = salesPerson;
		this.item = item;
	}

	public String getSalesCode() {
		return salesCode;
	}

	public Store getStore() {
		return store;
	}

	public Person getCustomer() {
		return customer;
	}

	public Person getSalesPerson() {
		return salesPerson;
	}

	public List<Item> getItem() {
		return item;
	}

	public void addGrandTotal(Double grandTotal) {
		this.grandTotal = grandTotal;
	}
	
	public Double getGrandTotal() {
		return grandTotal;
	}
	
	@Override
	public String toString() {
		return "Sale: " + salesCode + "\n" + 
				"Store: " + store.getStoreCode() + "\n" + 
				"Customer: " + "\n" + 
				customer.getLastName() + "," + customer.getFirstName() + customer.getEmail() + "\n" +
				customer.getAddress() + "\n\n" +
				"Sales Person:" + "\n" +
				salesPerson.getLastName() + "," + salesPerson.getFirstName() + "\n" +
				"Items:                                  Total" + "\n" +
				
				 ", store=" + store + ", salesPerson=" + salesPerson + ", salesCode="
				+ salesCode + ", item=" + item + "]";
		
	}

	
}