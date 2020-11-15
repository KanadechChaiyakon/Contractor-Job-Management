package model;

public class Contractor {

    private int ID;

    private String name, username, password;

    public Contractor(int ID, String name, String username, String passwore) {
        this.ID = ID;
        this.name = name;
        this.username = username;
        this.password = passwore;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
