package model;

import java.util.*;

import enums.Gender;
import enums.PersonalityType;
import interfaces.*;

public class StudentImpl implements Student {
	
	String name;
	String studentNo;
	PersonalityType personalityType;
	Gender gender;
	int experience;
	double gpa;
	ArrayList<Project> projectPreferences = new ArrayList<Project>();
	ArrayList<RoleRequirement> rolePreferences = new ArrayList<RoleRequirement>();
	ArrayList<Student> blacklist = new ArrayList<Student>();
	
	public StudentImpl(String name, String studentNo, PersonalityType personalityType, 
		Gender gender, int experience, double gpa, 
		Collection<Project> projectPreferences, 
		Collection<RoleRequirement> rolePreferences, 
		Collection<Student> blacklist) {
		
		this.name = name;
		this.studentNo = studentNo;
		this.personalityType = personalityType;
		this.gender = gender;
		this.experience = experience;
		this.gpa = gpa;
		
		// TODO ClassCastException?
		this.projectPreferences = (ArrayList<Project>) projectPreferences;
		this.rolePreferences = (ArrayList<RoleRequirement>) rolePreferences;
		this.blacklist = (ArrayList<Student>) blacklist;
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
		List<String> preferences = new ArrayList<>();
		for (Project project : projectPreferences) {
			preferences.add(project.getId());
		}
		
		return preferences;
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

	@Override
	public Collection<RoleRequirement> getRolePreferences() {
		return this.rolePreferences;
	}

	@Override
	public void setPreferences(Collection<Project> projects) {
		
		this.projectPreferences = (ArrayList<Project>) projects;
		
	}

	@Override
	public void setRolePreferences(Collection<RoleRequirement> roles) {
		
		this.rolePreferences = (ArrayList<RoleRequirement>) roles;
		
	}

	@Override
	public void addBlacklist(Student student) {

		this.blacklist.add(student);

	}

	@Override
	public double getExperience() {
		return this.experience;
	}

	

}
