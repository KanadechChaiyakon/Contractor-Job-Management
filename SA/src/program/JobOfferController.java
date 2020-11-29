package program;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Corporation;
import model.Job;

import java.io.IOException;
import java.util.ArrayList;

public class JobOfferController {

    @FXML
    private TableView<Job> jobTableView;

    @FXML
    private TableColumn<Job, String> address, date, type, status;

    @FXML
    private TableColumn<Job, Button> Detail;

    @FXML
    private Button logout;

    private ObservableList<Job> jobArrayList;

    private Corporation corporation;

    public void setCorporation(Corporation corporation){
        this.corporation = corporation;
    }

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                jobArrayList = DBConnect.ReadJobWithButton(corporation);

                ArrayList<Job> use_job = new ArrayList<>();
                for(Job job : jobArrayList){
                    if (job.getStatus().equals("Request") || job.getStatus().equals("Accepted") || job.getStatus().equals("Rejected") || job.getStatus().equals("Started")){
                        use_job.add(job);
                    }
                }

                ObservableList<Job> jobObservableList = FXCollections.observableArrayList(use_job);

                address.setCellValueFactory(new PropertyValueFactory<>("Address"));
                date.setCellValueFactory(new PropertyValueFactory<>("Date"));
                type.setCellValueFactory(new PropertyValueFactory<>("Type"));
                status.setCellValueFactory(new PropertyValueFactory<>("Status"));
                Detail.setCellValueFactory(new PropertyValueFactory<>("Detail"));


                jobTableView.setItems(jobObservableList);


            }
        });
    }

    @FXML
    private void GoLogoutOnAction(Event event)throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.setScene(scene);
    }
}
