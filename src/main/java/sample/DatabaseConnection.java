package sample;
import java.sql.*;
import java.util.HashMap;

public class DatabaseConnection {
    Connection myConnection;

    public void startConnection(){
        String dbUrl ="jdbc:mysql://localhost:3306/processline";
        String username = "madnisal";
        String password = "password";
        try{
            this.myConnection = DriverManager.getConnection(dbUrl,username,password);
        }catch(Exception e){
            System.out.println(e);
        }

    }

    public void UpdateData(HashMap<String,int[][]> x,int month, int year){
        try{
            Statement myStatement = this.myConnection.createStatement();
            for (String key : x.keySet()){
                myStatement.executeUpdate("CREATE TABLE IF NOT EXISTS "+key+" " +
                        "(Month int NOT NULL, Year int NOT NULL, productionLine int , produce int, PRIMARY KEY(Month,Year,productionLine));");
                for (int j=0;j<x.get(key).length;j++){
                    myStatement.executeUpdate("INSERT INTO "+key+" VALUES " +"("+month+","+year+","+j+","+x.get(key)[j][0]+")");
                }
            }

        }catch(Exception e){
            System.out.println(e);
        }

    }

}
