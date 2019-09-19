package model.teamFormation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
	private Map<String, Set<RoleRequirement>> roleRequirements = new HashMap<>();
	private List<ProjectScoresData> scoresDataList = new ArrayList<>();
	private Collection<Project> formedProjects = new ArrayList<>();
	
	public TeamFormationState(Collection<Student> remainingStudents, Collection<Project> remainingProjects) {
		for (Student student : remainingStudents) {
			this.remainingStudents.put(student.getStudentNo(), student);
		}
		
		// initialise roleRequirements (associate each project id - its role requirements)
		for (Project project : remainingProjects) {

			List<RoleRequirement> roleRequirements = (List<RoleRequirement>) project.getRoleRequirements();

			(this.remainingProjects).put(project.getId(), project);
			
			Set<RoleRequirement> roleRequirements1 = (Set<RoleRequirement>) project.getRoleRequirements();

			this.roleRequirements.put(project.getId(), roleRequirements1);
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
		Set<RoleRequirement> roleRequirementsList = roleRequirements.get(projectId);
		roleRequirementsList.remove(roleReq);
	}
	
	public Set<RoleRequirement> getRoleRequirements(Project project) {
		return roleRequirements.get(project.getId());
	}
	
	public void addScoresData(ProjectScoresData scoreSet) {
		scoresDataList.add(scoreSet);
	}
	
	public List<ProjectScoresData> getScoresDataList() {
		return scoresDataList;
	}
	
	public void resetScoresDataList() {
		scoresDataList.clear();
	}
	
	public void updateProject(Project project) {
		if (project.getStudents().size() >= Project.TEAM_CAPACITY) {
			Project formed = remainingProjects.remove(project.getId());
			formedProjects.add(formed);
		} else {
			remainingProjects.put(project.getId(), project);
		}
	}
	
	public Project getProject(String projectId) {
		return remainingProjects.get(projectId);
	}
	
	public Student getRemainingStudent(Student student) {
		return remainingStudents.get(student.getStudentNo());
	}
}
