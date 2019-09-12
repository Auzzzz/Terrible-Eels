package model;

import java.util.Collection;

import interfaces.Constraint;
import interfaces.Project;
import interfaces.SQLConnection;
import interfaces.Student;
import interfaces.Validator;

public class ValidatorImpl implements Validator {
	private SQLConnection connection;
	
	public ValidatorImpl(SQLConnection connection) {
		this.connection = connection;
	}

	@Override
	public int calculateFit(Project project) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<String> getAllConstraintDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Constraint getConstraint(String desc) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean validateHardConstraints(Project project, Student student) {
		// if adding the student violates any hard constraint he cannot join the project
		for (Constraint constraint : connection.getAllHardConstraints()) {
			if (!(constraint.validate(project, student))) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean validateRolesAndSkills(Project project, Student student) {
		// get required role and skills from project
		// get preferred role and skills from student
		
		// for each required role and skills of project
		// check if they match to student's skill
		// maybe ignore the student's preference?
		
		return false;
	}
	
	@Override
	public boolean validateRequirements(Project project, Student student) {
		return (validateHardConstraints(project, student) && validateRolesAndSkills(project, student));
	}
}
