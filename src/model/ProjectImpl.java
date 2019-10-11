package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import interfaces.Project;
import interfaces.Student;
import model.exceptions.TooManyRolesException;
import model.exceptions.TooManyStudentsException;

public class ProjectImpl implements Project {

	HashMap<String, Student> students = new HashMap<String, Student>();
	ArrayList<RoleRequirement> rolesReq = new ArrayList<RoleRequirement>();
	String projectId;
	String description;

	public ProjectImpl(String description, Collection<RoleRequirement> roles) throws TooManyRolesException {
		this.description = description;
		if (roles.size() > 4) {
			throw new TooManyRolesException();
		} else {
			this.rolesReq = (ArrayList<RoleRequirement>) roles;
		}
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
	public void addStudent(Student student) throws TooManyStudentsException {
		if (students.size() < 4) {
			students.put(student.getStudentNo(), student);
		} else {
			throw new TooManyStudentsException();
		}
	}

	@Override
	public Collection<RoleRequirement> getRoleRequirements() {
		return rolesReq;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ID: ").append(projectId).append('\n');
		builder.append("Description: ").append(description).append('\n');
		builder.append("Students: ").append('\n');

		for (String studentNo : students.keySet()) {
			builder.append("- ").append(studentNo).append('\n');
		}

		return builder.toString();
	}
}
