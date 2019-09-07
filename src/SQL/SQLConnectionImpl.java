package SQL;
import java.sql.*;
import java.util.List;

import interfaces.Constraint;
import interfaces.Project;
import interfaces.SQLConnection;
import interfaces.Student;
import model.constraints.SoftConstraint;

public class SQLConnectionImpl implements SQLConnection {

	public static void main(String[] args) {
		try {
			String url = "jdbc:mysql://localhost:3306/sef";
			Connection conn = DriverManager.getConnection(url, "root", "");
			Statement stmt = conn.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery("SELECT StuID FROM Student");
			while (rs.next()) {
				String stuid = rs.getString("StuID");
				System.out.println(stuid);
			}
			conn.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	@Override
	public int getStudentCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getProjectCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> getAllProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Constraint> getAllHardConstraints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SoftConstraint> getAllSoftConstraints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getFemaleStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getMaleStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> getPopularProjects(int popularProjectCounts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project getProject(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProject(Project project) {
		// TODO Auto-generated method stub
		
	}
}
