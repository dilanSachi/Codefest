package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import org.jfree.chart.*;
import org.jfree.data.general.*;
import org.apache.pdfbox.pdmodel.*;
import java.io.InputStream;
import java.io.*;
import org.apache.pdfbox.pdmodel.edit.*;
import org.apache.pdfbox.pdmodel.graphics.xobject.*;
import org.apache.pdfbox.exceptions.COSVisitorException;

import java.util.List;

import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;


//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.util.HashMap;
//
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        primaryStage.setTitle("Production Line");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        ServerSide s = new ServerSide();
        Thread Serverthread = new Thread(s);
        Serverthread.start();
        launch(args);
        DatabaseConnection data = new DatabaseConnection();
        data.startConnection();
        data.GetData();
        
        
        try {
            JasperPrint jp2 = DynamicJasperHelper.generateJasperPrint(
                    new Report().getBarReport(), new ClassicLayoutManager(),
                    DummyProcessDB.getListOfCars());

            
            JasperViewer.viewReport(jp2, false);
        }catch(Exception e){
            System.out.println('X');
        }
        
    }
}
