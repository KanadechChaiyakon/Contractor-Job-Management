package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerListController {

    @FXML
    private Button add,back;

    @FXML
    private TableView<String> customerlist;

    @FXML
    private void GoBackOnAction(Event event)throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(scene);
    }
}
