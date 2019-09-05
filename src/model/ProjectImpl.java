package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import interfaces.Project;
import interfaces.Role;
import interfaces.Student;

public class ProjectImpl implements Project {

	HashMap<String, Student> students = new HashMap<String, Student>();
	ArrayList<Role> rolesReq = new ArrayList<Role>();
	String projectId;
	String description;
	
	
	public ProjectImpl(String projectId, String description, Collection<Role> roles) {
		this.projectId = projectId;
		this.description = description;
		this.rolesReq = (ArrayList<Role>) roles;
	}
	
	@Override
	public String getId() {
		return projectId;
	}

	@Override
	public String getProjectDesc() {
		return description;
	}

	@Override
	public Collection<Student> getStudents() {
		return students.values();
	}

	@Override
	public boolean removeStudent(Student student) {
		return students.remove(student.getId(), student);
	}
	
	@Override
	public int calculateFit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addStudent(Student student) {
		students.put(student.getId(), student);
	}
	
}
