package day02;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Advent of Code 2017 Day 2: Calculate checksum of a table. Determine which two
 * numbers in each row divide evenly, then sum the quotients to determine the
 * checksum
 * 
 * @author Jeremy Banks
 *
 */
public class Day2Part2 {
	private static final String inputFile = "/day02/input.txt";

	public static void main(String[] args) throws Exception {
		InputStream inputFileStream = Day2Part2.class.getResourceAsStream(inputFile);
		if (inputFileStream == null) {
			System.out.println("Unable to open file at " + inputFile);
			System.exit(1);
		}
		int checksum = getChecksum(inputFileStream);
		System.out.println("The checksum is: " + checksum);
	}

	private static int getChecksum(InputStream inputFileStream) throws Exception {
		Scanner inputScanner = new Scanner(inputFileStream);
		int checksum = 0;
		while (inputScanner.hasNextLine()) {
			String line = inputScanner.nextLine();
			int lineQuotient = getLineQuotient(line);
			checksum += lineQuotient;
		}
		inputScanner.close();
		return checksum;
	}

	private static int getLineQuotient(String line) throws Exception {
		List<Integer> intList = new ArrayList<>();
		Scanner lineScanner = new Scanner(line);
		while (lineScanner.hasNextInt()) {
			int currentInt = lineScanner.nextInt();
			intList.add(currentInt);
		}
		lineScanner.close();
		// Sort the list in smallest -> largest order to hopefully speed up the following comparison
		Collections.sort(intList);
		
		for (int i = 0; i < intList.size(); i++) {
			for (int j = i + 1; j < intList.size(); j++) {
				if (intList.get(j) % intList.get(i) == 0) {
					return intList.get(j) / intList.get(i);
				}
			}
		}
		
		throw new Exception("Couldn't find evenly dividing numbers in this line: " + intList.toString());
	}

}
