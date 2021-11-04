package main;

import java.util.ArrayList;
import java.util.Scanner;

import main.Menu.Menu;
import main.Menu.MenuItem;
import main.Menu.Promotion;

public class MenuApp {
    private ArrayList<Menu> menus = new ArrayList<>();
    private ArrayList<MenuItem> normalCatalog = new ArrayList<>();
    private ArrayList<Promotion> promoCatalog = new ArrayList<>();

    public MenuApp() {
        // empty constructor
    }

    public void getItemCatalogFunctions() {
        int userChoice = 0;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Would you like to add/update/remove menu items in the catalog?");
            System.out.println("(1) Add\n" + "(2) Update\n" + "(3) Remove\n" + "Enter -1 to exit.");
            userChoice = scan.nextInt();
            switch (userChoice) {
            case 1:
                // add to menu item catalog
                break;
            case 2:
                // update menu item in catalog
                break;
            case 3:
                // remove menu item from catalog
                break;
            default:
                break;
            }
        } while (userChoice != -1);
    }

    public void getPromoCatalogFunctions() {

    }

    public void getMenuFunctions() {

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
