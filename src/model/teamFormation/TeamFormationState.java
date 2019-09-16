package model.teamFormation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private List<Student> remainingStudents;
	private List<Project> remainingProjects;
	private Map<String, List<RoleRequirement>> roleRequirements = new HashMap<>();
	private List<ProjectScoresData> scoresDataList = new ArrayList<>();
	
	public TeamFormationState(List<Student> remainingStudents, List<Project> remainingProjects) {
		this.remainingStudents = remainingStudents;
		this.remainingProjects = remainingProjects;
		
		// initialise roleRequirements (associate each project id - its role requirements)
		for (Project project : remainingProjects) {
			List<RoleRequirement> roleRequirements = project.getRoleRequirements();
			this.roleRequirements.put(project.getId(), roleRequirements);
		}
	}
	
	public List<Student> getRemainingStudents() {
		return remainingStudents;
	}
	
	public List<Project> getRemainingProjects() {
		return remainingProjects;
	}
	
	public void removeStudent(Student student) {
		remainingStudents.remove(student);
	}
	
	public void removeProject(Project project) {
		remainingProjects.remove(project);
	}
	
	public void addStudents(List<Student> students) {
		remainingStudents.addAll(students);
	}
	
	public void removeRoleRequirement(String projectId, RoleRequirement roleReq) {
		List<RoleRequirement> roleRequirementsList = roleRequirements.get(projectId);
		roleRequirementsList.remove(roleReq);
	}
	
	public List<RoleRequirement> getRoleRequirements(Project project) {
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
}
