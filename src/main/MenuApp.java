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
            System.out.println("Would you like to print/add/update/remove menu items in the catalog?");
            System.out.println("(1) Print\n" + "(2) Add\n" + "(3) Update\n" + "(4) Remove\n" + "Enter -1 to exit.");
            userChoice = scan.nextInt();
            scan.nextLine();
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

    public void getPromoCatalogFunctions() {
        int userChoice = 0;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Would you like to print/add/update/remove promotions in the catalog?");
            System.out.println("(1) Print\n" + "(2) Add\n" + "(3) Update\n" + "(4) Remove\n" + "Enter -1 to exit.");
            userChoice = scan.nextInt();
            scan.nextLine();
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

    public void getMenuFunctions() {

    }

    private void printNormalCatalog() {
        if (normalCatalog.size() == 0)
            System.out.println("Menu Item Catalog is empty.");
        else {
            for (int i = 0; i < normalCatalog.size(); i++) {
                System.out.printf("Catalog Item No. %d -> ", i + 1);
                normalCatalog.get(i).printInfo();
            }
        }
    }

    private void addToNormalCatalog() {
        Scanner scan = new Scanner(System.in);
        System.out.println("What is the name of your new menu item?");
        String name = scan.nextLine();
        System.out.println("What is the price of your new menu item?");
        double price = scan.nextDouble();
        scan.nextLine();
        System.out.println("What is the type of your new menu item?");
        String type = scan.nextLine();
        System.out.println("What is the description of your new menu item?");
        String description = scan.nextLine();
        normalCatalog.add(new MenuItem(name, price, type, description));
        System.out.println("Updated Menu Item Catalog:");
        this.printNormalCatalog();
    }

    private void updateNormalCatalog() {
        Scanner scan = new Scanner(System.in);
        this.printNormalCatalog();
        System.out.println("Which menu item do you want to update? Enter Item No to select. Enter 0 to cancel.");
        int choice = scan.nextInt() - 1;
        scan.nextLine();
        if (choice >= 0 && choice < normalCatalog.size()) {
            int normalChoice = 0;
            do {
                normalCatalog.get(choice).printInfo();
                System.out.println("Which part of this menu item would you like to update?");
                System.out.println("(1) Name\n" + "(2) Price\n" + "(3) Type\n" + "(4) Description\n"
                        + "Enter -1 to stop updating");
                normalChoice = scan.nextInt();
                scan.nextLine();
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
                    double inputPrice = scan.nextDouble();
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

    private void removeFromNormalCatalog() {
        if (normalCatalog.size() > 0) {
            Scanner scan = new Scanner(System.in);
            this.printNormalCatalog();
            System.out.println("Which menu item do you want to remove? Enter Item No to remove. Enter 0 to cancel.");
            int choice = scan.nextInt() - 1;
            scan.nextLine();
            if (choice >= 0 && choice < normalCatalog.size()) {
                normalCatalog.remove(choice);
            }
            System.out.println("Updated Menu Item Catalog:");
            this.printNormalCatalog();
        } else
            System.out.println("Menu Item Catalog is empty.");
    }

    private void printPromoCatalog() {
        if (promoCatalog.size() == 0)
            System.out.println("Promo Item Catalog is empty.");
        else {
            for (int i = 0; i < promoCatalog.size(); i++) {
                System.out.printf("Catalog Item No. %d -> ", i + 1);
                promoCatalog.get(i).printInfo();
            }
        }
    }

    private void addToPromoCatalog() {
        Scanner scan = new Scanner(System.in);
        System.out.println("What is the name of your new promo item?");
        String name = scan.nextLine();
        System.out.println("What is the price of your new promo item?");
        double price = scan.nextDouble();
        scan.nextLine();
        System.out.println("What is the type of your new promo item?");
        String type = scan.nextLine();
        System.out.println("What is the description of your new promo item?");
        String description = scan.nextLine();
        System.out.println("How many items are included in your promo item?");
        int numItems = scan.nextInt();
        Promotion promo = new Promotion(name, price, type, description);
        scan.nextLine();
        for (int i = 0; i < numItems; i++) {
            System.out.printf("What is the name of item number %d in this promo?\n", i + 1);
            promo.addItem(scan.nextLine());
        }
        promoCatalog.add(promo);
        System.out.println("Updated Promo Catalog:");
        this.printPromoCatalog();
    }

    private void updatePromoCatalog() {
        Scanner scan = new Scanner(System.in);
        this.printPromoCatalog();
        System.out.println("Which promo item do you want to update? Enter Item No to select. Enter -1 to cancel.");
        int choice = scan.nextInt() - 1;
        scan.nextLine();
        if (choice >= 0 && choice < promoCatalog.size()) {
            int normalChoice = 0;
            do {
                promoCatalog.get(choice).printInfo();
                System.out.println("Which part of this promotion would you like to update?");
                System.out.println("(1) Name\n" + "(2) Price\n" + "(3) Type\n" + "(4) Description\n"
                        + "(5) Included Items\n" + "Enter -1 to stop updating");
                normalChoice = scan.nextInt();
                scan.nextLine();
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
                    double inputPrice = scan.nextDouble();
                    scan.nextLine();
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
                        itemChoice = scan.nextInt();
                        scan.nextLine();
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
                                int removeChoice = scan.nextInt() - 1;
                                scan.nextLine();
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

    private void removeFromPromoCatalog() {
        if (promoCatalog.size() > 0) {
            Scanner scan = new Scanner(System.in);
            this.printPromoCatalog();
            System.out.println("Which promo item do you want to remove? Enter Item No to remove. Enter -1 to cancel.");
            int choice = scan.nextInt() - 1;
            scan.nextLine();
            if (choice >= 0 && choice < promoCatalog.size()) {
                promoCatalog.remove(choice);
            }
            System.out.println("Updated Promo Catalog:");
            this.printPromoCatalog();
        } else
            System.out.println("Promo Catalog is empty.");
    }
}
