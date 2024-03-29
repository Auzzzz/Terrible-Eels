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
import interfaces.DataStorage;
import interfaces.Student;
import model.ProjectImpl;
import model.RoleRequirement;
import model.StudentImpl;
import model.constraints.*;

public class SQLConnection implements DataStorage {
	private static Connection conn = null;
	private final String STUDENT_ID = "StuID";
	private final String GENDER = "gender";
	private final String PERSONALITY_TYPE = "PersonType";
	private final String WORK_EXPERIENCE = "WorkExp";
	private final String GPA = "GPA";
	private final String NAME = "Name";

	public SQLConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:db/SEF.db");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
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
			rs.close();
			state.close();
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
			rs.close();
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

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

			rs.close();
			state.close();
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
				Project project = new ProjectImpl(rs.getString("Desc"), rs.getString("ProID"),
						new ArrayList<RoleRequirement>());
				projects.add(project);

			}
			
			rs.close();
			state.close();
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
			
			rs.close();
			state.close();
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
			
			rs.close();
			state.close();
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

			rs.close();
			state.close();
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

			rs.close();
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
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
					String projectIntId = rs.getString("ProID");
					Collection<RoleRequirement> roles = getRequirements(projectIntId);

					project = new ProjectImpl(rs.getString("Desc"), projectIntId, roles);
					Collection<String> studentIds = getMembers(projectIntId);
					for (String studentId : studentIds) {
						Student member = getStudent(studentId);
						project.addStudent(member);
					}
				}
				
				rs.close();
				state.close();
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
		Collection<Student> students = project.getStudents();
		deleteTeam(project.getId());
		PreparedStatement prep;
		int teamID = getTeamID();

		try {
			String query = "INSERT INTO Teams (TeamID, ProID, StuID) VALUES (?, ?, ?);";
			
			for (Student member : students) {
				prep = conn.prepareStatement(query);
				prep.setInt(1, ++teamID);
				prep.setInt(2, Integer.parseInt(project.getId()));
				prep.setString(3, member.getStudentNo());
				prep.execute();
				prep.close();
			}

			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void saveConstraint(String desc, int weight) {
		String query = "UPDATE SoftConstraint SET Weight = '" + weight + "' WHERE Desc = '" + desc + "' ;";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void insertProject(Project project) {
		String query = "INSERT INTO Project (Desc) VALUES (?);";
		PreparedStatement prep;
		String projectId;

		try {
			prep = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			prep.setString(1, project.getProjectDesc());
			prep.execute();

			ResultSet generatedKeys = prep.getGeneratedKeys();
			if (generatedKeys.next()) {
				projectId = generatedKeys.getString(1);
				if (project.getRoleRequirements().size() > 0) {
					insertRoleRequirement(projectId, project.getRoleRequirements());
				}
			}
			
			prep.close();
			generatedKeys.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Project getProjectByStudentNo(String studentNo) {
		// SELECT StuID, ProID FROM Teams WHERE StuID =' + studentNo + ';'
		// SELECT * FROM Project WHERE ProID = 1;
		Project pdsc = null;
		String query = "select ProID from Teams where StuID ='" + studentNo + "';'";

		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);
			if (!rs.isClosed()) {
				String projectID = rs.getString("ProID");
				return getProjectByID(projectID);
			}

			state.close();
			rs.close();
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
			
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pdsc;
	}

	@Override
	public Student getStudent(String studentNo) {

		Student student = null;
		String query = "SELECT * FROM Student WHERE StuID = '" + studentNo + "';";

		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);

			if (rs.next()) {
				student = createStudent(rs);
			}
			
			state.close();
			rs.close();
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

	private Student createStudent(ResultSet rs) throws SQLException {
		List<RoleRequirement> rolePreferences = new ArrayList<RoleRequirement>();
		List<Student> blacklist = new ArrayList<Student>();
		List<String> projectPreferences = (List<String>) getProjectPreferences(rs.getString(STUDENT_ID));

		Student student = new StudentImpl(rs.getString(NAME), rs.getString(STUDENT_ID),
				PersonalityType.valueOf(rs.getString(PERSONALITY_TYPE)), Gender.valueOf(rs.getString(GENDER)),
				rs.getInt(WORK_EXPERIENCE), rs.getDouble(GPA), projectPreferences, rolePreferences, blacklist);

		return student;	
	}

	private Collection<String> getProjectPreferences(String StuID) {
		String query = "SELECT * FROM Student_Project WHERE " + STUDENT_ID + " = '" + StuID + "';";
		ArrayList<String> projects = new ArrayList<String>();

		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);

			projects.add(rs.getString("ProID1"));
			projects.add(rs.getString("ProID2"));
			projects.add(rs.getString("ProID3"));
			projects.add(rs.getString("ProID4"));

			state.close();
			rs.close();
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
			addStudents(project);
			state.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return project;
	}

	private void addStudents(Project project) {
		
		String query = "SELECT StuID FROM Teams WHERE ProID = '" + project.getId() + "';";
		
		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);
			
			while(rs.next()) {
				project.addStudent(getStudent(rs.getString("StuID")));
			}
			
			state.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
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

			state.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
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
			
			state.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return projectIds;
	}

	private Collection<String> getMembers(String projectId) {
		Collection<String> memberIds = new ArrayList<>();
		String query = String.format("SELECT StuID FROM Teams WHERE ProID = %s", projectId);

		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);

			while (rs.next()) {
				String stuId = rs.getString(1);
				memberIds.add(stuId);
			}
			
			state.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return memberIds;
	}

	private void insertRoleRequirement(String projectId, Collection<RoleRequirement> reqs) {
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
					prep.setString(1, projectId);
					prep.setInt(2, roleId);
					prep.setInt(3, skillId);
					prep.execute();
					prep.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private int getTeamID() {
		int teamID = 0;
		String query = "SELECT MAX(teamID) FROM Teams;";

		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(query);
			
			teamID = rs.getInt(1);
			rs.close();
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return teamID;
		
	}
	
	private void deleteTeam(String id) {
		String query = "DELETE FROM Teams WHERE ProID = '" + id + "';";
		try {
			PreparedStatement state = conn.prepareStatement(query);
			state.execute();
			state.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// INSERT INTO Student (Gender,PT, WorkExp, GPA,Role, name) VALUES ("MALE",'2',
	// 2005, 5.2, "THIS","Test");
	// INSERT INTO Preferences (StuID, ProID, Weight) VALUES (5, 1, 4);
	//

}