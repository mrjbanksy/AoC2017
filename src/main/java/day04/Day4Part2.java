package day04;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Day4Part2 {
	private static final String inputFile = "/day04/input.txt";

	public static void main(String[] args) {
		InputStream inputFileStream = Day4Part2.class.getResourceAsStream(inputFile);
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
		Set<Map<String, Integer>> wordSet = new HashSet<>();
		for (String word : words) {
			Map<String, Integer> characterMap = new HashMap<>();
			String[] characters = word.split("");
			for (String character : characters) {
				if (characterMap.containsKey(character)) {
					characterMap.put(character, characterMap.get(character) + 1);
				} else {
					characterMap.put(character, 1);
				}
			}
			if (!wordSet.add(characterMap)) {
				return 0;
			}
		}
		return 1;
	}

}
