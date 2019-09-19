package model.constraints;

import java.util.ArrayList;
import java.util.List;

import interfaces.Project;
import interfaces.Student;

/**
 *  Hard constraint: no team should have an average GPA exceeding 3.5
 */
public class AverageGPAConstraint extends AbstractConstraint {
	public static final double GPA_AVERAGE_2019 = 3.5;	// may be changed later by project manager
	private double averageGPA = GPA_AVERAGE_2019;		// variable to allow to be changed
	String description;
	
	public AverageGPAConstraint(String description) {
		super(description);
	}
	
	public void setAverageGPA(double gpa) {
		this.averageGPA = gpa;
	}
	
	public double getAverageGPA() {
		return averageGPA;
	}
	
	private double calculateAverageGPA(List<Student> students) {
		double totalGPA = 0;
		
		for (Student member : students) {
			totalGPA += member.getGpa();
		}
		
		return totalGPA / Project.TEAM_CAPACITY;
	}
	
	@Override
	public boolean validate(Project project, Student student) {
		List<Student> members = (List<Student>) project.getStudents();
		int count = members.size();
		
		// if there are two members or less, then new member can be added regardless
		if (count < (Project.TEAM_CAPACITY - 1)) {
			return true;	
		} 
		
		// three members - one more member can join
		if (count == Project.TEAM_CAPACITY - 1) {
			List<Student> currentMembers = new ArrayList<>(members);
			currentMembers.add(student);
						
			if (calculateAverageGPA(currentMembers) <= this.averageGPA) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean validate(Project project) {
		double averageGPA = calculateAverageGPA((List<Student>) project.getStudents());
		
		return (averageGPA >= this.averageGPA) ? false : true;
	}
}
