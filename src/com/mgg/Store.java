package com.mgg;

import java.util.List;

/**
 * 
 * @author sdatta
 * Class that models a store for the variables storeCode,
 *  managerCode, manager, and address
 */

public class Store {
 	
	private String storeCode;
	private Address address;
	private Person manager;
	
	public Store(String storeCode, String managerCode, Person manager, Address address) {
		//super();
		this.storeCode = storeCode;
		this.address = address;
		this.manager = manager;
	}

	public String getStoreCode() {
		return storeCode;
	}



	public Person getManager() {
		return manager;
	}
	public Address getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "Store [storeCode=" + storeCode + 
				 manager
				+ ", address=" + address + "]";
	}
	
	public static Store getStore(String storeCode, List<Store> storeList) {
		
		for (int i = 0; i < storeList.size(); i++) {
			if (storeList.get(i).getStoreCode().equals(storeCode)){
				return storeList.get(i);
			}
			
			
		}
		return null;
		
		
	}
	
	

}
