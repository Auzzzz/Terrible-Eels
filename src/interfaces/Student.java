package interfaces;

import java.util.List;

import enums.Gender;
import enums.PersonalityType;

public interface Student {
	String getId();
	
	String getName();
	
	List<String> getProjectPreferences();
	
	double getGpa();
	
	Gender getGender();
		
	PersonalityType getPersonalityType(); 
}
