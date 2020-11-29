package program;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Contractor;
import model.Equipment;
import model.EquipmentList;
import model.Job;

import java.io.IOException;
import java.util.ArrayList;

public class AddEquipmentController {

    @FXML
    private Button submit, back, addother;

    @FXML
    private TextField quantity, detail;

    @FXML
    private Label nullinput, checkprice, checkamount;

    @FXML
    private ComboBox<String> JobBox;

    @FXML
    private ComboBox<String> EquipmentBox;

    private Contractor contractor;

    private ArrayList<EquipmentList> equipmentLists;

    private ArrayList<Job> jobArrayList;

    private ArrayList<Equipment> equipmentArrayList;

    private int JobID, eid, eprice, budget, amount, qty, jobbudget;

    public void SetID(int jobID){
        this.JobID = jobID;
//        this.ContractorID = contractorID;
    }

    public void setContractor(Contractor contractor){
        this.contractor = contractor;
    }

    public void initialize(){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                jobArrayList = DBConnect.ReadJob();
                for(Job job : jobArrayList){
                    if (job.getContractorID() == contractor.getID() && job.getJobID() == JobID){
                        JobBox.getItems().add(job.getAddress());
                        jobbudget = job.getBudget();
                    }
                }
                equipmentArrayList = DBConnect.ReadEquipment();
                for(Equipment equipment : equipmentArrayList){
                    EquipmentBox.getItems().add(equipment.getEquipmentname()+" "+equipment.getPrice()+" บาท");
                }
                equipmentLists = DBConnect.ReadEquipmentList();
                for(EquipmentList equipmentList : equipmentLists){
                    if(equipmentList.getJob_id() == JobID){
                        budget += equipmentList.getAmount();
                    }
                }
            }
        });

    }

    @FXML
    private void GoBackOnAction(Event event)throws IOException {
        FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(), "EquipmentList.fxml");
        SceneChanger.ChangeSceneWithLoaderOnAction(back, "EquipmentList.fxml", loader);
        EquipmentListController equipmentListController = loader.getController();
        equipmentListController.SetContractor(contractor);
        equipmentListController.SetID(JobID);
    }

    @FXML
    private void GoAddOtherOnAction(Event event)throws IOException {
        FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(), "AddOtherEquipment.fxml");
        SceneChanger.ChangeSceneWithLoaderOnAction(back, "AddOtherEquipment.fxml", loader);
        AddOtherEquipmentController addOtherEquipmentController = loader.getController();
        addOtherEquipmentController.setContractor(contractor);
        addOtherEquipmentController.setJobID(JobID);
    }

    @FXML
    private void SubmitOnAction(Event event)throws IOException{

        nullinput.setOpacity(0);
        checkamount.setOpacity(0);
        checkprice.setOpacity(0);


        if(CheckTextField()){
            return;
        }
        else if (CheckQuantity()){
            return;
        }


        String[] choose = EquipmentBox.getSelectionModel().getSelectedItem().split(" ");
        for (Equipment equipment : equipmentArrayList){
            if(equipment.getEquipmentname().equals(choose[0])){
                eid = equipment.getEquipment_id();
                eprice = equipment.getPrice();
            }
        }

        qty = Integer.parseInt(quantity.getText());
        amount = eprice*qty;

        if(CheckOverBudget()){
            return;
        }


        DBConnect.WriteEquipmentList(JobID, eid, qty, amount, detail.getText());

//        for(Job job : jobArrayList){
//            if(JobBox.getValue().equals(job.getAddress())){
//                for(EquipmentList equipmentList : equipmentListArrayList){
//                    if(equipmentList.getJob_id()==job.getJobID()){
//                        this.equipmentList = equipmentList;
//                    }
//                }
//            }
//        }

//        int Price = Integer.parseInt(price.getText());
//        int Amount = Integer.parseInt(amount.getText());
//        int TotalPrice = Price*Amount;
//        int cost = equipmentList.getAmount();
//        int TotalCost = cost+TotalPrice;
//        //System.out.println(equipmentList.getTotal_cost());
//        DBConnect.WriteEquipment(equipment_name.getText(),Price);
//        System.out.println(TotalCost);
//        //DBConnect.UpdateEquipmentList(TotalCost,equipmentList.getEquipmentlist_id());

        EquipmentBox.getSelectionModel().clearSelection();
        quantity.setText("");
        detail.setText("");

        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Add Equipment Complete", ButtonType.OK);
        alert.show();
    }

    private boolean CheckOverBudget(){

        int budgetcheck = budget+amount;
        if (budgetcheck > jobbudget){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Over budget !!", ButtonType.OK);
            alert.show();
            return true;
        }
        return false;
    }

    private boolean CheckTextField(){
        if (quantity.getText().equals("")  || detail.getText().equals("") || EquipmentBox.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Please enter all information", ButtonType.OK);
            alert.show();
            return true;
        }
        return false;
    }

//    private boolean CheckPrice(){
//        try {
//            int a = Integer.parseInt(price.getText());
//        }catch (NumberFormatException e){
//            checkprice.setOpacity(1);
//            return true;
//        }
//        if(Integer.parseInt(price.getText()) <= 0){
//            return true;
//        }
//        return false;
//    }

    private boolean CheckQuantity(){
        try {
            int a = Integer.parseInt(quantity.getText());
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Quantity must be number", ButtonType.OK);
            alert.show();
            return true;
        }
        if(Integer.parseInt(quantity.getText()) <= 0 && Integer.parseInt(quantity.getText()) < 1000){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Quantity must between 1-999", ButtonType.OK);
            alert.show();
            return true;
        }
        return false;
    }

//    private boolean CheckValue(){
//        boolean acheck = CheckAmount();
//        //boolean pcheck = CheckPrice();
//        if(acheck){
//            return true;
//        }
//        return false;
//    }

}
