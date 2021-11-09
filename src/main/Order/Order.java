package main.Order;

import java.util.ArrayList;
import java.util.Scanner;

import main.MenuApp;
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
    
    public void printOrderShort() {
    	System.out.printf("Table " + table_number + ": ");
    	if (items == null) {
    		System.out.println("nil");
    	} else {
    		for (MenuItem item : items) {
    			System.out.println(item.getName());
    			System.out.println(", ");
    		}
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
    	if (items == null) {
    		System.out.println("	" + "Nothing yet");
    	} else {
        	for (MenuItem item : items) {
        		System.out.println("	" + i++ + ": ");
        		item.printInfo();
        	}
    	}
    }
    
	public void addItem() {
		MenuItem itemToAdd = null;
		
		int menuChoice;
		int itemChoice;
		Scanner sc = new Scanner(System.in);
		int stage = 1;
		while (true) {
			if (stage == 0) //exit
			{
				return;
			}
			if (stage == 1) //print menus, choose menu, print menu OR go back
			{
				menuChoice = MenuApp.chooseMenu();
				if (menuChoice == -1) continue;
				if (menuChoice == -2) stage--;
				else stage++;
			}
			if (stage == 2) //choose menuitem OR go back
			{
				itemChoice = sc.nextInt();
				sc.nextLine(); //throw away the \n not consumed by nextInt()
				if (itemChoice == -1) stage--;
				
				itemToAdd = MenuApp.getItemFromMenuApp();
			}
		}
		
		/*
		System.out.println( "(1) Add menu item\n" +
							"(2) Add promo item");
		Scanner sc = new Scanner(System.in);
		int choice = -1;
		do
		{
			choice = sc.nextInt();
			sc.nextLine(); //throw away the \n not consumed by nextInt()
			if (choice == 1)
			{
				MenuApp.printNormalCatalog();
			} else if (choice == 2)
			{
				MenuApp.printPromoCatalog();
			} else if (choice == -1) return;
			else
			{
				System.out.println("Invalid selection. Try again?");
			}
		} while (choice != -1 && choice != 1 && choice != 2);
		
		System.out.println("Select item number to add: ");
		int choice1 = -1;
		while (true)
		{
			choice1 = sc.nextInt();
			if (choice == 1 && choice1 <= MenuApp.getNormalCatalogSize())
			{
				items.add(MenuApp.getNormalItem(choice1-1));
				System.out.println("Normal item added.");
				break;
			}
			if (choice == 2 && choice1 <= MenuApp.getPromoCatalogSize())
			{
				items.add(MenuApp.getPromoItem(choice1-1));
				System.out.println("Promo item added.");
				break;
			}
			if (choice1 == -1) return;
			else {
				System.out.println("Invalid selection. Try again?");
			}
		}
		*/
	}

	public void removeItem() {
		
	}
}
