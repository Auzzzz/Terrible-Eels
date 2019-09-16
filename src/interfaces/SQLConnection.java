package interfaces;

import java.util.List;

public interface SQLConnection {
	int getStudentCount();
	
	int getProjectCount();
		
	List<Student> getAllStudents();
	
	List<Project> getAllProjects();
	
	List<Constraint> getAllHardConstraints();
	
	List<Constraint> getAllSoftConstraints();
	
	List<Student> getFemaleStudents();
	
	List<Student> getMaleStudents();
	
	List<Project> getPopularProjects(int idealNumberOfProjects);
	
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
