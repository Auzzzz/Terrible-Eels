package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {

	 public static void main  (String[] args) throws IOException {
	        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	        System.out.println("Hit 1 to Add Studnet to DB ");
	        System.out.println("Hit 2 to Add Student to a Team ");
	        System.out.println("Hit 3 to Change a students Personality");

	        int buttonCode = bufferRead.read();
	        //Button Codes for 1, 2 and 3 keys are 49, 50 and 51 respectively

	        
	        if (buttonCode==49) {
	        	System.out.println("Add stuent to Database");
	        } else if(buttonCode==50) {
	        	System.out.println("Add Student to a Team");
	        } else if (buttonCode==51) {
	        	System.out.println("Change Student Personality");
	        } else {
	            System.out.println("Wrong button pressed");
	        }
	    }
}
