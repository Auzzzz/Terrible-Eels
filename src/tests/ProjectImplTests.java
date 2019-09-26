package tests;

import java.util.ArrayList;
import org.junit.Test;

import enums.Gender;
import enums.PersonalityType;
import enums.Role;
import interfaces.Project;
import model.ProjectImpl;
import model.RoleRequirement;
import model.StudentImpl;
import model.exceptions.TooManyRolesException;
import model.exceptions.TooManyStudentsException;

public class ProjectImplTests {

	Project project;
	
	
	@Test
	public void addFourRoles() {
		ArrayList<RoleRequirement> roles = createRoles(4);
		
		project = new ProjectImpl("123", "Test Project", roles);
		assert(project.getRoleRequirements() == roles);
	}
	
	@Test(expected = TooManyRolesException.class) 
	public void addFiveRoles() {
		ArrayList<RoleRequirement> roles = createRoles(5);
		
		project = new ProjectImpl("123", "Test Project", roles);
	}
	
	@Test
	public void addFourStudents() {
		ArrayList<RoleRequirement> roles = createRoles(4);
		project = new ProjectImpl("123", "Test Project", roles);
		addStudents(project, 4);
		
		assert(project.getStudents().size() == 4);
	}
	
	@Test(expected = TooManyStudentsException.class) 
	public void addFiveStudents() {
		ArrayList<RoleRequirement> roles = createRoles(4);
		project = new ProjectImpl("123", "Test Project", roles);
		addStudents(project, 5);
	}
	
	
	
	
	private void addStudents(Project project, int num) {
		for (int i = 1; i <= num; i++) {
			project.addStudent(new StudentImpl("Name", String.valueOf(i), PersonalityType.A, Gender.OTHER, 3, 4, null, null, null));
		}
	}
	
	private ArrayList<RoleRequirement> createRoles(int num){
		ArrayList<RoleRequirement> roles = new ArrayList<RoleRequirement>();
		
		for (int i = 1; i <= num; i++) {
			roles.add(new RoleRequirement(Role.PROGRAMMER, new ArrayList<enums.Skill>()));
		}
		
		return roles;
		
	}

}
