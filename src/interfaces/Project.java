package interfaces;

import java.util.Collection;
import model.RoleRequirement;
import model.exceptions.TooManyStudentsException;

public interface Project {
	static final int TEAM_CAPACITY = 4;
	
	/**
	 * Returns the project's id
	 * @return 
	 */
	String getId();
	
	/**
	 * Returns the project description
	 * @return
	 */
	String getProjectDesc();
	
	/**
	 * Returns the students currently assigned to the project
	 * @return - a collection of student objects
	 */
	Collection<Student> getStudents();
	
	/**
	 * Adds a student to the project
	 * @param student
	 * @throws TooManyStudentsException
	 */
	void addStudent(Student student) throws TooManyStudentsException;
	
	/**
	 * Removes a student from the project
	 * @param student
	 * @return - true if the student was removed successfully
	 */
	boolean removeStudent(Student student);

	/**
	 * Gets the projects required roles
	 * @return - a collection of RoleRequirements
	 */
	Collection<RoleRequirement> getRoleRequirements();

}
