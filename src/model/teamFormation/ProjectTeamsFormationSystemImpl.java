package model.teamFormation;

import java.util.ArrayList;
import java.util.Collection;

import interfaces.*;
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
	public Collection<Project> assignStudents()
			throws InsufficientProjectsException, InsufficientStudentsException, RemainedStudentsException {
		return engine.assignStudents();
	}
	
	@Override
	public void confirmTeams() {
		engine.confirmTeams();
	}

	@Override
	public void addProject(String desc, Collection<RoleRequirement> roles) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPreferences(String studentID, Collection<String> projectIDs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addToBlacklist(String studentID, String blacklistID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRoles(String studentID, Collection<RoleRequirement> roles) {
		// TODO Auto-generated method stub
		
	}


}