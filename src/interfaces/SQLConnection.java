package interfaces;

import java.util.Collection;

public interface SQLConnection {
	int getStudentCount();
	
	int getProjectCount();
		
	Collection<Student> getAllStudents();
	
	Collection<Project> getAllProjects();
	
	Collection<Constraint> getAllHardConstraints();
	
	Collection<Constraint> getAllSoftConstraints();
	
	Collection<Student> getFemaleStudents();
	
	Collection<Student> getMaleStudents();
	
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

	void updateProject(Project project1);
}
