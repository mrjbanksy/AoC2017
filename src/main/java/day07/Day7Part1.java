package day07;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day7Part1 {
	private static final String inputFile = "/day07/input.txt";

	public static void main(String[] args) {
		InputStream inputFileStream = Day7Part1.class.getResourceAsStream(inputFile);
		if (inputFileStream == null) {
			System.out.println("Unable to open file at " + inputFile);
			System.exit(1);
		}
		Map<String, Program> programMap = parsePrograms(inputFileStream);
		Program bottomProgram = getBottomProgram(programMap);
		System.out.println("The bottom program is " + bottomProgram.getName());
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
}
