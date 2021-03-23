package com.mgg;

import java.util.List;

/**
 * Main class that runs the functions that output the reports
 * to the main output.
 *
 */

public class SalesReport {

	public static void main(String args[]) {
		
		List<Person> persons = LoadFiles.loadPersonsFile();

		List<Store> stores = LoadFiles.loadStoresFile(persons);
	
		List<Item> items = LoadFiles.loadItemsFile();

		List<Sale> sales = LoadFiles.loadSalesFile(persons,stores,items);
		
		Report.SalesReport(sales);
		
		Report.SalesPerson(sales);
		
		Report.StoreSales(sales); 
	}
	
	
}
