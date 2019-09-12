package interfaces;

import java.util.List;

public interface ProjectTeamsFormationSystem {
	List<String> getAllProjectDescs();
	
	/**
	 * get popular projects as a list of Projects
	 * the number of popular projects equals to the number of teams to be formed
	 * @return - list of Projects
	 */
	List<Project> getPopularProjects();
	
	List<String> getPopularProjectDescs();
	
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
	 * assign students into projects
	 */
	void assignStudents();
}
