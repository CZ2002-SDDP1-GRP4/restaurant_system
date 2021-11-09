package main.Order;

import java.util.ArrayList;
import java.util.Scanner;

import main.MenuApp;
import main.Menu.MenuItem;
import main.Staff.Staff;

public class Order {
	private int table_number;
	private Staff staff;
	private ArrayList<MenuItem> orderItems;
	
    public Order(int table_number, Staff staff) {
        this.table_number = table_number;
        this.staff = staff;
        orderItems = new ArrayList<MenuItem>();
    }
    
    public void printOrderShort() {
    	System.out.printf("Table " + table_number + ": ");
    	if (orderItems.size() == 0) {
    		System.out.println("no items ordered");
    	} else {
    		for (MenuItem item : orderItems) {
    			System.out.printf(item.getName() + ", ");
    		}
    		System.out.printf("\n");
    	}
    }
    
    public void printOrder() {
    	System.out.println
    	(
    		"Order for TABLE: " + table_number + "\n" +
    		"Served by STAFF: " + staff.toString() + "\n" +
    		"Ordered ITEMS: "
    	);
    	int i = 1;
    	if (orderItems == null) {
    		System.out.println("	" + "Nothing yet");
    	} else {
        	for (MenuItem item : orderItems) {
        		System.out.printf("	" + i++ + ": ");
        		item.printInfo();
        	}
    	}
    }
    
	public void addItem() {
		MenuItem itemToAdd = null;
		
		int menuChoice = MenuApp.chooseMenu();
		if (menuChoice == -1) return;
		
		Scanner sc = new Scanner(System.in);
		int itemChoice = sc.nextInt();
		sc.nextLine(); //throw away the \n not consumed by nextInt()
		if (itemChoice == -1) return;
		
		itemToAdd = MenuApp.findItem(menuChoice, itemChoice-1);
		if (itemToAdd == null) return;
		else {
			System.out.println("Added to order: item " + itemToAdd.getName());
			orderItems.add(itemToAdd);
		}
		
	}

	public void removeItem() {
		this.printOrder();
		int choice = -1;
		System.out.println("Which item do you want to remove?");
		Scanner sc = new Scanner(System.in);
		choice = sc.nextInt();
		sc.nextLine(); //throw away the \n not consumed by nextInt()
		if (1 <= choice && choice <= orderItems.size())
		{
			choice--;
			orderItems.remove(choice);
			System.out.println("Item removed.");
		} else if (choice == -1)
		{
			return;
		}
		else
		{
			System.out.println("Invalid selection");
		}
	}
}
