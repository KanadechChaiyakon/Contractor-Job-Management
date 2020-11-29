package program;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

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

    public static ArrayList<Corporation> ReadCorporation(){

        Connection connection = null;
        ArrayList<Corporation> corporationArrayList = new ArrayList<>();

        String sql = "SELECT CorporationID, Name, Username, Password, Email, PhoneNumber FROM Corporation";

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                corporationArrayList.add(new Corporation(
                        resultSet.getInt("CorporationID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Username"),
                        resultSet.getString("Password"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber")));
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }

        return corporationArrayList;
    }

    public static void WriteCorporation(String name, String username, String password, String email, String phonenumber){

        Connection connection = null;
        String sql = "INSERT INTO Corporation(name, username, password, email, phonenumber) VALUES(?,?,?,?,?)";

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

        String sql = "SELECT JobID, Type, Address, Date, Status, Budget, ContractorID FROM Job";

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
                        resultSet.getString("Status"),
                        resultSet.getInt("Budget"),
                        resultSet.getInt("ContractorID")));
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }

        return jobArrayList;
    }

    public static ObservableList<Job> ReadJobWithButton(Contractor contractor){

        Connection connection = null;
        ObservableList<Job> jobArrayList = FXCollections.observableArrayList();

        String sql = "SELECT JobID, Type, Address, Date, Status, Budget, ContractorID FROM Job";

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
                        resultSet.getString("Status"),
                        resultSet.getInt("Budget"),
                        contractor));
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }

        return jobArrayList;
    }

    public static ObservableList<Job> ReadJobWithButton(Corporation corporation){

        Connection connection = null;
        ObservableList<Job> jobArrayList = FXCollections.observableArrayList();

        String sql = "SELECT JobID, Type, Address, Date, Status, Budget, ContractorID FROM Job";

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
                        resultSet.getString("Status"),
                        resultSet.getInt("Budget"),
                        corporation));
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }

        return jobArrayList;
    }


    public static void WriteJob(String Type, String Address, String Date, String Status, int Budget, Integer ContractorID){

        Connection connection = null;
        String sql = "INSERT INTO Job( Type, Address, Date, Status, Budget, ContractorID) VALUES(?,?,?,?,?,?)";

        try{
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,Type);
            preparedStatement.setString(2,Address);
            preparedStatement.setString(3,Date);
            preparedStatement.setString(4,Status);
            preparedStatement.setInt(5,Budget);
            preparedStatement.setInt(6,ContractorID);
            preparedStatement.executeUpdate();
            System.out.println("Write Success");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static void UpdateJobStatus(int JobID, String status){

        Connection connection = null;
        String sql = "UPDATE Job SET Status = ? WHERE JobID = ?";
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,status);
            preparedStatement.setInt(2,JobID);
            preparedStatement.executeUpdate();
            System.out.println("Update Success");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<EquipmentList> ReadEquipmentList(){

        Connection connection = null;
        ArrayList<EquipmentList> equipmentListArrayList = new ArrayList<>();

        String sql = "SELECT JobID, EquipmentID, Quantity, Amount, Detail FROM EquipmentList";

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                equipmentListArrayList.add(new EquipmentList(
                        resultSet.getInt("EquipmentID"),
                        resultSet.getInt("JobID"),
                        resultSet.getInt("Quantity"),
                        resultSet.getInt("Amount"),
                        resultSet.getString("Detail")));
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }

        return equipmentListArrayList;
    }

    public static void WriteEquipmentList(Integer JobID,Integer EquipmentID, Integer Quantity, Integer Amount, String Detail){

        Connection connection = null;
        String sql = "INSERT INTO EquipmentList(JobID, EquipmentID, Quantity, Amount, Detail) VALUES(?,?,?,?,?)";

        try{
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,JobID);
            preparedStatement.setInt(2,EquipmentID);
            preparedStatement.setInt(3,Quantity);
            preparedStatement.setInt(4,Amount);
            preparedStatement.setString(5,Detail);
            preparedStatement.executeUpdate();
            System.out.println("Write Success");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static void UpdateEquipmentList(Integer JobID, Integer EqupmentID, Integer Amount, Integer Quantity){

        Connection connection = null;
        String sql = "UPDATE EquipmentList SET Quantity, Amount = ?,? WHERE JobID, EquipmentID = ?,?";
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,Amount);
            preparedStatement.setInt(2,Quantity);
            preparedStatement.setInt(3,JobID);
            preparedStatement.setInt(4,EqupmentID);
            preparedStatement.executeUpdate();
            System.out.println("Update Success");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<Equipment> ReadEquipment(){

        Connection connection = null;
        ArrayList<Equipment> equipmentArrayList = new ArrayList<>();

        String sql = "SELECT EquipmentID, EquipmentName, Price FROM Equipment";

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                equipmentArrayList.add(new Equipment(
                        resultSet.getInt("EquipmentID"),
                        resultSet.getInt("Price"),
                        resultSet.getString("EquipmentName")));
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }

        return equipmentArrayList;
    }

    public static void WriteEquipment(String EquipmentName, Integer Price){

        Connection connection = null;
        String sql = "INSERT INTO Equipment(EquipmentName, Price) VALUES(?,?)";

        try{
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,EquipmentName);
            preparedStatement.setInt(2,Price);
//            preparedStatement.setInt(3,Amount);
//            preparedStatement.setInt(4,TotalPrice);
//            preparedStatement.setString(5,Brand);
//            preparedStatement.setString(6,Detail);
//            preparedStatement.setInt(7,EquipmentListID);
            preparedStatement.executeUpdate();
            System.out.println("Write Success");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static void DeleteEquipmentList(int EquipmentID, int JobID){

        Connection connection = null;
        String sql = "DELETE FROM EquipmentList WHERE EquipmentID = ? AND JobID = ?";

        try{
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,EquipmentID);
            preparedStatement.setInt(2,JobID);
            preparedStatement.executeUpdate();
            System.out.println("Delete Success");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

}
