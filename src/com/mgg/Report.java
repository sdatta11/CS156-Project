package com.mgg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


/**
 * Takes in information from a list of sales, then generates reports of total sales,
 * individual sales, grand totals for sales people and stores.
 *
 */



public class Report {

	public static void SalesReport(List<Sale> sales){
		System.out.println("--------------------------------------------------");
		System.out.println("Sale Report: ");
		System.out.println("--------------------------------------------------");
		
		for( Sale sale : sales) {
			Double total = 0.0;
			Double totalTax = 0.0;
			System.out.println("Sale: " + sale.getSalesCode() + "\n" + 
				"Store: " + sale.getStore().getStoreCode() + "\n" + 
				"Customer: " + "\n" + 
				sale.getCustomer().getLastName() + "," + sale.getCustomer().getFirstName() + sale.getCustomer().getEmail() + "\n" +
				sale.getCustomer().getAddress() + "\n\n" +
				"Sales Person:" + "\n" +
				sale.getSalesPerson().getLastName() + "," + sale.getSalesPerson().getFirstName() + "\n" +
				"Items:                                  Total" + "\n");
				for (Item item : sale.getItem()) {
					
					System.out.println(
					item.getName() + "\n" +
					"(" + item.getType() + " " + item.getCode() + ")"  + "                  $"  +   getPrice(item));
					total += getPrice(item);
					totalTax += getTaxes(item);
				}
				
				total *= 100;
				total = (double) Math.round(total) / 100;
				System.out.println("Total:                   $" + total); 
				totalTax = (double) Math.round(totalTax*100) / 100;
				System.out.println("Tax:                     $" + totalTax);
				double discount = 0.0;
				if(sale.getCustomer().getType().equals("G")) {
					discount = Math.round((total+totalTax)*0.05*100) / 100;
					System.out.println("Discount(5%):  	           $"+ discount);
				}
				else if(sale.getCustomer().getType().equals("P")) {
					discount = Math.round((total+totalTax)*0.1*100) / 100;
					System.out.println("Discount(10%):             $"+ discount);
				}
				else if(sale.getCustomer().getType().equals("E")) {
					discount = Math.round((total+totalTax)*0.15*100) / 100;
					System.out.println("Discount(15%):             $"+ discount);
				}
				Double grandTotal = (double) Math.round((total+totalTax - discount)*100) / 100;
				System.out.println("Grand Total:              $"+grandTotal + "\n");
				sale.addGrandTotal(grandTotal);

		}	
	}
	
	
	
	public static double getPrice(Item item) {
		double sub = 0.0;
		
		
		if(item.getType().equals("PN") || item.getType().equals("PU") || item.getType().equals("PG")   ) {
			Product product = (Product) item;	
			if (item.getType().equals("PN")) {
				return product.getBasePrice() * product.getQuantity();
			}
			else if (item.getType().equals("PU")) {
				
				sub = (double) Math.round( (product.getBasePrice() * product.getQuantity() * 0.8)*100) /100;
			}
			else if (item.getType().equals("PG")) {
				return product.getQuantity();
			}			
		}
		
		if(item.getType().equals("SV")) {
			Service service = (Service) item;
			sub = (double) Math.round((service.getHourlyRate() * service.getHoursWorked())*100)/100;
			return sub;
		}
		
		if(item.getType().equals("SB")) {
			Subscription subscription = (Subscription) item;
			long daysBetween = ChronoUnit.DAYS.between(subscription.getStartDate(),subscription.getEndDate());
			sub = (double) Math.round(((daysBetween/365.0)*subscription.getAnnualFee())*100) / 100;
			return sub;
		}	
		else {
		
			return sub;
		}
	}
	
	public static double getTaxes(Item item) {
		
		double basePrice = getPrice(item);
		
		if (item.getType().equals("PN") || item.getType().equals("PU") || item.getType().equals("PG")){
			basePrice *= 0.0725;
			return basePrice;
		}
		if (item.getType().equals("SV")) {
			basePrice *= 0.0285;
			return basePrice;
		}
		else {
			return 0.0;
		}
	}
	
	public static void SalesPerson(List<Sale> sales) {
		System.out.println("--------------------------------------------------");
		System.out.println("Sales Person Summary Report: ");
		System.out.println("--------------------------------------------------");
		System.out.println("Employee:     Number of Sales:     Grand Total:  ");
		
		HashMap<Person,List<Double>> salesPerPerson = new HashMap<Person,List<Double>>();
		
		for (Sale sale: sales) {
			if (salesPerPerson.containsKey(sale.getSalesPerson())) {
				List<Double> tempArray = salesPerPerson.get(sale.getSalesPerson());
				tempArray.add(sale.getGrandTotal());
				salesPerPerson.put(sale.getSalesPerson(), tempArray);
			}
			else {
				List<Double> tempArrayTwo = new ArrayList<Double>();
				tempArrayTwo.add(sale.getGrandTotal());
				salesPerPerson.put(sale.getSalesPerson(), tempArrayTwo);
			}
		}
		for( Person p : salesPerPerson.keySet()) {
			double totalPerPerson = 0.0;
			int numOfSales = salesPerPerson.get(p).size();
			for (Double d : salesPerPerson.get(p)) {
			totalPerPerson += d;
			}
			
			System.out.printf("%s, %s, %d, %.2f \n"  , p.getLastName(), p.getFirstName(), numOfSales, totalPerPerson);
		}
		
		
	}
	
	public static void StoreSales(List<Sale> sales) {
		
		
		System.out.println("--------------------------------------------------");
		System.out.println("Store summary report: ");
		System.out.println("--------------------------------------------------");
		System.out.println("Store Code:   Manager:     Sales:    Total:  ");
		
		HashMap<Store,List<Double>> salesPerStore = new HashMap<Store,List<Double>>();
		
		for (Sale sale: sales) {
			if (salesPerStore.containsKey(sale.getStore())) {
				List<Double> tempArray = salesPerStore.get(sale.getStore());
				tempArray.add(sale.getGrandTotal());
				salesPerStore.put(sale.getStore(), tempArray);
			}
			else {
				List<Double> tempArrayTwo = new ArrayList<Double>();
				tempArrayTwo.add(sale.getGrandTotal());
				salesPerStore.put(sale.getStore(), tempArrayTwo);
			}
		}
		for( Store s : salesPerStore.keySet()) {
			double totalPerStore = 0.0;
			int numOfSales = salesPerStore.get(s).size();
			for (Double d : salesPerStore.get(s)) {
			totalPerStore += d;
			}
			
			System.out.printf("%s    %s, %s      %d        %.2f \n"  ,s.getStoreCode(), s.getManager().getLastName(), s.getManager().getFirstName(), numOfSales, totalPerStore);
		}
		
		
	}
	
	
}
	
	
