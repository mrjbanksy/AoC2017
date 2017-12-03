package day1;

public class Day1Part1 {

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
		for (int i = 0; i < input.length() - 1; i++) {
			int curLoc = Character.getNumericValue(input.charAt(i));
			int curLocPlusOne = Character.getNumericValue(input.charAt(i + 1));
			if (curLoc == curLocPlusOne) {
				sum += curLoc;
			}
		}
		int firstInt = Character.getNumericValue(input.charAt(0));
		int lastInt = Character.getNumericValue(input.charAt(input.length() - 1));
		if (firstInt == lastInt) {
			sum += lastInt;
		}
		return sum;
	}

}