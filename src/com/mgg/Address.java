package com.mgg;


/*
 * 
 * @author sdatta
 * This class models an address for a person including street, city, state, zip-code, and country
 *
 */
public class Address {

	private String street;
	private String city;
	private String state;
	private String zip;
	private String country;
	
	
	public Address(String street, String city, String state, String zip, String country) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
	}


	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", state=" + state + ", zip=" + zip + ", country="
				+ country + "]";
	}
	
	
	
}
