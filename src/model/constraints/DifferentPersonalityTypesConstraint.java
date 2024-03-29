package model.constraints;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import interfaces.Project;
import interfaces.Student;
import enums.PersonalityType;

/**
 * Soft constraint: all group members are of different personality types
 */
public class DifferentPersonalityTypesConstraint extends SoftConstraint {
	public DifferentPersonalityTypesConstraint(String description, int weight) {
		super(description, weight);
	}
	
	@Override
	public boolean validateAdd(Project project, Student student) {
		Collection<Student> members = project.getStudents();
		PersonalityType studentType = student.getPersonalityType();

		for (Student member : members) {
			if (member.getPersonalityType().equals(studentType)) {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public boolean validate(Project project) {
		Set<PersonalityType> types = new HashSet<>(); 
		
		for (Student member : project.getStudents()) {
			PersonalityType type = member.getPersonalityType();
						
			if (types.contains(type)) {
				return false;
			}
			
			types.add(type);
		}
		
		return true;
	}
}
