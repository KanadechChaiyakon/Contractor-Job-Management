package program;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Contractor;

import java.io.IOException;
import java.util.ArrayList;

public class RegisterController {

    @FXML
    private Button submit, cancel;

    @FXML
    private TextField name, username, password, confirmpassword;

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

        checktextfield.setOpacity(0);

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
                DBConnect.WriteContractor(name.getText(),username.getText(),password.getText());
            }else {
                passwordnotmatch.setOpacity(1);
            }
        }
    }
}
