package main.Order;

import java.util.ArrayList;
import main.ErrorApp;
import main.MenuApp;
import main.Menu.MenuItem;
import main.Staff.Staff;

/**
 * A class to define attributes and methods specific to a single order.
 * @author Bryan
 * @version 1.0
 */
public class Order {
	/**
	 * The table number of an order. Each order will be assigned to only one table.
	 */
	private int table_number;
	/**
	 * The staff serving the number. Each order will be assigned to only one staff.
	 */
	private Staff staff;
	/**
	 * An array list of MenuItems that the person has ordered.
	 */
	private ArrayList<MenuItem> orderItems;
	
	/**
	 * Pulic constructor to initialise an Order.
	 * @param Integer table_number
	 * @param Staff staff
	 * {@link Order#setOrderItems()}
	 */
    public Order(int table_number, Staff staff) {
        this.table_number = table_number;
        this.staff = staff;
        setOrderItems(new ArrayList<MenuItem>());
    }
    
    /**
     * Method to print the details of a single Order, in summarised form.
     * {@link Order#getOrderItems()}
     * {@link MenuItem#getName()}
     */
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
    
    /**
     * Method to print the details of a single Order, in detailed form.
     * {@link Staff#toString()}
     * {@link Order#getOrderItems()}
     * {@link MenuItem#printInfo()}
     */
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
    
    /**
     * Method to add a menu item to a single Order
     * {@link MenuApp#chooseMenu()}
     * {@link MenuApp#findItem()}
     * {@link MenuItem#getName()}
     */
	public void addItem() {
		MenuItem itemToAdd = null;
		
		int menuChoice = MenuApp.chooseMenu();
		if (menuChoice == -1) return;
		
		int itemChoice = ErrorApp.safeInteger();
		if (itemChoice == -1) return;
		
		itemToAdd = MenuApp.findItem(menuChoice, itemChoice-1);
		if (itemToAdd == null) return;
		else {
			System.out.println("Added to order: " + itemToAdd.getName());
			getOrderItems().add(itemToAdd);
		}
		
	}

	/**
	 * Method to remove an item from a single order
	 * {@link Order#printDetailedOrder()}
	 * {@link MenuItem#getOrderItems()}
	 */
	public void removeItem() {
		this.printDetailedOrder();
		if (this.getOrderItems().size() == 0)
		{
			System.out.println("Cannot remove anything from order");
			return;
		}
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

	/**
	 * Getter method for the array list of menu items
	 * @return the array list of menu items
	 */
	public ArrayList<MenuItem> getOrderItems() {
		return orderItems;
	}

	/**
	 * Setter method for the array list of menu items
	 * @param the array list of menu items
	 */
	public void setOrderItems(ArrayList<MenuItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	/**
	 * Getter method for the table number associated with the order.
	 * @return integer table_number
	 */
	public int getOrderTable() {
		return table_number;
	}

	/**
	 * Getter method for the staff associated with the order.
	 * @return Staff object
	 */
	public Staff getOrderStaff() {
		return staff;
	}
}
