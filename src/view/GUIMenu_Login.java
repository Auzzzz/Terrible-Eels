package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUIMenu_Login extends JFrame implements ActionListener {

	JLabel lheader, luser, lpass;
	JTextField tf1;
	JButton btn1;
	JPasswordField p1;

	public GUIMenu_Login() {
		JFrame frame = new JFrame("Login Form");
		lheader = new JLabel("Login Form");

		luser = new JLabel("Username");
		lpass = new JLabel("Password");
		tf1 = new JTextField();
		p1 = new JPasswordField();
		btn1 = new JButton("Login");

		lheader.setBounds(100, 30, 400, 30);
		luser.setBounds(80, 70, 200, 30);
		lpass.setBounds(80, 110, 200, 30);
		tf1.setBounds(300, 70, 200, 30);
		p1.setBounds(300, 110, 200, 30);
		btn1.setBounds(150, 160, 100, 30);

		frame.add(lheader);
		frame.add(luser);
		frame.add(tf1);
		frame.add(lpass);
		frame.add(p1);
		frame.add(btn1);

		frame.setSize(400, 400);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		String uname = tf1.getText();
		String pass = String.valueOf(p1.getPassword());
		JOptionPane.showInputDialog("Hi");
		if (uname.equals("aaa") && pass.equals("aaa")) {
			JOptionPane.showMessageDialog(this, "Correct login or password", "Error", JOptionPane.ERROR_MESSAGE);
			new GUIMenu();

		} else {
			JOptionPane.showMessageDialog(this, "Incorrect login or password", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}

//	public void actionlogin() {
//
//		blogin.addActionListener(new ActionListener() {
//			@SuppressWarnings("unlikely-arg-type")
//			public void actionPerformed(ActionEvent ae) {
//					//String iusername = txtUser.getText().trim();
//					//String ipassword = txtPass.getText().trim();
//				if (username.equals(" ")|| password.equals(" ")) {
//		JOptionPane.showMessageDialog(null," name or password or Role is wrong","Error",JOptionPane.ERROR_MESSAGE);
//				} else {
//					//System.out.println(iusername + " " + ipassword);
//					System.out.println(username);
//				//SQLconnteciton
//					
//				}
//
//			}
//		});
//	}}
