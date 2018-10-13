package day07;

import java.util.ArrayList;
import java.util.List;

public class Program {
	
	private String name;
	private int weight;
	private Program parent;
	private List<Program> children;
	
	public Program() {
		children = new ArrayList<Program>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Program getParent() {
		return parent;
	}

	public void setParent(Program parent) {
		this.parent = parent;
	}

	public List<Program> getChildren() {
		return children;
	}

	public void setChildren(List<Program> children) {
		this.children = children;
	}
	
	public void addChild(Program child) {
		children.add(child);
	}

}
