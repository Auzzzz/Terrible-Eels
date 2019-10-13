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
	ArrayList<String> projectPreferences = new ArrayList<String>();
	ArrayList<RoleRequirement> rolePreferences = new ArrayList<RoleRequirement>();
	ArrayList<Student> blacklist = new ArrayList<Student>();

	public StudentImpl(String name, String studentNo, PersonalityType personalityType, Gender gender, int experience,
			double gpa, Collection<String> projectPreferences, Collection<RoleRequirement> rolePreferences,
			Collection<Student> blacklist) {

		this.name = name;
		this.studentNo = studentNo;
		this.personalityType = personalityType;
		this.gender = gender;
		this.experience = experience;
		this.gpa = gpa;

		// TODO ClassCastException?
		this.projectPreferences = (ArrayList<String>) projectPreferences;
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
		return projectPreferences;
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
		ArrayList<String> preferences = new ArrayList<String>();
		projects.forEach(s -> {
			preferences.add(s.getId());
		});

		this.projectPreferences = preferences;

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

	@Override
	public Collection<Student> getBlacklist() {
		return blacklist;
	}

	@Override
	public boolean equals(Object student) {
		if (student instanceof Student) {
			if (((Student)student).getStudentNo().equalsIgnoreCase(this.studentNo)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
