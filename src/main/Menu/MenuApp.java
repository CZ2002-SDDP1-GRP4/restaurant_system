package main.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuApp {
    private ArrayList<Menu> menus = new ArrayList<>();
    private ArrayList<MenuItem> normalCatalog = new ArrayList<>();
    private ArrayList<Promotion> promoCatalog = new ArrayList<>();

    public MenuApp() {
        // empty constructor
    }

    public void getItemFunctions() {
        int userChoice = 0;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Would you like to add/update/remove menu items in the catalog or menus?");
            System.out.println("(1) Catalog\n" + "(2) Menus\n" + "Enter -1 to exit.");
            userChoice = scan.nextInt();
            switch (userChoice) {
            case 1:
                // catalog editing
            case 2:
                // menu editing
            }
        } while (userChoice != -1);
    }

    public void getPromoFunctions() {

    }

    public void createMenu(String name) {

    }

    public void removeMenu() {
        // UI
    }

    public void addToCatalogs() {

    }

    public void removeFromCatalogs() {

    }

    public void updateCatalogs() {

    }

    public void addToMenu() {

    }

    public void removeFromMenu() {

    }
}
