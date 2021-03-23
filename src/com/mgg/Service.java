package com.mgg;

/**
 *Class that models a service, using the variables hourlyRate,
 *employee, code, type, name, and hoursWorked.
 *
 */

public class Service extends Item {

	

	private double hourlyRate;
	private Person employee;
	private double hoursWorked;
	
	public Service(String code, String type, String name, double hourlyRate) {
		super(code, type, name);
		
		this.hourlyRate = hourlyRate;
		
	}

	public double getHourlyRate() {
		return hourlyRate;
	}
	public double getHoursWorked() {
		return hoursWorked;
	}

	@Override
	public String toString() {
		return getCode() + ", " + getType() + ", " + getName() + "Service [hourlyRate=" + hourlyRate + "]";
	}
	
	public void addHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	
	public void addEmployee(Person employee) {
		this.employee = employee;
	}
	
	
}
