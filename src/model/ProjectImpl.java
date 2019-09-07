package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import interfaces.Project;
import interfaces.Role;
import interfaces.Student;
import model.constraints.AverageGPAConstraint;

public class ProjectImpl implements Project {

	HashMap<String, Student> students = new HashMap<String, Student>();
	ArrayList<Role> rolesReq = new ArrayList<Role>();
	String projectId;
	String description;

	//Testing constructor
	public ProjectImpl() {
		
	}
	
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
		return students.remove(student.getStudentNo(), student);
	}

	@Override
	public int calculateFit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addStudent(Student student) {
		if (validateAdd(student)) {
			students.put(student.getStudentNo(), student);
		}
	}

	private boolean validateAdd(Student student) {
		/*if(AverageGPAConstraint.validate(this, student)) {
			return true;
		}*/
		return false;
	}
}
