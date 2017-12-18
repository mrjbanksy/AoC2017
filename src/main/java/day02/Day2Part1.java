package day02;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Advent of Code 2017 Day 2: Calculate checksum of a table. Calculate the
 * difference of the largest and smallest value in each row, then sum the
 * differences for the checksum
 * 
 * @author Jeremy Banks
 *
 */
public class Day2Part1 {
	private static final String inputFile = "/day02/input.txt";

	public static void main(String[] args) {
		InputStream inputFileStream = Day2Part1.class.getResourceAsStream(inputFile);
		if (inputFileStream == null) {
			System.out.println("Unable to open file at " + inputFile);
			System.exit(1);
		}
		int checksum = getChecksum(inputFileStream);
		System.out.println("The checksum is: " + checksum);
	}

	private static int getChecksum(InputStream inputFileStream) {
		Scanner inputScanner = new Scanner(inputFileStream);
		int checksum = 0;
		while (inputScanner.hasNextLine()) {
			String line = inputScanner.nextLine();
			int lineDifference = getLineDifference(line);
			checksum += lineDifference;
		}
		inputScanner.close();
		return checksum;
	}

	private static int getLineDifference(String line) {
		int lineMax = Integer.MIN_VALUE;
		int lineMin = Integer.MAX_VALUE;
		Scanner lineScanner = new Scanner(line);
		while (lineScanner.hasNextInt()) {
			int currentInt = lineScanner.nextInt();
			lineMax = Integer.max(lineMax, currentInt);
			lineMin = Integer.min(lineMin, currentInt);
		}
		lineScanner.close();
		int lineDifference = lineMax - lineMin;
		return lineDifference;
	}

}
