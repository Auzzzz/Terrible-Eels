package model.teamFormation;

import java.util.SortedSet;
import java.util.TreeSet;

import interfaces.Project;

/**
 * ProjectScoresData:
 * 
 * Maintains each student's fitness score for a project. 
 * The score is calculated based on the students' preferences, and the roles and skills
 * required by the project.
 * 
 * For example, if 
 * - this project is student X's first preference (10 points) AND
 * - the student X has a role and skill required (/preferred) by the project (10 points)
 * then the student X will have the highest possible score ( 20 points) 
 *
 */
public class ProjectScoresData {
	private Project project;
	private SortedSet<StudentScore> studentScores = new TreeSet<>();
	
	public ProjectScoresData(Project project) {
		this.project = project;
	}
	
	public Project getProject() {
		return project;
	}
	
	public SortedSet<StudentScore> getStudentScores() {
		return studentScores;
	}
	
	public void addStudentScore(StudentScore score) {
		studentScores.add(score);
	}
}

