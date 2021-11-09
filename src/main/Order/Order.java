package main.Order;

import java.util.ArrayList;

import main.Menu.MenuItem;
import main.Staff.Staff;

public class Order {
	private int table_number;
	private Staff staff;
	private ArrayList<MenuItem> items;
	
    public Order(int table_number, Staff staff) {
        this.table_number = table_number;
        this.staff = staff;
    }
    
    public void printOrder() {
    	System.out.println
    	(
    		"Order for TABLE: " + table_number + "\n" +
    		"Served by STAFF: " + staff.toString() + "\n" +
    		"Ordered ITEMS: "
    	);
    	int i = 1;
    	if (items == null) {
    		System.out.println("	" + "Nothing yet");
    	} else {
        	for (MenuItem item : items) {
        		System.out.println("	" + i++ + ": ");
        		item.printInfo();
        	}
    	}
    }
}
