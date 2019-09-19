package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import interfaces.Constraint;
import interfaces.Project;
import interfaces.Student;
import interfaces.Validator;
import model.constraints.*;

public class ValidatorImpl implements Validator {
	
	HashMap<String, Constraint> constraints = new HashMap<String, Constraint>();
	
	public ValidatorImpl() {
		constraints.put("GPAverage", new AverageGPAConstraint("GPAverage"));
		constraints.put("Personality", new DifferentPersonalityTypesConstraint("Personality", 1));
		constraints.put("Gender", new GenderConstraint("Gender"));
		constraints.put("AorB", new PersonalityAorBConstraint("GPA", 1));
		constraints.put("WorkExp", new WorkExperienceConstraint("WorkExp", 1));
	}

	@Override
	public int calculateFit(Project project) {
		int result = 0;
		
		for(Constraint c : constraints.values()) {
			if(c instanceof SoftConstraint && c.validate(project)) {
				result += 1*((SoftConstraint) c).getWeight();
			}
		}
		
		return result;
	}

	@Override
	public Collection<String> getAllConstraintDesc() {
		ArrayList<String> descs = new ArrayList<String>();
		
		constraints.forEach( (k, v) -> { descs.add(k);});
		
		return descs;
	}

	@Override
	public Constraint getConstraint(String desc) {
		return constraints.get(desc);
	}

	@Override
	public boolean validateHardConstraints(Project project, Student student) {
		boolean result = true;
		
		for(Constraint constraint : constraints.values()) {
			if(!(constraint instanceof SoftConstraint)) {
				result &= constraint.validate(project, student); 
			}
		}
		
		return result;
	}

}
