package interfaces;

import java.util.Collection;

import model.constraints.SoftConstraint;

public interface SQLConnection {
	int getStudentCount();
	
	int getProjectCount();
		
	/**
	 * Gets all of the students stored in the database
	 * @return - a collection of students
	 */
	Collection<Student> getAllStudents();
	
	/**
	 * Gets all of the projects stored in the database
	 * @return - a collection of projects
	 */
	Collection<Project> getAllProjects();
	
	/**
	 * Gets all of the soft constraints stored in the database
	 * @return - a collection of soft constraints
	 */
	Collection<SoftConstraint> getAllSoftConstraints();
	
	/**
	 * Gets all of the female students stored in the database
	 * @return - a collection of students
	 */
	Collection<Student> getFemaleStudents();
	
	/**
	 * Gets all of the male students stored in the database
	 * @return - a collection of students
	 */
	Collection<Student> getMaleStudents();
	
	/**
	 * Gets all of the other students stored in the database
	 * @return - a collection of students
	 */
	Collection<Student> getOtherStudents();
	
	/**
	 * get the id of popular projects as a list of String
	 * @param idealNumberOfProject
	 * @return - list of project IDs
	 */
	Collection<Project> getPopularProjects(int idealNumberOfProjects);
	
	/**
	 * get project which the given student is assigned to
	 * @param student - belongs to the project to be returned
	 * @return - project
	 */
	Project getProject(Student student);
	
	/**
	 * update a project with the data of the given project
	 * @param project
	 */
	void saveProject(Project project);
	
	/**
	 * update the weight of the given constraint
	 * @param desc - of constraint to update
	 * @param weight - update
	 */
	void saveConstraint(String desc, int weight);

	/**
	 * add a project to the database
	 * @param project
	 */
	void updateProject(Project project);
}
