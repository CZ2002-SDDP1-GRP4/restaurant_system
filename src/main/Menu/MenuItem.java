package main.Menu;

import java.lang.Math;

/**
 * Represents a menu item that can be stored in the catalog or in menus.
 */
public class MenuItem {
    /** Name of menu item */
    private String name;
    /** Description of menu item */
    private String description;
    /** Price of menu item */
    private double price;
    /** Type of menu item */
    private String type;

    /**
     * Public constructor for MenuItem class
     * 
     * @param name
     * @param price
     * @param type
     * @param description
     */
    public MenuItem(String name, double price, String type, String description) {
        this.name = name;
        this.price = Math.round(price * 100.0) / 100.0;
        this.type = type;
        this.description = description;
    }

    /**
     * Get name of menu item
     * 
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get description of menu item
     * 
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get price of menu item
     * 
     * @return price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Get type of menu item
     * 
     * @return type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Prints a summary of the menu item's name, price, type, and description
     */
    public void printInfo() {
        System.out.printf("Name: %s, Price: %.2f, Type: %s, Description: %s\n", this.name, this.price, this.type,
                this.description);
    }

    /**
     * Sets name of menu item with String parameter
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets description of menu item with String parameter
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets price of menu item with double parameter
     * 
     * @param price
     */
    public void setPrice(double price) {
        this.price = Math.round(price * 100.0) / 100.0;
    }

    /**
     * Sets type of menu item with String parameter
     * 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
}
