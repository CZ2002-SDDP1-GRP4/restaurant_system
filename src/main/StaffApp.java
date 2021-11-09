package main;

import java.util.ArrayList;
import java.util.*; 
import java.util.Scanner;

import main.Staff.Staff;


public class StaffApp {
	
	// COMMENT bleow i changed this to static btw
	private static ArrayList<Staff> staffs;
	
	public StaffApp() {
		
		staffs = new ArrayList<Staff>();
	}

	public void createStaff() {
	Scanner sc = new Scanner(System.in);
	int choice = -1;
	
	   
    do{
        System.out.println(
            "Available Functions:\n"+
            "1. Input Staff Details\n"+
            "2. Print details of all employees\n"+
            "Cancel (-1)\n"+
            "Enter choice:"
        );
        choice = sc.nextInt();
        sc.nextLine(); //throw away the \n not consumed by nextInt()
        
        
        switch(choice) {
        case 1:
        	String name,title;
        	int sex,id;
        	System.out.print("Please enter the name of staff:\n");
        	name = sc.nextLine();

        	System.out.print("Please enter the gender of staff: 1.Male 2.Female 3.Others\n");
        	
        	// REFACTOR ErrorApp?
        	sex = sc.nextInt();
        	sc.nextLine(); //throw away the \n not consumed by nextInt()
        	
        	if(sex > 3 || sex <0) {
        		System.out.print("Please enter a valid option of 1/2/3 : 1.Male 2.Female 3.Others\n");
        	    sex = sc.nextInt();
        	    sc.nextLine(); //throw away the \n not consumed by nextInt()
        	}
        	
        	System.out.print("Please enter the ID number of staff:\n");
        	id = sc.nextInt();
        	sc.nextLine(); //throw away the \n not consumed by nextInt()
        	
        	System.out.print("Please enter the title of the staff:\n");
        	title = sc.nextLine();    
    
        	staffs.add(new Staff(name,id,title,sex));
        	System.out.println("Staff has been added!");
        	break;
        case 2:
        	for(Staff Staff: staffs) {
        		Staff.printDetails();
        	}
        }
        
        
    } while(choice != -1);
	}

	
	//// COMMENT bleow 8/11 
	// i use this for OrderApp --> CreateOrder
	public static int getStaffbyId() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your staff ID (-1 to go back): ");
		int staffId = sc.nextInt();
		sc.nextLine(); //throw away the \n not consumed by nextInt()
		for (Staff staff : staffs) {
			if (staffId == staff.getStaffId())
			{
				return staffId;
			}
		}	
		System.out.println("No such staff found. Did you enter your staff ID correctly?");
		return -1;
	}
	
	public static Staff getStaffbyId(int staffId) {
		for (Staff staff : staffs) {
			if (staffId == staff.getStaffId())
			{
				return staff;
			}
		}
		return null;
	}

	
    
}
