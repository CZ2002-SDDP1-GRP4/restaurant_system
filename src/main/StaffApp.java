package main;

import java.util.ArrayList;
import java.util.Scanner;

import main.Staff.Staff;


public class StaffApp {
	
	private ArrayList<Staff> staff;
	
	public StaffApp() {
		
		staff = new ArrayList<Staff>();
	}

	public void createStaff() {
	Scanner sc = new Scanner(System.in);
	int choice = -1;
	
	   
    do{
        System.out.println(
            "Available Functions:\n"+
            "1. Input Staff Details\n"+
            "Cancel (-1)\n"+
            "Enter choice:"
        );
        choice = sc.nextInt();
        
        
        if(choice == 1) {
        	String name,title;
        	int sex,id;
        	System.out.println("Please enter the name of staff:\n");
        	name = sc.nextLine();
        	System.out.println("Please enter the gender of staff: 1.Male 2.Female 3.Others\n");
        	sex = sc.nextInt();
        	
        	if(sex > 3 || sex <0) {
        		System.out.println("Please enter a valid option of 1/2/3 : 1.Male 2.Female 3.Others\n");
        	    sex = sc.nextInt();
        	}
        	
        	System.out.println("Please enter the ID number of staff:\n");
        	id = sc.nextInt();
        	
        	System.out.println("Please enter the title of the staff:\n");
        	title = sc.nextLine();    
    
        	staff.add(new Staff(name,id,title,sex));
        	System.out.println("Staff has been added!");
 
        }
        
        
    }while(choice != -1);
    
   
	}
    
    
}
