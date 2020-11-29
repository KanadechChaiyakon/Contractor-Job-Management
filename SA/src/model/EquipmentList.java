package model;

public class EquipmentList {

    private int equipment_id, job_id, quantity, amount;

    private String detail, equipment_name;

    public EquipmentList(int equipment_id, int job_id, int quantity, int amount, String detail) {
        this.equipment_id = equipment_id;
        this.job_id = job_id;
        this.quantity = quantity;
        this.amount = amount;
        this.detail = detail;
    }

    public EquipmentList(int quantity, int amount, String detail, String equipment_name) {
        this.quantity = quantity;
        this.amount = amount;
        this.detail = detail;
        this.equipment_name = equipment_name;
    }

    public int getEquipment_id() {
        return equipment_id;
    }

    public int getJob_id() {
        return job_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getAmount() {
        return amount;
    }

    public String getDetail() {
        return detail;
    }

    public String getEquipment_name() {
        return equipment_name;
    }
}
