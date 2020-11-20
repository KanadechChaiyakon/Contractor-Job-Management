package program;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Contractor;
import model.EquipmentList;
import model.Job;

import java.io.IOException;
import java.util.ArrayList;

public class AddEquipmentController {

    @FXML
    private Button submit, back;

    @FXML
    private TextField equipment_name, price, amount, brand, detail;

    @FXML
    private Label nullinput, checkprice, checkamount;

    @FXML
    private ComboBox<String> JobBox;

    private Contractor contractor;

    private EquipmentList equipmentList;

    private ArrayList<Job> jobArrayList;

    private ArrayList<EquipmentList> equipmentListArrayList;

    private int JobID, ContractorID;

    public void SetID(int jobID, int contractorID){
        this.JobID = jobID;
        this.ContractorID = contractorID;
    }

    public void setContractor(Contractor contractor){
        this.contractor = contractor;
    }

    public void initialize(){

        equipmentListArrayList = DBConnect.ReadEquipmentList();
        jobArrayList = DBConnect.ReadJob();
        for(Job job : jobArrayList){
            JobBox.getItems().add(job.getAddress());
        }
    }

    @FXML
    private void GoBackOnAction(Event event)throws IOException {
        FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(), "EquipmentList.fxml");
        SceneChanger.ChangeSceneWithLoaderOnAction(back, "EquipmentList.fxml", loader);
        EquipmentListController equipmentListController = loader.getController();
        equipmentListController.SetContractor(contractor);
        equipmentListController.SetID(JobID, ContractorID);
    }

    @FXML
    private void SubmitOnAction(Event event)throws IOException{

        nullinput.setOpacity(0);
        checkamount.setOpacity(0);
        checkprice.setOpacity(0);

        if(CheckTextField()){
            nullinput.setOpacity(1);
            return;
        }

        if (CheckPrice()){
            return;
        }

        if (CheckAmount()){
            return;
        }

        for(Job job : jobArrayList){
            if(JobBox.getValue().equals(job.getAddress())){
                for(EquipmentList equipmentList : equipmentListArrayList){
                    if(equipmentList.getJob_id()==job.getJobID()){
                        this.equipmentList = equipmentList;
                    }
                }
            }
        }

        int Price = Integer.parseInt(price.getText());
        int Amount = Integer.parseInt(amount.getText());
        int TotalPrice = Price*Amount;
        int cost = equipmentList.getTotal_cost();
        int TotalCost = cost+TotalPrice;
        //System.out.println(equipmentList.getTotal_cost());
        DBConnect.WriteEquipment(equipment_name.getText(),Price,Amount,TotalPrice,brand.getText(),detail.getText(), equipmentList.getEquipmentlist_id());
        System.out.println(TotalCost);
        DBConnect.UpdateEquipmentList(TotalCost,equipmentList.getEquipmentlist_id());

        equipment_name.setText("");
        price.setText("");
        amount.setText("");
        brand.setText("");
        detail.setText("");

        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Add Equipment Complete", ButtonType.OK);
        alert.showAndWait();
    }

    private boolean CheckTextField(){
        if (equipment_name.getText().equals("") || price.getText().equals("") || amount.getText().equals("") || brand.getText().equals("") || detail.getText().equals("") || JobBox.getSelectionModel().getSelectedItem() == null){
            return true;
        }
        return false;
    }

    private boolean CheckPrice(){
        try {
            int a = Integer.parseInt(price.getText());
        }catch (NumberFormatException e){
            checkprice.setOpacity(1);
            return true;
        }
        if(Integer.parseInt(price.getText()) <= 0){
            return true;
        }
        return false;
    }

    private boolean CheckAmount(){
        try {
            int a = Integer.parseInt(amount.getText());
        }catch (NumberFormatException e){
            checkamount.setOpacity(1);
            return true;
        }
        if(Integer.parseInt(amount.getText()) <= 0){
            return true;
        }
        return false;
    }

}
