package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import enums.Role;
import interfaces.*;

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
	
	/**
	 * find a project with given id from the list of popular projects.
	 * @param projectId
	 * @param popularProjects
	 * @return - project object of the list that was found
	 */
	private Project isPopularProject(String projectId, List<Project> popularProjects) {
		for (Project project : popularProjects) {
			if (projectId.equals(project.getId())) {
				return project;
			}
		}
		
		return null;
	}
	
	/**
	 * assign the given student to a project based on his preferences and constraints
	 * @param student
	 * @param popularProjects
	 * @return - true if the student is successfully assigned
	 */
	private boolean assignStudent(Student student, List<Project> popularProjects) {
		List<String> preferences = student.getProjectPreferences();
		
		for (String preference : preferences) {			
			
			// find the preferred 'popular project' object 
			Project project = isPopularProject(preference, popularProjects);
			
			// the preferred project is popular enough
			if (project != null) {
				boolean valid = false;
				
				// if the project has no member yet
				if (project.getStudents().isEmpty()) {
					valid = true;
				} else {
					valid = validator.validateRequirements(project, student);
				}
				
				if (valid) {
					project.addStudent(student);
					connection.saveProject(project);
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	// TODO: return value?
	private List<Student> assignStudents(List<Student> students, List<Project> popularProjects) {
		List<Student> unassignedStudents = new LinkedList<>();
		
		for (Student student : students) {
			boolean assigned = assignStudent(student, popularProjects);
			
			if (!assigned) {
				unassignedStudents.add(student);
			}
		}
		
		return unassignedStudents;
	}
	
	// Assign students into teams based on preferences and constraints
	@Override
	public void assignStudents() {
		// popular projects to which students can be assigned
		List<Project> popularProjects = getPopularProjects();
		
		List<Student> femaleStudents = connection.getFemaleStudents();
		List<Student> maleStudents = connection.getMaleStudents();
		
		// TODO: what to do with unassigned students?
		List<Student> unassigned = assignStudents(femaleStudents, popularProjects);
		unassigned.addAll(assignStudents(maleStudents, popularProjects));
	}
}