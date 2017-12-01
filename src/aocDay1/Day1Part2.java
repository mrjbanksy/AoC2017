package aocDay1;

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
		for (int i = 0; i < input.length() / 2; i++) {
			int curLoc = Character.getNumericValue(input.charAt(i));
			int curLocPlusHalfSize = Character.getNumericValue(input.charAt(i + (input.length() / 2)));
			if (curLoc == curLocPlusHalfSize) {
				sum += curLoc;
			}
		}
		return sum*2;
	}

}
