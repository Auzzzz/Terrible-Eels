package driver;

import java.awt.EventQueue;
import javax.swing.SwingUtilities;

import SQL.SQLConnectionImpl;
import interfaces.ProjectTeamsFormationSystem;
import interfaces.SQLConnection;
import model.teamFormation.ProjectTeamsFormationSystemImpl;
import view.GUIMenu;
import view.GUIMenu.GUI_Menu;
import view.GUIMenu_Login;
import view.Menu;

public class Driver {

	public static void main(String[] args) {
		
		//Menu menu = new Menu();
		//menu.run();
		
	    //-----------------------\\
       //	    DONT USE ME!!!    \\
      //---------------------------\\

		//new GUI_Menu();
		//new GUIMenu_Login();
		
		SQLConnection SQL = new SQLConnectionImpl();
		ProjectTeamsFormationSystem system = new ProjectTeamsFormationSystemImpl(SQL);
		Menu menu = new Menu(system);
		menu.run();

	}

}
