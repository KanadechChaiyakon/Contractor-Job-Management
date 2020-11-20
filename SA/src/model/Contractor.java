package model;

public class Contractor {

    private int ID;

    private String name, username, password, email, phone_number;


    public Contractor(int ID, String name, String username, String password, String email, String phone_number) {
        this.ID = ID;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
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

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }
}
