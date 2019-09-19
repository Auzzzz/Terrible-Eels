package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import interfaces.Project;
import interfaces.Student;

public class ProjectImpl implements Project {

	HashMap<String, Student> students = new HashMap<String, Student>();
	ArrayList<RoleRequirement> rolesReq = new ArrayList<RoleRequirement>();
	String projectId;
	String description;

	public ProjectImpl(String projectId, String description, Collection<RoleRequirement> roles) {
		this.projectId = projectId;
		this.description = description;
		this.rolesReq = (ArrayList<RoleRequirement>) roles;
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
	public void addStudent(Student student) {
		students.put(student.getStudentNo(), student);
	}

	@Override
	public Collection<RoleRequirement> getRoleRequirements() {
		return rolesReq;
	}
}
