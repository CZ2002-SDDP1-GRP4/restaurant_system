package main.Menu;

public class MenuItem {
    private String name;
    private String description;
    private double price;
    private String type;

    public MenuItem(String name, double price, String type, String description) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
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

    public void printInfo() {
        System.out.printf("Name: %s, Price: %f, Type: %s, Description: %s\n", this.name, this.price, this.type,
                this.description);
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
