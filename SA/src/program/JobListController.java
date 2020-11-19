package program;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Contractor;
import model.EquipmentList;
import model.Job;

import java.io.IOException;
import java.util.ArrayList;

public class JobListController {

    @FXML
    private Button add, back;

    @FXML
    private Label contractorname;

    @FXML
    private TableView<Job> joblist;

    @FXML
    private TableColumn<Job,Integer> JobID, ID;

    @FXML
    private TableColumn<Job,String> Type, Address, Date;

    @FXML
    private TableColumn<Job, Button> Detail;

    private ObservableList<Job> jobArrayList;

    private ArrayList<EquipmentList> equipmentListArrayList;

    private Contractor contractor;

    private int jobid, contractorid;

    public void setContractor(Contractor contractor){
        this.contractor = contractor;
    }



    public void initialize(){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                contractorname.setText(contractor.getName());
                ArrayList<Job> use_job = new ArrayList<>();

                jobArrayList = DBConnect.ReadJobWithButton();
                equipmentListArrayList = DBConnect.ReadEquipmentList();

                for(Job job : jobArrayList){
                    if(job.getContractorID() == contractor.getID()){
                        use_job.add(job);
                    }
                }


                ObservableList<Job> jobObservableList = FXCollections.observableArrayList(use_job);

                JobID.setCellValueFactory(new PropertyValueFactory<>("JobID"));
                Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
                Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
                Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
                ID.setCellValueFactory(new PropertyValueFactory<>("ContractorID"));
                Detail.setCellValueFactory(new PropertyValueFactory<>("Detail"));


                joblist.setItems(jobObservableList);
            }
        });

    }

    @FXML
    private void GoBackOnAction(Event event)throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void GoAddJobOnAction(Event event)throws IOException {

        FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(), "AddJob.fxml");
        SceneChanger.ChangeSceneWithLoaderOnAction(add, "AddJob.fxml", loader);
        AddJobController addJobController = loader.getController();
        addJobController.SetContractor(contractor);
    }

}
