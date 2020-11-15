package program;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddEquipmentController {

    @FXML
    private Button submit,cancel;

    @FXML
    private TextField equipment_name,price,amount;

    @FXML
    private void GoCancelOnAction(Event event)throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("EquipmentList.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void SubmitOnAction(Event event)throws IOException{

    }
}
