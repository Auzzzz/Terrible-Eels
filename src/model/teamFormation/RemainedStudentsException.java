package model.teamFormation;

import java.util.ArrayList;
import java.util.Collection;

import interfaces.Project;
import interfaces.Student;

@SuppressWarnings("serial")
public class RemainedStudentsException extends Exception {
	private Collection<Student> remainedStudents;
	private Collection<Project> formedProjects;
	
	public RemainedStudentsException(Collection<Student> remainedStudents, Collection<Project> formedProjects) {
		this.remainedStudents = new ArrayList<>(remainedStudents);
		this.formedProjects = new ArrayList<>(formedProjects);
	}
	
	public Collection<Student> getRemainedStudents() {
		return remainedStudents;
	}
	
	public Collection<Project> getFormedProjects() {
		return formedProjects;
	}
}
