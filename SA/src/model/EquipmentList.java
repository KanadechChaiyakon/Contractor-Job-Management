package model;

public class EquipmentList {

    private int equipmentlist_id, job_id, total_cost;

    public EquipmentList(int equipmentlist_id, int job_id, int total_cost) {
        this.equipmentlist_id = equipmentlist_id;
        this.job_id = job_id;
        this.total_cost = total_cost;
    }

    public int getEquipmentlist_id() {
        return equipmentlist_id;
    }

    public int getJob_id() {
        return job_id;
    }

    public int getTotal_cost() {
        return total_cost;
    }
}
