package interfaces;

import java.util.List;

import model.constraints.SoftConstraint;

public interface SQLConnection {
	int getStudentCount();
	
	int getProjectCount();
		
	List<Student> getAllStudents();
	
	List<Project> getAllProjects();
	
	List<Constraint> getAllHardConstraints();
	
	List<SoftConstraint> getAllSoftConstraints();
	
	List<Student> getFemaleStudents();
	
	List<Student> getMaleStudents();
	
	List<Project> getPopularProjects(int idealNumberOfProjects);
	
	List<Project> getUnPopularProjects();
	
	Project getProject(Student student);
	
	void updateProject(Project project);
}
