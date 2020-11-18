package program;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import model.Contractor;
import model.Equipment;
import model.EquipmentList;
import model.Job;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteEquipmentController {

    @FXML
    private ComboBox<String> jobbox, equipmentbox;

    @FXML
    private Button submit, back;

    private ArrayList<EquipmentList> equipmentListArrayList;

    private ArrayList<Equipment> equipmentArrayList;

    private ArrayList<Job> jobArrayList;

    private Contractor contractor;

    private EquipmentList use_equipmentList;

    private int JobID, ContractorID, job_id, equipmentlistid;

    public void SetContractor(Contractor contractor){
        this.contractor = contractor;
    }

    public void SetID(int jobID, int contractorID){
        this.ContractorID = contractorID;
        this.JobID = jobID;
    }

    public void initialize(){

        jobArrayList = DBConnect.ReadJob();

        for(Job job : jobArrayList){
            jobbox.getItems().add(job.getAddress());
        }

    }

    @FXML
    private void JobSelectedOnAction(Event event){

        String job_address = jobbox.getValue();
        for(Job job : jobArrayList){
            if(job_address.equals(job.getAddress())){
                job_id = job.getJobID();
            }
        }
        equipmentListArrayList = DBConnect.ReadEquipmentList();
        equipmentArrayList = DBConnect.ReadEquipment();

        for(EquipmentList equipmentList : equipmentListArrayList){
            if(equipmentList.getJob_id() == job_id){
                this.use_equipmentList = equipmentList;
                for (Equipment equipment : equipmentArrayList){
                    if(equipment.getEquipmentlistid() == use_equipmentList.getEquipmentlist_id()){
                        equipmentbox.getItems().add(equipment.getEquipment_id()+" "+equipment.getEquipmentname());
                    }
                }
            }
        }
    }

    @FXML
    private void DeleteOnAction(Event event){

        int equipment_id;
        String[] data = equipmentbox.getValue().split(" ");
        equipment_id = Integer.parseInt(data[0]);
        for(Equipment equipment : equipmentArrayList){
            //System.out.println(equipment.getEquipmentlistid()+" "+use_equipmentList.getEquipmentlist_id()+equipment.getEquipment_id()+" "+equipment_id);
            if(equipment.getEquipmentlistid() == use_equipmentList.getEquipmentlist_id() && equipment_id == equipment.getEquipment_id()){
                System.out.println("checked");
                int cost = use_equipmentList.getTotal_cost() - equipment.getTotalprice();
                DBConnect.UpdateEquipmentList(cost,use_equipmentList.getEquipmentlist_id());
                DBConnect.DeleteEquipment(equipment.getEquipment_id());
            }
        }

        equipmentbox.getSelectionModel().clearSelection();
        equipmentbox.getItems().clear();
        equipmentListArrayList = DBConnect.ReadEquipmentList();
        equipmentArrayList = DBConnect.ReadEquipment();

        for(EquipmentList equipmentList : equipmentListArrayList){
            if(equipmentList.getJob_id() == job_id){
                this.use_equipmentList = equipmentList;
                for (Equipment equipment : equipmentArrayList){
                    if(equipment.getEquipmentlistid() == use_equipmentList.getEquipmentlist_id()){
                        equipmentbox.getItems().add(equipment.getEquipment_id()+" "+equipment.getEquipmentname());
                    }
                }
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Delete Equipment Complete", ButtonType.OK);
        alert.showAndWait();

    }

    @FXML
    private void GoBackOnAction(Event event)throws IOException {
        FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(), "EquipmentList.fxml");
        SceneChanger.ChangeSceneWithLoaderOnAction(back, "EquipmentList.fxml", loader);
        EquipmentListController equipmentListController = loader.getController();
        equipmentListController.SetContractor(contractor);
        equipmentListController.SetID(JobID, ContractorID);
    }
}
