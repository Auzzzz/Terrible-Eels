package interfaces;

import java.util.Collection;

public interface Validator {
	int calculateFit(Project project);
	
	Collection<String> getAllConstraintDesc();
	
	Constraint getConstraint(String desc);
	
	boolean validateHardConstraints(Project project, Student student);

	boolean validateHardConstraints(Project project);
}
