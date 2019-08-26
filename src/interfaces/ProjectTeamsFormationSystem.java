package interfaces;

import java.util.List;

public interface ProjectTeamsFormationSystem {
	boolean swap(Student s1, Student s2, int acceptableChange);
	
	List<Project> getPopularProjects();
	
	void assign();
}
