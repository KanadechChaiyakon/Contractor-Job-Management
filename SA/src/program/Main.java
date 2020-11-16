package program;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Contractor");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void connect(){

        Connection connection = null;

        try {
            String url = "jdbc:sqlite:test.db";
            connection = DriverManager.getConnection(url);
            System.out.println("database connected");

            PreparedStatement create = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS 'Contractor' ('ID' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,'Name' TEXT, 'Username' TEXT, 'Password' TEXT)");
            PreparedStatement create2 = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS 'Job' ('JobID' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'Type' TEXT, 'Address' TEXT, 'Date' TEXT, 'ID' TEXT, FOREIGN KEY (ID) REFERENCES 'Contractor' ('ID'))");
            PreparedStatement create3 = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS 'Equipment' ('EquipmentID' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,'Name' TEXT, 'Price' INTEGER, 'Brand' TEXT, 'Detail' TEXT)");
            PreparedStatement create4 = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS 'EquipmentList' ('EquipmentListID' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,'Equipment Name' TEXT, 'Amount' INTEGER, 'TotalPrice' INTEGER, 'JobID' INTEGER, FOREIGN KEY (JobID) REFERENCES 'Job' ('JobID'))");

            create.execute();
            create2.execute();
            create3.execute();
            create4.execute();

        }catch (SQLException e){
            System.err.println(e.getMessage());
        }finally {
            try {
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException e){
                System.err.println(e.getMessage());
            }

        }
    }


    public static void main(String[] args) {
        connect();
        launch(args);
    }
}
