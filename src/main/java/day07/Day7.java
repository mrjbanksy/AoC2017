package day07;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day7 {
	private static final String inputFile = "/day07/input.txt";

	public static void main(String[] args) {
		InputStream inputFileStream = Day7.class.getResourceAsStream(inputFile);
		if (inputFileStream == null) {
			System.out.println("Unable to open file at " + inputFile);
			System.exit(1);
		}
		Map<String, Program> programMap = parsePrograms(inputFileStream);
		Program bottomProgram = getBottomProgram(programMap);
		System.out.println("The bottom program is " + bottomProgram.getName());
		Program unbalancedProgram = getUnbalancedProgram(bottomProgram);
		int correctWeight = getCorrectWeight(unbalancedProgram);
		System.out.println("The correct weight for the unbalanced program is: " + correctWeight);
	}

	private static Map<String, Program> parsePrograms(InputStream inputFileStream) {
		Map<String, Program> programMap = new HashMap<String, Program>();
		Map<String, String> parentChildMap = new HashMap<String, String>();
		Scanner inputScanner = new Scanner(inputFileStream);
		while (inputScanner.hasNextLine()) {
			String programData = inputScanner.nextLine();
			String[] programDataArray = programData.split(" ", 4);
			String programName = programDataArray[0];
			int programWeight = Integer.parseInt(programDataArray[1].substring(1, programDataArray[1].length() - 1));
			Program program = new Program();
			program.setName(programName);
			program.setWeight(programWeight);
			programMap.put(programName, program);
			if (programDataArray.length == 4) {
				parentChildMap.put(programName, programDataArray[3]);
			}
		}
		inputScanner.close();
		parentChildMap.entrySet().forEach(entry -> {
			Program parent = programMap.get(entry.getKey());
			List<Program> children = new ArrayList<Program>();
			String[] childArray = entry.getValue().split(", ");
			for (String child : childArray) {
				children.add(programMap.get(child));
			}
			parent.setChildren(children);
			for (Program child : children) {
				child.setParent(parent);
			}
		});
		return programMap;
	}

	private static Program getBottomProgram(Map<String, Program> programMap) {
		Program bottomProgram = programMap.entrySet().iterator().next().getValue();
		while (bottomProgram.getParent() != null) {
			bottomProgram = bottomProgram.getParent();
		}
		return bottomProgram;
	}

	private static Program getUnbalancedProgram(Program program) {
		List<Integer> childWeight = new ArrayList<Integer>();
		for (Program child : program.getChildren()) {
			childWeight.add(getTowerWeight(child));
		}
		List<Integer> sortedWeight = childWeight.stream().sorted().collect(Collectors.toList());
		if (sortedWeight.get(0) == sortedWeight.get(sortedWeight.size() - 1)) {
			return program;
		} else {
			for (Program child : program.getChildren()) {
				return getUnbalancedProgram(child);
			}
		}
		return null;
	}

	private static int getTowerWeight(Program program) {
		int childWeight = 0;
		for (Program child : program.getChildren()) {
			childWeight += getTowerWeight(child);
		}
		return program.getWeight() + childWeight;
	}
	
	private static int getCorrectWeight(Program unbalancedProgram) {
		Program parent = unbalancedProgram.getParent();
		for (Program child : parent.getChildren()) {
			System.out.println("This child's weight is " + child.getWeight());
			System.out.println("Its tower weight is " + getTowerWeight(child));
		}
		return 0;
	}
}
