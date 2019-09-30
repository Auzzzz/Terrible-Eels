package interfaces;

import java.util.Collection;

public interface Validator {

	/**
	 * Calculate's the fit of a project based on the soft constraint's that the
	 * project fits, and their weight, i.e if the project fits 2 soft constraints
	 * with weight 2 and 1 with weight 3, the fit will be 7.
	 * 
	 * @param project
	 * @return
	 */
	int calculateFit(Project project);

	/**
	 * gets all constraints imposed on the system
	 * 
	 * @return
	 */
	Collection<String> getAllConstraintDesc();

	/**
	 * gets a particular constraint based on it's description
	 * @param desc
	 * @return
	 */
	Constraint getConstraint(String desc);
	
	/**
	 * Ensures that adding the given student to the given project will not violate any hard constraints
	 * @param project
	 * @param student
	 * @return
	 */
	boolean validateHardConstraints(Project project, Student student);

	/**
	 * Checks that the given project does not violate any hard constraints
	 * @param project
	 * @param student
	 * @return
	 */
	boolean validateHardConstraints(Project project);
}
