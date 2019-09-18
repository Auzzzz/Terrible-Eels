package SQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import interfaces.Constraint;
import interfaces.Project;
import interfaces.SQLConnection;
import interfaces.Student;
import model.ProjectImpl;

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
		String query = "SELECT COUNT(StuID) FROM Student;";
		
		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);
	    	
	    	if (rs.next()) {
	    		return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int getProjectCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Project> getAllProjects() {
		List<Project> projects = new ArrayList<>();
		String query = "SELECT * FROM Project;";
		
		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);
	    	
	    	while (rs.next()) {
	    		Project project = new ProjectImpl(rs.getString(1), rs.getString(2), null);
	    		projects.add(project);
	    		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return projects;
	}

	@Override
	public Collection<Constraint> getAllHardConstraints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Constraint> getAllSoftConstraints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Student> getFemaleStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Student> getMaleStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * get the id of popular projects as a list of String
	 * @param idealNumberOfProject
	 * @return - list of project IDs
	 */
	private Collection<String> getPopularProjectIds(int idealNumberOfProject) {
		List<String> projectIds = new ArrayList<>();
		String query = "SELECT ProID, SUM(Weight) AS Weight FROM Preferences GROUP BY ProID ORDER BY Weight DESC LIMIT " + idealNumberOfProject + ";";
		
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
	
	// TODO - fix project instantiation - more data required
	@Override
	public Collection<Project> getPopularProjects(int idealNumberOfProjects) {
		Collection<Project> projects = new ArrayList<>();
		Collection<String> projectIds = getPopularProjectIds(idealNumberOfProjects);
		
		for (String projectId : projectIds) {
			Project project = null;
			String query = "SELECT * FROM Project WHERE ProID = " + projectId + ";";
			
			try {
				Statement state = conn.createStatement();
				ResultSet rs = state.executeQuery(query);
		    	
		    	if (rs.next()) {
		    		// TODO: use another project constructor
		    		project = new ProjectImpl(rs.getString(1), rs.getString(2), null);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if (project != null) {
				projects.add(project);
			}
		}
		
		return projects;
	}

	@Override
	public Project getProject(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveProject(Project project) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveConstraint(String desc, int weight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProject(Project project1) {
		// TODO Auto-generated method stub
		
	}
}
