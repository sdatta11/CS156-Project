package com.mgg;

import java.util.List; 

/**
 * 
 * @author sdatta
 * Class that initializes a person, used to store all the information given
 * into a class for future use.
 */

public class Person {

	private String code;
	private String type;
	private String lastName;
	private String firstName;
	private Address address;
	private List<String> email;
	
	public Person(String code, String type, String lastName, String firstName, Address address, List<String> email) {
		super();
		this.code = code;
		this.type = type;
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public String getType() {
		return type;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public Address getAddress() {
		return address;
	}

	public List<String> getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "Person [code=" + code + "\n"
	+ ", type=" + type + "\n" 
	+ ", lastName=" + lastName + "\n"
	+ ", firstName=" + firstName
	+ ", address=" + address +  "\n" 
	+ ", email=" + email + "]";
	}

	
	
	
	
}




