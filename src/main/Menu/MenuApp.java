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

    public void MenuItemUI() {
        Scanner scan = new Scanner(System.in);
        int userInput = 0;
        do {
            System.out.println("(1) Create Menu Item in Catalog\n" + "(2) Update Menu Item in Catalog\n"
                    + "(3) Delete Menu Item from Catalog\n" + "Or enter -1 to go to the previous menu.");
            userInput = scan.nextInt();
            switch (userInput) {
            case 1:
                this.addToCatalogs();
                break;
            case 2:
            }
        } while (userInput != -1);
    }

    public void PromotionUI() {
        Scanner scan = new Scanner(System.in);
        int userInput = 0;
    }

    public void MenuUI() {

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
