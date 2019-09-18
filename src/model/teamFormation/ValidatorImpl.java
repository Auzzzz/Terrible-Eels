package model.teamFormation;

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
	public boolean validateHardConstraints(Project project) {
		boolean valid = false;
		
		for (Constraint constraint : connection.getAllHardConstraints()) {
			valid = (constraint.validate(project)) ? true : false;
		}
		
		return valid;
	}
}
