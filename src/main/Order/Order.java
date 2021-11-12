package main.Order;

import java.util.ArrayList;
import main.ErrorApp;
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
        setOrderItems(new ArrayList<MenuItem>());
    }
    
    public void printShortOrder() {
    	System.out.printf("Table " + table_number + ": ");
    	if (getOrderItems().size() == 0) {
    		System.out.println("no items ordered");
    	} else {
    		for (MenuItem item : getOrderItems()) {
    			System.out.printf(item.getName() + ", ");
    		}
    		System.out.printf("\n");
    	}
    }
    
    public void printDetailedOrder() {
    	System.out.println
    	(
    		"Order for TABLE: " + table_number + "\n" +
    		"Served by STAFF: " + staff.toString() + "\n" +
    		"Ordered ITEMS: "
    	);
    	int i = 1;
    	if (getOrderItems() == null || getOrderItems().size() == 0) {
    		System.out.println("Nothing yet");
    	} else {
        	for (MenuItem item : getOrderItems()) {
        		System.out.printf(i++ + ": ");
        		item.printInfo();
        	}
    	}
    }
    
	public void addItem() {
		MenuItem itemToAdd = null;
		
		int menuChoice = MenuApp.chooseMenu();
		if (menuChoice == -1) return;
		
		int itemChoice = ErrorApp.safeInteger();
		if (itemChoice == -1) return;
		
		itemToAdd = MenuApp.findItem(menuChoice, itemChoice-1);
		if (itemToAdd == null) return;
		else {
			System.out.println("Added to order: item " + itemToAdd.getName());
			getOrderItems().add(itemToAdd);
		}
		
	}

	public void removeItem() {
		this.printDetailedOrder();
		int choice = -1;
		System.out.println("Which item do you want to remove?");
		choice = ErrorApp.safeInteger();
		if (1 <= choice && choice <= getOrderItems().size())
		{
			choice--;
			getOrderItems().remove(choice);
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

	public ArrayList<MenuItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(ArrayList<MenuItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	public int getOrderTable() {
		return table_number;
	}

	public Staff getOrderStaff() {
		return staff;
	}
}
