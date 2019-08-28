package interfaces;

import enums.Gender;
import enums.PersonalityType;

public interface Student {
	double getGpa();
	
	PersonalityType getPersonalityType();
	
	Gender getGender();
}
