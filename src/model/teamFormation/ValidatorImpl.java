package model.teamFormation;

import java.util.ArrayList;
import java.util.Collection;

import interfaces.Constraint;
import interfaces.Project;
import interfaces.SQLConnection;
import interfaces.Student;
import interfaces.Validator;
import model.constraints.SoftConstraint;

public class ValidatorImpl implements Validator {
	private SQLConnection connection;

	public ValidatorImpl(SQLConnection connection) {
		this.connection = connection;
	}

	@Override
	public int calculateFit(Project project) {
		int result = 0;
		for (Constraint c : connection.getAllSoftConstraints()) {
			if (c.validate(project)) {
				result += 1 * ((SoftConstraint) c).getWeight();
			}
		}
		return result;
	}

	@Override
	public Collection<String> getAllConstraintDesc() {
		ArrayList<String> descs = new ArrayList<String>();

		connection.getAllHardConstraints().forEach(c -> {
			descs.add(c.getDescription());
		});
		connection.getAllSoftConstraints().forEach(c -> {
			descs.add(c.getDescription());
		});

		return descs;
	}

	@Override
	public Constraint getConstraint(String desc) {
		
		ArrayList<Constraint> hardConstraints = (ArrayList<Constraint>) connection.getAllHardConstraints();
		ArrayList<Constraint> softConstraints = (ArrayList<Constraint>) connection.getAllSoftConstraints();
		
		for(Constraint c : hardConstraints) {
			if(c.getDescription().equalsIgnoreCase(desc)) {
				return c;
			}
		}
		
		for(Constraint c : softConstraints) {
			if(c.getDescription().equalsIgnoreCase(desc)) {
				return c;
			}
		}
		
		return null;
	}

	@Override
	public boolean validateHardConstraints(Project project, Student student) {
		// if adding the student violates any hard constraint he cannot join the project
		for (Constraint constraint : connection.getAllHardConstraints()) {
			if (!(constraint.validateAdd(project, student))) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean validateHardConstraints(Project project) {		
		for (Constraint constraint : connection.getAllHardConstraints()) {
			if (!constraint.validate(project)) {
				return false;
			}
		}
		
		return true;
	}
}
