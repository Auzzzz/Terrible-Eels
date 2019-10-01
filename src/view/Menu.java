package view;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import SQL.SQLConnectionImpl;
import enums.Role;
import enums.Skill;
import interfaces.ProjectTeamsFormationSystem;
import interfaces.SQLConnection;
import model.RoleRequirement;
import model.teamFormation.ProjectTeamsFormationSystemImpl;

public class Menu {

	SQLConnection SQL;
	ProjectTeamsFormationSystem system;

	public void run() {

		String Username = "user";
		String Password = "111";

		SQL = new SQLConnectionImpl();
		system = new ProjectTeamsFormationSystemImpl(SQL);
		Scanner scanner = new Scanner(System.in);
		String input = "";

		while (true) {
			System.out.println("Please enter student, client, or project manager:");
			input = scanner.nextLine().toLowerCase();

			System.out.println("Please Enter Username: ");
			String username = scanner.next();

			System.out.println("Please Enter Password: ");
			String password = scanner.next();

			if (input.compareTo("student") == 0) {

			}

			if (username.equals(Username) && password.equals(Password)) {
				System.out.println("You are now logged in " + Username);
				displayMenu(username, input, scanner);
			} else {
				System.out.println("Invalid Username and Password" + Username + Password);
			}
		}
	}

	private void displayMenu(String username, String user, Scanner scanner) {
		String input = " ";

		while (user.compareTo("student") == 0) {
			System.out.println("*******************************************");
			System.out.println("Press 1 to Enter Project Preferences");
			System.out.println("Press 2 to Blacklist Members");
			System.out.println("Press 3 to Specify Preferred Roles");
			System.out.println("Press 4 to exit");
			System.out.println("*******************************************");

			input = scanner.nextLine();

			switch (input) {
			case "1":
				ArrayList<String> projects = (ArrayList<String>) system.getAllProjectDescs();
				ArrayList<String> preferences = new ArrayList<String>();
				
				for(int i = 0; i < projects.size(); i++) {
					System.out.println(i + " " + projects.get(i));
				}
				
				System.out.println("Please enter your first preference");
				preferences.add(projects.get(scanner.nextInt()-1));
				
				System.out.println("Please enter your second preference");
				preferences.add(projects.get(scanner.nextInt()-1));
				
				System.out.println("Please enter your third preference");
				preferences.add(projects.get(scanner.nextInt()-1));
				
				System.out.println("Please enter your fourth preference");
				preferences.add(projects.get(scanner.nextInt()-1));
				system.setPreferences(username, preferences);
				
				break;
			case "2":
				
				System.out.println("Please enter the student number of the student you would like to blacklist:");
				
				system.addToBlacklist(username, scanner.nextLine());
				break;
			case "3":

				break;
			case "4":
				user = "exit";
				break;
			default:
				System.out.println("Please enter a valid input");
				break;
			}
		}

		while (user.compareTo("client") == 0) {
			System.out.println("*******************************************");
			System.out.println("Press 1 to create a new Project");
			System.out.println("Press 2 to exit");
			System.out.println("*******************************************");

			input = scanner.nextLine();

			switch (input) {
			case "1":
				System.out.println("Please enter a project description:");
				String desc = scanner.nextLine();

				system.addProject(desc, promptRoles(scanner));
				break;
			case "2":
				user = "exit";
				break;
			default:
				System.out.println("Please enter a valid input");
				break;
			}
		}

		while (user.compareTo("project manager") == 0) {
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
				Collection<String> descs = system.getAllProjectDescs();
				descs.forEach(System.out::println);
				break;
			case "2":
				Collection<String> popularDescs = system.getPopularProjectDescs();
				popularDescs.forEach(System.out::println);
				break;

			case "3":
				// System.out.println(system.getUnPopularProjects());
				System.out.println("Display All Unpopular projects");
				break;
			case "4":
				System.out.println("Delete all unpopular projects");
				break;
			case "5":
				user = "exit";
				break;
			default:
				System.out.println("Please enter a valid input");
				break;
			}
		}

	}

	private Collection<RoleRequirement> promptRoles(Scanner scanner) {
		ArrayList<RoleRequirement> roleRequirements = new ArrayList<RoleRequirement>();
		ArrayList<Skill> skills = new ArrayList<Skill>();
		int response = 0;
		Role[] roles = Role.values();
		int roleIndex;
		Skill[] skillsValues = Skill.values();
		int skillIndex;
		Role role = null;

		while (response != -1) {
			System.out.println("Please choose a role to add:");
			for (roleIndex = 0; roleIndex < roles.length; roleIndex++) {
				System.out.println((roleIndex + 1) + ". " + roles[roleIndex]);
			}
			System.out.println((roleIndex + 2) + ". No more roles to add");
			response = scanner.nextInt();
			if (response == roleIndex + 2) {
				response = -1;
			}else if (response > roles.length || response <= 0) {
				System.out.println("Please enter a valid option");
				response = -2;
			} else {
				role = roles[response];
			}

			while (response > 0) {
				System.out.println("Please choose the skills that are relevant to this role:");
				for (skillIndex = 0; skillIndex < skillsValues.length; skillIndex++) {
					System.out.println((skillIndex + 1) + ". " + skillsValues[skillIndex]);
				}
				System.out.println((skillIndex + 2) + ". No more skills to add");
				response = scanner.nextInt();

				if (response == skillIndex + 2) {
					response = -2;
				} else if (response > skillsValues.length || response <= 0) {
					System.out.println("Please enter a valid option");
				} else if (skills.contains(skillsValues[response - 1])) {
					System.out.println("Skill already added");
				} else {
					skills.add(skillsValues[response - 1]);
				}
			}
			roleRequirements.add(new RoleRequirement(role, skills));
		}

		return roleRequirements;
	}
}
