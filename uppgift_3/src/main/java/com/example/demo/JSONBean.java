package com.example.demo;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class JSONBean {
	private String JSON = " ";
	private ArrayList<String> specificJSON = new ArrayList<String>();
	private ArrayList<String> errorFinder = new ArrayList<String>();
	private ArrayList<String> error = new ArrayList<String>();

	public JSONBean() {
		createJSON();
		error.add("Invalid Input");
	}

	public String getJSON() {
		return JSON;
	}

	private void createJSON() {
		ArrayList<CSVBean> CSVlist = makeDates();
		String pattern = "{\"Order Date\":{";
		JSON += pattern;
		for (int i = 1; i < CSVlist.size(); i++) {
			if (i + 1 == CSVlist.size()) {
				pattern = "\"%s\":{\"Region\":\"%s\",\"Last name\":\"%s\",\"First Name\":\"%s\",\"Item\":\"%s\",\"Units\":\"%s\",\"Unit Cost\":\"%s\",\"Total\":\"%s\"}}}";
				JSON += format(CSVlist, i, pattern);
			} else {
				pattern = "\"%s\":{\"Region\":\"%s\",\"Last name\":\"%s\",\"First Name\":\"%s\",\"Item\":\"%s\",\"Units\":\"%s\",\"Unit Cost\":\"%s\",\"Total\":\"%s\"},";
				JSON += format(CSVlist, i, pattern);
			}
		}
	}

	private String format(ArrayList<CSVBean> CSVlist, int i, String pattern) {
		return String.format(pattern, CSVlist.get(i).getDate(), CSVlist.get(i).getRegion(),
				CSVlist.get(i).getLastName(), CSVlist.get(i).getFirstName(), CSVlist.get(i).getItem(),
				CSVlist.get(i).getUnits(), CSVlist.get(i).getUnitCost(), CSVlist.get(i).getTotal());
	}

	public ArrayList<String> getSpecificJSON(String columnChoice) {

		switch (columnChoice.toLowerCase()) {
		case "orderdate":
			return redundencyCheck("OrderDate", 1);
		case "region":
			return redundencyCheck("Region", 2);
		case "rep1":
			return redundencyCheck("Rep1", 3);
		case "rep2":
			return redundencyCheck("Rep2", 4);
		case "item":
			return redundencyCheck("Item", 5);
		case "units":
			return redundencyCheck("Units", 6);
		case "unitcost":
			return redundencyCheck("UnitCost", 7);
		case "total":
			return redundencyCheck("Total", 8);
		default:
			return error;
		}

	}

	public ArrayList<String> redundencyCheck(String check, int column) {
		try {
			if (specificJSON.get(0).equals(check)) {
				return specificJSON;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		specificJSON.clear();
		for (ArrayList<String> row : readCSV.getWholeSheet()) {
			specificJSON.add(row.get(column));
		}
		return specificJSON;

	}

	public ArrayList<String> errorFinder() {
		if (errorFinder.isEmpty()) {
			DecimalFormat format = new DecimalFormat("0.00");
			ArrayList<CSVBean> CSVlist = makeDates();
			for (int i = 1; i < CSVlist.size(); i++) {
				double check = Double.parseDouble(format.format(Double.parseDouble(CSVlist.get(i).getUnits())
						* Double.parseDouble(CSVlist.get(i).getUnitCost())).replace(",", "."));
				double result = Double.parseDouble(CSVlist.get(i).getTotal());
				if (check != result) {
					String pattern = "{\"Order Date\":{\"%s\":{\"Region\":\"%s\",\"Last name\":\"%s\",\"First Name\":\"%s\",\"Item\":\"%s\",\"Units\":\"%s\",\"Unit Cost\":\"%s\",\"Total\":\"%s\"}}}";
					errorFinder.add(format(CSVlist, i, pattern));
				}
			}
		}
		return errorFinder;
	}

	public ArrayList<CSVBean> makeDates() {
		ArrayList<CSVBean> CSVlist = new ArrayList<CSVBean>();
		for (ArrayList<String> row : readCSV.getWholeSheet()) {
			CSVBean bean = new CSVBean(row);
			CSVlist.add(bean);
		}
		return CSVlist;
	}
}
