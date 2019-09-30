package interfaces;

import java.util.Collection;

import model.RoleRequirement;
import model.teamFormation.InsufficientProjectsException;
import model.teamFormation.InsufficientStudentsException;
import model.teamFormation.RemainedStudentsException;

public interface ProjectTeamsFormationSystem {
	Collection<String> getAllProjectDescs();
	
	/**
	 * get popular projects as a list of Projects
	 * the number of popular projects equals to the number of teams to be formed
	 * @return - list of Projects
	 * @throws InsufficientStudentsException 
	 */
	Collection<Project> getPopularProjects() throws InsufficientStudentsException;
	
	Collection<String> getPopularProjectDescs();
	
	/**
	 * swaps members between teams as long as the overall fitness value for swapped teams 
	 * does not change by more than the specified value
	 * @param s1 
	 * @param s2
	 * @param acceptableChange
	 * @return - true if swap succeeded
	 */
	boolean swap(Student s1, Student s2, int acceptableChange);
	
	
	/**
	 * assign all students into projects
	 * @return - true if all students are successfully assigned to projects
	 * @throws InsufficientStudentsException 
	 * @throws InsufficientProjectsException 
	 * @throws RemainedStudentsException 
	 */
	boolean assignStudents() throws InsufficientProjectsException, InsufficientStudentsException, RemainedStudentsException;

	/**
	 * adds a project to the database
	 * @param desc
	 * @param roles
	 */
	void addProject(String desc, Collection<RoleRequirement> roles);
	
	/**
	 * sets the preferences of a student
	 * @param student
	 * @param projects
	 */
	void setPreferences(Student student, Collection<Project> projects);
	
	/**
	 * sets the blacklist of a student
	 * @param student
	 * @param students
	 */
	void setBlacklist(Student student, Collection<Student> students);

	/**
	 * sets the roles of a student
	 * @param student
	 * @param roles
	 */
	void setRoles(Student student, Collection<RoleRequirement> roles);
	
	
}
