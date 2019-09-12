package interfaces;

import java.util.Collection;

public interface Validator {
	int calculateFit(Project project);
	
	Collection<String> getAllConstraintDesc();
	
	Constraint getConstraint(String desc);
	
	boolean validateHardConstraints(Project project, Student student);
	
	boolean validateRolesAndSkills(Project project, Student student);
	
	// TODO if have this method, above two may be private? see if they are publicly used
	boolean validateRequirements(Project project, Student student);
}
