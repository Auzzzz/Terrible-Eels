package interfaces;

import java.util.Collection;

import model.RoleRequirement;

public interface Project {
	static final int TEAM_CAPACITY = 4;
	
	String getId();
	
	String getProjectDesc();
	
	Collection<Student> getStudents();
	
	void addStudent(Student student);
	
	boolean removeStudent(Student student);

	Collection<RoleRequirement> getRoleRequirements();
}
