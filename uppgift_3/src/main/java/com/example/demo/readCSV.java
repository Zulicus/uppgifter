package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.core.io.ClassPathResource;

public class readCSV {

	private static String COMMA_DELIMITER = ",";
	private static ArrayList<ArrayList<String>> wholeSheet = new ArrayList<ArrayList<String>>();

	public static ArrayList<ArrayList<String>> getWholeSheet() {
		if (wholeSheet.isEmpty()) {
			loadCSV();
		}
		return wholeSheet;
	}

	public static void printCSV() {
		for (ArrayList<String> row : wholeSheet) {
			System.out.println(row);
		}
	}

	private static void loadCSV() {
		var csvFile = new ClassPathResource("sample.csv");
		try (Scanner scanner = new Scanner(csvFile.getFile())) {
			int i = 0;
			while (scanner.hasNextLine()) {
				wholeSheet.add(getRows(scanner.nextLine(), i));
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static ArrayList<String> getRows(String nextLine, int i) {
		ArrayList<String> rowValues = new ArrayList<String>();
		try (Scanner rowScanner = new Scanner(nextLine)) {
			rowScanner.useDelimiter(COMMA_DELIMITER);
			rowValues.add(String.valueOf(i));
			while (rowScanner.hasNext()) {
				rowValues.add(rowScanner.next());
			}
		}
		return rowValues;
	}
}
