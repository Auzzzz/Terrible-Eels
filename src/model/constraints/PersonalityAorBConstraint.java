package model.constraints;

import interfaces.Student;
import enums.PersonalityType;
import interfaces.Project;

/**
 * Soft constraint: each group contains at least one type A or B student
 */
public class PersonalityAorBConstraint extends SoftConstraint {
	public PersonalityAorBConstraint(String description, int weight) {
		super(description, weight);
	}
	 
	@Override
	public boolean validateAdd(Project project, Student student) {
		if (!validate(project)) {
			PersonalityType type = student.getPersonalityType();
			return (type.equals(PersonalityType.A) || type.equals(PersonalityType.B));
		}
		return true;
	}

	@Override
	public boolean validate(Project project) {
		for (Student member : project.getStudents()) {
			PersonalityType type = member.getPersonalityType();
			
			if (type.equals(PersonalityType.A) || type.equals(PersonalityType.B)) {
				return true;
			}
		}
		return false;
	}
}
