package main.Menu;

public class Promotion extends MenuItem {
    String[] items = new String[30];
    int numOfItems;

    public Promotion(String name, String description, double price, String type, int numOfItems) {
        super(name, description, price, type);
        this.numOfItems = numOfItems;
    }

    public String[] getItems() {
        return this.items;
    }

    public void addItem(MenuItem item) {
        items[numOfItems] = item.getName();
        numOfItems++;
    }

    public void removeItem() {

    }
}
