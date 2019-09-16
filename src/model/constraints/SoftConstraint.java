package model.constraints;

import interfaces.Project;
import interfaces.Student;

public abstract class SoftConstraint extends AbstractConstraint {
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
	public abstract boolean validate(Project project, Student student);
	
	@Override
	public abstract boolean validate(Project project);
}
