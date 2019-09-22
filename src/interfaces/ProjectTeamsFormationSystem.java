package interfaces;

import java.util.Collection;

import model.teamFormation.InsufficientProjectsException;
import model.teamFormation.NoStudentException;
import model.teamFormation.RemainedStudentsException;

public interface ProjectTeamsFormationSystem {
	Collection<String> getAllProjectDescs();
	
	/**
	 * get popular projects as a list of Projects
	 * the number of popular projects equals to the number of teams to be formed
	 * @return - list of Projects
	 * @throws NoStudentException 
	 */
	Collection<Project> getPopularProjects() throws NoStudentException;
	
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
	 * @throws NoStudentException 
	 * @throws InsufficientProjectsException 
	 * @throws RemainedStudentsException 
	 */
	boolean assignStudents() throws InsufficientProjectsException, NoStudentException, RemainedStudentsException;

}
