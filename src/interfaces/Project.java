package interfaces;

import java.util.List;

public interface Project {
	static final int TEAM_CAPACITY = 4;
	
	String getId();
	
	String getProjectDesc();
	
	List<Student> getStudents();
	
	void addStudent(Student student);
	
	boolean removeStudent(Student student);
	
	int calculateFit();
}
