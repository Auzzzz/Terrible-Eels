package model.teamFormation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
	public Collection<Project> getPopularProjects() throws InsufficientStudentsException {
		int numStudents = connection.getStudentCount();
		
		// there need to be at least 4 students
		if (numStudents < Project.TEAM_CAPACITY) {
			throw new InsufficientStudentsException();
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
		} catch (InsufficientStudentsException e) {
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
	
	/**
	 * Find a RoleRequirement of Project of which Role matches with the student's preference on Role.
	 * Return null if no matching Role was found.
	 * 
	 * @param state
	 * @param project
	 * @param student
	 * @return - a RoleRequirement with matching Role
	 */
	private RoleRequirement getRoleMatch(TeamFormationState state, Project project, Student student) {
		Collection<RoleRequirement> pRoleReqs = state.getRoleRequirements(project);
		Collection<RoleRequirement> sRoleReqs = student.getRolePreferences();
				
		for (RoleRequirement pRoleReq : pRoleReqs) {
			for (RoleRequirement sRoleReq : sRoleReqs) {
				// project's Role and student's Role matches
				if (pRoleReq.getRole().equals(sRoleReq.getRole())) {
					return pRoleReq;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Find a RoleRequirement of Project of which Role requires a Skill that matches 
	 * with one of the student's Skills.
	 * Return null if no matching Skill was found.
	 * 
	 * @param state
	 * @param project
	 * @param student
	 * @return - a RoleRequirement with matching Skill
	 */
	private RoleRequirement getSkillMatch(TeamFormationState state, Project project, Student student) {
		Collection<RoleRequirement> pRoleReqs = state.getRoleRequirements(project);
		Collection<RoleRequirement> sRoleReqs = student.getRolePreferences();
		
		for (RoleRequirement pRoleReq : pRoleReqs) {
			Collection<Skill> pSkills = pRoleReq.getSkills();
			
			for (RoleRequirement sRoleReq : sRoleReqs) {
				Collection<Skill> sSkills = sRoleReq.getSkills();
				
				// check if the project's role requirement contains a skill that the student has
				for (Skill sSkill : sSkills) {
					if (pSkills.contains(sSkill)) {
						return pRoleReq;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Find a RoleRequirement of Project of which Role AND one of its Skills matches with
	 * the student's preference.
	 * 
	 * E.g., If the project has (Role 1 - Skill 1, Skill 2, Skill 3) and 
	 * the student has (Role 1 - Skill 1, Skill 4), then a RoleRequirement for Role1 is returned
	 * because (Role 1 - Skill 1) pair appears in the student's.
	 * 
	 * @param state
	 * @param project
	 * @param student
	 * @return - a RoleRequirement with matching Skill
	 */
	private RoleRequirement getRoleAndSkillMatch(TeamFormationState state, Project project, Student student) {
		Collection<RoleRequirement> pRoleReqs = state.getRoleRequirements(project);
		Collection<RoleRequirement> sRoleReqs = student.getRolePreferences();
				
		for (RoleRequirement pRoleReq : pRoleReqs) {
			for (RoleRequirement sRoleReq : sRoleReqs) {
				
				// project's Role and student's Role matches
				if (pRoleReq.getRole().equals(sRoleReq.getRole())) {
					Collection<Skill> pSkills = pRoleReq.getSkills();
					Collection<Skill> sSkills = sRoleReq.getSkills();
					
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
	
	/**
	 * calculate the student's 'fitness score' for the project based on the student's preferences; 
	 * if the project is preferred by the student, certain value is added to the score.
	 * 
	 * @param project
	 * @param student
	 * @return - fitness score of the student for the project
	 */
	private int calculatePreferenceScore(Project project, Student student) {
		// fitness values
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
	
	/**
	 * calculate the student's 'fitness score' for the project based on the student's preferences; 
	 * if the project's Role / Skill requirement matches with the student's preference, 
	 * certain value is added to the score.
	 * 
	 * @param project
	 * @param student
	 * @return - fitness score of the student for the project
	 */
	private int calculateRoleRequirementScore(Project project, Student student, TeamFormationState state) {
		// fitness values
		final int ROLE_SKILL_MATCH = 10;
		final int SKILL_MATCH = 9;
		final int ROLE_MATCH = 8;
		int score = 0;
		
		if (getRoleAndSkillMatch(state, project, student) != null) {
			score += ROLE_SKILL_MATCH;
		}
		else if (getSkillMatch(state, project, student) != null) {
			score += SKILL_MATCH;
		}
		else if (getRoleMatch(state, project, student) != null) {
			score += ROLE_MATCH;
		}
		
		return score;
	}
	
	/**
	 * Calculate the total fitness scores of each student for each project.
	 * The total fitness score is the addition of the score based on students' preferences on projects
	 * and another based on role requirement / preferences
	 * 
	 * @param state
	 * @return - the result of all calculations, as a list of ProjectScoresData
	 */
	private Collection<ProjectScoresData> calculateScores(TeamFormationState state) {
		List<ProjectScoresData> scoresDataList = new ArrayList<>();
		Collection<Project> allProjects = state.getRemainingProjects();
		Collection<Student> allStudents = state.getRemainingStudents();
		
		// calculate score of each remaining student for each remaining project 
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
	 * assign the student into the project
	 * @param state
	 * @param project
	 * @param student
	 */
	private void assign(TeamFormationState state, Project project, Student student) {
		project.addStudent(student);
		connection.saveProject(project);
		state.removeStudent(student);
		state.updateProject(project);
	}
	
	/**
	 * Validate if the student can be assigned into the project satisfying hard constraints 
	 * @param project
	 * @param student
	 * @return - whether the assignment is valid
	 */
	private boolean validateAssignment(Project project, Student student) {
		return (project.getStudents().isEmpty() || validator.validateHardConstraints(project, student));
	}
	
	/**
	 * Assignment action called in assignStudentsPhase1() method.
	 * The student is assigned to the project only if the project has a Role that is preferred by the student,
	 * while also meeting hard constraints.
	 * 
	 * @param state
	 * @param project
	 * @param student
	 */
	private void assignForPhase1(TeamFormationState state, Project project, Student student) {
		RoleRequirement roleMatch = getRoleMatch(state, project, student);
		
		if (roleMatch != null) {
			if (validateAssignment(project, student)) {
				assign(state, project, student);
				state.removeRoleRequirement(project.getId(), roleMatch);
			}
		}
	}
	
	/**
	 * Assignment action called in assignStudentsPhase2() method.
	 * The student is assigned to the project regardless of the project's role requirement,
	 * if the hard constrains are met.
	 * 
	 * @param state
	 * @param project
	 * @param student
	 */
	private void assignForPhase2(TeamFormationState state, Project project, Student student) {
		RoleRequirement roleMatch = getRoleMatch(state, project, student);
		
		if (validateAssignment(project, student)) {
			assign(state, project, student);
			
			if (roleMatch != null) {
				state.removeRoleRequirement(project.getId(), roleMatch);
			}
		}
	}
	
	/**
	 * The first phase of assigning students.
	 * 
	 * Attempts to assign students into projects ensuring that the role requirements of projects
	 * as well as hard constraints are met
	 * 
	 * Students with higher 'score' for project (higher match to the project considering preferences and
	 * requirements) are assigned prior to others, as long as role requirement and hard constraints
	 * are satisfied.
	 * 
	 * @param state
	 * @throws InsufficientProjectsException
	 * @throws InsufficientStudentsException
	 */
	private void assignStudentsPhase1(TeamFormationState state) throws InsufficientProjectsException, InsufficientStudentsException {
		Collection<Student> students = state.getRemainingStudents();
		Collection<Project> projects = state.getRemainingProjects();
		
		// there's no student to form team
		if (students.size() == 0) {
			throw new InsufficientStudentsException();
		} 
		
		// not enough number of projects for all students
		if ((students.size() / Project.TEAM_CAPACITY) > projects.size()) {
			throw new InsufficientProjectsException();
		}
		
		Collection<ProjectScoresData> scoresDataList = calculateScores(state);
		
		
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
					assignForPhase1(state, project, student);
				}
			}
		}
	}
	
	/**
	 * The second phase of assigning students.
	 * 
	 * Attempts to assign students into projects ensuring hard constraints are met.
	 * 
	 * Students with higher 'score' for project (higher match to the project considering preferences and
	 * requirements) are assigned prior to others as long as hard constraints are satisfied.
	 * 
	 * @param state
	 * @throws InsufficientProjectsException
	 * @throws InsufficientStudentsException
	 */
	private void assignStudentsPhase2(TeamFormationState state) {
		Collection<ProjectScoresData> scoresDataList = calculateScores(state);
		
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
					assignForPhase2(state, project, student);
				}
			}
		}
	}
	
	/**
	 * Wrapper method for the phase 1 and 2 of assignment
	 * 
	 * @param state
	 * @throws InsufficientProjectsException
	 * @throws InsufficientStudentsException
	 */
	private void assignStudents(TeamFormationState state) throws InsufficientProjectsException, InsufficientStudentsException {
		assignStudentsPhase1(state);
		assignStudentsPhase2(state);
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
					assign(state, project, student);
					break;
				}
			}
		}
	}
	
	@Override
	public boolean assignStudents() throws InsufficientProjectsException, InsufficientStudentsException, RemainedStudentsException {
		// projects to which students are to be assigned
		Collection<Project> candidateProjects = getPopularProjects();
		Collection<Student> femaleStudents = connection.getFemaleStudents();
		Collection<Student> maleStudents = connection.getMaleStudents();
		
		// assign female students first
		TeamFormationState state = new TeamFormationState(femaleStudents, candidateProjects);
		assignStudents(state);
		
		// assign male students
		state.addStudents(maleStudents);
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