package model;

public class Equipment {

    private int equipment_id, price, amount, totalprice, equipmentlistid;

    private String equipmentname, brand, detail;

    public Equipment(int equipment_id, int price, int amount, int totalprice, String equipmentname, String brand, String detail, int equipmentlistid) {
        this.equipment_id = equipment_id;
        this.price = price;
        this.amount = amount;
        this.totalprice = totalprice;
        this.equipmentname = equipmentname;
        this.brand = brand;
        this.detail = detail;
        this.equipmentlistid = equipmentlistid;
    }

    public Equipment(int amount, int totalprice, String equipmentname, String brand, String detail) {
        this.amount = amount;
        this.totalprice = totalprice;
        this.equipmentname = equipmentname;
        this.brand = brand;
        this.detail = detail;
    }

    public int getEquipment_id() {
        return equipment_id;
    }

    public String getEquipmentname() {
        return equipmentname;
    }

    public String getBrand() {
        return brand;
    }

    public String getDetail() {
        return detail;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public int getEquipmentlistid() {
        return equipmentlistid;
    }
}
