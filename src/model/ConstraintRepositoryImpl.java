package model;

import java.util.Arrays;
import java.util.Collection;

import interfaces.Constraint;
import interfaces.ConstraintRepository;
import interfaces.DataStorage;
import model.constraints.*;

public class ConstraintRepositoryImpl implements ConstraintRepository {
	private DataStorage db;
	private Collection<Constraint> hardConstraints;
	
	public ConstraintRepositoryImpl(DataStorage db) {
		this.db = db;
		hardConstraints = Arrays.asList(new GPAConstraint("GPA Constraint"), new AverageGPAConstraint("Average GPA Constraint"), new GenderConstraint("Gender Constraint"));
	}
	
	public Collection<Constraint> getHardConstraints() {
		return this.hardConstraints;
	}
	
	public Collection<SoftConstraint> getSoftConstraints() {
		return db.getAllSoftConstraints();
	}
}
