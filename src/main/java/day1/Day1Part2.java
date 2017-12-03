package day1;

/**
 * Advent of Code 2017 Day 1 Part 1: Given a string, find the sum of all digits
 * that match the digit halfway around the list. The list is circular, so the
 * digit after the last digit is the first digit in the list.
 * 
 * @author Jeremy Banks
 *
 */
public class Day1Part2 {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Incorrect command line arguments");
			return;
		}

		String input = args[0];
		int sum = checkStringForMatches(input);
		System.out.println("The sum of the matching characters is: " + sum);
	}

	private static int checkStringForMatches(String input) {
		int sum = 0;
		int length = input.length();
		for (int i = 0; i < length; i++) {
			int curLoc = Character.getNumericValue(input.charAt(i));
			int curLocPlusHalfSize = Character.getNumericValue(input.charAt((i + length / 2) % length));
			if (curLoc == curLocPlusHalfSize) {
				sum += curLoc;
			}
		}
		return sum;
	}

}