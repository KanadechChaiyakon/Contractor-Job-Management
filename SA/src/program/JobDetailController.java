package program;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class JobDetailController {

    @FXML
    private Label address, date, type, status, budget, equipmentcost, contractorname, email, phone;

    @FXML
    private Button accept, reject, offer, canceloffer, back, startwork, manage;

    @FXML
    private TableView<EquipmentList> equipmenttable;

    @FXML
    private TableColumn<EquipmentList,String> name, detail;

    @FXML
    private TableColumn<EquipmentList, Integer> quantity, amount;

    private ArrayList<Job> jobArrayList;

    private ArrayList<Equipment> equipmentArrayList;

    private ArrayList<EquipmentList> equipmentListArrayList;

    private ArrayList<Contractor> contractorArrayList;

    private String CheckUser;

    private int JobID, TotalAmount;

    private Contractor contractor;

    private Corporation corporation;

    private Job Job;

    public void setJobID(int jobID) {
        JobID = jobID;
    }

    public void setContractor(Contractor contractor) {
        CheckUser = "Contractor";
        this.contractor = contractor;
    }

    public void setCorporation(Corporation corporation) {
        CheckUser = "Corporation";
        this.corporation = corporation;
    }

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                contractorArrayList = DBConnect.ReadContractor();
                jobArrayList = DBConnect.ReadJob();
                equipmentArrayList = DBConnect.ReadEquipment();
                equipmentListArrayList = DBConnect.ReadEquipmentList();

                for(Job job : jobArrayList){
                    if(job.getJobID() == JobID){
                        Job =job;
                        address.setText("ที่อยู่งาน : " + job.getAddress());
                        type.setText("ประเภทงาน : " + job.getType());
                        date.setText("วันที่รับงาน : " + job.getDate());
                        status.setText("สถานะ : " + job.getStatus());
                        budget.setText("งบประมาณ : " + job.getBudget());

                        for(Contractor contractor : contractorArrayList){
                            if (contractor.getID() == job.getContractorID()){
                                contractorname.setText("ผู้รับเหมา : " + contractor.getName());
                                email.setText("email : " + contractor.getEmail());
                                phone.setText("เบอร์โทร : " + contractor.getPhone_number());
                            }
                        }

                    }
                }

                ArrayList<EquipmentList> use_equipment = new ArrayList<>();
                for(EquipmentList equipmentList : equipmentListArrayList){
                    if(equipmentList.getJob_id() == JobID){
                        for(Equipment equipment : equipmentArrayList){
                            if(equipment.getEquipment_id() == equipmentList.getEquipment_id()){
                                use_equipment.add(new EquipmentList(equipmentList.getQuantity(), equipmentList.getAmount(), equipmentList.getDetail(), equipment.getEquipmentname()));
                                TotalAmount += equipmentList.getAmount();
                            }
                        }
                    }
                }

                if(CheckUser.equals("Contractor")){
                    accept.setOpacity(0);
                    reject.setOpacity(0);
                    accept.setDisable(true);
                    reject.setDisable(true);
                }
                else if(CheckUser.equals("Corporation")){
                    offer.setOpacity(0);
                    canceloffer.setOpacity(0);
                    manage.setOpacity(0);
                    startwork.setOpacity(0);
                    offer.setDisable(true);
                    canceloffer.setDisable(true);
                    manage.setDisable(true);
                    startwork.setDisable(true);
                }

                equipmentcost.setText("ค่าอุปกรณ์ : " + TotalAmount);
                ObservableList<EquipmentList> equipmentObservableList = FXCollections.observableArrayList(use_equipment);

                name.setCellValueFactory(new PropertyValueFactory<>("equipment_name"));
                quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
                detail.setCellValueFactory(new PropertyValueFactory<>("detail"));

                equipmenttable.setItems(equipmentObservableList);

            }
        });
    }

    @FXML
    private void offerOnAction(Event event)throws IOException{

        if(Job.getStatus().equals("Rejected") || Job.getStatus().equals("Accepted") || Job.getStatus().equals("Started") || Job.getStatus().equals("Request")){
            if (Job.getStatus().equals("Rejected")){
                Alert alert = new Alert(Alert.AlertType.WARNING,"Job already rejected", ButtonType.OK);
                alert.show();
            }
            else if (Job.getStatus().equals("Accepted")){
                Alert alert = new Alert(Alert.AlertType.WARNING,"Job already Accepted", ButtonType.OK);
                alert.show();
            }
            else if (Job.getStatus().equals("Started")){
                Alert alert = new Alert(Alert.AlertType.WARNING,"Job already started", ButtonType.OK);
                alert.show();
            }
            else if (Job.getStatus().equals("Request")){
                Alert alert = new Alert(Alert.AlertType.WARNING,"Job already requested", ButtonType.OK);
                alert.show();
            }
            return;
        }

        DBConnect.UpdateJobStatus(JobID, "Request");
        if(CheckUser.equals("Contractor")){
            FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(),"JobDetail.fxml");
            SceneChanger.ChangeSceneWithLoaderOnAction(offer,"JobDetail.fxml",loader);
            JobDetailController jobDetailController = loader.getController();
            jobDetailController.setContractor(contractor);
            jobDetailController.setJobID(JobID);
        }
        else if(CheckUser.equals("Corporation")){
            FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(),"JobDetail.fxml");
            SceneChanger.ChangeSceneWithLoaderOnAction(offer,"JobDetail.fxml",loader);
            JobDetailController jobDetailController = loader.getController();
            jobDetailController.setCorporation(corporation);
            jobDetailController.setJobID(JobID);
        }
    }

    @FXML
    private void cancelofferOnAction(Event event)throws IOException{

        if(Job.getStatus().equals("Rejected") || Job.getStatus().equals("Accepted") || Job.getStatus().equals("Wait for Request") || Job.getStatus().equals("Started")){
            if (Job.getStatus().equals("Rejected")){
                Alert alert = new Alert(Alert.AlertType.WARNING,"Job already rejected", ButtonType.OK);
                alert.show();
            }
            else if (Job.getStatus().equals("Accepted")){
                Alert alert = new Alert(Alert.AlertType.WARNING,"Job already Accepted", ButtonType.OK);
                alert.show();
            }
            else if (Job.getStatus().equals("Started")){
                Alert alert = new Alert(Alert.AlertType.WARNING,"Job already started", ButtonType.OK);
                alert.show();
            }
            else if (Job.getStatus().equals("Wait for Request")){
                Alert alert = new Alert(Alert.AlertType.WARNING,"Job already in wait for request", ButtonType.OK);
                alert.show();
            }
            return;
        }

        DBConnect.UpdateJobStatus(JobID,"Wait for Request");
        if(CheckUser.equals("Contractor")){
            FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(),"JobDetail.fxml");
            SceneChanger.ChangeSceneWithLoaderOnAction(canceloffer,"JobDetail.fxml",loader);
            JobDetailController jobDetailController = loader.getController();
            jobDetailController.setContractor(contractor);
            jobDetailController.setJobID(JobID);
        }
        else if(CheckUser.equals("Corporation")){
            FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(),"JobDetail.fxml");
            SceneChanger.ChangeSceneWithLoaderOnAction(canceloffer,"JobDetail.fxml",loader);
            JobDetailController jobDetailController = loader.getController();
            jobDetailController.setCorporation(corporation);
            jobDetailController.setJobID(JobID);
        }
    }

    @FXML
    private void acceptOnAction(Event event)throws IOException{

        System.out.println(Job.getStatus());

        if(Job.getStatus().equals("Rejected") || Job.getStatus().equals("Accepted") || Job.getStatus().equals("Wait for Request") || Job.getStatus().equals("Started")){
            if (Job.getStatus().equals("Rejected")){
                Alert alert = new Alert(Alert.AlertType.WARNING,"Job already rejected", ButtonType.OK);
                alert.show();
            }
            else if (Job.getStatus().equals("Accepted")){
                Alert alert = new Alert(Alert.AlertType.WARNING,"Job already Accepted", ButtonType.OK);
                alert.show();
            }
            else if (Job.getStatus().equals("Started")){
                Alert alert = new Alert(Alert.AlertType.WARNING,"Job already started", ButtonType.OK);
                alert.show();
            }
            return;
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure to accept this job ?",ButtonType.APPLY,ButtonType.CANCEL);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.APPLY){
                DBConnect.UpdateJobStatus(JobID,"Accepted");
                if(CheckUser.equals("Contractor")){
                    FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(),"JobDetail.fxml");
                    SceneChanger.ChangeSceneWithLoaderOnAction(canceloffer,"JobDetail.fxml",loader);
                    JobDetailController jobDetailController = loader.getController();
                    jobDetailController.setContractor(contractor);
                    jobDetailController.setJobID(JobID);
                }
                else if(CheckUser.equals("Corporation")){
                    FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(),"JobDetail.fxml");
                    SceneChanger.ChangeSceneWithLoaderOnAction(canceloffer,"JobDetail.fxml",loader);
                    JobDetailController jobDetailController = loader.getController();
                    jobDetailController.setCorporation(corporation);
                    jobDetailController.setJobID(JobID);
                }
        }


        }
    }

    @FXML
    private void rejectOnAction(Event event)throws IOException{

        System.out.println(Job.getStatus());

        if(Job.getStatus().equals("Rejected") || Job.getStatus().equals("Accepted") || Job.getStatus().equals("Wait for Request") || Job.getStatus().equals("Started")){
            if (Job.getStatus().equals("Rejected")){
                Alert alert = new Alert(Alert.AlertType.WARNING,"Job already rejected", ButtonType.OK);
                alert.show();
            }
            else if (Job.getStatus().equals("Accepted")){
                Alert alert = new Alert(Alert.AlertType.WARNING,"Job already Accepted", ButtonType.OK);
                alert.show();
            }
            else if (Job.getStatus().equals("Started")){
                Alert alert = new Alert(Alert.AlertType.WARNING,"Job already started", ButtonType.OK);
                alert.show();
            }
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure to rejected this job ?",ButtonType.APPLY,ButtonType.CANCEL);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.APPLY) {
            DBConnect.UpdateJobStatus(JobID, "Rejected");
            if (CheckUser.equals("Contractor")) {
                FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(), "JobDetail.fxml");
                SceneChanger.ChangeSceneWithLoaderOnAction(canceloffer, "JobDetail.fxml", loader);
                JobDetailController jobDetailController = loader.getController();
                jobDetailController.setContractor(contractor);
                jobDetailController.setJobID(JobID);
            } else if (CheckUser.equals("Corporation")) {
                FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(), "JobDetail.fxml");
                SceneChanger.ChangeSceneWithLoaderOnAction(canceloffer, "JobDetail.fxml", loader);
                JobDetailController jobDetailController = loader.getController();
                jobDetailController.setCorporation(corporation);
                jobDetailController.setJobID(JobID);
            }
        }
    }

    @FXML
    private void StartWorkOnAction(Event event)throws IOException{

        if(Job.getStatus().equals("Rejected") || Job.getStatus().equals("Accepted") || Job.getStatus().equals("Wait for Request") || Job.getStatus().equals("Started")){
            if (Job.getStatus().equals("Rejected")){
                Alert alert = new Alert(Alert.AlertType.WARNING,"Job already rejected", ButtonType.OK);
                alert.show();
            }
            else if (Job.getStatus().equals("Accepted")){
                Alert alert = new Alert(Alert.AlertType.WARNING,"Job already Accepted", ButtonType.OK);
                alert.show();
            }
            else if (Job.getStatus().equals("Started")){
                Alert alert = new Alert(Alert.AlertType.WARNING,"Job already started", ButtonType.OK);
                alert.show();
            }
            else if (Job.getStatus().equals("Wait for Request")){
                Alert alert = new Alert(Alert.AlertType.WARNING,"Job in wait for request", ButtonType.OK);
                alert.show();
            }
            return;
        }

        if(Job.getStatus().equals("Accepted")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure to start work ?",ButtonType.CANCEL,ButtonType.APPLY);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.APPLY){
                DBConnect.UpdateJobStatus(JobID,"Started");
                FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(), "JobDetail.fxml");
                SceneChanger.ChangeSceneWithLoaderOnAction(canceloffer, "JobDetail.fxml", loader);
                JobDetailController jobDetailController = loader.getController();
                jobDetailController.setContractor(contractor);
                jobDetailController.setJobID(JobID);
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Job Must Be Accepted", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void ManageEquipmentOnAction(Event event)throws IOException{
        FXMLLoader loader = SceneChanger.GetLoaderOnAction(JobDetailController.class, "EquipmentList.fxml");
        SceneChanger.ChangeSceneOnAction(manage, JobDetailController.class, "EquipmentList.fxml", loader);
        EquipmentListController equipmentListController = loader.getController();
        equipmentListController.SetID(JobID);
        equipmentListController.SetContractor(contractor);
    }

    @FXML
    private void GoBackOnAction(Event event)throws IOException {

        if(CheckUser.equals("Contractor")){
            FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(),"JobList.fxml");
            SceneChanger.ChangeSceneWithLoaderOnAction(back,"JobList.fxml",loader);
            JobListController jobListController = loader.getController();
            jobListController.setContractor(contractor);
        }
        else if(CheckUser.equals("Corporation")){
            FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(),"JobOffer.fxml");
            SceneChanger.ChangeSceneWithLoaderOnAction(back,"JobOffer.fxml",loader);
            JobOfferController jobOfferController = loader.getController();
            jobOfferController.setCorporation(corporation);
        }
    }

}
