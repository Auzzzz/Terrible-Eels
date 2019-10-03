package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUIMenu {

	public static class GUI_Menu {
		@SuppressWarnings("null")
		public GUI_Menu(){
			JFrame frame = new JFrame("Group Managment System - Students");
			JButton button,button1, button2, button3,button4;
			button = new JButton("Add Student");
			button1 = new JButton("Add Student to Group");
			button2 = new JButton("button 3");
			button3 = new JButton("button 4");
			button4 = new JButton("button 5");
			frame.add(button);
			frame.add(button1);
			frame.add(button2);
			frame.add(button3);
			frame.add(button4);
			frame.setLayout(new FlowLayout());
			frame.setSize(300,300);  
			frame.setVisible(true);  
	
			//add Student
			button.addActionListener(new ActionListener()
			    {
			      public void actionPerformed(ActionEvent e)
			      {
			    	  JFrame f=new JFrame("Add Student"); 
						//submit button
			JButton stuaddsubmit=new JButton("Submit");    
			stuaddsubmit.setBounds(100,130,140, 40);    
						//enter name label
			JLabel label = new JLabel();		
			label.setText("Enter Name :");
			label.setBounds(10, 10, 100, 100);
						//empty label which will show event after button clicked
			JLabel label1 = new JLabel();
			label1.setBounds(10, 110, 200, 100);
						//textfield to enter name
			JTextField textfieldname= new JTextField();
			textfieldname.setBounds(110, 50, 130, 30);
			//textfield to enter name
			JTextField textfieldgend= new JTextField();
			textfieldgend.setBounds(110, 90, 130, 30);
						//add to frame
			f.add(label1);
			f.add(textfieldname);
			f.add(label);
			f.add(textfieldgend);
			f.add(stuaddsubmit);    
			f.setSize(300,300);    
			f.setLayout(null);    
			f.setVisible(true);    
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
			
								//action listener
			stuaddsubmit.addActionListener(new ActionListener() {
		        
				@Override
				public void actionPerformed(ActionEvent arg0) {
						label1.setText("Name has been submitted.");				
				}          
		      });
			}
			      }

		);
			
			//add student to group
			button1.addActionListener(new ActionListener()
		    {

		public void actionPerformed(ActionEvent e) {
			// display/center the jdialog when the button is pressed
			JDialog d = new JDialog(frame, "Hello", true);
			d.setLocationRelativeTo(frame);
			d.setVisible(true);
		}

	});

}

}

}