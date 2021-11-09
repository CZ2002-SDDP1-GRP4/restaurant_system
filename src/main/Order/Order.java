package main.Order;

import java.util.ArrayList;

import main.Menu.MenuItem;
import main.Staff.Staff;

public class Order {
	private int table_number;
	private Staff staff;
	private ArrayList<MenuItem> items;
	
    public Order(int table_number, Staff staff){
        this.table_number = table_number;
        this.staff = staff;
    }
    
}
