package model;

public class Equipment {

    private int equipment_id, price;

    private String equipmentname;

    public Equipment(int equipment_id, int price, String equipmentname) {
        this.equipment_id = equipment_id;
        this.price = price;
        this.equipmentname = equipmentname;
    }

    public int getEquipment_id() {
        return equipment_id;
    }

    public int getPrice() {
        return price;
    }

    public String getEquipmentname() {
        return equipmentname;
    }
}
