package main.Menu;

import java.util.Scanner;
import java.util.ArrayList;

public class Promotion extends MenuItem {
    private ArrayList<String> items = new ArrayList<>();

    public Promotion(String name, String description, double price, String type) {
        super(name, description, price, type);
    }

    public ArrayList<String> getItems() {
        return this.items;
    }

    public void addItem(String name) {
        items.add(name);
    }

    public void removeItem() {
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("Item No. %d: %s", i, items.get(i));
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Which item would you like to remove from this promotion? Please enter its Item No.");
        int userInput = scan.nextInt();
        if (userInput < items.size()) {
            items.remove(userInput);
        } else {
            System.out.println("Invalid Item No.");
        }
    }
}
