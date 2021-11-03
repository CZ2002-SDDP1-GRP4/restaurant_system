package main.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<MenuItem> menuItems = new ArrayList<>();
    private String menuType;

    public void Menu(String menuType) {
        this.menuType = menuType;
    }

    public ArrayList<MenuItem> getItems() {
        return this.menuItems;
    }

    public void addItem(MenuItem item) {
        MenuItem copy = new MenuItem(item.getName(), item.getDescription(), item.getPrice(), item.getType());
        menuItems.add(copy);
    }

    public void removeItem() {
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.printf("Item No. %d: %s", i, menuItems.get(i));
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Which item would you like to remove from this menu? Please enter its Item No.");
        int userInput = scan.nextInt();
        if (userInput < menuItems.size()) {
            menuItems.remove(userInput);
        } else {
            System.out.println("Invalid Item No.");
        }
    }
}
