package interfaces;

import java.util.Collection;
import model.RoleRequirement;
import model.exceptions.TooManyStudentsException;

public interface Project {
	static final int TEAM_CAPACITY = 4;
	
	String getId();
	
	String getProjectDesc();
	
	Collection<Student> getStudents();
	
	void addStudent(Student student) throws TooManyStudentsException;
	
	boolean removeStudent(Student student);

	Collection<RoleRequirement> getRoleRequirements();

}
