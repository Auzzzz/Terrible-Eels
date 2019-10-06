package model.teamFormation;

import interfaces.Student;

/**
 * StudentScore:
 * 
 * Maintains a student and their fitness for a given project
 * Used to sort students by the score so that those with higher score can be considered first 
 * for assignment to the project.
 */
public class StudentScore implements Comparable<StudentScore> {
	private Student student;
	private int score;
	
	public StudentScore(Student student, int score) {
		this.student = student;
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public Student getStudent() {
		return student;
	}

	@Override
	public int compareTo(StudentScore o) {
		if (this.score < o.getScore()) {
			return -1;
		}
		if (this.score >= o.getScore()) {
			return 1;
		}
		
		return 0;
	}
}
