package model.constraints;

import java.util.Collection;

import interfaces.Project;
import interfaces.Student;

/**
 * Soft constraint: 
 * Every team also have one member with at least 5 years of previous work experience
 */
public class WorkExperienceConstraint extends SoftConstraint {
	public static final double EXPERIENCE = 5.0;
	
	public WorkExperienceConstraint(String description, int weight) {
		super(description, weight);
	}
	
	@Override
	public boolean validateAdd(Project project, Student student) {
		if (!validate(project)) {
			return (student.getGpa() >= EXPERIENCE);
		}
		return true;
	}
	
	@Override
	public boolean validate(Project project) {
		Collection<Student> students = project.getStudents();
		
		for (Student member : students) {
			if (member.getGpa() >= EXPERIENCE) {
				return true;
			}
		}
		return false;
	}
}
