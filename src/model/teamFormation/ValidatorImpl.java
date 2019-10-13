package model.teamFormation;

import java.util.ArrayList;
import java.util.Collection;

import interfaces.*;
import model.ConstraintRepositoryImpl;
import model.constraints.SoftConstraint;

public class ValidatorImpl implements Validator {
	private ConstraintRepository repo;

	public ValidatorImpl(DataStorage connection) {
		this.repo = new ConstraintRepositoryImpl(connection);
	}

	@Override
	public int calculateFit(Project project) {
		int result = 0;
		for (SoftConstraint c : repo.getSoftConstraints()) {
			if (c.validate(project)) {
				result += 1 * c.getWeight();
			}
		}
		return result;
	}

	@Override
	public Collection<String> getAllConstraintDesc() {
		ArrayList<String> descs = new ArrayList<String>();

		repo.getHardConstraints().forEach(c -> {
			descs.add(c.getDescription());
		});
		repo.getSoftConstraints().forEach(c -> {
			descs.add(c.getDescription());
		});

		return descs;
	}

	@Override
	public Constraint getConstraint(String desc) {
		
		ArrayList<Constraint> hardConstraints = (ArrayList<Constraint>) repo.getHardConstraints();
		ArrayList<SoftConstraint> softConstraints = (ArrayList<SoftConstraint>) repo.getSoftConstraints();
		
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
		for (Constraint constraint : repo.getHardConstraints()) {
			if (!(constraint.validateAdd(project, student))) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean validateHardConstraints(Project project) {		
		for (Constraint constraint : repo.getHardConstraints()) {
			if (!constraint.validate(project)) {
				return false;
			}
		}
		return true;
	}
}
