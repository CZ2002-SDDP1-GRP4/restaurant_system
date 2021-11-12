package main.Menu;

import java.util.ArrayList;

/**
 * Represents a special type of menu item called a Promotion, which is a set
 * package of menu items at a single price
 */
public class Promotion extends MenuItem {
    /** List of menu items within this set package promotion */
    private ArrayList<String> items = new ArrayList<>();

    /**
     * Public constructor for Promotion class
     * 
     * @param name
     * @param price
     * @param type
     * @param description
     */
    public Promotion(String name, double price, String type, String description) {
        super(name, price, type, description);
    }

    public Promotion(String name, double price, String type, String description, ArrayList<String> items) {
        super(name, price, type, description);
        this.items = items;
    }

    /**
     * Prints a summary of the menu item's name, price, type, and description
     * (superclass MenuItem functionality). Also prints a list of items contained
     * within this set package (overridden functionality)
     */
    @Override
    public void printInfo() {
        super.printInfo();
        System.out.print("  Items included: ");
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("%s", items.get(i));
            if (i < items.size() - 1)
                System.out.print(", ");
        }
        System.out.println();
    }

    /**
     * Prints just the list of items contained within this set package
     */
    public void printItems() {
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("Item No. %d: %s\n", i + 1, items.get(i));
        }
    }

    /**
     * Returns number of items within this set package
     * 
     * @return size
     */
    public int itemNum() {
        return this.items.size();
    }

    /**
     * Adds a new menu item into this set package by its name contained within a
     * String parameter
     * 
     * @param name
     */
    public void addItem(String name) {
        items.add(name);
    }

    /**
     * Removes a specific item within this set package by index
     * 
     * @param index
     */
    public void removeItem(int index) {
        if (index < items.size() && index >= 0) {
            items.remove(index);
        } else {
            System.out.println("Invalid Item No.");
        }
    }

    /**
     * Returns the ArrayList of String objects that represent items within this set
     * package
     * 
     * @return items
     */
    public ArrayList<String> getPromoItems() {
        return this.items;
    }
}
