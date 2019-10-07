package model.constraints;

import interfaces.Project;
import interfaces.Student;

public abstract class SoftConstraint extends AbstractConstraint implements Comparable<SoftConstraint>{
	private int weight;
	
	public SoftConstraint(String description, int weight) {
		super(description);
		this.weight = weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	@Override
	public int compareTo(SoftConstraint o) {
		if (this.weight < o.getWeight()) {
			return -1;
		}
		if (this.weight > o.getWeight()) {
			return 1;
		}
		
		return 0;
	}
	
	@Override
	public abstract boolean validateAdd(Project project, Student student);
	
	@Override
	public abstract boolean validate(Project project);
}
