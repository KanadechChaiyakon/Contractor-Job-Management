package program;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contractor;
import model.Equipment;
import model.EquipmentList;
import model.Job;

import java.sql.*;
import java.util.ArrayList;

public class DBConnect {

    public static ArrayList<Contractor> ReadContractor(){

        Connection connection = null;
        ArrayList<Contractor> contractorArrayList = new ArrayList<>();

        String sql = "SELECT ContractorID, Name, Username, Password, Email, PhoneNumber FROM Contractor";

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                contractorArrayList.add(new Contractor(
                        resultSet.getInt("ContractorID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Username"),
                        resultSet.getString("Password"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber")));
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }

        return contractorArrayList;
    }

    public static void WriteContractor(String name, String username, String password, String email, String phonenumber){

        Connection connection = null;
        String sql = "INSERT INTO Contractor(name, username, password, email, phonenumber) VALUES(?,?,?,?,?)";

        try{
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,username);
            preparedStatement.setString(3,password);
            preparedStatement.setString(4,email);
            preparedStatement.setString(5,phonenumber);
            preparedStatement.executeUpdate();
            System.out.println("Write Success");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<Job> ReadJob(){

        Connection connection = null;
        ArrayList<Job> jobArrayList = new ArrayList<>();

        String sql = "SELECT JobID, Type, Address, Date, ContractorID FROM Job";

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                jobArrayList.add(new Job(
                        resultSet.getInt("JobID"),
                        resultSet.getString("Type"),
                        resultSet.getString("Address"),
                        resultSet.getString("Date"),
                        resultSet.getInt("ContractorID")));
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }

        return jobArrayList;
    }

    public static ObservableList<Job> ReadJobWithButton(){

        Connection connection = null;
        ObservableList<Job> jobArrayList = FXCollections.observableArrayList();

        String sql = "SELECT JobID, Type, Address, Date, ContractorID FROM Job";

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                jobArrayList.add(new Job(
                        resultSet.getInt("JobID"),
                        resultSet.getString("Type"),
                        resultSet.getString("Address"),
                        resultSet.getString("Date"),
                        resultSet.getInt("ContractorID"),
                        "Yes"));
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }

        return jobArrayList;
    }


    public static void WriteJob(String Type, String Address, String Date, Integer ContractorID){

        Connection connection = null;
        String sql = "INSERT INTO Job( Type, Address, Date, ContractorID) VALUES(?,?,?,?)";

        try{
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,Type);
            preparedStatement.setString(2,Address);
            preparedStatement.setString(3,Date);
            preparedStatement.setInt(4,ContractorID);
            preparedStatement.executeUpdate();
            System.out.println("Write Success");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<EquipmentList> ReadEquipmentList(){

        Connection connection = null;
        ArrayList<EquipmentList> equipmentListArrayList = new ArrayList<>();

        String sql = "SELECT EquipmentListID, JobID, TotalCost FROM EquipmentList";

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                equipmentListArrayList.add(new EquipmentList(
                        resultSet.getInt("EquipmentListID"),
                        resultSet.getInt("JobID"),
                        resultSet.getInt("TotalCost")));
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }

        return equipmentListArrayList;
    }

    public static void WriteEquipmentList(Integer TotalCost,Integer JobID){

        Connection connection = null;
        String sql = "INSERT INTO EquipmentList(TotalCost, JobID) VALUES(?,?)";

        try{
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,TotalCost);
            preparedStatement.setInt(2,JobID);
            preparedStatement.executeUpdate();
            System.out.println("Write Success");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static void UpdateEquipmentList(Integer Cost, Integer EqupmentListID){

        Connection connection = null;
        String sql = "UPDATE EquipmentList SET TotalCost = ? WHERE EquipmentListID = ?";
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,Cost);
            preparedStatement.setInt(2,EqupmentListID);
            preparedStatement.executeUpdate();
            System.out.println("Update Success");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<Equipment> ReadEquipment(){

        Connection connection = null;
        ArrayList<Equipment> equipmentArrayList = new ArrayList<>();

        String sql = "SELECT EquipmentID, Price, Amount, TotalPrice, EquipmentName, Brand, Detail, EquipmentListID FROM Equipment";

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                equipmentArrayList.add(new Equipment(
                        resultSet.getInt("EquipmentID"),
                        resultSet.getInt("Price"),
                        resultSet.getInt("Amount"),
                        resultSet.getInt("TotalPrice"),
                        resultSet.getString("EquipmentName"),
                        resultSet.getString("Brand"),
                        resultSet.getString("Detail"),
                        resultSet.getInt("EquipmentListID")));
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }

        return equipmentArrayList;
    }

    public static void WriteEquipment(String EquipmentName, Integer Price, Integer Amount, Integer TotalPrice, String Brand, String Detail, Integer EquipmentListID){

        Connection connection = null;
        String sql = "INSERT INTO Equipment(EquipmentName, Price, Amount, TotalPrice, Brand, Detail, EquipmentListID) VALUES(?,?,?,?,?,?,?)";

        try{
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,EquipmentName);
            preparedStatement.setInt(2,Price);
            preparedStatement.setInt(3,Amount);
            preparedStatement.setInt(4,TotalPrice);
            preparedStatement.setString(5,Brand);
            preparedStatement.setString(6,Detail);
            preparedStatement.setInt(7,EquipmentListID);
            preparedStatement.executeUpdate();
            System.out.println("Write Success");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static void DeleteEquipment(int EquipmentID){

        Connection connection = null;
        String sql = "DELETE FROM Equipment WHERE EquipmentID = ?";

        try{
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,EquipmentID);
            preparedStatement.executeUpdate();
            System.out.println("Delete Success");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

}
