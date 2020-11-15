package model;

public class Equipment {

    private int ID;

    private String name, price;

    public Equipment(int ID, String name, String price) {
        this.ID = ID;
        this.name = name;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
