package interfaces;

import java.util.Collection;

public interface Validator {
	int calculateFit(Project project);
	
	// TODO
	Collection<String> getAllConstraintDesc();
	
	Constraint getConstraint(String desc);
}
