package sample;
import org.apache.lucene.index.ReaderSlice;

import java.sql.*;
import java.util.ArrayList;
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

    public void GetData(){
        ArrayList<ResultSet> d = new ArrayList<ResultSet>();
        Statement myStatement;
        ResultSet processes;
        try{
            myStatement = this.myConnection.createStatement();
            processes = myStatement.executeQuery("SELECT * from productionLineNames");
            while(processes.next()){
                for (int i =0 ;i<4;i++){
                    myStatement = this.myConnection.createStatement();
                    System.out.println("SELECT  * from "+processes.getString("name")+
                            " where productionLine = "+i);
                    ResultSet r = myStatement.executeQuery("SELECT  * from "+processes.getString("name")+
                            " where productionLine = "+i+";");
                    d.add(r);
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        System.out.println("d");
        try{
            for (ResultSet r : d){
                while(r.next()){
                    System.out.println(r.getString("produce"));
                }
            }
        }catch(Exception e){}


    }

}
