package model;

import java.util.Scanner;
import SQL.SQLConnectionImpl;
import interfaces.ProjectTeamsFormationSystem;
import interfaces.SQLConnection;

public class Menu {

	Boolean Logged = false;
	
	public void run() {

		String Username;
		String Password;
		Username = "PM";
		Password = "111";
	

		SQLConnection SQL = new SQLConnectionImpl();
		ProjectTeamsFormationSystem system = new ProjectTeamsFormationSystemImpl(SQL);
		Scanner scanner = new Scanner(System.in);
		String input = "";
		if (Logged == false) {
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

				Scanner in1 = new Scanner(System.in);
				System.out.println("Please Enter Username: ");
				String username = in1.next();

				Scanner in2 = new Scanner(System.in);
				System.out.println("Please Enter Password: ");
				String password = in2.next();

				if (username.equals(Username) && password.equals(Password)) {
					System.out.println("You are now logged in " + Username);
					Logged = true;
					run();
					// Not logged in

				} else if (username.equals(Username)) {
					System.out.println("Invalid Password" + Username + Password);
					run();
				} else {
					System.out.println("Invalid Username and Password" + Username + Password);
					run();
				}

			}
		} else {
			while (input != "1") {
				System.out.println("*******************************************");
				System.out.println("Press 1 to Display All Projects ");
				System.out.println("Press 2 to Display Popular Projects ");
				System.out.println("Press 3 to review unpopular");
				System.out.println("Press 4 to delete all unpopular projects");
				System.out.println("Press 5 to exit");
				System.out.println("*******************************************");

				input = scanner.nextLine();

				switch (input) {
				case "1":
					System.out.println(system.getAllProjectDescs());
					run();
					break;
				case "2":
					System.out.println(system.getPopularProjectDescs());
					run();
					break;

				case "3":
					//System.out.println(system.getUnPopularProjects());
					System.out.println("Display All Unpopular projects");
					run();
				case "4":
					System.out.println("Delete all unpopular projects");
					run();
				case "5":
					Logged = false;
					run();
				default:
					run();
					break;

				}

				scanner.close();
				break;
			}

		}
	}

}