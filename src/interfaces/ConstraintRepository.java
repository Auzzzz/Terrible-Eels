package interfaces;

import java.util.Collection;

import model.constraints.SoftConstraint;

public interface ConstraintRepository {
	public Collection<Constraint> getHardConstraints();
	
	public Collection<SoftConstraint> getSoftConstraints();
}
