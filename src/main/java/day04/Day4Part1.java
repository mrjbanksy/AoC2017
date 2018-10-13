package day04;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day4Part1 {
	private static final String inputFile = "/day04/input.txt";

	public static void main(String[] args) {
		InputStream inputFileStream = Day4Part1.class.getResourceAsStream(inputFile);
		if (inputFileStream == null) {
			System.out.println("Unable to open file at " + inputFile);
			System.exit(1);
		}
		int validPhrases = getValidPhrases(inputFileStream);
		System.out.println("The number of valid passphrases is: " + validPhrases);
	}

	private static int getValidPhrases(InputStream inputFileStream) {
		Scanner inputScanner = new Scanner(inputFileStream);
		int validCount = 0;
		while (inputScanner.hasNextLine()) {
			String phrase = inputScanner.nextLine();
			validCount += checkPhrase(phrase);
		}
		inputScanner.close();
		return validCount;
	}

	/**
	 * @param phrase The phrase to check
	 * @return 1 if valid, 0 if not so I don't have to do a conditional check with a boolean above
	 */
	private static int checkPhrase(String phrase) {
		String[] words = phrase.split("\\s+");
		Set<String> wordset = new HashSet<>();
		for (String word : words) {
			if (!wordset.add(word)) {
				return 0;
			}
		}
		return 1;
	}

}
