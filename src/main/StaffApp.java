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
        choice = ErrorApp.safeInteger();
        
        switch(choice) {
        case 1:{
        	
        	System.out.print("Please enter the ID number of staff:\n");
        	int id1 = ErrorApp.safeInteger();
        	
        	Staff NULL = null;
        	
			while(getStaffbyId(id1) != NULL) {
        		System.out.println("Employee ID taken!");	
        		System.out.printf("These are the invalid IDs: ");
        		for(Staff Staff1: staffs) {
        				System.out.printf("%d ",Staff1.getStaffId());}
        		System.out.printf("\n");
        		System.out.println("Please enter another ID:"); 
        		id1 = ErrorApp.safeInteger();	
        	};
        	int id = id1;
        	
        	System.out.print("Please enter the name of staff:\n");
        	String name = ErrorApp.alphaString();

        	System.out.print("Please enter the gender of staff: 1.Male 2.Female 3.Others\n");
        	
        	int sex = ErrorApp.safeInteger();
        	
        	while(sex > 3 || sex <0) {
        		System.out.print("Please enter a valid option of 1/2/3 : 1.Male 2.Female 3.Others\n");
        		sex = ErrorApp.safeInteger();
        	}
        	    	
        	System.out.print("Please enter the title of the staff:\n");
        	String title = ErrorApp.alphaString();   
    
        	staffs.add(new Staff(name,id,title,sex));
        	System.out.println("Staff has been added!");
        	break;
        }
        case 2:{
        	for(Staff Staff: staffs) {
        		System.out.printf("Employee No: %d\n", Staff.getStaffId());
        		Staff.printDetails();
        		System.out.printf("\n");
        	}
        	break;
        }}
        }while(choice != -1);

    }
	
	//// COMMENT bleow 8/11 
	// i use this for OrderApp --> CreateOrder
	public static int getStaffbyId() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your staff ID (-1 to go back): ");
		int staffId = ErrorApp.safeInteger();
		
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
