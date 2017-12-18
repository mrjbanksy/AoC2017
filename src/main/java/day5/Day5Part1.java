package day5;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import day2.Day2Part1;

public class Day5Part1 {
	private static final String inputFile = "/day5/input.txt";

	public static void main(String[] args) {
		InputStream inputFileStream = Day2Part1.class.getResourceAsStream(inputFile);
		if (inputFileStream == null) {
			System.out.println("Unable to open file at " + inputFile);
			System.exit(1);
		}
		int jumpCount = getJumpCount(inputFileStream);
		System.out.println("The number of jumps is: " + jumpCount);
	}

	private static int getJumpCount(InputStream inputFileStream) {
		Scanner inputScanner = new Scanner(inputFileStream);
		List<Integer> instructions = new ArrayList<>();
		while (inputScanner.hasNextLine()) {
			instructions.add(Integer.parseInt(inputScanner.nextLine()));
		}
		inputScanner.close();
		int jumpCount = 0;
		int instructionPosition = 0;
		while (instructionPosition < instructions.size()) {
			int currentPosition = instructionPosition;
			int offset = instructions.get(currentPosition);
			instructions.set(currentPosition, instructions.get(currentPosition) + 1);
			jumpCount++;
			instructionPosition = currentPosition + offset;
		}
		return jumpCount;
	}

}
