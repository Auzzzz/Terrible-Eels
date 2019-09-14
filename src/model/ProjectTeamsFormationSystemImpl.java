package model;

import java.util.ArrayList;
import java.util.List;

import enums.Role;
import enums.Skill;
import interfaces.*;
import model.ProjectImpl;
import model.RoleRequirement;

public class ProjectTeamsFormationSystemImpl implements ProjectTeamsFormationSystem {
	private SQLConnection connection;
	private Validator validator;
	
	public ProjectTeamsFormationSystemImpl(SQLConnection connection) {
		this.connection = connection;
		this.validator = new ValidatorImpl(connection);
	}
	
	@Override
	public List<String> getAllProjectDescs() {
		List<String> projectDescs = new ArrayList<>();
		
		for (Project project : connection.getAllProjects()) {
			projectDescs.add(project.getProjectDesc());
		}
		
		return projectDescs;
	}
	
	@Override
	public List<Project> getPopularProjects() {
		int idealNumberOfProjects = (connection.getStudentCount() / Project.TEAM_CAPACITY);
		
		return connection.getPopularProjects(idealNumberOfProjects);
	}
	
	@Override
	public List<String> getPopularProjectDescs() {
		List<String> projectDescs = new ArrayList<>();
		
		for (Project project : getPopularProjects()) {
			projectDescs.add(project.getProjectDesc());
		}
		
		return projectDescs;
	}
	
	@Override
	public boolean swap(Student s1, Student s2, int acceptableChange) {
		Project project1 = connection.getProject(s1);
		Project project2 = connection.getProject(s2);
		
		if (!((project1.getId()).equals(project2.getId()))) {
			// create temporary teams
			Project temp1 = new ProjectImpl("s1", "A test project", new ArrayList<Role>());
			Project temp2 = new ProjectImpl("s2", "Another test project", new ArrayList<Role>());
			
			temp1.addStudent(s2);
			temp2.addStudent(s1);
			
			for (Student student : project1.getStudents()) {
				if (!((student.getStudentNo()).equals(s1.getStudentNo()))) {
					temp1.addStudent(student);
				}
			}
			
			for (Student student : project2.getStudents()) {
				if (!((student.getStudentNo()).equals(s2.getStudentNo()))) {
					temp2.addStudent(student);
				}
			}
			
			if ((validator.calculateFit(temp1) >= acceptableChange) && (validator.calculateFit(temp2) >= acceptableChange)) {
				project1.removeStudent(s1);
				project1.addStudent(s2);
				project2.removeStudent(s2);
				project2.addStudent(s1);
				
				connection.saveProject(project1);
				connection.saveProject(project2);
				
				return true;
			}
		}
		
		return false;
	}
	
	private RoleRequirement getRoleMatch(TeamFormationState state, Project project, Student student) {
		List<RoleRequirement> pRoleReqs = state.getRoleRequirements(project);
		List<RoleRequirement> sRoleReqs = student.getRoleRequirements();
				
		for (RoleRequirement pRoleReq : pRoleReqs) {
			for (RoleRequirement sRoleReq : sRoleReqs) {
				if (pRoleReq.getRole() == sRoleReq.getRole()) {
					return pRoleReq;
				}
			}
		}
		
		return null;
	}
	
	private RoleRequirement getSkillMatch(TeamFormationState state, Project project, Student student) {
		List<RoleRequirement> pRoleReqs = state.getRoleRequirements(project);
		List<RoleRequirement> sRoleReqs = student.getRoleRequirements();
		
		for (RoleRequirement pRoleReq : pRoleReqs) {
			List<Skill> pSkills = pRoleReq.getSkills();
			
			for (RoleRequirement sRoleReq : sRoleReqs) {
				List<Skill> sSkills = sRoleReq.getSkills();
				
				for (Skill sSkill : sSkills) {
					if (pSkills.contains(sSkill)) {
						return pRoleReq;
					}
				}
			}
		}
		return null;
	}
	
	private RoleRequirement getRoleAndSkillMatch(TeamFormationState state, Project project, Student student) {
		List<RoleRequirement> pRoleReqs = state.getRoleRequirements(project);
		List<RoleRequirement> sRoleReqs = student.getRoleRequirements();
				
		for (RoleRequirement pRoleReq : pRoleReqs) {
			for (RoleRequirement sRoleReq : sRoleReqs) {
				if (pRoleReq.getRole() == sRoleReq.getRole()) {
					List<Skill> pSkills = pRoleReq.getSkills();
					List<Skill> sSkills = sRoleReq.getSkills();
					
					for (Skill sSkill : sSkills) {
						if (pSkills.contains(sSkill)) {
							return pRoleReq;
						}
					}
				}
			}
		}
		
		return null;
	}
	
	private int calculatePreferenceScore(Project project, Student student) {
		final int FIRST_PREFERENCE = 10;
		final int SECOND_PREFERENCE = 7;
		final int THIRD_PREFERENCE = 4;
		final int FOURTH_PREFERENCE = 1;
		int score = 0;
		List<String> preferences = student.getProjectPreferences();
		
		for (int i = 1; i <= student.getProjectPreferences().size(); i++) {
			if (project.getId().equals(preferences.get(i))) {
				switch(i) {
					case 1:
						score += FIRST_PREFERENCE;
						break;
					
					case 2:
						score += SECOND_PREFERENCE;
						break;
					
					case 3:
						score += THIRD_PREFERENCE;
						break;
						
					case 4:
						score += FOURTH_PREFERENCE;
						break;
					
					default:
				}
				break;
			}
		}
		return score;
	}
	
	private int calculateRoleRequirementScore(Project project, Student student, TeamFormationState state) {
		final int ROLE_SKILL_MATCH = 10;
		final int SKILL_MATCH = 9;
		final int ROLE_MATCH = 8;
		int score = 0;
		
		if (getRoleAndSkillMatch(state, project, student) != null) {
			score += ROLE_SKILL_MATCH;
		} else if (getSkillMatch(state, project, student) != null) {
			score += SKILL_MATCH;
		} else if (getRoleMatch(state, project, student) != null) {
			score += ROLE_MATCH;
		}
		
		return score;
	}
	
	private TeamFormationState calculateScores(TeamFormationState state) {
		state.resetScoresDataList();
		List<Project> allProjects = state.getRemainingProjects();
		List<Student> allStudents = state.getRemainingStudents();
		
		for (Project project : allProjects) {
			ProjectScoresData scoresData = new ProjectScoresData(project);
			
			for (Student student : allStudents) {
				int preferenceScore = calculatePreferenceScore(project, student);
				int roleRequirementScore = calculateRoleRequirementScore(project, student, state);
				
				StudentScore studentScore = new StudentScore(student, preferenceScore+roleRequirementScore);
				scoresData.addStudentScore(studentScore);
			}
			
			state.addScoresData(scoresData);
		}
		
		return state;
	}
	
	/**
	 * assign the student into the project if the assignment is valid
	 * @param project
	 * @param student
	 * @return - whether assignment was successful
	 */
	private boolean assign(Project project, Student student) {
		// if the project has no member yet, or if assigning the student meets requirement
		if (project.getStudents().isEmpty() || (validator.validateHardConstraints(project, student))) {
			project.addStudent(student);
			connection.saveProject(project);
			return true;
		}
		
		return false;
	}
	
	private TeamFormationState assignStudents(TeamFormationState state) {
		state = calculateScores(state);
		List<ProjectScoresData> scoresDataList = state.getScoresDataList();
		
		// for each project scores data (each student's score for the project)
		for (ProjectScoresData scoresData : scoresDataList) {
			Project project = scoresData.getProject();
			
			// traverse through sorted collection of StudentScore objects for this project
			for (StudentScore score : scoresData.getStudentScores()) {
				if (project.getStudents().size() >= Project.TEAM_CAPACITY) {
					break;
				}
				
				// students with higher score are first considered
				Student student = score.getStudent();
				
				// if the project has a role available for the student
				// and if the assignment meets hard constraint
				RoleRequirement roleMatch = getRoleMatch(state, project, student);
				if ((roleMatch != null) && (assign(project, student))) {
					state.removeStudent(student);
					state.removeRoleRequirement(project.getId(), roleMatch);
				}
			}
		}
		
		return state;
	}

	private void forceAssign(TeamFormationState state) {
		List<Student> leftOverStudents = state.getRemainingStudents();
		List<Project> leftOverProjects = state.getRemainingProjects();
		
		for (Student student : leftOverStudents) {
			for (Project project : leftOverProjects) {
				if (!(project.getStudents().isEmpty())) {
					project.addStudent(student);
					connection.saveProject(project);
					break;
				}
			}
		}
	}
	
	@Override
	public void assignStudents() {
		// projects to which students are to be assigned
		List<Project> candidateProjects = getPopularProjects();
		List<Student> femaleStudents = connection.getFemaleStudents();
		List<Student> maleStudents = connection.getMaleStudents();
		
		TeamFormationState state = new TeamFormationState(femaleStudents, candidateProjects);
		state = assignStudents(state);
		state.addStudents(maleStudents);
		state = assignStudents(state);
		
		forceAssign(state);
	}
}