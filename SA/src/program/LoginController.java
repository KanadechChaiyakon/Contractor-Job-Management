package program;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Contractor;

import java.io.IOException;
import java.util.ArrayList;

public class LoginController {

    @FXML
    private Button login, register;

    @FXML
    private TextField username,password;

    @FXML
    private Label usernameincorrect, passwordincorrect, nullinput;

    private ArrayList<Contractor> contractors;

    public void initialize(){
        contractors = DBConnect.ReadContractor();
    }


    @FXML
    private void LoginOnAction(Event event)throws IOException {

        usernameincorrect.setOpacity(0);
        passwordincorrect.setOpacity(0);
        nullinput.setOpacity(0);

        if(username.getText().equals("") || password.getText().equals("")){
            nullinput.setOpacity(1);
            return;
        }
        for (Contractor contractor : contractors){
            System.out.println(contractor.getUsername());
            if(username.getText().equals(contractor.getUsername())){
                if(password.getText().equals(contractor.getPassword())){
                    Parent loader = FXMLLoader.load(getClass().getResource("CustomerList.fxml"));
                    Scene scene = new Scene(loader);
                    Stage stage = (Stage) login.getScene().getWindow();
                    stage.setScene(scene);
                }else {
                    passwordincorrect.setOpacity(1);
                    return;
                }
            }
            else {
                usernameincorrect.setOpacity(1);
                return;
            }
        }
    }

    @FXML
    private void GoRegisterOnAction(Event event)throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage) register.getScene().getWindow();
        stage.setScene(scene);
    }

}
