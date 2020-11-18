package model;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import program.EquipmentListController;
import program.JobListController;
import program.SceneChanger;

import java.io.IOException;

public class Job {

    private String Type, Address, Date, Status;

    private int JobID, ContractorID;

    private Button Detail;

    public Job(int jobID, String type, String address, String date, int contractorID) {
        JobID = jobID;
        Type = type;
        Address = address;
        Date = date;
        ContractorID = contractorID;
    }

    public Job(int jobID, String type, String address, String date, int contractorID, String status) {
        Type = type;
        Address = address;
        Date = date;
        Status = status;
        JobID = jobID;
        ContractorID = contractorID;
        this.Detail = new Button("Info");
        this.Detail.setOnAction(actionEvent -> {
            //EquipmentListController equipmentListController = new EquipmentListController();
            //equipmentListController.SetID(this.JobID,this.ContractorID);
            try {
                FXMLLoader loader = SceneChanger.GetLoaderOnAction(JobListController.class, "EquipmentList.fxml");
                SceneChanger.ChangeSceneOnAction(Detail, JobListController.class, "EquipmentList.fxml", loader);
                EquipmentListController equipmentListController = loader.getController();
                equipmentListController.SetID(jobID,contractorID);
                //Parent loader = FXMLLoader.load(JobListController.class.getResource("EquipmentList.fxml"));
                //Scene scene = new Scene(loader);
                //Stage stage = (Stage) Detail.getScene().getWindow();
                //stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public String getType() {
        return Type;
    }

    public String getAddress() {
        return Address;
    }

    public String getDate() {
        return Date;
    }

    public int getJobID() {
        return JobID;
    }

    public int getContractorID() {
        return ContractorID;
    }

    public Button getDetail() {
        return Detail;
    }
}
