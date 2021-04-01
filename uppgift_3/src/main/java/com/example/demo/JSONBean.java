package com.example.demo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class JSONBean {
	private String JSON = " ";
	private ArrayList<String> specificJSON = new ArrayList<String>();
	private ArrayList<String> errorFinder = new ArrayList<String>();

	public JSONBean() {
		createJSON();
	}

	public String getJSON() {
		return JSON;
	}

	private void createJSON() {
		ArrayList<CSVBean> CSVlist = getCSV();
		String pattern = "{\"Order Dates\":{";
		JSON += pattern;
		for (int i = 1; i < CSVlist.size(); i++) {
			if (i + 1 == CSVlist.size()) {
				pattern = "\"Date\":\"%s\":{\"Region\":\"%s\",\"Name\":{\"Last name\":\"%s\",\"First Name\":\"%s\"},\"Item\":\"%s\",\"Units\":\"%s\",\"Unit Cost\":\"%s\",\"Total\":\"%s\"}}}";
				JSON += format(CSVlist, i, pattern);
			} else {
				pattern = "\"Date\":\"%s\":{\"Region\":\"%s\",\"Name\":{\"Last name\":\"%s\",\"First Name\":\"%s\"},\"Item\":\"%s\",\"Units\":\"%s\",\"Unit Cost\":\"%s\",\"Total\":\"%s\"},";
				JSON += format(CSVlist, i, pattern);
			}
		}
	}

	private String format(ArrayList<CSVBean> CSVlist, int i, String pattern) {
		return String.format(pattern, CSVlist.get(i).getDate(), CSVlist.get(i).getRegion(),
				CSVlist.get(i).getLastName(), CSVlist.get(i).getFirstName(), CSVlist.get(i).getItem(),
				CSVlist.get(i).getUnits(), CSVlist.get(i).getUnitCost(), CSVlist.get(i).getTotal());
	}

	public String getSpecificJSON(String columnChoice) {

		switch (columnChoice.toLowerCase()) {
		case "orderdate":
			return priettyJSON(redundencyCheck("OrderDate", 1), "Order Date", 1);
		case "region":
			return priettyJSON(redundencyCheck("Region", 2), "Region", 1);
		case "rep1":
			return priettyJSON(redundencyCheck("Rep1", 3), "Last Name", 1);
		case "rep2":
			return priettyJSON(redundencyCheck("Rep2", 4), "First Name", 1);
		case "item":
			return priettyJSON(redundencyCheck("Item", 5), "Item", 1);
		case "units":
			return priettyJSON(redundencyCheck("Units", 6), "Units", 1);
		case "unitcost":
			return priettyJSON(redundencyCheck("UnitCost", 7), "Unit Cost", 1);
		case "total":
			return priettyJSON(redundencyCheck("Total", 8), "Total", 1);
		default:
			return "Invalid Input";
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
			ArrayList<CSVBean> CSVlist = getCSV();
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

	public ArrayList<CSVBean> getCSV() {
		ArrayList<CSVBean> CSVlist = new ArrayList<CSVBean>();
		for (ArrayList<String> row : readCSV.getWholeSheet()) {
			CSVBean bean = new CSVBean(row);
			CSVlist.add(bean);
		}
		return CSVlist;
	}

	public String getSortedJSON(String column) {
		getSpecificJSON(column);
		if (column.toLowerCase().equals("units") || column.toLowerCase().equals("unitcost")
				|| column.toLowerCase().equals("total")) {
			ArrayList<Double> numberList = new ArrayList<Double>();
			for (int i = 1; i < specificJSON.size(); i++) {
				numberList.add(Double.parseDouble(specificJSON.get(i)));
			}
			Collections.sort(numberList);
			specificJSON.clear();
			for (Double item : numberList) {
				specificJSON.add(item.toString());
			}
		} else {
			Collections.sort(specificJSON);
		}
		return priettyJSON(specificJSON, column, 0);
	}

	public String priettyJSON(ArrayList<String> inJSON, String column, int startpoint) {
		String pattern = "{\"%s\":{";
		pattern = String.format(pattern, column);
		for (int i = startpoint; i < inJSON.size(); i++) {
			pattern += "\"" + inJSON.get(i) + "\",";
		}
		pattern = pattern.replaceAll(",$", "}}");
		return pattern;

	}
}
