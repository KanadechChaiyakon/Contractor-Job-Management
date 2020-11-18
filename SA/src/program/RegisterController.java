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
    private Label checktextfield, usernametaken, passwordnotmatch;

    private ArrayList<Contractor> contractors;

    public void initialize(){
        contractors = DBConnect.ReadContractor();
    }

    public boolean CheckUsername(){
        for(Contractor contractor : contractors){
            if(contractor.getUsername().equals(username.getText())){
                return true;
            }
        }
        return false;
    }

    public boolean CheckTextField(){
        if(name.getText().equals("") || username.getText().equals("") || password.getText().equals("")){
            return true;
        }
        return false;
    }

    @FXML
    private void RegisterOnAction(Event event)throws IOException{

        usernametaken.setOpacity(0);
        checktextfield.setOpacity(0);
        passwordnotmatch.setOpacity(0);

        if (CheckTextField()){
            checktextfield.setOpacity(1);
            return;
        }

        if (CheckUsername()){
            usernametaken.setOpacity(1);
            return;
        }
        else {
            if (password.getText().equals(confirmpassword.getText())){

                DBConnect.WriteContractor(name.getText(),username.getText(),password.getText(), email.getText(), phonenumber.getText());

                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Registration Complete", ButtonType.OK);
                alert.showAndWait();

                Parent loader = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Scene scene = new Scene(loader);
                Stage stage = (Stage) cancel.getScene().getWindow();
                stage.setScene(scene);

            }else {
                passwordnotmatch.setOpacity(1);
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
