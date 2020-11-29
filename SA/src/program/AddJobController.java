package program;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import model.Contractor;
import model.Job;

import java.io.IOException;
import java.util.ArrayList;

public class AddJobController {

    @FXML
    private Button back,submit;

    @FXML
    private TextField JobType,JobAddress, JobBudget;

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
            return;
        }
        else if (CheckJobAddress()){
            return;
        }
        else if (CheckJobType()){
            return;
        }
        else if (CheckBudget()){
            return;
        }

        int budget = Integer.parseInt(JobBudget.getText());
        DBConnect.WriteJob(JobType.getText(), JobAddress.getText(), JobDate.getValue().toString(), "Wait for Request", budget, contractor.getID());

//        jobArrayList = DBConnect.ReadJob();
//
//        for(Job job : jobArrayList){
//            if(job.getAddress().equals(JobAddress.getText()) && job.getType().equals(JobType.getText())){
//                DBConnect.WriteEquipmentList(0,job.getJobID());
//            }
//        }

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

        if(JobType.getText().equals("") || JobAddress.getText().equals("") || JobDate.getValue().toString().equals("") || JobBudget.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Please enter all information", ButtonType.OK);
            alert.show();
            return true;
        }
        return false;
    }

    private boolean CheckJobAddress(){
        try {
            int a = Integer.parseInt(JobBudget.getText());
        }catch (NumberFormatException e){
            return false;
        }
        Alert alert = new Alert(Alert.AlertType.WARNING,"Job Address must be character", ButtonType.OK);
        alert.show();
        return true;
    }

    private boolean CheckJobType(){
        try {
            int a = Integer.parseInt(JobBudget.getText());
        }catch (NumberFormatException e){
            return false;
        }
        Alert alert = new Alert(Alert.AlertType.WARNING,"Job Type must be character", ButtonType.OK);
        alert.show();
        return true;
    }

    private boolean CheckBudget(){
        try {
            int a = Integer.parseInt(JobBudget.getText());
            if(a<=0 || a>9999999){
                Alert alert = new Alert(Alert.AlertType.WARNING,"Budget should between 1-9999999", ButtonType.OK);
                alert.show();
                return true;
            }
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Budget must be number", ButtonType.OK);
            alert.show();
            return true;
        }
        return false;
    }


}
