package model.constraints;

import enums.Gender;
import interfaces.Student;
import interfaces.Project;

/**
 * Hard constraint: no more than 1 female student is placed in one team
 */
public class GenderConstraint extends AbstractConstraint {
	public GenderConstraint(String description) {
		super(description);
	}

	private boolean teamContainsFemale(Project project) {
		int count = 0;
		for (Student member : project.getStudents()) {
			if (member.getGender() == Gender.FEMALE) {
				count++;
			}
		}
		if(count > 1) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public boolean validateAdd(Project project, Student student) {
		if (student.getGender() == Gender.FEMALE) {
			return !teamContainsFemale(project);
		}
		
		return true;
	}
	
	@Override
	public boolean validate(Project project) {
		boolean hasFemale = false;
		
		for (Student member : project.getStudents()) {
			if (member.getGender() == Gender.FEMALE) {
				if (hasFemale) {
					return false;
				} else {
					hasFemale = true;
				}
			}
		}
		
		return true;
	}
}
