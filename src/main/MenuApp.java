package main;

import java.util.ArrayList;
import java.util.Scanner;

import main.Menu.Menu;
import main.Menu.MenuItem;
import main.Menu.Promotion;

import main.IO.*;

/**
 * Menu Application to process Menu operations
 * @author Maoters
 *
 */
public class MenuApp implements RW {

    /**
     * List of menus containing menu items and promotions that will be displayed to
     * customers
     */
    private static ArrayList<Menu> menus = new ArrayList<>();
    /**
     * List of normal menu items that may not currently be on sale, but serves as a
     * "storage" for menu items and their details
     */
    private ArrayList<MenuItem> normalCatalog = new ArrayList<>();
    /**
     * List of promotions that may not currently be on sale, but serves as a
     * "storage" for promotions and their details
     */
    private ArrayList<Promotion> promoCatalog = new ArrayList<>();

    private final String menuFileName = "menus";
    private final String normalFileName = "normalCatalog";
    private final String promoFileName = "promoCatalog";

    /**
     * Contains control logic that enables users to print, add, update, or remove
     * menu items from the normal menu item catalog
     */
    public void getNormalCatalogFunctions() {
        int userChoice = 0;
        do {
            System.out.println("Would you like to print/add/update/remove menu items in the catalog?");
            System.out.println("(1) Print\n" + "(2) Add\n" + "(3) Update\n" + "(4) Remove\n" + "Enter -1 to exit.");
            userChoice = ErrorApp.safeInteger();
            switch (userChoice) {
            case 1:
                this.printNormalCatalog();
                break;
            case 2:
                this.addToNormalCatalog();
                break;
            case 3:
                this.updateNormalCatalog();
                break;
            case 4:
                this.removeFromNormalCatalog();
                break;
            default:
                break;
            }
        } while (userChoice != -1);
    }

    /**
     * Contains control logic that enables users to print, add, update, or remove
     * menu items from the promotion catalog
     */
    public void getPromoCatalogFunctions() {
        int userChoice = 0;
        do {
            System.out.println("Would you like to print/add/update/remove promotions in the catalog?");
            System.out.println("(1) Print\n" + "(2) Add\n" + "(3) Update\n" + "(4) Remove\n" + "Enter -1 to exit.");
            userChoice = ErrorApp.safeInteger();
            switch (userChoice) {
            case 1:
                this.printPromoCatalog();
                break;
            case 2:
                this.addToPromoCatalog();
                break;
            case 3:
                this.updatePromoCatalog();
                break;
            case 4:
                this.removeFromPromoCatalog();
                break;
            default:
                break;
            }
        } while (userChoice != -1);
    }

    /**
     * Contains control logic to enable users to print, add, update, and remove
     * menus
     */
    public void getMenuFunctions() {
        this.write();
        int userChoice = 0;
        do {
            System.out.println("Would you like to print/add/update/remove menus?");
            System.out.println("(1) Print Existing Menu\n" + "(2) Add New Menu\n" + "(3) Update Existing Menu\n"
                    + "(4) Remove Existing Menu\n" + "Enter -1 to exit.");
            userChoice = ErrorApp.safeInteger();
            switch (userChoice) {
            case 1:
                MenuApp.printMenus();
                if (menus.size() > 0) {
                    System.out.println("Which menu would you like to view?");
                    int menuChoice = ErrorApp.safeInteger() - 1;
                    if (menuChoice >= 0 && menuChoice < menus.size())
                        menus.get(menuChoice).printItems();
                    else
                        System.out.println("Invalid input.");
                }
                break;
            case 2:
                this.addNewMenu();
                break;
            case 3:
                this.updateMenu();
                break;
            case 4:
                this.removeMenu();
                break;
            default:
                break;
            }
        } while (userChoice != -1);
    }

    /**
     * Prints the names of all menus
     */
    public static void printMenus() {
        if (menus.size() > 0) {
            System.out.println("Current Menus:");
            for (int i = 0; i < menus.size(); i++) {
                System.out.printf("Menu No. %d: %s\n", i + 1, menus.get(i).getMenuName());
            }
        } else
            System.out.println("There are no existing menus.");
    }

    /**
     * Allows user to create a new menu
     */
    private void addNewMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("What is the name of your new menu?");
        String name = scan.nextLine();
        Menu newMenu = new Menu(name);
        int choice = 0;
        if (normalCatalog.size() > 0) {
            do {
                System.out.println("Menu Item Catalog:");
                this.printNormalCatalog();
                System.out.printf(
                        "Which items from the menu item catalog would you like to add into your new menu, %s? Enter -1 to finish.\n",
                        name);
                choice = ErrorApp.safeInteger() - 1;
                if (choice >= 0 && choice < normalCatalog.size()) {
                    newMenu.addItem(normalCatalog.get(choice));
                }
                System.out.println("This is what your new menu currently looks like.");
                newMenu.printItems();
                System.out.println();
            } while (choice >= 0);
        } else
            System.out.println("Menu Item Catalog is empty, please add items to catalog.");
        choice = 0;
        if (promoCatalog.size() > 0) {
            do {
                System.out.println("Promotion Catalog:");
                this.printPromoCatalog();
                System.out.printf(
                        "Which items from the promotion catalog would you like to add into your new menu, %s? Enter -1 to finish.\n",
                        name);
                choice = ErrorApp.safeInteger() - 1;
                if (choice >= 0 && choice < promoCatalog.size()) {
                    newMenu.addItem((MenuItem) promoCatalog.get(choice));
                }
                System.out.println("This is what your new menu currently looks like.");
                newMenu.printItems();
            } while (choice >= 0);
        } else
            System.out.println("Promotion Catalog is empty, please add items to catalog.");
        menus.add(newMenu);
        MenuApp.printMenus();
    }

    /**
     * Allows users to update and edit existing menus
     */
    private void updateMenu() {
        if (menus.size() > 0) {
            MenuApp.printMenus();
            System.out.println("Which menu would you like to update? Enter Menu No.");
            int menuChoice = ErrorApp.safeInteger() - 1;
            if (menuChoice >= 0 && menuChoice < menus.size()) {
                menus.get(menuChoice).printItems();
                System.out.println("Would you like to add/update/remove menu items in this menu?");
                System.out.println("(1) Add Menu Items\n" + "(2) Update Menu Items\n" + "(3) Remove Menu Items\n"
                        + "Enter -1 to exit.");
                int option = ErrorApp.safeInteger();
                switch (option) {
                case 1:
                    // add menu items to menu
                    int itemChoice = 0;
                    if (normalCatalog.size() > 0) {
                        do {
                            System.out.println("Menu Item Catalog:");
                            this.printNormalCatalog();
                            System.out.println(
                                    "Which items from the menu item catalog would you like to add into this menu? Enter -1 to finish.");
                            itemChoice = ErrorApp.safeInteger() - 1;
                            if (itemChoice >= 0 && itemChoice < normalCatalog.size()) {
                                menus.get(menuChoice).addItem(normalCatalog.get(itemChoice));
                            }
                            System.out.println("This is what your menu currently looks like.");
                            menus.get(menuChoice).printItems();
                            System.out.println();
                        } while (itemChoice >= 0);
                    } else
                        System.out.println("Menu Item Catalog is empty, please add items to catalog.");
                    itemChoice = 0;
                    if (promoCatalog.size() > 0) {
                        do {
                            System.out.println("Promotion Catalog:");
                            this.printPromoCatalog();
                            System.out.println(
                                    "Which items from the promotion catalog would you like to add into this menu? Enter -1 to finish.");
                            itemChoice = ErrorApp.safeInteger() - 1;
                            if (itemChoice >= 0 && itemChoice < promoCatalog.size()) {
                                menus.get(menuChoice).addItem((MenuItem) promoCatalog.get(itemChoice));
                            }
                            System.out.println("This is what your new menu currently looks like.");
                            menus.get(menuChoice).printItems();
                        } while (itemChoice >= 0);
                    } else
                        System.out.println("Promotion Catalog is empty, please add items to catalog.");
                    break;
                case 2:
                    // update
                    menus.get(menuChoice).updateItem();
                    break;
                case 3:
                    // remove from menu
                    menus.get(menuChoice).printItems();
                    System.out.println("Which item do you want to remove from this menu?");
                    int removeChoice = ErrorApp.safeInteger() - 1;
                    if (removeChoice >= 0 && removeChoice < menus.get(menuChoice).getMenuSize())
                        menus.get(menuChoice).removeItem(removeChoice);
                    System.out.println("This is what your menu looks like now.");
                    menus.get(menuChoice).printItems();
                    break;
                default:
                    break;
                }
            }
            MenuApp.printMenus();
        } else
            System.out.println("There are no existing menus.");
    }

    /**
     * Allows users to remove existing menus
     */
    private void removeMenu() {
        if (menus.size() > 0) {
            Scanner scan = new Scanner(System.in);
            MenuApp.printMenus();
            System.out.println("Which menu would you like to remove? Enter Menu No.");
            int userChoice = ErrorApp.safeInteger() - 1;
            if (userChoice >= 0 && userChoice < menus.size())
                menus.remove(userChoice);
            MenuApp.printMenus();
        } else
            System.out.println("There are no existing menus.");

    }

    /**
     * Prints all menu items within the menu item catalog
     */
    public void printNormalCatalog() {
        if (normalCatalog.size() == 0)
            System.out.println("Menu Item Catalog is empty.");
        else {
            for (int i = 0; i < normalCatalog.size(); i++) {
                System.out.printf("Catalog Item No. %d -> ", i + 1);
                normalCatalog.get(i).printInfo();
            }
        }
    }

    /**
     * Enables users to add new menu items into the menu item catalog
     */
    private void addToNormalCatalog() {
        Scanner scan = new Scanner(System.in);
        System.out.println("What is the name of your new menu item?");
        String name = scan.nextLine();
        System.out.println("What is the price of your new menu item?");
        double price = ErrorApp.safeDouble();
        System.out.println("What is the type of your new menu item?");
        String type = scan.nextLine();
        System.out.println("What is the description of your new menu item?");
        String description = scan.nextLine();
        normalCatalog.add(new MenuItem(name, price, type, description));
        System.out.println("Updated Menu Item Catalog:");
        this.printNormalCatalog();
    }

    /**
     * Enables users to update the details of menu items within the menu item
     * catalog
     */
    private void updateNormalCatalog() {
        Scanner scan = new Scanner(System.in);
        this.printNormalCatalog();
        System.out.println("Which menu item do you want to update? Enter Item No to select. Enter 0 to cancel.");
        int choice = ErrorApp.safeInteger() - 1;
        if (choice >= 0 && choice < normalCatalog.size()) {
            int normalChoice = 0;
            do {
                normalCatalog.get(choice).printInfo();
                System.out.println("Which part of this menu item would you like to update?");
                System.out.println("(1) Name\n" + "(2) Price\n" + "(3) Type\n" + "(4) Description\n"
                        + "Enter -1 to stop updating");
                normalChoice = ErrorApp.safeInteger();
                switch (normalChoice) {
                case 1:
                    System.out.println("What would you want to change this item's name to?");
                    String inputName = scan.nextLine();
                    normalCatalog.get(choice).setName(inputName);
                    System.out.printf("Successfully changed, item's new name is: %s\n",
                            normalCatalog.get(choice).getName());
                    break;
                case 2:
                    System.out.println("What would you want to change this item's price to?");
                    double inputPrice = ErrorApp.safeDouble();
                    normalCatalog.get(choice).setPrice(inputPrice);
                    System.out.printf("Successfully changed, item's new price is: %f\n",
                            normalCatalog.get(choice).getPrice());
                    break;
                case 3:
                    System.out.println("What would you want to change this item's type to?");
                    String inputType = scan.nextLine();
                    normalCatalog.get(choice).setType(inputType);
                    System.out.printf("Successfully changed, item's new type is: %s\n",
                            normalCatalog.get(choice).getType());
                    break;
                case 4:
                    System.out.println("What would you want to change this item's description to?");
                    String inputDesc = scan.nextLine();
                    normalCatalog.get(choice).setDescription(inputDesc);
                    System.out.printf("Successfully changed, item's new description is: %s\n",
                            normalCatalog.get(choice).getDescription());
                    break;
                default:
                    break;
                }
            } while (normalChoice != -1);
            System.out.println("Updated Menu Item Catalog:");
            this.printNormalCatalog();
        }
    }

    /**
     * Allows users to remove menu items from the menu item catalog
     */
    private void removeFromNormalCatalog() {
        if (normalCatalog.size() > 0) {
            Scanner scan = new Scanner(System.in);
            this.printNormalCatalog();
            System.out.println("Which menu item do you want to remove? Enter Item No to remove. Enter 0 to cancel.");
            int choice = ErrorApp.safeInteger() - 1;
            if (choice >= 0 && choice < normalCatalog.size()) {
                normalCatalog.remove(choice);
            }
            System.out.println("Updated Menu Item Catalog:");
            this.printNormalCatalog();
        } else
            System.out.println("Menu Item Catalog is empty.");
    }

    /**
     * Prints all promotions within the promotion catalog
     */
    public void printPromoCatalog() {
        if (promoCatalog.size() == 0)
            System.out.println("Promo Item Catalog is empty.");
        else {
            for (int i = 0; i < promoCatalog.size(); i++) {
                System.out.printf("Catalog Item No. %d -> ", i + 1);
                promoCatalog.get(i).printInfo();
            }
        }
    }

    /**
     * Allows users to add new promotions into the promotion catalog
     */
    private void addToPromoCatalog() {
        Scanner scan = new Scanner(System.in);
        System.out.println("What is the name of your new promo item?");
        String name = scan.nextLine();
        System.out.println("What is the price of your new promo item?");
        double price = ErrorApp.safeDouble();
        System.out.println("What is the type of your new promo item?");
        String type = scan.nextLine();
        System.out.println("What is the description of your new promo item?");
        String description = scan.nextLine();
        System.out.println("How many items are included in your promo item?");
        int numItems = ErrorApp.safeInteger();
        Promotion promo = new Promotion(name, price, type, description);
        for (int i = 0; i < numItems; i++) {
            System.out.printf("What is the name of item number %d in this promo?\n", i + 1);
            promo.addItem(scan.nextLine());
        }
        promoCatalog.add(promo);
        System.out.println("Updated Promo Catalog:");
        this.printPromoCatalog();
    }

    /**
     * Allows users to update the details of promotions in the promotion catalog
     */
    private void updatePromoCatalog() {
        Scanner scan = new Scanner(System.in);
        this.printPromoCatalog();
        System.out.println("Which promo item do you want to update? Enter Item No to select. Enter -1 to cancel.");
        int choice = ErrorApp.safeInteger() - 1;
        if (choice >= 0 && choice < promoCatalog.size()) {
            int normalChoice = 0;
            do {
                promoCatalog.get(choice).printInfo();
                System.out.println("Which part of this promotion would you like to update?");
                System.out.println("(1) Name\n" + "(2) Price\n" + "(3) Type\n" + "(4) Description\n"
                        + "(5) Included Items\n" + "Enter -1 to stop updating");
                normalChoice = ErrorApp.safeInteger();
                switch (normalChoice) {
                case 1:
                    System.out.println("What would you want to change this promo's name to?");
                    String inputName = scan.nextLine();
                    promoCatalog.get(choice).setName(inputName);
                    System.out.printf("Successfully changed, promo's new name is: %s\n",
                            promoCatalog.get(choice).getName());
                    break;
                case 2:
                    System.out.println("What would you want to change this promo's price to?");
                    double inputPrice = ErrorApp.safeDouble();
                    promoCatalog.get(choice).setPrice(inputPrice);
                    System.out.printf("Successfully changed, promo's new price is: %f\n",
                            promoCatalog.get(choice).getPrice());
                    break;
                case 3:
                    System.out.println("What would you want to change this promo's type to?");
                    String inputType = scan.nextLine();
                    promoCatalog.get(choice).setType(inputType);
                    System.out.printf("Successfully changed, promo's new type is: %s\n",
                            promoCatalog.get(choice).getType());
                    break;
                case 4:
                    System.out.println("What would you want to change this promo's description to?");
                    String inputDesc = scan.nextLine();
                    promoCatalog.get(choice).setDescription(inputDesc);
                    System.out.printf("Successfully changed, promo's new description is: %s\n",
                            promoCatalog.get(choice).getDescription());
                    break;
                case 5:
                    int itemChoice = 0;
                    do {
                        System.out.println("Would you like to add or remove items from this promo? Enter -1 to exit.");
                        System.out.println("(1) Add\n" + "(2) Remove");
                        itemChoice = ErrorApp.safeInteger();
                        switch (itemChoice) {
                        case 1:
                            System.out.println("What item would you like to add to this promo?");
                            String name = scan.nextLine();
                            promoCatalog.get(choice).addItem(name);
                            System.out.println("Updated promo item list:");
                            promoCatalog.get(choice).printItems();
                            break;
                        case 2:
                            if (promoCatalog.get(choice).itemNum() > 0) {
                                System.out.println("What item would you like to remove from this promo?");
                                promoCatalog.get(choice).printItems();
                                int removeChoice = ErrorApp.safeInteger() - 1;
                                if (removeChoice >= 0 && removeChoice < promoCatalog.get(choice).itemNum()) {
                                    promoCatalog.get(choice).removeItem(removeChoice);
                                }
                                System.out.println("Updated promo item list:");
                                promoCatalog.get(choice).printItems();
                            } else
                                System.out.println("There are no items in this promo.");
                            break;
                        default:
                            System.out.println("All changes to promo item list completed.");
                            break;
                        }
                    } while (itemChoice != -1);
                    break;
                default:
                    break;
                }
            } while (normalChoice != -1);
            System.out.println("Updated Promo Catalog:");
            this.printPromoCatalog();
        }
    }

    /**
     * Allows users to remove promotions from the promotion catalog
     */
    private void removeFromPromoCatalog() {
        if (promoCatalog.size() > 0) {
            Scanner scan = new Scanner(System.in);
            this.printPromoCatalog();
            System.out.println("Which promo item do you want to remove? Enter Item No to remove. Enter -1 to cancel.");
            int choice = ErrorApp.safeInteger() - 1;
            if (choice >= 0 && choice < promoCatalog.size()) {
                promoCatalog.remove(choice);
            }
            System.out.println("Updated Promo Catalog:");
            this.printPromoCatalog();
        } else
            System.out.println("Promo Catalog is empty.");
    }

    /**
     * Allows viewing of menus when adding menu items to orders
     * 
     * @return int
     */
    public static int chooseMenu() {
        Scanner sc = new Scanner(System.in);
        MenuApp.printMenus();
        if (menus.size() > 0) {
            System.out.println("Which menu would you like to view? (-1 to go back)");
            int menuChoice = ErrorApp.safeInteger() - 1;
            if (menuChoice >= 0 && menuChoice < menus.size()) {
                menus.get(menuChoice).printItems();
                return menuChoice;
            } else if (menuChoice >= menus.size()) {
                System.out.println("Invalid input.");
                return -1;
            }
        }
        return -1;
    }

    /**
     * Given a menu choice and item choice, return the desired MenuItem object
     * 
     * @param menuChoice array index of menu selected
     * @param itemChoice array index of item selected based on menu
     * @return itemToFind
     */
    public static MenuItem findItem(int menuChoice, int itemChoice) {
        MenuItem itemToFind = null;
        Menu inThisMenu = menus.get(menuChoice);
        itemToFind = inThisMenu.findItem(itemChoice);
        return itemToFind;
    }

    /**
     * Overwrites backup .txt files with current menus, normal catalog, and promo
     * catalog
     */
    @Override
    public void write() {
        // STORING PROMO CATALOG
        IO.setFileName(promoFileName);
        IO.setWriter();
        for (int i = 0; i < promoCatalog.size(); i++) {
            Promotion castedPromoItem = promoCatalog.get(i);
            IO.write(castedPromoItem.getName() + "\n");
            IO.write(castedPromoItem.getPrice() + "\n");
            IO.write(castedPromoItem.getType() + "\n");
            IO.write(castedPromoItem.getDescription() + "\n");
            ArrayList<String> promoItems = castedPromoItem.getPromoItems();
            for (int k = 0; k < promoItems.size(); k++) {
                IO.write(promoItems.get(k) + "\n");
            }
            IO.write("-----\n");
        }
        IO.closeWriter();

        // STORING NORMAL CATALOG
        IO.setFileName(normalFileName);
        IO.setWriter();
        for (int i = 0; i < normalCatalog.size(); i++) {
            IO.write(normalCatalog.get(i).getName() + "\n");
            IO.write(normalCatalog.get(i).getPrice() + "\n");
            IO.write(normalCatalog.get(i).getType() + "\n");
            IO.write(normalCatalog.get(i).getDescription() + "\n");
            IO.write("-----\n");
        }
        IO.closeWriter();

        // STORING MENUS
        IO.setFileName(menuFileName);
        IO.setWriter();
        for (int i = 0; i < menus.size(); i++) {
            ArrayList<MenuItem> menuItemsIO = menus.get(i).getMenuItems();
            IO.write(menus.get(i).getMenuName() + "\n");
            for (int j = 0; j < menuItemsIO.size(); j++) {
                if (menuItemsIO.get(j) instanceof Promotion) {
                    Promotion castedPromoItem = (Promotion) menuItemsIO.get(j);
                    IO.write("[P]\n");
                    IO.write(castedPromoItem.getName() + "\n");
                    IO.write(castedPromoItem.getPrice() + "\n");
                    IO.write(castedPromoItem.getType() + "\n");
                    IO.write(castedPromoItem.getDescription() + "\n");
                    ArrayList<String> promoItems = castedPromoItem.getPromoItems();
                    for (int k = 0; k < promoItems.size(); k++) {
                        IO.write(promoItems.get(k) + "\n");
                    }
                    IO.write("-----\n");
                } else {
                    IO.write("[M]\n");
                    IO.write(menuItemsIO.get(j).getName() + "\n");
                    IO.write(menuItemsIO.get(j).getPrice() + "\n");
                    IO.write(menuItemsIO.get(j).getType() + "\n");
                    IO.write(menuItemsIO.get(j).getDescription() + "\n");
                    IO.write("-----\n");
                }
            }
            IO.write("==========\n");
        }
        IO.closeWriter();
    }

    /**
     * Reads in data from .txt files and restores menus, normal catalog, and promo
     * catalog from backup
     */
    @Override
    public void read() {
        // READING PROMO CATALOG
        IO.setFileName(promoFileName);
        IO.setReader();
        if (IO.checkFileExist()) {
            System.out.println("Promotion Catalog backup file exists, restoring now.");
            IO.readLine();
            while (IO.getLine() != null) {
                String itemName = IO.getLine();
                IO.readLine();
                double itemPrice = Double.parseDouble(IO.getLine());
                IO.readLine();
                String itemType = IO.getLine();
                IO.readLine();
                String itemDesc = IO.getLine();
                IO.readLine();
                ArrayList<String> promoItems = new ArrayList<String>();
                while (!IO.getLine().equals("-----")) {
                    promoItems.add(IO.getLine());
                    IO.readLine();
                }
                promoCatalog.add(new Promotion(itemName, itemPrice, itemType, itemDesc, promoItems));
                IO.readLine(); // get next line after -----
            }
            IO.closeReader();
        }

        // READING NORMAL CATALOG
        IO.setFileName(normalFileName);
        IO.setReader();
        if (IO.checkFileExist()) {
            System.out.println("Menu Item Catalog backup file exists, restoring now.");
            IO.readLine();
            while (IO.getLine() != null) {
                String itemName = IO.getLine();
                IO.readLine();
                double itemPrice = Double.parseDouble(IO.getLine());
                IO.readLine();
                String itemType = IO.getLine();
                IO.readLine();
                String itemDesc = IO.getLine();
                normalCatalog.add(new MenuItem(itemName, itemPrice, itemType, itemDesc));
                IO.readLine(); // get in -----
                IO.readLine();
            }
            IO.closeReader();
        }

        // READING MENUS
        IO.setFileName(menuFileName);
        IO.setReader();
        if (IO.checkFileExist()) {
            System.out.println("Menu backup file exists, restoring now.");
            do // check for menus
            {
                Menu newMenu;
                IO.readLine();
                if (IO.getLine() != null) // if menu name exists
                {
                    String menuName = IO.getLine(); // store menu name
                    newMenu = new Menu(menuName);
                    do {
                        IO.readLine(); // take in first menu item type
                        if (!IO.getLine().equals("==========")) // if menu item exists
                        {
                            String menuItemOrPromo = IO.getLine(); // checks M or P
                            IO.readLine(); // read in menu item name
                            String itemName = IO.getLine(); // store menu item name
                            IO.readLine();
                            double itemPrice = Double.parseDouble(IO.getLine());
                            IO.readLine();
                            String itemType = IO.getLine();
                            IO.readLine();
                            String itemDesc = IO.getLine();
                            if (menuItemOrPromo.equals("[P]")) {
                                IO.readLine();
                                ArrayList<String> items = new ArrayList<String>();
                                while (!IO.getLine().equals("-----")) {
                                    items.add(IO.getLine());
                                    IO.readLine();
                                }
                                newMenu.addItem(
                                        (MenuItem) new Promotion(itemName, itemPrice, itemType, itemDesc, items));
                            } else {
                                IO.readLine(); // skip -----
                                newMenu.addItem(new MenuItem(itemName, itemPrice, itemType, itemDesc));
                            }
                        } else
                            break;
                    } while (true);
                    menus.add(newMenu);
                }
            } while (IO.getLine() != null);
            IO.closeReader();
        }
    }
}
