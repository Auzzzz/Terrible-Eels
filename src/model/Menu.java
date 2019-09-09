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

		System.out.println("Please enter student, client, or project manager:");
		input = scanner.nextLine().toLowerCase();
		switch (input) {
		case "student":
			System.out.println("Not yet implemented");
			run();
		case "client":
			System.out.println("Not yet implemented");
			run();
		case "project manager":
			System.out.println("Please Enter Username:");
			input = scanner.nextLine();
			System.out.println("Please Enter Password:");
			input = scanner.nextLine();

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
				break;
			}
		}

	}
}
