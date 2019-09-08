package model;


import java.util.Scanner;
import SQL.SQLConnectionImpl;
import interfaces.ProjectTeamsFormationSystem;
import interfaces.SQLConnection;

public class Menu {

	public void run() {
		SQLConnection SQL = new SQLConnectionImpl();
		ProjectTeamsFormationSystem system = new ProjectTeamsFormationSystemImpl(SQL);
		Scanner scanner = new Scanner(System.in);
		String input = "";

		while (input != "1") {
			System.out.println("Press 1 to Display All Projects ");
			System.out.println("Press 2 to Display Popular Projects ");
			System.out.println("Press 3 to exit");

			input = scanner.nextLine();

			switch (input) {
			case "1":
				System.out.println(system.getAllProjectDescs());
				break;
			case "2":
				System.out.println(system.getPopularProjectDescs());
				break;
			default:
				System.out.println("Wrong button pressed, please try again.");
				break;

			}
			
			scanner.close();

		}

	}
}
