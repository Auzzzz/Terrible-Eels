package model.teamFormation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import interfaces.Project;
import interfaces.Student;
import model.RoleRequirement;

/**
 * TeamFormationState:
 * 
 * Maintains the state of team formation process.
 * Therefore its attributes change as students are assigned, a whole team is formed, 
 * or as a role requirement for a project is fulfilled.
 *
 */
public class TeamFormationState {
	private Map<String, Student> remainingStudents = new HashMap<>();
	private Map<String, Project> remainingProjects = new LinkedHashMap<>();
	private Map<String, Collection<RoleRequirement>> roleRequirements = new HashMap<>();
	private Collection<Project> formedProjects = new ArrayList<>();
	
	public TeamFormationState(Collection<Student> remainingStudents, Collection<Project> remainingProjects) {
		for (Student student : remainingStudents) {
			this.remainingStudents.put(student.getStudentNo(), student);
		}
		
		for (Project project : remainingProjects) {
			(this.remainingProjects).put(project.getId(), project);
			(this.roleRequirements).put(project.getId(), project.getRoleRequirements());
		}
	}
	
	public Collection<Student> getRemainingStudents() {
		return remainingStudents.values();
	}
	
	public Collection<Project> getRemainingProjects() {
		return remainingProjects.values();
	}
	
	public Collection<Project> getFormedProjects() {
		return formedProjects;
	}
	
	public void removeStudent(Student student) {
		remainingStudents.remove(student.getStudentNo());
	}
	
	public void removeProject(Project project) {
		remainingProjects.remove(project.getId());
	}
	
	public void addStudents(Collection<Student> students) {
		for (Student student : students) {
			remainingStudents.put(student.getStudentNo(), student);
		}
	}
	
	public void removeRoleRequirement(String projectId, RoleRequirement roleReq) {
		Collection<RoleRequirement> roleReqCollection = roleRequirements.get(projectId);
		roleReqCollection.remove(roleReq);
	}
	
	public Collection<RoleRequirement> getRoleRequirements(Project project) {
		return roleRequirements.get(project.getId());
	}
	
	public void updateProject(Project project) {
		if (project.getStudents().size() >= Project.TEAM_CAPACITY) {
			Project formed = remainingProjects.remove(project.getId());
			formedProjects.add(formed);
		} else {
			remainingProjects.put(project.getId(), project);
		}
	}
	
	public Project getRemainingProject(String projectId) {
		return remainingProjects.get(projectId);
	}
	
	public Student getRemainingStudent(String studentNo) {
		return remainingStudents.get(studentNo);
	}
}
