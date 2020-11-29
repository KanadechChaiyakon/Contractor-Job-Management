package program;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Contractor;
import model.Corporation;
import model.Equipment;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginController {

    @FXML
    private Button login, register;

    @FXML
    private TextField username,password;

    @FXML
    private Label usernameincorrect, passwordincorrect, nullinput;

    private ArrayList<Contractor> contractors;

    private ArrayList<Corporation> corporations;

    private ArrayList<Equipment> equipments;

    private ArrayList<String> ename =  new ArrayList<String>(){{
        add("สายไฟ(30เมตร)");
        add("ปลั๊กไฟติดผนัง");
        add("แผงควบคุมไฟฟ้า");
        add("สวิตช์ไฟติดผนัง");
        add("หลอดไฟLED");
        add("หลอดไส้");
        add("เบรกเกอร์");
        add("ท่อตรง(ขนาด1นิ้วยาวภเมตร)");
        add("ตัวยึงท่อ");
        add("ท่ออ่อน(ขนาด16มม.ยาว5เมตร)");
        add("รางเก็บสายไฟ(2เมตร)");
        add("กล่องพักสายไฟ(ขนาด4x4นิ้ว)");
    }};

    private ArrayList<Integer> eprice = new ArrayList<Integer>(){{
        add(450);
        add(100);
        add(2500);
        add(100);
        add(230);
        add(50);
        add(300);
        add(130);
        add(5);
        add(50);
        add(50);
        add(80);
    }};

    public void initialize(){
        contractors = DBConnect.ReadContractor();
        corporations = DBConnect.ReadCorporation();
        equipments = DBConnect.ReadEquipment();

        if(equipments.size() == 0){
            int n = ename.size();
            for (int i=0 ; i<n; i++){
                DBConnect.WriteEquipment(ename.get(i),eprice.get(i));
            }
        }
    }


    @FXML
    private void LoginOnAction(Event event)throws IOException {

        int checkusername = 0;

        usernameincorrect.setOpacity(0);
        passwordincorrect.setOpacity(0);
        nullinput.setOpacity(0);

        if(username.getText().equals("") || password.getText().equals("")){
            nullinput.setOpacity(1);
            return;
        }
        for (Contractor contractor : contractors){
            System.out.println(contractor.getUsername());
            if(username.getText().equals(contractor.getUsername())){
                if(password.getText().equals(contractor.getPassword())){

                    FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(),"JobList.fxml");
                    SceneChanger.ChangeSceneWithLoaderOnAction(login,"JobList.fxml",loader);
                    JobListController jobListController = loader.getController();
                    jobListController.setContractor(contractor);


                }else {
                    passwordincorrect.setOpacity(1);
                    return;
                }
            }
            else {
                checkusername += 1;
            }
        }

        for (Corporation corporation : corporations){
            System.out.println(corporation.getUsername());
            if(username.getText().equals(corporation.getUsername())){
                if(password.getText().equals(corporation.getPassword())){

                    FXMLLoader loader = SceneChanger.GetLoaderOnAction(getClass(),"JobOffer.fxml");
                    SceneChanger.ChangeSceneWithLoaderOnAction(login,"JobOffer.fxml",loader);
                    JobOfferController jobOfferController = loader.getController();
                    jobOfferController.setCorporation(corporation);


                }else {
                    passwordincorrect.setOpacity(1);
                    return;
                }
            }
            else {
                checkusername += 1;
            }
        }

        if(checkusername == contractors.size()+corporations.size()){
            usernameincorrect.setOpacity(1);
        }
    }

    @FXML
    private void GoRegisterOnAction(Event event)throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene scene = new Scene(loader);
        Stage stage = (Stage) register.getScene().getWindow();
        stage.setScene(scene);
    }

}
