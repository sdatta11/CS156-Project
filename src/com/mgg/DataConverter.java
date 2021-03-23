package com.mgg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.PrintWriter;
import java.time.LocalDate;

/**
 * 
 * @author Shaunak Datta and Jacob Volling
 * handin: sdatta and jvolling
 * date: 2021/02/26
 * 
 * purpose: Main function which the program runs with
 *
 */

public class DataConverter {
 
	
	public static void main(String args[]) {
		List<Person> persons = LoadFiles.loadPersonsFile();

		List<Store> stores = LoadFiles.loadStoresFile(persons);
	
		List<Item> items = LoadFiles.loadItemsFile();

		

		
		
		try {
			ExportFiles.exportPersonsFile(persons);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			ExportFiles.exportStoresFile(stores);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			ExportFiles.exportItemsFile(items);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		

	}
}

