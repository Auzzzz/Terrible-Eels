package interfaces;

import java.util.Collection;
import java.util.List;
import enums.Gender;
import enums.PersonalityType;
import model.RoleRequirement;

public interface Student {
	
	/**
	 * gets the student's id
	 * @return
	 */
	String getStudentNo();
	
	/**
	 * gets the student's name
	 * @return
	 */
	String getName();
	
	/**
	 * get the id of four preferred projects as a list of String
	 * @return - list of project ids 
	 */
	List<String> getProjectPreferences();
	
	/**
	 * gets the student's gpa
	 * @return
	 */
	double getGpa();
	
	/**
	 * gets the student's gender
	 * @return
	 */
	Gender getGender();
		
	/**
	 * gets the student's personality type
	 * @return
	 */
	PersonalityType getPersonalityType();

	/**
	 * gets the students's preferred roles and skills
	 * @return - a collection of RoleRequirements
	 */
	Collection<RoleRequirement> getRolePreferences(); 
	
	/**
	 * Sets a student's preferences
	 * @param projects
	 */
	void setPreferences(Collection<Project> projects);
	
	/**
	 * Sets a student's preferred roles and skills
	 * @param roles
	 */
	void setRolePreferences(Collection<RoleRequirement> roles);
	
	/**
	 * Adds a student to this student's blacklist
	 * @param student
	 */
	void addBlacklist(Student student);
	
}
