package model.teamFormation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import enums.Skill;
import interfaces.Project;
import interfaces.DataStorage;
import interfaces.Student;
import interfaces.TeamFormationEngine;
import interfaces.Validator;
import model.ProjectImpl;
import model.RoleRequirement;
import model.constraints.SoftConstraint;

public class TeamFormationEngineImpl implements TeamFormationEngine {
	private DataStorage connection;
	private Validator validator;
	private TeamFormationState state = null;

	public TeamFormationEngineImpl(DataStorage connection) {
		this.connection = connection;
		this.validator = new ValidatorImpl(connection);
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

	/**
	 * create a temporary team in which one member is swapped out and another member
	 * swapped in
	 * 
	 * @param swapOut - student to be swapped out
	 * @param swapIn  - student to be swapped in
	 * @return - temporary team
	 */
	private Project createTemporaryTeam(Student swapOut, Student swapIn) {
		Project temp = new ProjectImpl(null, null, null);
		Project project = connection.getProjectByStudentNo(swapOut.getStudentNo());
		ArrayList<Student> tempMembers = new ArrayList<Student>();
		tempMembers.addAll(project.getStudents());

		tempMembers.remove(swapOut);
		tempMembers.add(swapIn);
		tempMembers.forEach(temp::addStudent);

		return temp;
	}

	/**
	 * get the difference between two projects' fitness values
	 * 
	 * @param p1 - project 1
	 * @param p2 - project 2
	 * @return - difference
	 */
	private int getFitDifference(Project p1, Project p2) {
		int p1Fit = validator.calculateFit(p1);
		int p2Fit = validator.calculateFit(p2);

		return Math.abs(p1Fit - p2Fit);
	}

	@Override
	public boolean swap(String sNo1, String sNo2, int acceptableChange) {
		Student s1 = connection.getStudent(sNo1);
		Student s2 = connection.getStudent(sNo2);
		Project p1 = connection.getProjectByStudentNo(sNo1); // s1's original project
		Project p2 = connection.getProjectByStudentNo(sNo2); // s2's original project

		// if s1 and s2 are from different projects
		if (!((p1.getId()).equals(p2.getId()))) {
			// temporary projects after students are swapped
			Project p1Temp = createTemporaryTeam(s1, s2); // s1's team members + s2
			Project p2Temp = createTemporaryTeam(s2, s1); // s2's team members + s1

			// if the change is within acceptable value
			int p1FitChange = getFitDifference(p1, p1Temp);
			int p2FitChange = getFitDifference(p2, p2Temp);
			if ((p1FitChange <= acceptableChange) && (p2FitChange <= acceptableChange)) {
				p1.removeStudent(s1);
				p1.addStudent(s2);
				p2.removeStudent(s2);
				p2.addStudent(s1);
				connection.saveProject(p1);
				connection.saveProject(p2);

				return true;
			}
		}

		return false;
	}

	/**
	 * Find a RoleRequirement of Project of which Role matches with the student's
	 * preference on Role. Return null if no matching Role was found.
	 * 
	 * @param project
	 * @param student
	 * @return - a RoleRequirement with matching Role
	 */
	private RoleRequirement getRoleMatch(Project project, Student student) {
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
	 * with one of the student's Skills. Return null if no matching Skill was found.
	 * 
	 * @param project
	 * @param student
	 * @return - a RoleRequirement with matching Skill
	 */
	private RoleRequirement getSkillMatch(Project project, Student student) {
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
	 * Find a RoleRequirement of Project of which Role AND one of its Skills matches
	 * with the student's preference.
	 * 
	 * E.g., If the project has (Role 1 - Skill 1, Skill 2, Skill 3) and the student
	 * has (Role 1 - Skill 1, Skill 4), then a RoleRequirement for Role1 is returned
	 * because (Role 1 - Skill 1) pair appears in the student's.
	 * 
	 * @param project
	 * @param student
	 * @return - a RoleRequirement with matching Skill
	 */
	private RoleRequirement getRoleAndSkillMatch(Project project, Student student) {
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
	 * calculate the student's 'fitness score' for the project based on the
	 * student's preferences; if the project is preferred by the student, certain
	 * value is added to the score.
	 * 
	 * @param project
	 * @param student
	 * @return - fitness score of the student for the project
	 */
	private int calcProjectPreferenceScore(Project project, Student student) {
		// fitness values
		final int FIRST_PREFERENCE = 10;
		final int SECOND_PREFERENCE = 7;
		final int THIRD_PREFERENCE = 4;
		final int FOURTH_PREFERENCE = 1;
		int score = 0;
		List<String> preferences = student.getProjectPreferences();
		int preferencesSize = preferences.size();
		String projectId = project.getId();

		for (int i = 0; i < preferencesSize; i++) {
			String preference = preferences.get(i);

			// if the project is the student's preference
			if (projectId.equals(preference)) {
				switch (i) {
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
	 * calculate the student's 'fitness score' for the project based on the
	 * student's preferences; if the project's Role / Skill requirement matches with
	 * the student's preference, certain value is added to the score.
	 * 
	 * @param project
	 * @param student
	 * @return - fitness score of the student for the project
	 */
	private int calcRoleRequirementScore(Project project, Student student) {
		// fitness values
		final int ROLE_SKILL_MATCH = 10;
		final int SKILL_MATCH = 9;
		final int ROLE_MATCH = 8;
		int score = 0;

		if (getRoleAndSkillMatch(project, student) != null) {
			score += ROLE_SKILL_MATCH;
		} else if (getSkillMatch(project, student) != null) {
			score += SKILL_MATCH;
		} else if (getRoleMatch(project, student) != null) {
			score += ROLE_MATCH;
		}

		return score;
	}

	/**
	 * calculate the student's 'fitness score' for the project based on soft
	 * constraints; if addition of the student satisfies a soft constraint, its
	 * weight is added to the score
	 * 
	 * @param project
	 * @param student
	 * @return - fitness score of the student for the project
	 */
	private int calcSoftConstraintScore(Project project, Student student) {
		final int MAX_SCORE = 10;
		int score = 0;
		List<SoftConstraint> constraints = new ArrayList<>(connection.getAllSoftConstraints());
		Collections.sort(constraints); // sorted by weight
		int maxWeight = constraints.get(0).getWeight();

		for (SoftConstraint constraint : constraints) {
			// if the constraint is satisfied
			if (constraint.validateAdd(project, student)) {
				int weight = constraint.getWeight();
				score += (int) ((weight / maxWeight) * MAX_SCORE);
			}
		}

		return score;
	}

	/**
	 * Calculate the total fitness scores of each student for each project. The
	 * total fitness score is the addition of the score based on students'
	 * preferences on projects and another based on role requirement / preferences
	 * 
	 * @return - the result of all calculations, as a list of ProjectScoresData
	 */
	private Collection<ProjectScoresData> calcScores() {
		List<ProjectScoresData> scoresDataList = new ArrayList<>();
		Collection<Project> remainingProjects = state.getRemainingProjects();
		Collection<Student> remainingStudents = state.getRemainingStudents();

		// calculate score of each student for each project
		for (Project project : remainingProjects) {
			ProjectScoresData scoresData = new ProjectScoresData(project);

			for (Student student : remainingStudents) {
				int projectPrefScore = calcProjectPreferenceScore(project, student);
				int roleReqScore = calcRoleRequirementScore(project, student);
				int softConstScore = calcSoftConstraintScore(project, student);

				StudentScore studentScore = new StudentScore(student, projectPrefScore + roleReqScore + softConstScore);
				scoresData.addStudentScore(studentScore);
			}

			scoresDataList.add(scoresData);
		}

		return scoresDataList;
	}

	/**
	 * assign the student into the project
	 * 
	 * @param project
	 * @param student
	 */
	private void assign(Project project, Student student) {
		project.addStudent(student);
		state.removeStudent(student);
		state.updateProject(project);
	}

	/**
	 * Validate if the student can be assigned into the project satisfying hard
	 * constraints
	 * 
	 * @param project
	 * @param student
	 * @return - whether the assignment is valid
	 */
	private boolean validateAssignment(Project project, Student student) {
		return (project.getStudents().isEmpty() || validator.validateHardConstraints(project, student));
	}

	/**
	 * Assignment action called in assignStudentsPhase1() method. The student is
	 * assigned to the project only if the project has a Role that is preferred by
	 * the student, while also meeting hard constraints.
	 * 
	 * @param project
	 * @param student
	 */
	private void assignForPhase1(Project project, Student student) {
		RoleRequirement roleMatch = getRoleMatch(project, student);

		if (roleMatch != null) {
			if (validateAssignment(project, student)) {
				assign(project, student);
				state.removeRoleRequirement(project.getId(), roleMatch);
			}
		}
	}

	/**
	 * Assignment action called in assignStudentsPhase2() method. The student is
	 * assigned to the project regardless of the project's role requirement, if the
	 * hard constrains are met.
	 * 
	 * @param project
	 * @param student
	 */
	private void assignForPhase2(Project project, Student student) {
		RoleRequirement roleMatch = getRoleMatch(project, student);

		if (validateAssignment(project, student)) {
			assign(project, student);

			if (roleMatch != null) {
				state.removeRoleRequirement(project.getId(), roleMatch);
			}
		}
	}

	/**
	 * The first phase of assigning students. Attempts to assign students into
	 * projects ensuring that the role requirements of projects as well as hard
	 * constraints are met. Students with higher 'score' for project (higher match
	 * to the project considering preferences and requirements) are assigned prior
	 * to others, as long as role requirement and hard constraints are satisfied.
	 * 
	 * @throws InsufficientProjectsException
	 * @throws InsufficientStudentsException
	 */
	private void assignStudentsPhase1() throws InsufficientProjectsException, InsufficientStudentsException {
		Collection<Student> students = state.getRemainingStudents();
		Collection<Project> projects = state.getRemainingProjects();

		// not enough students to form a team
		if (students.size() < Project.TEAM_CAPACITY) {
			throw new InsufficientStudentsException();
		}

		// not enough projects for all students
		if ((students.size() / Project.TEAM_CAPACITY) > projects.size()) {
			throw new InsufficientProjectsException();
		}

		// data of all student's score for each project
		Collection<ProjectScoresData> scoresData = calcScores();

		// for each project, check all the students' scores for the project
		for (ProjectScoresData scoresForProject : scoresData) {
			Project project = scoresForProject.getProject();

			// consider each student in the order of score
			for (StudentScore score : scoresForProject.getStudentScores()) {

				// if the project team has been formed
				Project remainingProject = state.getRemainingProject(project.getId());
				if ((remainingProject == null)) {
					break;
				}

				// if the student has not been assigned
				Student student = score.getStudent();
				Student remainingStudent = state.getRemainingStudent(student.getStudentNo());
				if (remainingStudent != null) {
					assignForPhase1(project, student);
				}
			}
		}
	}

	/**
	 * The second phase of assigning students. Attempts to assign students into
	 * projects ensuring hard constraints are met. Students with higher 'score' for
	 * project (higher match to the project considering preferences and
	 * requirements) are assigned prior to others as long as hard constraints are
	 * satisfied.
	 */
	private void assignStudentsPhase2() {
		// data of all student's score for each project
		Collection<ProjectScoresData> scoresData = calcScores();

		// for each project, check all the students' scores for the project
		for (ProjectScoresData scoresForProject : scoresData) {
			Project project = scoresForProject.getProject();

			// consider each student in the order of score
			for (StudentScore score : scoresForProject.getStudentScores()) {

				// if the project team has been formed
				Project remainingProject = state.getRemainingProject(project.getId());
				if ((remainingProject == null)) {
					break;
				}

				// if the student has not been assigned
				Student student = score.getStudent();
				Student remainingStudent = state.getRemainingStudent(student.getStudentNo());
				if (remainingStudent != null) {
					assignForPhase2(project, student);
				}
			}
		}
	}

	/**
	 * The third phase of assigning students. Attempts to assign students into
	 * projects ensuring the role requirements of projects are met, but ignoring
	 * hard constraints.
	 */
	private void assignStudentsPhase3() {
		// data of all student's score for each project
		Collection<ProjectScoresData> scoresData = calcScores();

		// for each project, check all the students' scores for the project
		for (ProjectScoresData scoresForProject : scoresData) {
			Project project = scoresForProject.getProject();

			// consider each student in the order of score
			for (StudentScore score : scoresForProject.getStudentScores()) {

				// if the project team has been formed
				Project remainingProject = state.getRemainingProject(project.getId());
				if ((remainingProject == null)) {
					break;
				}

				// if the student has not been assigned
				Student student = score.getStudent();
				Student remainingStudent = state.getRemainingStudent(student.getStudentNo());
				if (remainingStudent != null) {
					RoleRequirement roleMatch = getRoleMatch(project, student);

					if (roleMatch != null) {
						assign(project, student);
						state.removeRoleRequirement(project.getId(), roleMatch);
					}
				}
			}
		}
	}

	/**
	 * Wrapper method for the assignment phase 1 - 3
	 * 
	 * @throws InsufficientProjectsException
	 * @throws InsufficientStudentsException
	 */
	private void assignStudents() throws InsufficientProjectsException, InsufficientStudentsException {
		assignStudentsPhase1();

		if (state.getRemainingStudents().size() > 0) {
			assignStudentsPhase2();
		}

		if (state.getRemainingStudents().size() > 0) {
			assignStudentsPhase3();
		}
	}

	/**
	 * assign remaining students to any vacant project regardless of hard
	 * constraints
	 */
	private void forceAssign() {
		List<Student> students = new ArrayList<>(state.getRemainingStudents());
		List<Project> projects = new ArrayList<>(state.getRemainingProjects());

		for (Student student : students) {
			for (Project project : projects) {
				if ((project.getStudents().size() < Project.TEAM_CAPACITY)) {
					assign(project, student);
					break;
				}
			}
		}
	}

	@Override
	public Collection<Project> formTeams()
			throws InsufficientProjectsException, InsufficientStudentsException, RemainedStudentsException {
		Collection<Project> candidateProjects = getPopularProjects();
		for (Project project : candidateProjects) {
			project.resetStudents();
			connection.saveProject(project);
		}

		List<Student> female = new LinkedList<>(connection.getFemaleStudents());
		List<Student> other = new LinkedList<>(connection.getMaleStudents());
		other.addAll(connection.getOtherStudents());

		// shuffle students to get different resulting teams each time assignStudents()
		// is called
		Collections.shuffle(female, new Random(System.currentTimeMillis()));
		Collections.shuffle(other, new Random(System.currentTimeMillis()));

		// assign female students and then others
		state = new TeamFormationState(female, candidateProjects);
		assignStudents();
		state.addStudents(other);
		assignStudents();

		// assign remaining students to any vacant project
		forceAssign();

		// student(s) will remain if the number of all students is not divisible by team
		// capacity
		Collection<Student> remainders = state.getRemainingStudents();
		Collection<Project> formedProjects = state.getFormedProjects();

		if (remainders.size() > 0) {
			throw new RemainedStudentsException(remainders, formedProjects);
		}

		return formedProjects;
	}

	@Override
	public void confirmTeams() {
		if (state != null) {
			Collection<Project> formedProjects = state.getFormedProjects();
			formedProjects.forEach(connection::saveProject);
		}
	}

	@Override
	public int getFitnessValue(Project project) {
		return validator.calculateFit(project);
	}
}
