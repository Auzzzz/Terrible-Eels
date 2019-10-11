package model.teamFormation;

import java.util.ArrayList;
import java.util.Collection;

import interfaces.*;
import model.ProjectImpl;
import model.RoleRequirement;

public class ProjectTeamsFormationSystemImpl implements ProjectTeamsFormationSystem {
	private SQLConnection connection;
	private TeamFormationEngine engine;

	public ProjectTeamsFormationSystemImpl(SQLConnection connection) {
		this.connection = connection;
		this.engine = new TeamFormationEngineImpl(connection);
	}

	@Override
	public Collection<String> getAllProjectDescs() {
		Collection<String> projectDescs = new ArrayList<>();

		for (Project project : connection.getAllProjects()) {
			projectDescs.add(project.getProjectDesc());
		}

		return projectDescs;
	}

	@Override
	public Collection<String> getPopularProjectDescs() {
		Collection<String> projectDescs = new ArrayList<>();

		try {
			for (Project project : engine.getPopularProjects()) {
				projectDescs.add(project.getProjectDesc());
			}
		} catch (InsufficientStudentsException e) {
			// e.printStackTrace();
		}

		return projectDescs;
	}

	@Override
	public boolean swap(String s1, String s2, int acceptableChange) {
		return engine.swap(s1, s2, acceptableChange);
	}

	@Override
	public Collection<Project> formTeams()
			throws InsufficientProjectsException, InsufficientStudentsException, RemainedStudentsException {
		return engine.formTeams();
	}

	@Override
	public void confirmTeams() {
		engine.confirmTeams();
	}

	@Override
	public void addProject(String desc, Collection<RoleRequirement> roles) {
		// TODO Figure out how to set project ID
		Project project = new ProjectImpl("ID", desc, roles);
		connection.saveProject(project);

	}

	@Override
	public void setPreferences(String studentID, Collection<String> projectIDs) {
		Student student = connection.getStudent(studentID); 
		ArrayList<Project> projects = new ArrayList<Project>();
		projectIDs.forEach(p -> {
			projects.add(connection.getProjectByDesc(p));
		});
		student.setPreferences(projects);
	}

	@Override
	public void addToBlacklist(String studentID, String blacklistID) {

		Student blacklister = connection.getStudent(studentID);
		Student blacklistee = connection.getStudent(blacklistID);
		blacklister.addBlacklist(blacklistee);

	}

	@Override
	public void setRoles(String studentID, Collection<RoleRequirement> roles) {

		Student student = connection.getStudent(studentID);
		student.setRolePreferences(roles);

	}

	@Override
	public boolean checkStudentNum(String studentNo) {
		if (connection.getStudent(studentNo) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String displayProjects(Collection<Project> projects) {
		// TODO Auto-generated method stub
		return null;
	}

}