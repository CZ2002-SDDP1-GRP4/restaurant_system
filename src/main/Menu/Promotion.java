package main.Menu;

import java.util.Scanner;
import java.util.ArrayList;

public class Promotion extends MenuItem {
    private ArrayList<String> items = new ArrayList<>();

    public Promotion(String name, String description, double price, String type) {
        super(name, description, price, type);
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.print(", Items included: ");
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("%s", items.get(i));
            if (i < items.size() - 1)
                System.out.print(",");
        }
    }

    public void printItems() {
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("Item No. %d: %s\n", i + 1, items.get(i));
        }
    }

    public void addItem(String name) {
        items.add(name);
    }

    public void updateItem() {
        // print items
        // choose individual items to change
    }

    public void removeItem(int index) {
        if (index < items.size() && index >= 0) {
            items.remove(index);
        } else {
            System.out.println("Invalid Item No.");
        }
    }
}
