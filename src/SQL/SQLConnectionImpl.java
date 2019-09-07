package SQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import interfaces.Constraint;
import interfaces.Project;
import interfaces.SQLConnection;
import interfaces.Student;
import model.ProjectImpl;
import model.constraints.SoftConstraint;

public class SQLConnectionImpl implements SQLConnection {
	private static Connection conn = null;
	
	public SQLConnectionImpl()  {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:db/SEF.db");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void getStudent() {
		try {
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

	private List<String> getPopularProjectIds(int popularProjectCounts) {
		List<String> projectIds = new ArrayList<>();
		//String query = "SELECT ProID, SUM(Weight) AS Weight FROM Preferences GROUP BY ProID ORDER BY Weight DESC LIMIT " + popularProjectCounts + ";";
		String query = "SELECT ProID, SUM(Weight) AS Weight FROM Preferences GROUP BY ProID ORDER BY Weight DESC LIMIT 10;";
		
		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);
	    	
	    	while(rs.next()) {
	    		projectIds.add(rs.getString(1));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
		return projectIds;
	}
	
	private Project getProject(String projectId) {
		String query = "SELECT * FROM Project WHERE ProID = " + projectId + ";";
		
		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);
	    	
	    	if (rs.next()) {
	    		Project project = new ProjectImpl(rs.getString(1), rs.getString(2), null);
	    		return project;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public List<Project> getPopularProjects(int popularProjectCounts) {
		List<Project> projects = new ArrayList<>();
		
		List<String> projectIds = getPopularProjectIds(popularProjectCounts);
		
		for (String projectId : projectIds) {
			Project project = getProject(projectId); 
			projects.add(project);
		}
		
		return projects;
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
