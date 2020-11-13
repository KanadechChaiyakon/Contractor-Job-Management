package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button login,forgotpassword;

    @FXML
    private TextField username,password;

    @FXML
    private Label usernameincorrect,passwordincorrect;

    @FXML
    private void LoginOnAction(Event event)throws IOException{
        Parent loader = FXMLLoader.load(getClass().getResource("CustomerList.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage) login.getScene().getWindow();
        stage.setScene(scene);
    }

}
