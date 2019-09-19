package model.teamFormation;

import java.util.ArrayList;
import java.util.Collection;

import interfaces.Student;

public class RemainedStudentsException extends Exception {
	private Collection<Student> remainedStudents = new ArrayList<>();
	
	public RemainedStudentsException(Collection<Student> remainedStudents) {
		this.remainedStudents = remainedStudents;
	}
}
