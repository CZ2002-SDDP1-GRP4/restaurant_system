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

    public void getNormalCatalogFunctions() {
        int userChoice = 0;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Would you like to add/update/remove menu items in the catalog?");
            System.out.println("(1) Add\n" + "(2) Update\n" + "(3) Remove\n" + "Enter -1 to exit.");
            userChoice = scan.nextInt();
            switch (userChoice) {
            case 1:
                this.addToNormalCatalog();
                break;
            case 2:
                this.updateNormalCatalog();
                break;
            case 3:
                this.removeFromNormalCatalog();
                break;
            default:
                break;
            }
        } while (userChoice != -1);
        scan.close();
    }

    public void getPromoCatalogFunctions() {

    }

    public void getMenuFunctions() {

    }

    private void addToNormalCatalog() {
        int userChoice = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("What is the name of your new menu item?");
    }

    private void updateNormalCatalog() {

    }

    private void removeFromNormalCatalog() {

    }
}
