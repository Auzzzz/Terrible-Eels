package SQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import enums.Gender;
import enums.PersonalityType;
import interfaces.Project;
import interfaces.SQLConnection;
import interfaces.Student;
import model.ProjectImpl;
import model.RoleRequirement;
import model.StudentImpl;
import model.constraints.SoftConstraint;

public class SQLConnectionImpl implements SQLConnection {
	private static Connection conn = null;
	private final String STUDENT_ID = "StuID";
	private final String GENDER = "gender";
	private final String PERSONALITY_TYPE = "PersonType";
	private final String WORK_EXPERIENCE = "WorkExp";
	private final String GPA = "GPA";
	private final String NAME = "Name";

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
				// Student student = new StudentImpl();
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
				Project project = new ProjectImpl(rs.getString(1), rs.getString(2), new ArrayList<RoleRequirement>());
				projects.add(project);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return projects;
	}

	@Override
	public Collection<SoftConstraint> getAllSoftConstraints() {
		List<SoftConstraint> softconstraint = new ArrayList<SoftConstraint>();
		String query = "select * from SoftConstraint;";

		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return softconstraint;
	}

	@Override
	public Collection<Student> getFemaleStudents() {
		List<Student> student = new ArrayList<Student>();
		String query = "SELECT StuID from Student WHERE Gender = 'F';";

		try {
			Statement state = conn.createStatement();
			state.executeQuery(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;

	}

	@Override
	public Collection<Student> getMaleStudents() {
		List<Student> student = new ArrayList<Student>();
		String query = "SELECT StuID from Student WHERE Gender = 'M';";

		try {
			Statement state = conn.createStatement();
			state.executeQuery(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
	}

	@Override
	public Collection<Student> getOtherStudents() {
		List<Student> student = new ArrayList<Student>();
		String query = "SELECT StuID from Student WHERE Gender = 'O';";

		try {
			Statement state = conn.createStatement();
			state.executeQuery(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
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
					project = new ProjectImpl(rs.getString(1), rs.getString(2), new ArrayList<RoleRequirement>());
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
		String query = "SELECT StuID from Student WHERE Gender = 'F';";

		try {
			Statement state = conn.createStatement();
			state.executeQuery(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void saveConstraint(String desc, int weight) {
		String query = "INSERT INTO SoftConstraint (Desc, Weight) VALUES (" + desc + "," + weight + ");";

		try {
			Statement state = conn.createStatement();
			state.executeQuery(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateProject(Project project1) {
		//String query = "UPDATE Project Set Desc = 'THis is a update' WHERE ProID = " + project1 + ");";

		try {
			Statement state = conn.createStatement();
			//state.executeQuery(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Project getProjectByStudentNo(String studentNo) {
		// SELECT StuID, ProID FROM Teams WHERE StuID =' + studentNo + ';'
		// SELECT * FROM Project WHERE ProID = 1;
		Project pdsc = null;
		String query = "select * from Project P where P.Desc ='" + studentNo + "';'";

		try {
			Statement state = conn.createStatement();
			state.executeQuery(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pdsc;
	}

	@Override
	public Project getProjectByDesc(String desc) {
		Project pdsc = null;
		String query = "select * from Project P where P.Desc ='" + desc + "';'";

		try {
			Statement state = conn.createStatement();
			state.executeQuery(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pdsc;
	}

	@Override
	public Student getStudent(String studentNo) {

		Student student = null;
		String query = "SELECT * FROM Student WHERE StuID = " + studentNo + ";";

		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);
			List<Project> projectPreferences = new ArrayList<Project>();
			List<RoleRequirement> rolePreferences = new ArrayList<RoleRequirement>();
			List<Student> blacklist = new ArrayList<Student>();

			if (rs.next()) {
				student = new StudentImpl(rs.getString(STUDENT_ID), "name",
						toPersonalityType(rs.getString(PERSONALITY_TYPE)), toGender(rs.getString(GENDER)),
						rs.getInt(WORK_EXPERIENCE), rs.getDouble(GPA), projectPreferences, rolePreferences, blacklist);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
	}

	@Override
	public void updateStudent(Student student) {
		String query = "UPDATE Student SET " + GENDER + " = '" + student.getGender() + "', " + PERSONALITY_TYPE + " = '"
				+ student.getPersonalityType() + "', " + WORK_EXPERIENCE + " = '" + student.getExperience() + "', "
				+ GPA + " = '" + student.getGpa() + "', " +
				// ROLE + " = '" + student.getStudentNo() + ", " +
				NAME + " = '" + student.getName() + "' WHERE " + STUDENT_ID + " = '" + student.getStudentNo() + "';";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private PersonalityType toPersonalityType(String personalityType) {

		for (PersonalityType p : PersonalityType.values()) {
			if (p.toString().equalsIgnoreCase(personalityType)) {
				return p;
			}
		}
		return null;
	}

	private Gender toGender(String gender) {

		for (Gender p : Gender.values()) {
			if (p.toString().equalsIgnoreCase(gender)) {
				return p;
			}
		}
		return null;
	}
}