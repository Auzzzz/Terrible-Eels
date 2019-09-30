package model;

import java.util.Arrays;
import java.util.Collection;

import interfaces.Constraint;
import interfaces.ConstraintRepository;
import interfaces.SQLConnection;
import model.constraints.*;

public class ConstraintRepositoryImpl implements ConstraintRepository {
	private SQLConnection db;
	private Collection<Constraint> hardConstraints;
	
	public ConstraintRepositoryImpl(SQLConnection db) {
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
