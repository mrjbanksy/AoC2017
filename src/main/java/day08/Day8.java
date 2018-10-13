package day08;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day8 {
	private static final String inputFile = "/day08/input.txt";
	private static Map<String, Integer> registerValues;
	private static int historicMax;

	public static void main(String[] args) {
		InputStream inputFileStream = Day8.class.getResourceAsStream(inputFile);
		if (inputFileStream == null) {
			System.out.println("Unable to open file at " + inputFile);
			System.exit(1);
		}
		registerValues = new HashMap<String, Integer>();
		historicMax = Integer.MIN_VALUE;
		processInstructions(inputFileStream);
		int maxValue = getMaxRegisterValue();
		System.out.println("The current max register value is " + maxValue);
		System.out.println("The historic max register value is " + historicMax);
	}

	private static void processInstructions(InputStream inputFileStream) {
		Scanner inputScanner = new Scanner(inputFileStream);
		while (inputScanner.hasNextLine()) {
			String instructionString = inputScanner.nextLine();
			Instruction instruction = new Instruction(instructionString);
			processInstruction(instruction);
		}
		inputScanner.close();
	}

	private static void processInstruction(Instruction instruction) {
		int conditionalRegisterValue = getCurrentRegisterValue(instruction.getConditionalRegister());
		boolean executeInstruction = false;
		switch (instruction.getConditionalType()) {
		case ">":
			if (conditionalRegisterValue > instruction.getConditionalValue()) {
				executeInstruction = true;
			}
			break;
		case "<":
			if (conditionalRegisterValue < instruction.getConditionalValue()) {
				executeInstruction = true;
			}
			break;
		case ">=":
			if (conditionalRegisterValue >= instruction.getConditionalValue()) {
				executeInstruction = true;
			}
			break;
		case "==":
			if (conditionalRegisterValue == instruction.getConditionalValue()) {
				executeInstruction = true;
			}
			break;
		case "<=":
			if (conditionalRegisterValue <= instruction.getConditionalValue()) {
				executeInstruction = true;
			}
			break;
		case "!=":
			if (conditionalRegisterValue != instruction.getConditionalValue()) {
				executeInstruction = true;
			}
			break;
		}
		if (executeInstruction) {
			int destinationRegisterValue = getCurrentRegisterValue(instruction.getDestinationRegister());
			switch (instruction.getInstructionType()) {
			case "inc":
				destinationRegisterValue += instruction.getInstructionValue();
				break;
			case "dec":
				destinationRegisterValue -= instruction.getInstructionValue();
				break;
			}
			if (destinationRegisterValue > historicMax) {
				historicMax = destinationRegisterValue;
			}
			registerValues.put(instruction.getDestinationRegister(), destinationRegisterValue);
		}
		
	}

	private static int getCurrentRegisterValue(String conditionalRegister) {
		if (registerValues.containsKey(conditionalRegister)) {
			return registerValues.get(conditionalRegister);
		} else {
			return 0;
		}
	}
	
	private static int getMaxRegisterValue() {
		return Collections.max(registerValues.values());
	}
}
