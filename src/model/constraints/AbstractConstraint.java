package model.constraints;

import interfaces.Constraint;
import interfaces.Project;
import interfaces.Student;

public abstract class AbstractConstraint implements Constraint {
	private String description;
	
	public AbstractConstraint(String description) {
		this.description = description;
	}
	
	@Override
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public abstract boolean validateAdd(Project project, Student student);
	
	@Override
	public abstract boolean validate(Project project);
}
