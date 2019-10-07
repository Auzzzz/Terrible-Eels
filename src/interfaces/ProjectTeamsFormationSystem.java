package interfaces;

import java.util.Collection;

import model.RoleRequirement;
import model.teamFormation.InsufficientProjectsException;
import model.teamFormation.InsufficientStudentsException;
import model.teamFormation.RemainedStudentsException;

public interface ProjectTeamsFormationSystem {
	/**
	 * get description of all projects
	 * @return
	 */
	Collection<String> getAllProjectDescs();
	
	/**
	 * gets the most popular projects
	 * @return
	 */
	Collection<String> getPopularProjectDescs();
	
	/**
	 * swaps members between teams as long as the overall fitness value for swapped teams 
	 * does not change by more than the specified value
	 * @param s1 - student number
	 * @param s2 - student number
	 * @param acceptableChange
	 * @return - true if swap succeeded
	 */
	boolean swap(String s1, String s2, int acceptableChange);
	
	/**
	 * form teams by assigning students into projects
	 * @return - all formed projects
	 * @throws InsufficientStudentsException 
	 * @throws InsufficientProjectsException 
	 * @throws RemainedStudentsException 
	 */
	Collection<Project> formTeams() throws InsufficientProjectsException, InsufficientStudentsException, RemainedStudentsException;

	/**
	 * save all formed projects 
	 */
	void confirmTeams();
	
	/**
	 * adds a project to the database
	 * @param desc
	 * @param roles
	 */
	void addProject(String desc, Collection<RoleRequirement> roles);
	
	/**
	 * sets the preferences of a student
	 * @param studentID
	 * @param projectIDs
	 */
	void setPreferences(String studentID, Collection<String> projectIDs);
	
	/**
	 * sets the blacklist of a student
	 * @param studentID
	 * @param blacklistID
	 */
	void addToBlacklist(String studentID, String blacklistID);

	/**
	 * sets the roles of a student
	 * @param studentID
	 * @param roles
	 */
	void setRoles(String studentID, Collection<RoleRequirement> roles);

	boolean checkStudentNum(String studentNo);	
}
