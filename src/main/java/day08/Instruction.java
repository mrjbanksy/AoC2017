package day08;

public class Instruction {

	private String destinationRegister;
	private String instructionType;
	private int instructionValue;
	private String conditionalRegister;
	private String conditionalType;
	private int conditionalValue;

	public Instruction(String instructionString) {
		String[] split = instructionString.split(" ");
		destinationRegister = split[0];
		instructionType = split[1];
		instructionValue = Integer.parseInt(split[2]);
		conditionalRegister = split[4];
		conditionalType = split[5];
		conditionalValue = Integer.parseInt(split[6]);
		
	}

	public String getDestinationRegister() {
		return destinationRegister;
	}

	public void setDestinationRegister(String destinationRegister) {
		this.destinationRegister = destinationRegister;
	}

	public String getInstructionType() {
		return instructionType;
	}

	public void setInstructionType(String instructionType) {
		this.instructionType = instructionType;
	}

	public String getConditionalRegister() {
		return conditionalRegister;
	}

	public void setConditionalRegister(String conditionalRegister) {
		this.conditionalRegister = conditionalRegister;
	}

	public String getConditionalType() {
		return conditionalType;
	}

	public void setConditionalType(String conditionalType) {
		this.conditionalType = conditionalType;
	}

	public int getConditionalValue() {
		return conditionalValue;
	}

	public void setConditionalValue(int conditionalValue) {
		this.conditionalValue = conditionalValue;
	}

	public int getInstructionValue() {
		return instructionValue;
	}

	public void setInstructionValue(int instructionValue) {
		this.instructionValue = instructionValue;
	}

}
