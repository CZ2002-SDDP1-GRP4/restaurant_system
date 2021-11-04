package main.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<MenuItem> menuItems = new ArrayList<>();
    private String menuName;

    public Menu(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    private void printItems() {
        if (menuItems.size() == 0)
            System.out.println("Menu is empty.");
        else {
            for (int i = 0; i < menuItems.size(); i++) {
                System.out.printf("Item No. %d -> ", i + 1);
                menuItems.get(i).printInfo();
                System.out.println();
            }
        }
    }

    public void addItem(MenuItem item) {
        MenuItem copy = new MenuItem(item.getName(), item.getPrice(), item.getType(), item.getDescription());
        menuItems.add(copy);
        System.out.println("Updated menu:");
        this.printItems();
    }

    public void updateItem() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Which item in this menu would you like to update? Please enter its Item No.");
        this.printItems();
        int userInput = scan.nextInt() - 1;
        scan.nextLine();
        if (userInput < menuItems.size()) {
            menuItems.get(userInput).printInfo();
            if (menuItems.get(userInput) instanceof Promotion) {
                Promotion castedPromo = (Promotion) menuItems.get(userInput);
                int promoChoice = 0;
                do {
                    castedPromo.printInfo();
                    System.out.println("Which part of this promotion would you like to update?");
                    System.out.println("(1) Name\n" + "(2) Price\n" + "(3) Type\n" + "(4) Description\n"
                            + "(5) Included Items\n" + "Enter -1 to stop updating");
                    promoChoice = scan.nextInt();
                    scan.nextLine();
                    switch (promoChoice) {
                    case 1:
                        System.out.println("What would you want to change this promo's name to?");
                        String inputName = scan.nextLine();
                        castedPromo.setName(inputName);
                        System.out.printf("Successfully changed, promo's new name is: %s\n", castedPromo.getName());
                        break;
                    case 2:
                        System.out.println("What would you want to change this promo's price to?");
                        double inputPrice = scan.nextDouble();
                        scan.nextLine();
                        castedPromo.setPrice(inputPrice);
                        System.out.printf("Successfully changed, promo's new price is: %f\n", castedPromo.getPrice());
                        break;
                    case 3:
                        System.out.println("What would you want to change this promo's type to?");
                        String inputType = scan.nextLine();
                        castedPromo.setType(inputType);
                        System.out.printf("Successfully changed, promo's new type is: %s\n", castedPromo.getType());
                        break;
                    case 4:
                        System.out.println("What would you want to change this promo's description to?");
                        String inputDesc = scan.nextLine();
                        castedPromo.setDescription(inputDesc);
                        System.out.printf("Successfully changed, promo's new description is: %s\n",
                                castedPromo.getDescription());
                        break;
                    case 5:
                        int itemChoice = 0;
                        do {
                            System.out.println(
                                    "Would you like to add or remove items from this promo? Enter -1 to exit.");
                            System.out.println("(1) Add\n" + "(2) Remove\n");
                            itemChoice = scan.nextInt();
                            switch (itemChoice) {
                            case 1:
                                System.out.println("What item would you like to add to this promo?");
                                String name = scan.nextLine();
                                castedPromo.addItem(name);
                                System.out.println("Updated promo item list:");
                                castedPromo.printItems();
                                break;
                            case 2:
                                System.out.println("What item would you like to remove from this promo?");
                                castedPromo.printItems();
                                int removeChoice = scan.nextInt();
                                scan.nextLine();
                                castedPromo.removeItem(removeChoice);
                                System.out.println("Updated promo item list:");
                                castedPromo.printItems();
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
                } while (promoChoice != -1);
            } else {
                int normalChoice = 0;
                do {
                    menuItems.get(userInput).printInfo();
                    System.out.println("Which part of this menu item would you like to update?");
                    System.out.println("(1) Name\n" + "(2) Price\n" + "(3) Type\n" + "(4) Description\n"
                            + "Enter -1 to stop updating");
                    normalChoice = scan.nextInt();
                    scan.nextLine();
                    switch (normalChoice) {
                    case 1:
                        System.out.println("What would you want to change this item's name to?");
                        String inputName = scan.nextLine();
                        menuItems.get(userInput).setName(inputName);
                        System.out.printf("Successfully changed, item's new name is: %s\n",
                                menuItems.get(userInput).getName());
                        break;
                    case 2:
                        System.out.println("What would you want to change this item's price to?");
                        double inputPrice = scan.nextDouble();
                        scan.nextLine();
                        menuItems.get(userInput).setPrice(inputPrice);
                        System.out.printf("Successfully changed, item's new price is: %f\n",
                                menuItems.get(userInput).getPrice());
                        break;
                    case 3:
                        System.out.println("What would you want to change this item's type to?");
                        String inputType = scan.nextLine();
                        menuItems.get(userInput).setType(inputType);
                        System.out.printf("Successfully changed, item's new type is: %s\n",
                                menuItems.get(userInput).getType());
                        break;
                    case 4:
                        System.out.println("What would you want to change this item's description to?");
                        String inputDesc = scan.nextLine();
                        menuItems.get(userInput).setDescription(inputDesc);
                        System.out.printf("Successfully changed, item's new description is: %s\n",
                                menuItems.get(userInput).getDescription());
                        break;
                    default:
                        break;
                    }
                } while (normalChoice != -1);
            }
        }
        scan.close();
    }

    public void removeItem() {
        this.printItems();
        System.out.println("Which item in this menu would you like to remove? Please enter its Item No.");
        Scanner scan = new Scanner(System.in);
        int userInput = scan.nextInt() - 1;
        if (userInput < menuItems.size()) {
            menuItems.remove(userInput);
            System.out.println("Updated menu:");
            this.printItems();
        } else {
            System.out.println("Invalid Item No.");
        }
        scan.close();
    }
}
