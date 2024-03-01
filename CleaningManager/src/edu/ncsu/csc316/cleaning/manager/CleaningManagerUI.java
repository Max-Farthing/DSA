package edu.ncsu.csc316.cleaning.manager;

import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.cleaning.dsa.DataStructure;


/**
 * This class will serve as the user interface which will interact with users and provide
 * them information about the program upon their request. The UI in this program will be
 * through the terminal
 * 
 * @author Max Farthing
 */
public class CleaningManagerUI {
	
	/**
	 * Main method where users will interact with program via terminal
	 * @param args array of strings containing file information
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Input room log file: ");
		String roomList = scan.next();
		System.out.print("Input cleaning log file: ");
		String cleaningLog = scan.next();

		ReportManager report = null;
		try {
			
			report = new ReportManager(roomList, cleaningLog);
			
		} catch(FileNotFoundException e) {
			System.out.println("Error opening input files");
			System.exit(1);
		}
		
		
		String option = "";
		while(!"Q".equalsIgnoreCase(option)) {
			System.out.println();
			displayMenu();
			
			option = scan.next();
			System.out.println();
			
			if("F".equalsIgnoreCase(option)) {
				
				System.out.print("Number of rooms: ");
				int n = scan.nextInt();
				String view = report.getFrequencyReport(n);
				System.out.println();
				System.out.println(view);
				
			} else if("R".equalsIgnoreCase(option)) {
				
				String view = report.getRoomReport();
				System.out.println(view);
				
			} else if("V".equalsIgnoreCase(option)) {
				
				System.out.print("Enter time stamp(MM/DD/YYYY HH:MM:SS): ");
				String time = scan.next();
				String time2 = scan.next();
				String view = report.getVacuumBagReport(time + " " + time2);
				System.out.println();
				System.out.println(view);
				
			} else if("Q".equalsIgnoreCase(option)) {
				System.out.println("Program Terminating");
				System.exit(0);
			} else {
				System.out.println("Invalid command");
			}
		}

		scan.close();
		
	}
	
	/**
	 * static helper method to help display menu options
	 */
	public static void displayMenu() {
		System.out.println("Cleaning Manager Program - please choose an option: ");
		System.out.println();
		System.out.println("F - View most frequently cleaned rooms");
		System.out.println("R - View a room report by cleanings");
		System.out.println("V - View estimated remaining vacuum bag life");
		System.out.println("Q - quit program");
		System.out.println();
		System.out.print("Option: ");
	}
}
