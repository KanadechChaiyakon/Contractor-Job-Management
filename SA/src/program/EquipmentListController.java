package program;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Contractor;
import model.Equipment;
import model.EquipmentList;
import model.Job;

import java.io.IOException;
import java.util.ArrayList;

public class EquipmentListController {

    @FXML
    private Button back, add, delete;

    @FXML
    private Label cost;

    @FXML
    private TableView<Equipment> equipmentTableView;

    @FXML
    private TableColumn<Equipment,String> name, brand, detail;

    @FXML
    private TableColumn<Equipment,Integer> total, amount;

    private Contractor contractor;

    private EquipmentList equipmentList;

    private  ArrayList<EquipmentList> equipmentListArrayList;

    private ArrayList<Equipment> equipmentArrayList;

    private ArrayList<Job> jobArrayList;

    private ArrayList<Contractor> contractorArrayList;

    private int JobID, ContractorID;

    public void SetContractor(Contractor contractor){
        this.contractor = contractor;
    }

    public void SetID(int jobID, int contractorID){
        this.JobID = jobID;
        this.ContractorID = contractorID;
    }

    public void setEquipmentList(EquipmentList equipmentList){
        this.equipmentList = equipmentList;
    }

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                contractorArrayList = DBConnect.ReadContractor();
                for(Contractor contractor : contractorArrayList){
                    if(contractor.getID() == ContractorID){
                        SetContractor(contractor);
                    }
                }
                equipmentListArrayList = DBConnect.ReadEquipmentList();
                jobArrayList = DBConnect.ReadJob();
                //for(Job job : jobArrayList){
                //   JobBox.getItems().add(job.getAddress());
                //}
                for(Job job : jobArrayList){
                    if(job.getJobID() == JobID){
                        for(EquipmentList equipmentList : equipmentListArrayList){
                            if(equipmentList.getJob_id()==job.getJobID()){
                                setEquipmentList(equipmentList);
                            }
                        }
                    }
                }

                ArrayList<Equipment> use_equipment = new ArrayList<>();

                equipmentArrayList = DBConnect.ReadEquipment();
                for(Equipment equipment : equipmentArrayList){
                    if(equipment.getEquipmentlistid() == equipmentList.getEquipmentlist_id()){
                        use_equipment.add(new Equipment(equipment.getAmount(),equipment.getTotalprice(), equipment.getEquipmentname(), equipment.getBrand(), equipment.getDetail()));
                    }
                }

                ObservableList<Equipment> equipmentObservableList = FXCollections.observableArrayList(use_equipment);

                name.setCellValueFactory(new PropertyValueFactory<>("equipmentname"));
                brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
                amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
                total.setCellValueFactory(new PropertyValueFactory<>("totalprice"));
                detail.setCellValueFactory(new PropertyValueFactory<>("detail"));

                equipmentTableView.setItems(equipmentObservableList);

                cost.setText("Total Cost : " + equipmentList.getTotal_cost());
            }
        });
    }


    @FXML
    private void GoBackOnAction(Event event)throws IOException {
        FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(),"JobList.fxml");
        SceneChanger.ChangeSceneWithLoaderOnAction(back,"JobList.fxml",loader);
        JobListController jobListController = loader.getController();
        jobListController.setContractor(contractor);
    }

    @FXML
    private void GoDeleteOnAction(Event event)throws IOException {
        FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(), "DeleteEquipment.fxml");
        SceneChanger.ChangeSceneWithLoaderOnAction(delete, "DeleteEquipment.fxml", loader);
        DeleteEquipmentController deleteEquipmentController = loader.getController();
        deleteEquipmentController.SetContractor(contractor);
        deleteEquipmentController.SetID(JobID, ContractorID);
    }

    @FXML
    private void GoAddEquipmentOnAction(Event event)throws IOException {
        FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(), "AddEquipment.fxml");
        SceneChanger.ChangeSceneWithLoaderOnAction(add, "AddEquipment.fxml", loader);
        AddEquipmentController addEquipmentController = loader.getController();
        addEquipmentController.setContractor(contractor);
        addEquipmentController.SetID(JobID,ContractorID);
    }
}
