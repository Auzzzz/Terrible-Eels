package model.teamFormation;

import java.util.ArrayList;
import java.util.Collection;

import interfaces.*;
import model.ProjectImpl;
import model.RoleRequirement;
import model.constraints.SoftConstraint;

public class ProjectTeamsFormationSystemImpl implements ProjectTeamsFormationSystem {
	private DataStorage connection;
	private TeamFormationEngine engine;

	public ProjectTeamsFormationSystemImpl(DataStorage connection) {
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
			System.out.println("Error: Insufficient Students");
		}

		return projectDescs;
	}

	@Override
	public boolean swap(String s1, String s2, int acceptableChange) {
		return engine.swap(s1, s2, acceptableChange);
	}

	@Override
	public Collection<String> formTeams() throws InsufficientProjectsException, InsufficientStudentsException {
		// each string is info of a project
		Collection<String> results = new ArrayList<>();
		Collection<Project> formedProjects;
		Collection<Student> remainedStudents = new ArrayList<>();

		try {
			formedProjects = engine.formTeams();

		} catch (RemainedStudentsException e) {
			formedProjects = e.getFormedProjects();
			remainedStudents = e.getRemainedStudents();
		}

		for (Project project : formedProjects) {
			int fitVal = engine.getFitnessValue(project);
			String str = convertTeamToString(project, fitVal);
			results.add(str);
		}

		// if RemainedStudentsException was thrown, show remaining students as well
		// after formed projects
		if (!remainedStudents.isEmpty()) {
			StringBuilder builder = new StringBuilder();
			builder.append("Remaining Students: \n");

			for (Student student : remainedStudents) {
				builder.append(student.getStudentNo()).append('\n');
			}

			results.add(builder.toString());
		}

		return results;
	}

	private String convertTeamToString(Project project, int fitVal) {
		StringBuilder builder = new StringBuilder();
		builder.append("Description: " + project.getProjectDesc()).append('\n');
		builder.append("Students: \n");

		project.getStudents().forEach(s -> {
			builder.append("-" + s.getStudentNo() + ", " + s.getName() + ", " + s.getGender() + ", GPA: " + s.getGpa()
					+ ", Personality Type:" + s.getPersonalityType() + "\n");
		});
		builder.append("Fitness Value:").append(fitVal).append('\n');

		return builder.toString();

	}

	@Override
	public void confirmTeams() {
		engine.confirmTeams();
	}

	@Override
	public void addProject(String desc, Collection<RoleRequirement> roles) {
		Project project = new ProjectImpl(desc, null, roles);
		connection.insertProject(project);
	}

	@Override
	public void setPreferences(String studentID, Collection<String> projectIDs) {
		Student student = connection.getStudent(studentID);
		ArrayList<Project> projects = new ArrayList<Project>();
		projectIDs.forEach(p -> {
			projects.add(connection.getProjectByDesc(p));
		});
		student.setPreferences(projects);
		connection.updateStudent(student);
	}

	@Override
	public void addToBlacklist(String studentID, String blacklistID) {

		Student blacklister = connection.getStudent(studentID);
		Student blacklistee = connection.getStudent(blacklistID);
		blacklister.addBlacklist(blacklistee);
		connection.updateStudent(blacklister);
	}

	@Override
	public void setRoles(String studentID, Collection<RoleRequirement> roles) {

		Student student = connection.getStudent(studentID);
		student.setRolePreferences(roles);
		connection.updateStudent(student);
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
	public Collection<String> getProjectInString() {
		Collection<String> strProjects = new ArrayList<>();
		Collection<Project> formedProjects;
		try {
			formedProjects = engine.getPopularProjects();

			for (Project project : formedProjects) {
				Collection<Student> members = project.getStudents();

				if (!members.isEmpty()) {
					int fitVal = engine.getFitnessValue(project);
					String strProject = convertTeamToString(project, fitVal);
					strProjects.add(strProject);
				}
			}
		} catch (InsufficientStudentsException e) {
			e.printStackTrace();
		}

		return strProjects;
	}

	@Override
	public Collection<SoftConstraint> getSoftConstraints() {
		return connection.getAllSoftConstraints();
	}

	@Override
	public void updateConstraints(Collection<SoftConstraint> constraints) {
		for (SoftConstraint sc : constraints) {
			connection.saveConstraint(sc.getDescription(), sc.getWeight());
		}
	}

}