package com.mgg;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author sdatta
 * Contains functions that are used to collect information from files,
 * and convert the information to classes that can be used for outputting 
 * information in a formatted manner
 */

public class LoadFiles {
 
	public static List<Person> loadPersonsFile() {
		//initialize variables and scanners
		Scanner s = null;
		int dataPoints = 0;
		String line = null;
		List<Person> persons = new ArrayList<>();
		
	
		try {
			s = new Scanner(new File("data/Persons.csv"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		//reads first line to see how many dataPoints there are
		dataPoints = s.nextInt();
		s.nextLine();
		for(int i = 0; i < dataPoints; i++) {
			//initialize list of emails
			List<String> emails = new ArrayList<>();
			//parse line by line
			line = s.nextLine();
			String tokens[] = line.split(",");
			String code = tokens[0];
			String type = tokens[1];
			String lastName = tokens[2];
			String firstName = tokens[3];
			String street = tokens[4];
			String city = tokens[5];
			String state = tokens[6];
			String zip = tokens[7];
			String country = tokens[8];
			//adds all of the emails at the end of the line to the array
			for(int j = 9; j < tokens.length; j++) {
				
				emails.add(tokens[j]);
			}
			//creates instances of address and person
			Address address = new Address(street,city,state,zip,country);
			Person person = new Person(code,type,lastName,firstName,address,emails);
			//adds person to the list of persons
			persons.add(person);
		}
		
		s.close();
		return persons;
	}
	
	
	
	public static List<Store> loadStoresFile(List<Person> persons) {
		//initialize variables and scanner
		Scanner s = null;
		int dataPoints = 0;
		String line = null;
		List<Store> stores = new ArrayList<>();
		try {
			s = new Scanner(new File("data/Stores.csv"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		//reads first line to see how many data points there are
		dataPoints = s.nextInt();
		s.nextLine();
		for(int i = 0; i < dataPoints; i++) {
			//initialize instance of manager
			Person manager = null;
			line = s.nextLine();
			String tokens[] = line.split(",");
			String storeCode = tokens[0];
			String managerCode = tokens[1];
			String street = tokens[2];
			String city = tokens[3];
			String state = tokens[4];
			String zip = tokens[5];
			String country = tokens[6];
			Address address = new Address(street,city,state,zip,country);
			//checks the list of persons for the manager code and adds that person as the manager
			for (Person person : persons){
				if (managerCode.equals(person.getCode())) {
					manager = person;
				}
			}
			Store store = new Store(storeCode,managerCode,manager,address);
			stores.add(store);
		}
		s.close();
		return stores;

	}
	
	
 	public static List<Item> loadItemsFile(){
 		//initialize variables and scanner
		Scanner s = null;
		int dataPoints = 0;
		String line = null;
		List<Item> items = new ArrayList<>();
		try {
			s = new Scanner(new File("data/Items.csv"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		//reads the first integer to see how many data points there are
		dataPoints = s.nextInt();
		s.nextLine();
		for(int i = 0; i < dataPoints; i++) {
			line = s.nextLine();
			String tokens[] = line.split(",");
			String code = tokens[0];
			String type = tokens[1];
			String name = tokens[2];
			//checks if the item is a service
			if(type.equals("SV")){
				String b = tokens[3];
				double hourlyRate = Double.parseDouble(b);
				Service item = new Service(code,type,name,hourlyRate);
				items.add(item);
			}
			//checks if the item is a subscription
			else if(type.equals("SB")) {
				String b = tokens[3];
				double annualFee = Double.parseDouble(b);
				Subscription item = new Subscription(code,type,name,annualFee);
				items.add(item);
			}
			//checks if the item is a product
			else if(type.equals("PN") || type.equals("PU") || type.equals("PG")) {
				//checks if the item is a gift card
				if(type.equals("PG")) {
					Product item = new Product(code,type,name, 0.0);
					items.add(item);
				}
				else {
					String b = tokens[3];
					double basePrice = Double.parseDouble(b);
					Product item = new Product(code,type,name,basePrice);
					items.add(item);
				}
			}
		}
		s.close();
		return items;
		
	}
 	
 	public static List<Sale> loadSalesFile(List<Person> persons, List<Store> stores, List<Item> items){
 		//initalize scanner and variables		
 		Scanner s = null;
 		int dataPoints = 0;
 		String line = null; 
 		
 		List<Sale> sales = new ArrayList<>();
 		Store saleStore = null;
 		Person customer = null;
 		Person salesPerson = null;
 		
 		
 		try {
			s = new Scanner(new File("data/Sales.csv"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
 		dataPoints = s.nextInt();
 		s.nextLine();
 		//for loop to run through every line
 		for(int i = 0; i < dataPoints; i++) {
 			List<Item> itemList = new ArrayList<>();
 			line = s.nextLine();
 			String tokens[] = line.split(",");
 			String salesCode = tokens[0];
 			
 			String storeCode = tokens[1];
 			//for loop to find matching store to store code
 			for (Store store : stores) {
 				if (store.getStoreCode().equals(storeCode)){
 					saleStore = store;
 				}
 			}
 			String customerCode = tokens[2];
 			String salesPersonCode = tokens[3];
 			//for loop to find matching persons customer and salesperson from list of persons
 			for (Person person : persons) {
 				
 				if (person.getCode().equals(customerCode)) {
 					customer = person;
 					
 				}
 				else if (person.getCode().equals(salesPersonCode)) {
 					salesPerson = person;
 				}
 			}
 			//while loop to parse through list of items			
 			int j = 4;
 			while( j < tokens.length ) {
 				
 				String itemCode = tokens[j];
 				j++;
 				//match item code to the item
 				for (Item item : items ) {
 					if (item.getCode().equals(itemCode) ) {
 						
 						// checks if the item is a product
 						if(item.getType().equals("PU") || item.getType().equals("PN") || item.getType().equals("PG")) {
 							Double quantity = Double.parseDouble(tokens[j]);
 							Product product = (Product)item;
 							//adds the member variables to the class
 							product = new Product(product, quantity);
 							//adds product to the list
 							itemList.add(product);
 							j++;
 						}
 						
 						else if(item.getType().equals("SV")) {
 							Person employee = null;
 							String employeeCode = tokens[j];
 							//matches employee code to the person
 							for (Person person : persons) {
 				 				if (person.getCode().equals(employeeCode)) {
 				 					employee = person;
 				 				}
 							}
 							j++;
 							Double numHours = Double.parseDouble(tokens[j]);
 							j++;
 							Service service = (Service)item;
 							service.addHoursWorked(numHours);
							service.addEmployee(employee);
							
							itemList.add(service);
							
 							
 						}
 						else if(item.getType().equals("SB")) {
 							LocalDate begin = null;
 							LocalDate end = null;
 							begin = LocalDate.parse(tokens[j]);
 							j++;
 							end = LocalDate.parse(tokens[j]);
 							j++;
 							Subscription sub = (Subscription)item;
 							sub.addStartDate(begin);
 							sub.addEndDate(end);
 							itemList.add(sub);
 						}
 					}
 				}		
 			}
 			
 			Sale sale = new Sale(salesCode,saleStore, customer, salesPerson, itemList);
 			sales.add(sale);
 			
 		}
 		
 		return sales;
 		
 	}
	
	
	
	
	
	
}
