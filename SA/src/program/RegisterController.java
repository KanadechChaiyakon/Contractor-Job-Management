package program;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Contractor;

import java.io.IOException;
import java.util.ArrayList;

public class RegisterController {

    @FXML
    private Button submit, cancel;

    @FXML
    private TextField name, username, password, confirmpassword, email, phonenumber;

    @FXML
    private Label checktextfield, usernametaken, passwordnotmatch, checkemail, checkphone, checkusername, checkname;

    @FXML
    private ComboBox<String> usertype;

    private ArrayList<Contractor> contractors;

    public void initialize(){
        contractors = DBConnect.ReadContractor();
        usertype.getItems().addAll("บริษัท","ผู้รับเหมา");
    }

    public boolean CheckUsername(){
        for(Contractor contractor : contractors){
            if(contractor.getUsername().equals(username.getText())){
                Alert alert = new Alert(Alert.AlertType.WARNING,"Already have this username", ButtonType.OK);
                alert.show();
                return true;
            }
        }
        return false;
    }

    public boolean CheckTextField(){
        if(name.getText().equals("") || username.getText().equals("") || password.getText().equals("") || confirmpassword.getText().equals("") || email.getText().equals("") || phonenumber.getText().equals("") || usertype.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Please enter all information", ButtonType.OK);
            alert.show();
            return true;
        }
        return false;
    }

    private boolean CheckEmail(){

        String[] data = email.getText().split("@");
        if (data.length == 1){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Invalid Email", ButtonType.OK);
            alert.show();
            return true;
        }
        return false;
    }

    private boolean CheckPhoneNumber(){

        try {
            int a = Integer.parseInt(phonenumber.getText());
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Phone Number must be number", ButtonType.OK);
            alert.show();
            return true;
        }
        String[] data = phonenumber.getText().split("");

        if(data.length != 10 && data[0].equals("0")){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Phone Number must have 10 digit", ButtonType.OK);
            alert.show();
            return true;
        }
        else if (!data[0].equals("0")){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Phone Number must start with 0", ButtonType.OK);
            alert.show();
            return true;
        }
        return false;
    }

    private boolean CheckUsernameIsString(){
        try {
            int a = Integer.parseInt(username.getText());
        }catch (NumberFormatException e){
            return false;
        }
        Alert alert = new Alert(Alert.AlertType.WARNING,"Username must have character", ButtonType.OK);
        alert.show();
        return true;
    }

    private boolean CheckNameIsString(){
        try {
            int a = Integer.parseInt(name.getText());
        }catch (NumberFormatException e){
            return false;
        }
        Alert alert = new Alert(Alert.AlertType.WARNING,"Name must be character", ButtonType.OK);
        alert.show();
        return true;
    }

    private boolean CheckValue(){
        boolean ncheck, ucheck, echeck, pcheck;
        ncheck = CheckNameIsString();
        ucheck = CheckUsernameIsString();
        echeck = CheckEmail();
        pcheck = CheckPhoneNumber();

        if(ncheck || ucheck || echeck || pcheck){
            return true;
        }
        return false;
    }

    @FXML
    private void RegisterOnAction(Event event)throws IOException{

        usernametaken.setOpacity(0);
        checktextfield.setOpacity(0);
        passwordnotmatch.setOpacity(0);
        checkemail.setOpacity(0);
        checkphone.setOpacity(0);
        checkusername.setOpacity(0);
        checkname.setOpacity(0);

        if (CheckTextField()){
            return;
        }
        else if(CheckValue()){
            return;
        }
        else if (CheckUsername()){
            return;
        }
        else {
            if (password.getText().equals(confirmpassword.getText())){

                if(usertype.getValue().equals("ผู้รับเหมา")){
                    DBConnect.WriteContractor(name.getText(),username.getText(),password.getText(), email.getText(), phonenumber.getText());
                }
                else if (usertype.getValue().equals("บริษัท")){
                    DBConnect.WriteCorporation(name.getText(),username.getText(),password.getText(), email.getText(), phonenumber.getText());
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING,"Please choose user type", ButtonType.OK);
                    alert.show();
                    return;
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Registration Complete", ButtonType.OK);
                alert.showAndWait();

                Parent loader = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Scene scene = new Scene(loader);
                Stage stage = (Stage) cancel.getScene().getWindow();
                stage.setScene(scene);

            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Password not match", ButtonType.OK);
                alert.showAndWait();
                return;
            }
        }
    }

    @FXML
    private void CancelOnAction(Event event)throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.setScene(scene);
    }

}
