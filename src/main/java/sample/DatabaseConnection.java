package sample;
import java.sql.*;

public class DatabaseConnection {

    public void startConnection(){
        String dbUrl ="jdbc:mysql://localhost:3306/food";
        String username = "madnisal";
        String password = "password";
        try{
            Connection myConnection = DriverManager.getConnection(dbUrl,username,password);
            Statement myStatement = myConnection.createStatement();
            myStatement.executeUpdate("Insert into FoodProduct values (5,'Mango')");

        }catch(Exception e){
            System.out.println(e);
        }

    }

}
