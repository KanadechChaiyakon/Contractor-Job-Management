package program;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.Contractor;
import model.Equipment;

import java.io.IOException;
import java.util.ArrayList;


public class AddOtherEquipmentController {

    @FXML
    private TextField equipmentname, price;

    @FXML
    private Button back, add;

    private int JobID;

    private Contractor contractor;

    private ArrayList<Equipment> equipmentArrayList;

    public void initialize(){
        equipmentArrayList = DBConnect.ReadEquipment();
    }

    public void setJobID(int jobID){
        this.JobID = jobID;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    private boolean CheckEquipmentNameIsString(){
        try {
            int a = Integer.parseInt(equipmentname.getText());
        }catch (NumberFormatException e){
            return false;
        }
        Alert alert = new Alert(Alert.AlertType.WARNING,"EquipmentName must be character", ButtonType.OK);
        alert.show();
        return true;
    }

    private boolean CheckPrice(){
        try {
            int a = Integer.parseInt(price.getText());
            if(a<=0 || a>1000000){
                Alert alert = new Alert(Alert.AlertType.WARNING,"Price should between 1-9999999 baht", ButtonType.OK);
                alert.show();
                return true;
            }
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Price must be number", ButtonType.OK);
            alert.show();
            return true;
        }
        return false;
    }

    private boolean CheckEquipment(String equipmentname){

        for(Equipment equipment : equipmentArrayList){
            if(equipmentname.equals(equipment.getEquipmentname())){
                Alert alert = new Alert(Alert.AlertType.WARNING,"Already have this Equipment", ButtonType.OK);
                alert.show();
                return true;
            }
        }
        return false;
    }

    private boolean CheckInput(){
        if(equipmentname.getText().equals("") || price.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Please enter all information", ButtonType.OK);
            alert.show();
            return true;
        }
        return false;
    }

    @FXML
    private void AddOnAction(Event event)throws IOException {

        if (CheckInput()){
            return;
        }
        else if(CheckPrice()){
            return;
        }
        else if(CheckEquipmentNameIsString()){
            return;
        }else if (CheckEquipment(equipmentname.getText())){
            return;
        }
        else {
            DBConnect.WriteEquipment(equipmentname.getText(),Integer.parseInt(price.getText()));
            Alert alert = new Alert(Alert.AlertType.WARNING,"Equipment added", ButtonType.OK);
            alert.show();

            FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(), "AddEquipment.fxml");
            SceneChanger.ChangeSceneWithLoaderOnAction(add, "AddEquipment.fxml", loader);
            AddEquipmentController addEquipmentController = loader.getController();
            addEquipmentController.setContractor(contractor);
            addEquipmentController.SetID(JobID);
        }

    }


    @FXML
    private void GoBackOnAction(Event event)throws IOException {
        FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(), "AddEquipment.fxml");
        SceneChanger.ChangeSceneWithLoaderOnAction(back, "AddEquipment.fxml", loader);
        AddEquipmentController addEquipmentController = loader.getController();
        addEquipmentController.setContractor(contractor);
        addEquipmentController.SetID(JobID);
    }
}
