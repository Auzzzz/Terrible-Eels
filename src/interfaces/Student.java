package interfaces;

import java.util.Collection;
import java.util.List;
import enums.Gender;
import enums.PersonalityType;
import model.RoleRequirement;

public interface Student {
	String getStudentNo();
	
	String getName();
	
	/**
	 * get the id of four preferred projects as a list of String
	 * @return - list of project ids 
	 */
	List<String> getProjectPreferences();
	
	double getGpa();
	
	Gender getGender();
		
	PersonalityType getPersonalityType();

	Collection<RoleRequirement> getRolePreferences(); 
}
