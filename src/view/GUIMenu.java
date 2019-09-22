package view;
//-----------------------\\

//    DONT USE ME!!!     \\
//-----------------------\\

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Scanner;
import SQL.SQLConnectionImpl;
import interfaces.ProjectTeamsFormationSystem;
import interfaces.SQLConnection;
import model.teamFormation.ProjectTeamsFormationSystemImpl;


public class GUIMenu extends JFrame {

	public void run() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 409, 243);
		setTitle("ThaiCreate.Com Java GUI Tutorial");
		getContentPane().setLayout(null);

		// Model
		DefaultListModel model = new DefaultListModel();

		Connection connect = ConnectDB();
		Statement s = null;

		try {

			s = connect.createStatement();

			String sql = "SELECT * FROM  student";

			ResultSet rec = s.executeQuery(sql);
			int row = 0;
			while ((rec != null) && (rec.next())) {
				model.addElement(rec.getString("StuID") + " - " + rec.getString("Gender"));
			}

			rec.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}

		try {
			if (s != null) {
				s.close();
				connect.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Label Result
		final JLabel lblResult = new JLabel("Result");
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setBounds(62, 154, 272, 14);
		getContentPane().add(lblResult);

		// Scroll Pane
		JScrollPane scrollPane = new JScrollPane();

		final JList list = new JList(model);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				lblResult.setText(list.getSelectedValue().toString());
			}
		});

		scrollPane.setViewportView(list);
		scrollPane.setBounds(113, 48, 167, 67);
		getContentPane().add(scrollPane);

	}

	// Connection
	private Connection ConnectDB() {
		try {
			Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection("jdbc:sqlite:db/SEF.db");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}

//public class MyForm extends JFrame {
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				run frame = new run();
//				frame.setVisible(true);
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public MyForm() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 409, 243);
//		setTitle("ThaiCreate.Com Java GUI Tutorial");
//		getContentPane().setLayout(null);
//
//		// Model
//		DefaultListModel model = new DefaultListModel();
//
//		Connection connect = ConnectDB();
//		Statement s = null;
//
//		try {
//
//			s = connect.createStatement();
//
//			String sql = "SELECT * FROM  customer ORDER BY CustomerID ASC";
//
//			ResultSet rec = s.executeQuery(sql);
//			int row = 0;
//			while ((rec != null) && (rec.next())) {
//				model.addElement(rec.getString("CustomerID") + " - " + rec.getString("Name"));
//			}
//
//			rec.close();
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			JOptionPane.showMessageDialog(null, e.getMessage());
//			e.printStackTrace();
//		}
//
//		try {
//			if (s != null) {
//				s.close();
//				connect.close();
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// Label Result
//		final JLabel lblResult = new JLabel("Result");
//		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
//		lblResult.setBounds(62, 154, 272, 14);
//		getContentPane().add(lblResult);
//
//		// Scroll Pane
//		JScrollPane scrollPane = new JScrollPane();
//
//		final JList list = new JList(model);
//		list.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent evt) {
//				lblResult.setText(list.getSelectedValue().toString());
//			}
//		});
//
//		scrollPane.setViewportView(list);
//		scrollPane.setBounds(113, 48, 167, 67);
//		getContentPane().add(scrollPane);
//
//	}
//
//	// Connection
//	private Connection ConnectDB() {
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			return DriverManager.getConnection("jdbc:mysql://localhost/mydatabase" + "?user=root&password=root");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//}