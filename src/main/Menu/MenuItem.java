package main.Menu;

public class MenuItem {
    private String name;
    private String description;
    private double price;
    private String type;

    public MenuItem(String name, String description, double price, String type) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public double getPrice() {
        return this.price;
    }

    public String getType() {
        return this.type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }
}
