package model.constraints;

import java.util.List;

import interfaces.Project;
import interfaces.Student;

/**
 * Hard constraint: Every team should have at least two or more students with GPA 3
 */
public class GPAConstraint extends AbstractConstraint {
	public static final double GPA_THREE = 3.0;
	public static final int COUNT_TWO = 2;
	private double gpa = GPA_THREE;  // variable - change allowed
	
	public GPAConstraint(String description) {
		super(description);
	}
	
	public void setGPA(double gpa) {
		this.gpa = gpa;
	}
	
	public double getGPA() {
		return this.gpa;
	}
	
	private int countGPAThree(List<Student> students) {
		int gpaThreeCount = 0;
		
		for (Student student : students) {
			if (student.getGpa() >= GPA_THREE) {
				gpaThreeCount++;
			}
		}
		
		return gpaThreeCount;
	}
	
	@Override
	public boolean validate(Project project, Student student) {
		List<Student> members = (List<Student>)project.getStudents();
		int memberCount = members.size();
		int gpaThreeCount = countGPAThree(members);  // number of members with GPA > 3.0
		double candidateGPA = student.getGpa();
		
		switch(memberCount) {
			case 0:
			case 1:
				return true;
			
			case 2:
				// if two existing members are less than 3 and this member is also less than 3, then false
				if ((gpaThreeCount == 0) && (candidateGPA >= GPA_THREE)) {
					return true;
				}
				break;
				
			case 3:
				if ((gpaThreeCount < COUNT_TWO) && (candidateGPA >= GPA_THREE)) {
					return true;
				}
				
				if ((gpaThreeCount == COUNT_TWO) && (candidateGPA < GPA_THREE)) {
					return true;
				}
				
				break;
			
			default:
				return false;
		}
		
		return false;
	}
	
	@Override
	public boolean validate(Project project) {
		return (countGPAThree((List<Student>)project.getStudents()) >= COUNT_TWO) ? true : false;
	}
}