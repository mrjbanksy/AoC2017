package day06;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import day02.Day2Part1;

public class Day6Part1 {
	private static final String inputFile = "/day06/input.txt";

	public static void main(String[] args) {
		InputStream inputFileStream = Day2Part1.class.getResourceAsStream(inputFile);
		if (inputFileStream == null) {
			System.out.println("Unable to open file at " + inputFile);
			System.exit(1);
		}
		int redistributionCount = getRedistributionCount(inputFileStream);
		System.out.println("The number of redistribution is: " + redistributionCount);
	}

	private static int getRedistributionCount(InputStream inputFileStream) {
		Scanner inputScanner = new Scanner(inputFileStream);
		List<Integer> memoryBanks = new ArrayList<>();
		Set<List<Integer>> redistributionTracker = new HashSet<>();
		int redistributionCount = 0;
		for (int i = 0; i < 16; i++) {
			memoryBanks.add(inputScanner.nextInt());
		}
		inputScanner.close();
		redistributionCount = redistribute(memoryBanks, redistributionTracker, redistributionCount);
		return redistributionCount;
	}

	private static int redistribute(List<Integer> memoryBanks, Set<List<Integer>> redistributionTracker, int redistributionCount) {
		int fullestBank = getFullestBank(memoryBanks);
		List<Integer> redistributedMemoryBank = redistributeFullestBank(fullestBank, memoryBanks);
		redistributionCount++;
		if (!redistributionTracker.add(redistributedMemoryBank)) {
			return redistributionCount;
		} else {
			return redistribute(redistributedMemoryBank, redistributionTracker, redistributionCount);
		}
	}

	private static int getFullestBank(List<Integer> memoryBanks) {
		int currentMax = Integer.MIN_VALUE;
		int fullestBank = Integer.MIN_VALUE;
		for (int i = 0; i < 16; i++) {
			if (memoryBanks.get(i) > currentMax) {
				currentMax = memoryBanks.get(i);
				fullestBank = i;
			}
		}
		return fullestBank;
	}
	
	private static List<Integer> redistributeFullestBank(int fullestBank, List<Integer> memoryBanks) {
		int memUsage = memoryBanks.get(fullestBank);
		memoryBanks.set(fullestBank, 0);
		for (int i = 0; i < memUsage; i++) {
			int currentBank = (fullestBank + 1 + i) % 16;
			memoryBanks.set(currentBank, memoryBanks.get(currentBank) + 1);
		}
		return memoryBanks;
	}

}
