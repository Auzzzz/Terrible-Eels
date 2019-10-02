package interfaces;

import java.util.Collection;

import model.teamFormation.InsufficientProjectsException;
import model.teamFormation.InsufficientStudentsException;
import model.teamFormation.RemainedStudentsException;

public interface TeamFormationEngine {
	
	/**
	 * get popular projects as a list of Projects
	 * the number of popular projects equals to the number of teams to be formed
	 * @return - list of Projects
	 * @throws InsufficientStudentsException 
	 */
	Collection<Project> getPopularProjects() throws InsufficientStudentsException;
		
	/**
	 * swaps members between teams as long as the overall fitness value for swapped teams 
	 * does not change by more than the specified value
	 * @param s1 - student number
	 * @param s2 - student number
	 * @param acceptableChange
	 * @return - true if swap succeeded
	 */
	boolean swap(String sNo1, String sNo2, int acceptableChange);
	
	/**
	 * assign all students into projects
	 * @return - all formed projects
	 * @throws InsufficientStudentsException 
	 * @throws InsufficientProjectsException 
	 * @throws RemainedStudentsException 
	 */
	Collection<Project> assignStudents() throws InsufficientProjectsException, InsufficientStudentsException, RemainedStudentsException;

	/**
	 * save all formed projects 
	 */
	void confirmTeams();
}
