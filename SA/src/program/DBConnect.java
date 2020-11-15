package program;

import model.Contractor;
import model.Equipment;

import java.sql.*;
import java.util.ArrayList;

public class DBConnect {

    public static ArrayList<Contractor> ReadContractor(){

        Connection connection = null;
        ArrayList<Contractor> contractorArrayList = new ArrayList<>();

        String sql = "SELECT ID, Name, Username, Password FROM Contractor";

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                contractorArrayList.add(new Contractor(
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Username"),
                        resultSet.getString("Password")));
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }

        return contractorArrayList;
    }

    public static void WriteContractor(String name, String username, String password){

        Connection connection = null;
        String sql = "INSERT INTO Contractor(name, username, password) VALUES(?,?,?)";

        try{
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,username);
            preparedStatement.setString(3,password);
            preparedStatement.executeUpdate();
            System.out.println("Write Success");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static void WriteEquipment(String name, String price){

        Connection connection = null;
        String sql = "INSERT INTO Equipment(name, price) VALUES(?,?,?)";

        try{
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,price);
            preparedStatement.executeUpdate();
            System.out.println("Write Success");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

}
