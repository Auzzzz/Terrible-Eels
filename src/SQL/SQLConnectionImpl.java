package SQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import interfaces.Project;
import interfaces.SQLConnection;
import interfaces.Student;
import model.ProjectImpl;
import model.constraints.SoftConstraint;

public class SQLConnectionImpl implements SQLConnection {
	private static Connection conn = null;

	public SQLConnectionImpl() {
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
		String query = "SELECT COUNT(ProID) FROM Project;";

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

//TODO: fix getallstudents
	@Override
	public Collection<Student> getAllStudents() {
		List<Student> students = new ArrayList<Student>();
		String query = "SELECT StuID from Student ORDER BY StuID ASC";

		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);

			while (rs.next()) {
				// Student student = new
				// Student.add(student);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
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
	public Collection<SoftConstraint> getAllSoftConstraints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Student> getFemaleStudents() {
		List<Student> student = new ArrayList<Student>();
		String query = "SELECT StuID from Student WHERE Gender = 'F';";

		
		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return student;

	}

	@Override
	public Collection<Student> getMaleStudents() {
		// TODO Auto-generated method stub
		// SELECT StuID from Student WHERE Gender = 'M';

		return null;
	}

	@Override
	public Collection<Student> getOtherStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	private Collection<String> getPopularProjectIds(int idealNumberOfProject) {
		List<String> projectIds = new ArrayList<>();
		String query = "SELECT ProID, SUM(Weight) AS Weight FROM Preferences GROUP BY ProID ORDER BY Weight DESC LIMIT "
				+ idealNumberOfProject + ";";

		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);

			while (rs.next()) {
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
	public void saveProject(Project project) {
		// TODO Auto-generated method stub
		// INSERT INTO Project Values ( NULL , 'Test DESC', 'Req', NULL)
		// Can leave first Null, DESC, Requirements, Null if Client not known)
	}

	@Override
	public void saveConstraint(String desc, int weight) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateProject(Project project1) {
		// TODO Auto-generated method stub
		// UPDATE Project Set Desc = 'THis is a update' WHERE ProID = '2'

	}

	@Override
	public Project getProjectByStudentNo(String studentNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStudent(String studentNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project getProjectByDesc(String desc) {
		// TODO Auto-generated method stub
		return null;
	}
}
