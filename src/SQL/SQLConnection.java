package SQL;
import java.sql.*;

public class SQLConnection {

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
}
