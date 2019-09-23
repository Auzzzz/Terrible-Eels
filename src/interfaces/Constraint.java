package interfaces;

public interface Constraint {
	/**
	 * setter for description of the constraint
	 * @param description
	 */
	void setDescription(String description);
	
	/**
	 * getter for description of the constraint
	 * @param description
	 */
	String getDescription();
	
	/**
	 * validate whether addition of the given student into the project would not violate the constraint
	 * @param project  
	 * @param student 
	 * @return - true if validated
	 */
	boolean validateAdd(Project project, Student student);
	
	/**
	 * validate whether members of the project meet the constraint
	 * @param project
	 * @return - true if validated
	 */
	boolean validate(Project project);
}
