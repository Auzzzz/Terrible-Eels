package model.teamFormation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
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
	public Collection<String> getAllProjectDescs() {
		Collection<String> projectDescs = new ArrayList<>();
		
		for (Project project : connection.getAllProjects()) {
			projectDescs.add(project.getProjectDesc());
		}
		
		return projectDescs;
	}
	
	@Override
	public Collection<Project> getPopularProjects() throws NoStudentException {
		int numStudents = connection.getStudentCount();
		
		if (numStudents == 0) {
			throw new NoStudentException();
		}
		
		int idealNumberOfProjects = (numStudents / Project.TEAM_CAPACITY);
		
		return connection.getPopularProjects(idealNumberOfProjects);
	}
	
	@Override
	public Collection<String> getPopularProjectDescs() {
		Collection<String> projectDescs = new ArrayList<>();
		
		try {
			for (Project project : getPopularProjects()) {
				projectDescs.add(project.getProjectDesc());
			}
		} catch (NoStudentException e) {
			// e.printStackTrace();
		}
		
		return projectDescs;
	}
	
	@Override
	public boolean swap(Student s1, Student s2, int acceptableChange) {
		Project project1 = connection.getProject(s1);
		Project project2 = connection.getProject(s2);
		
		if (!((project1.getId()).equals(project2.getId()))) {
			// create temporary teams
			Project temp1 = new ProjectImpl("s1", "A test project", new ArrayList<RoleRequirement>());
			Project temp2 = new ProjectImpl("s2", "Another test project", new ArrayList<RoleRequirement>());
			
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
		Set<RoleRequirement> pRoleReqs = state.getRoleRequirements(project);
		Set<RoleRequirement> sRoleReqs = student.getRolePreferences();
				
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
		Set<RoleRequirement> pRoleReqs = state.getRoleRequirements(project);
		Set<RoleRequirement> sRoleReqs = student.getRolePreferences();
		
		for (RoleRequirement pRoleReq : pRoleReqs) {
			Set<Skill> pSkills = (Set<Skill>) pRoleReq.getSkills();
			
			for (RoleRequirement sRoleReq : sRoleReqs) {
				Set<Skill> sSkills = (Set<Skill>) sRoleReq.getSkills();
				
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
		Set<RoleRequirement> pRoleReqs = state.getRoleRequirements(project);
		Set<RoleRequirement> sRoleReqs = student.getRolePreferences();
				
		for (RoleRequirement pRoleReq : pRoleReqs) {
			for (RoleRequirement sRoleReq : sRoleReqs) {
				if (pRoleReq.getRole() == sRoleReq.getRole()) {
					Set<Skill> pSkills = (Set<Skill>) pRoleReq.getSkills();
					Set<Skill> sSkills = (Set<Skill>) sRoleReq.getSkills();
					
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
		int preferencesSize = preferences.size();
		
		for (int i = 0; i < preferencesSize; i++) {
			String preference = preferences.get(i);
			String projectId = project.getId();
			
			if (projectId.equals(preference)) {
				switch(i) {
					case 0:
						score += FIRST_PREFERENCE;
						break;
					
					case 1:
						score += SECOND_PREFERENCE;
						break;
					
					case 2:
						score += THIRD_PREFERENCE;
						break;
						
					case 3:
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
	
	private List<ProjectScoresData> calculateScores(TeamFormationState state) {
		List<ProjectScoresData> scoresDataList = new ArrayList<>();
		
		Collection<Project> allProjects = state.getRemainingProjects();
		Collection<Student> allStudents = state.getRemainingStudents();
		
		for (Project project : allProjects) {
			ProjectScoresData scoresData = new ProjectScoresData(project);
			
			for (Student student : allStudents) {
				int preferenceScore = calculatePreferenceScore(project, student);
				int roleRequirementScore = calculateRoleRequirementScore(project, student, state);
				
				StudentScore studentScore = new StudentScore(student, preferenceScore+roleRequirementScore);
				scoresData.addStudentScore(studentScore);
			}
			
			scoresDataList.add(scoresData);
		}
		
		return scoresDataList;
	}
	
	/**
	 * assign the student into the project if the assignment is valid
	 * @param project
	 * @param student
	 * @return - whether assignment was successful
	 */
	private void assign(TeamFormationState state, Project project, Student student) {
		// if the project has no member yet, or if assigning the student meets requirement
		if (project.getStudents().isEmpty() || (validator.validateHardConstraints(project, student))) {
			project.addStudent(student);
			connection.saveProject(project);
			
			state.removeStudent(student);
			state.updateProject(project);
		}
	}
	
	
	private void assignToRole(TeamFormationState state, Project project, Student student) {
		RoleRequirement roleMatch = getRoleMatch(state, project, student);
		
		if (roleMatch != null) {
			assign(state, project, student);
			state.removeRoleRequirement(project.getId(), roleMatch);
		}
	}
	
	private void assignStudentsToRole(TeamFormationState state) throws InsufficientProjectsException, NoStudentException {
		Collection<Student> students = state.getRemainingStudents();
		Collection<Project> projects = state.getRemainingProjects();
		
		// there's no student to form team
		if (students.size() == 0) {
			throw new NoStudentException();
		} 
		
		// not enough number of projects for all students
		if ((students.size() / Project.TEAM_CAPACITY) > projects.size()) {
			throw new InsufficientProjectsException();
		}
		
		List<ProjectScoresData> scoresDataList = calculateScores(state);
		
		
		// for each project scores data (each student's score for the project)
		for (ProjectScoresData scoresData : scoresDataList) {
			Project project = scoresData.getProject();
			
			// traverse through sorted collection of StudentScore objects for this project
			for (StudentScore score : scoresData.getStudentScores()) {
				
				// check if the project team has been formed
				Project remainingProject = state.getRemainingProject(project.getId());
				if ((remainingProject == null)) {
					break;
				}
				
				// students with higher score are first considered
				Student student = score.getStudent();
			
				// check if the student has not been assigned
				Student remainingStudent = state.getRemainingStudent(student.getStudentNo());
				if (remainingStudent != null) {
					assignToRole(state, project, student);
				}
			}
		}
	}
	
	private void assignStudents(TeamFormationState state) {
		List<ProjectScoresData> scoresDataList = calculateScores(state);
		
		// for each project scores data (each student's score for the project)
		for (ProjectScoresData scoresData : scoresDataList) {
			Project project = scoresData.getProject();
			
			// traverse through sorted collection of StudentScore objects for this project
			for (StudentScore score : scoresData.getStudentScores()) {
				
				// check if the project team has been formed
				Project remainingProject = state.getRemainingProject(project.getId());
				if ((remainingProject == null)) {
					break;
				}
				
				// students with higher score are first considered
				Student student = score.getStudent();
			
				// check if the student has not been assigned
				Student remainingStudent = state.getRemainingStudent(student.getStudentNo());
				if (remainingStudent != null) {
					assign(state, project, student);
				}
			}
		}
	}

	/**
	 * assign remaining students to any project with vacancy ignoring hard constraints
	 * @param state
	 */
	private void forceAssign(TeamFormationState state) {
		List<Student> students = new ArrayList<>(state.getRemainingStudents());
		List<Project> projects = new ArrayList<>(state.getRemainingProjects());
		
		for (Student student : students) {
			for (Project project : projects) {
				if ((project.getStudents().size() < Project.TEAM_CAPACITY)) {
					project.addStudent(student);
					connection.saveProject(project);
					
					state.removeStudent(student);
					state.updateProject(project);
					
					break;
				}
			}
		}
	}
	
	@Override
	public boolean assignStudents() throws InsufficientProjectsException, NoStudentException, RemainedStudentsException {
		// projects to which students are to be assigned
		Collection<Project> candidateProjects = getPopularProjects();
		Collection<Student> femaleStudents = connection.getFemaleStudents();
		Collection<Student> maleStudents = connection.getMaleStudents();
		
		// assign female students first
		TeamFormationState state = new TeamFormationState(femaleStudents, candidateProjects);
		assignStudentsToRole(state);
		assignStudents(state);
		
		// assign male students
		state.addStudents(maleStudents);
		assignStudentsToRole(state);
		assignStudents(state);
		
		// assign remaining students to any project with vacancy
		forceAssign(state);
		
		// there will be remained students if the number of students is not divisible by team capacity
		Collection<Student> remained = state.getRemainingStudents();
		if (remained.size() > 0) {
			throw new RemainedStudentsException(remained);
		}
		
		return (state.getRemainingStudents().size() == 0);
	}
}