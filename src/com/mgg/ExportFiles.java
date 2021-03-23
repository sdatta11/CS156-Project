package com.mgg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author sdatta
 * Exports information of the people, the stores, and the items 
 * to a JSON file
 */

public class ExportFiles {

	public static void exportPersonsFile(List<Person> persons) throws FileNotFoundException {
 		//initialize pretty printing for json
 		Gson gson = new GsonBuilder().setPrettyPrinting().create();
 		//opens files
 		File outputFile = new File("data/Persons.json");
 		PrintWriter pw = new PrintWriter(outputFile);
 		
 		for (Person person : persons) {
 			String jsonPerson = gson.toJson(person);
 			//print every person in the list persons
 			pw.println(jsonPerson);
 		}
 		 			
 		pw.close();
 		
 	}
 	
 	public static void exportStoresFile(List<Store> stores) throws FileNotFoundException {
 		//initialize pretty printing for json
 		Gson gson = new GsonBuilder().setPrettyPrinting().create();
 		File outputFile = new File("data/Stores.json");
 		PrintWriter pw = new PrintWriter(outputFile);
 		
 		for (Store store : stores) {
 			String jsonStore = gson.toJson(store);
 			//print every store in the list stores
 			pw.println(jsonStore);
 		}
 		 			
 		pw.close();
 		
 		
 	}
	
 	public static void exportItemsFile(List<Item> items) throws FileNotFoundException {
 		//initialize pretty printing for json
 		Gson gson = new GsonBuilder().setPrettyPrinting().create();
 		File outputFile = new File("data/Items.json");
 		PrintWriter pw = new PrintWriter(outputFile);
 		
 		for (Item item : items) {
 			String jsonItem = gson.toJson(item);
 			//print every Item in the list items
 			pw.println(jsonItem);
 		}
 		 			
 		pw.close();
 		
 	}
	
}
