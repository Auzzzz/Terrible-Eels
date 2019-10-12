package SQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import enums.Gender;
import enums.PersonalityType;
import enums.Role;
import enums.Skill;
import interfaces.Project;
import interfaces.SQLConnection;
import interfaces.Student;
import model.ProjectImpl;
import model.RoleRequirement;
import model.StudentImpl;
import model.constraints.*;

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

		String query = "SELECT * from Student ORDER BY StuID ASC";
		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);

			while (rs.next()) {
				students.add(createStudent(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
	}

	private Student createStudent(ResultSet rs) throws SQLException {
		List<RoleRequirement> rolePreferences = new ArrayList<RoleRequirement>();
		List<Student> blacklist = new ArrayList<Student>();
		List<Project> projectPreferences = (List<Project>) getProjectPreferences(rs.getString(STUDENT_ID));

		Student student = new StudentImpl(rs.getString(NAME), rs.getString(STUDENT_ID),
				toPersonalityType(rs.getString(PERSONALITY_TYPE)), toGender(rs.getString(GENDER)),
				rs.getInt(WORK_EXPERIENCE), rs.getDouble(GPA), projectPreferences, rolePreferences, blacklist);

		return student;
	}

	private Collection<Project> getProjectPreferences(String StuID) {
		String query = "SELECT * FROM Student_Project WHERE " + STUDENT_ID + " = '" + StuID + "';";
		ArrayList<Project> projects = new ArrayList<Project>();

		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);

			projects.add(getProjectByID(rs.getString("ProID1")));
			projects.add(getProjectByID(rs.getString("ProID2")));
			projects.add(getProjectByID(rs.getString("ProID3")));
			projects.add(getProjectByID(rs.getString("ProID4")));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return projects;
	}

	private Project getProjectByID(String projectID) {
		Project project = null;
		String query = "select * from Project where ProID ='" + projectID + "';'";

		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);
			project = new ProjectImpl(rs.getString("Desc"), rs.getString("ProID"), getRequirements(projectID));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return project;
	}

	private Collection<RoleRequirement> getRequirements(String projectID) {
		ArrayList<RoleRequirement> roles = new ArrayList<RoleRequirement>();
		ArrayList<Skill> skills = new ArrayList<Skill>();
		String query = "SELECT Roles.RoleType, Skills.Name FROM Roles, RoleRequirements, Skills "
				+ "WHERE (Roles.RoleID = RoleRequirements.RoleID) AND (Skills.ID = RoleRequirements.SkillID) AND RoleRequirements.ProID ='"
				+ projectID + "';";
		boolean alreadyAdded = false;

		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);

			while (rs.next()) {
				Role role = Role.valueOf(rs.getString("RoleType"));
				for (int i = 0; i < roles.size(); i++) {
					if (roles.get(i).getRole() == role) {
						roles.get(i).addSkill(Skill.valueOf(rs.getString("name")));
						alreadyAdded = true;
					}
				}
				if (!alreadyAdded) {
					roles.add(new RoleRequirement(role, skills));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
	}

	@Override
	public Collection<Project> getAllProjects() {
		List<Project> projects = new ArrayList<>();

		String query = "SELECT * FROM Project;";

		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);

			while (rs.next()) {
				Project project = new ProjectImpl(rs.getString("Desc"), rs.getString("ProID"),
						new ArrayList<RoleRequirement>());
				projects.add(project);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return projects;
	}

	@Override
	public Collection<SoftConstraint> getAllSoftConstraints() {
		List<SoftConstraint> softConstraints = new ArrayList<SoftConstraint>();
		String query = "SELECT * FROM SoftConstraint;";

		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);
			while (rs.next()) {
				String desc = rs.getString("Desc");
				switch (desc) {
				case "WorkExperience":
					softConstraints.add(new WorkExperienceConstraint(rs.getString("Desc"), rs.getInt("Weight")));
					break;
				case "PersonalityAorB":
					softConstraints.add(new PersonalityAorBConstraint(rs.getString("Desc"), rs.getInt("Weight")));
					break;
				case "DifferentPersonalityTypes":
					softConstraints.add(new WorkExperienceConstraint(rs.getString("Desc"), rs.getInt("Weight")));
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return softConstraints;
	}

	@Override
	public Collection<Student> getFemaleStudents() {
		List<Student> students = new ArrayList<Student>();
		String query = "SELECT * from Student WHERE Gender = 'FEMALE';";

		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);
			while (rs.next()) {
				students.add(createStudent(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;

	}

	@Override
	public Collection<Student> getMaleStudents() {
		List<Student> students = new ArrayList<Student>();
		String query = "SELECT * from Student WHERE Gender = 'MALE';";

		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);
			while (rs.next()) {
				students.add(createStudent(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
	}

	@Override
	public Collection<Student> getOtherStudents() {
		List<Student> students = new ArrayList<Student>();
		String query = "SELECT * from Student WHERE Gender = 'OTHER';";

		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);
			while (rs.next()) {
				students.add(createStudent(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
	}

	private Collection<String> getPopularProjectIds(int idealNumberOfProject) {
		List<String> projectIds = new ArrayList<>();
		String query = String.format(
				"SELECT ProID, SUM(Weight) AS Weight FROM Preferences GROUP BY ProID ORDER BY Weight DESC LIMIT %d;",
				idealNumberOfProject);

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

	@Override
	public Collection<Project> getPopularProjects(int idealNumberOfProjects) {
		Collection<Project> projects = new ArrayList<>();
		Collection<String> projectIds = getPopularProjectIds(idealNumberOfProjects);

		for (String projectId : projectIds) {
			Project project = null;
			String query = String.format("SELECT * FROM Project WHERE ProID = %s;", projectId);

			try {
				Statement state = conn.createStatement();
				ResultSet rs = state.executeQuery(query);

				if (rs.next()) {
					int projectIntId = rs.getInt(1);
					Collection<RoleRequirement> roles = getRoleRequirements(projectIntId);
					
					project = new ProjectImpl(rs.getString("Desc"), String.valueOf(projectIntId), roles);
					Collection<String> studentIds = getMembers(projectIntId);
					for (String studentId : studentIds) {
						Student member = getStudent(studentId);
						project.addStudent(member);
					}
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

	private Collection<RoleRequirement> getRoleRequirements(int projectId) {
		Collection<RoleRequirement> roles = new ArrayList<>();
		String query = String.format("SELECT RoleId FROM RoleRequirements WHERE ProID = %d;", projectId);

		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);

			while (rs.next()) {
				int roleId = rs.getInt(1);
				Role role = Role.getRole(roleId);
				Collection<Skill> skills = getSkills(projectId, roleId);
				roles.add(new RoleRequirement(role, skills));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return roles;
	}

	private Collection<Skill> getSkills(int projectId, int roleId) {
		Collection<Skill> skills = new ArrayList<>();
		String query = String.format("SELECT SkillID FROM RoleRequirements WHERE ProID = %d AND RoleID = %d;",
				projectId, roleId);

		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);

			while (rs.next()) {
				int skillId = rs.getInt(1);
				Skill skill = Skill.getSkill(skillId);
				skills.add(skill);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return skills;
	}

	private Collection<String> getMembers(int projectId) {
		Collection<String> memberIds = new ArrayList<>();
		String query = String.format("SELECT StuID FROM Teams WHERE ProID = %d;", projectId);

		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);

			while (rs.next()) {
				String stuId = rs.getString(1);
				memberIds.add(stuId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return memberIds;
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
	public void insertProject(Project project) {
		String query = "INSERT INTO Project (Desc) VALUES (?);";
		PreparedStatement prep;
		int projectId;

		try {
			prep = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			prep.setString(1, project.getProjectDesc());
			prep.execute();

			ResultSet generatedKeys = prep.getGeneratedKeys();
			if (generatedKeys.next()) {
				long lastId = generatedKeys.getLong(1);
				projectId = Long.valueOf(lastId).intValue();
				if (project.getRoleRequirements().size() > 0) {
					insertRoleRequirement(projectId, project.getRoleRequirements());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void insertRoleRequirement(int projectId, Collection<RoleRequirement> reqs) {
		String query = "INSERT INTO RoleRequirements (ProID, RoleID, SkillID) VALUES (?, ?, ?);";
		PreparedStatement prep;

		for (RoleRequirement req : reqs) {
			Role role = req.getRole();
			int roleId = role.getId();
			Collection<Skill> skills = req.getSkills();

			for (Skill skill : skills) {
				int skillId = skill.getId();
				try {
					prep = conn.prepareStatement(query);
					prep.setInt(1, projectId);
					prep.setInt(2, roleId);
					prep.setInt(3, skillId);
					prep.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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

			if (rs.next()) {
				student = createStudent(rs);
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

	// INSERT INTO Student (Gender,PT, WorkExp, GPA,Role, name) VALUES ("MALE",'2',
	// 2005, 5.2, "THIS","Test");
	// INSERT INTO Preferences (StuID, ProID, Weight) VALUES (5, 1, 4);
	//

}