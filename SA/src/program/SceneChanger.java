package program;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneChanger {

    @FXML
    public static void ChangeSceneOnAction(Button button, Class thisclass, String fxml, FXMLLoader loader)throws IOException {
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    public static void ChangeSceneWithLoaderOnAction(Button button, String fxml, FXMLLoader loader)throws IOException {
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
    }


    @FXML
    public static FXMLLoader GetLoaderOnAction(Class thisclass, String fxml){
        FXMLLoader loader = new FXMLLoader(thisclass.getResource(fxml));
        return loader;
    }

}
