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

public class AddJobController {

    @FXML
    private Button back,submit;

    @FXML
    private TextField JobType,JobAddress,JobDate;

    @FXML
    private void GoBackOnAction(Event event)throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("CustomerList.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(scene);
    }


}
