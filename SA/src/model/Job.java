package model;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import program.*;

import java.io.IOException;

public class Job {

    private String Type, Address, Date, Status, ContractorName;

    private int JobID, ContractorID, Budget;

    private Button Detail;

    private Contractor contractor;

    private Corporation corporation;

    public Job(int jobID, String type, String address, String date, String status, int Budget, int contractorID) {
        JobID = jobID;
        Type = type;
        Address = address;
        Date = date;
        ContractorID = contractorID;
        Status = status;
        this.Budget = Budget;
    }

    public Job(int jobID, String type, String address, String date, int contractorID, String status, int Budget, Contractor contractor) {
        Type = type;
        Address = address;
        Date = date;
        Status = status;
        JobID = jobID;
        ContractorID = contractorID;
        this.Budget = Budget;
        this.contractor = contractor;
        this.Detail = new Button("Info");
        this.Detail.setOnAction(actionEvent -> {
            //EquipmentListController equipmentListController = new EquipmentListController();
            //equipmentListController.SetID(this.JobID,this.ContractorID);
            try {
                FXMLLoader loader = SceneChanger.GetLoaderOnAction(JobListController.class, "JobDetail.fxml");
                SceneChanger.ChangeSceneOnAction(Detail, JobListController.class, "JobDetail.fxml", loader);
                JobDetailController jobDetailController = loader.getController();
                jobDetailController.setJobID(jobID);
                jobDetailController.setContractor(contractor);
                //Parent loader = FXMLLoader.load(JobListController.class.getResource("EquipmentList.fxml"));
                //Scene scene = new Scene(loader);
                //Stage stage = (Stage) Detail.getScene().getWindow();
                //stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public Job(int jobID, String type, String address, String date, int contractorID, String status, int Budget, Corporation corporation) {
        Type = type;
        Address = address;
        Date = date;
        Status = status;
        JobID = jobID;
        ContractorID = contractorID;
        this.Budget = Budget;
        this.corporation = corporation;
        this.Detail = new Button("Info");
        this.Detail.setOnAction(actionEvent -> {
            //EquipmentListController equipmentListController = new EquipmentListController();
            //equipmentListController.SetID(this.JobID,this.ContractorID);
            try {
                FXMLLoader loader = SceneChanger.GetLoaderOnAction(JobOfferController.class, "JobDetail.fxml");
                SceneChanger.ChangeSceneOnAction(Detail, JobOfferController.class, "JobDetail.fxml", loader);
                JobDetailController jobDetailController = loader.getController();
                jobDetailController.setJobID(jobID);
                jobDetailController.setCorporation(corporation);
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

    public String getStatus() {
        return Status;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public int getBudget() {
        return Budget;
    }
}
