package com.mgg;

import java.time.LocalDate;

/**
 * 
 * @author sdatta
 *Class that models a subscription service, using the variables
 *code, type, name, and annualFee.
 */


public class Subscription extends Item{
	
	private double annualFee;
	private LocalDate startDate;
	private LocalDate endDate;
	
	
	public Subscription(String code, String type, String name, double annualFee) {
		super(code, type, name);
		this.annualFee = annualFee;
	}
	
	
	public double getAnnualFee() {
		return annualFee;
	}


	@Override
	public String toString() {
		return this.getCode() + ", " + getType() + ", " + getName() + "Subscription [annualFee=" + annualFee + "]";
	}
	

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void addStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public void addEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
}
