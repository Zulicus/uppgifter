package com.example.demo;

import java.util.ArrayList;

public class CSVBean {
	private String date;
	private String region;
	private String lastName;
	private String firstName;
	private String item;
	private String units;
	private String unitCost;
	private String total;

	public String getDate() {
		return date;
	}

	public String getRegion() {
		return region;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getItem() {
		return item;
	}

	public String getUnits() {
		return units;
	}

	public String getUnitCost() {
		return unitCost;
	}

	public String getTotal() {
		return total;
	}

	public CSVBean(ArrayList<String> row) {
		this.date = row.get(1);
		this.region = row.get(2);
		this.lastName = row.get(3);
		this.firstName = row.get(4);
		this.item = row.get(5);
		this.units = row.get(6);
		this.unitCost = row.get(7);
		this.total = row.get(8);

	}
}
