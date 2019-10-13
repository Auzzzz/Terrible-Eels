package driver;


import SQL.SQLConnection;
import interfaces.ProjectTeamsFormationSystem;
import interfaces.DataStorage;
import model.teamFormation.ProjectTeamsFormationSystemImpl;
import view.GUIMenu.GUI_Menu;
import view.GUIMenu_Login;
import view.Menu;

public class Driver {

	public static void main(String[] args) {
		
		
	    //-----------------------\\
       //	    DONT USE ME!!!    \\
      //---------------------------\\

		//new GUI_Menu();
		//new GUIMenu_Login();
		
		DataStorage SQL = new SQLConnection();
		ProjectTeamsFormationSystem system = new ProjectTeamsFormationSystemImpl(SQL);
		Menu menu = new Menu(system);
		menu.run();

	}

}
