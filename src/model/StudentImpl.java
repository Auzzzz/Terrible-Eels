package model;

import java.util.*;
import java.util.ArrayList;
import enums.Gender;
import enums.PersonalityType;
import enums.Role;
import interfaces.*;

public class StudentImpl implements Student {
	
	String name;
	String studentNo;
	PersonalityType personalityType;
	Gender gender;
	int experience;
	double gpa;
	ArrayList<Project> projectPreferences = new ArrayList<Project>();
	ArrayList<Role> rolePreferences = new ArrayList<Role>();
	ArrayList<Student> blacklist = new ArrayList<Student>();
	
	public StudentImpl(String name, String studentNo, PersonalityType personalityType, 
		Gender gender, int experience, double gpa, 
		Collection<Project> projectPreferences, 
		Collection<Role> rolePreferences, 
		Collection<Student> blacklist) {
		
		this.name = name;
		this.studentNo = studentNo;
		this.personalityType = personalityType;
		this.gender = gender;
		this.experience = experience;
		this.gpa = gpa;
		/* TODO: 
		 * projectPreferences
		 * rolePreferences
		 * blacklist
		 */
	}
	
	@Override
	public String getStudentNo() {
		return studentNo;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<String> getProjectPreferences() {
		// TODO 
		return null;
	}

	@Override
	public double getGpa() {
		return gpa;
	}

	@Override
	public Gender getGender() {
		return gender;
	}

	@Override
	public PersonalityType getPersonalityType() {
		return personalityType;
	}

}
