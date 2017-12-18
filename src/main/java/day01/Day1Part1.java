package day01;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Advent of Code 2017 Day 1 Part 1: Given a string, find the sum of all digits
 * that match the next digit in the list. The list is circular, so the digit
 * after the last digit is the first digit in the list.
 * 
 * @author Jeremy Banks
 *
 */
public class Day1Part1 {
	private static final String inputFile = "/day01/input.txt";

	public static void main(String[] args) {
		String input = getInputString();
		int sum = checkStringForMatches(input);
		System.out.println("The sum of the matching characters is: " + sum);
	}

	private static String getInputString() {
		InputStream inputFileStream = Day1Part1.class.getResourceAsStream(inputFile);
		if (inputFileStream == null) {
			System.out.println("Unable to open file at " + inputFile);
			System.exit(1);
		}
		Scanner inputScanner = new Scanner(inputFileStream);
		String input = inputScanner.next();
		inputScanner.close();
		return input;
	}

	private static int checkStringForMatches(String input) {
		int sum = 0;
		int length = input.length();
		for (int i = 0; i < length; i++) {
			int curLoc = Character.getNumericValue(input.charAt(i));
			int curLocPlusOne = Character.getNumericValue(input.charAt((i + 1) % length));
			if (curLoc == curLocPlusOne) {
				sum += curLoc;
			}
		}
		return sum;
	}

}