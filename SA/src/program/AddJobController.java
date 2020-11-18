package program;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Contractor;
import model.Job;

import java.io.IOException;
import java.util.ArrayList;

public class AddJobController {

    @FXML
    private Button back,submit;

    @FXML
    private TextField JobType,JobAddress;

    @FXML
    private DatePicker JobDate;

    @FXML
    private Label nullinput,submitcomplete;

    public Contractor contractor;

    private ArrayList<Job> jobArrayList;

    public void SetContractor(Contractor contractor){
        this.contractor = contractor;
    }

    @FXML
    private void SubmitOnAction(Event event)throws IOException{

        nullinput.setOpacity(0);

        if(CheckTextField()){
            nullinput.setOpacity(1);
            return;
        }

        DBConnect.WriteJob(JobType.getText(), JobAddress.getText(), JobDate.getValue().toString(), contractor.getID());

        jobArrayList = DBConnect.ReadJob();

        for(Job job : jobArrayList){
            if(job.getAddress().equals(JobAddress.getText()) && job.getType().equals(JobType.getText())){
                DBConnect.WriteEquipmentList(0,job.getJobID());
            }
        }

        FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(), "JobList.fxml");
        SceneChanger.ChangeSceneWithLoaderOnAction(submit, "JobList.fxml", loader);
        JobListController jobListController = loader.getController();
        jobListController.setContractor(contractor);
    }

    @FXML
    private void GoBackOnAction(Event event)throws IOException {
        FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(), "JobList.fxml");
        SceneChanger.ChangeSceneWithLoaderOnAction(back, "JobList.fxml", loader);
        JobListController jobListController = loader.getController();
        jobListController.setContractor(contractor);
    }

    private boolean CheckTextField(){

        if(JobType.getText().equals("") || JobAddress.getText().equals("") || JobDate.getValue().toString().equals("")){
            return true;
        }
        return false;
    }


}
