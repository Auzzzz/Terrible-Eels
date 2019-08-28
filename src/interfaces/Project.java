package interfaces;

import java.util.List;

public interface Project {
	static final int TEAM_CAPACITY = 4;
	
	List<Student> getMembers();
}
