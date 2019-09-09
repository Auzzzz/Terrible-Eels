package interfaces;

import java.util.List;

public interface ProjectTeamsFormationSystem {
	List<String> getAllProjectDescs();
	
	List<Project> getPopularProjects();
	
	List<String> getPopularProjectDescs();
	
	List<Project> getUnPopularProjects();
	
	boolean swap(Student s1, Student s2, int acceptableChange);
	
	void assign();
}
