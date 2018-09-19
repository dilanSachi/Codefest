package sample;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.FlowPane;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import java.io.File;
import javafx.event.*;
import org.jfree.chart.*;
import org.jfree.data.general.*;
import org.apache.pdfbox.pdmodel.*;
import java.io.InputStream;
import java.io.*;
import org.apache.pdfbox.pdmodel.edit.*;
import org.apache.pdfbox.pdmodel.graphics.xobject.*;
import org.apache.pdfbox.exceptions.COSVisitorException;

import java.util.HashMap;
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
import javafx.scene.control.Button;


public class Main extends Application {

    private Controller c = new Controller();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        primaryStage.setTitle("Production Line");






        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();

        NumberAxis x1 = new NumberAxis();
        NumberAxis y1 = new NumberAxis();

        x.setLabel("Time");
        y.setLabel("Errors");
        x1.setLabel("Time");
        y1.setLabel("Total");

        LineChart<Number, Number> LineChart = new LineChart<Number, Number>(x,y);
        LineChart<Number, Number> LineChart2 = new LineChart<Number, Number>(x1,y1);



        final XYChart.Series series = new XYChart.Series();
        final XYChart.Series series1 = new XYChart.Series();
        final XYChart.Series series2 = new XYChart.Series();
        final XYChart.Series series3 = new XYChart.Series();

        final XYChart.Series tseries = new XYChart.Series();
        final XYChart.Series tseries1 = new XYChart.Series();
        final XYChart.Series tseries2 = new XYChart.Series();
        final XYChart.Series tseries3 = new XYChart.Series();

        series.setName("Stage 1");
        series1.setName("Stage 2");
        series2.setName("Stage 3");
        series3.setName("Stage 4");

        tseries.setName("Stage 1");
        tseries1.setName("Stage 2");
        tseries2.setName("Stage 3");
        tseries3.setName("Stage 4");

        FlowPane root1 = new FlowPane();

        for(String d : ServerSide.LineDetails.keySet()){
            final Button myButton = new Button(d);
            myButton.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    myButton.setText("d");
                }
            });
            root1.getChildren().add(myButton);
        }

        class updatethread extends Thread{
            class updaterunnable implements Runnable{
                int Timer;
                public updaterunnable(int Timer){
                    this.Timer = Timer;
                }
                public void run(){
                    series.getData().add(new XYChart.Data((Number)Timer,(Number)(ServerSide.LineDetails.get("p1")[0][0]/10)));
                    series1.getData().add(new XYChart.Data((Number)Timer,(Number)(ServerSide.LineDetails.get("p1")[1][0]/10)));
                    series2.getData().add(new XYChart.Data((Number)Timer,(Number)(ServerSide.LineDetails.get("p1")[2][0]/10)));
                    series3.getData().add(new XYChart.Data((Number)Timer,(Number)(ServerSide.LineDetails.get("p1")[3][0]/10)));
                    tseries.getData().add(new XYChart.Data((Number)Timer,(Number)(ServerSide.LineDetails.get("p1")[0][1]/10)));
                    tseries1.getData().add(new XYChart.Data((Number)Timer,(Number)(ServerSide.LineDetails.get("p1")[1][1]/10)));
                    tseries2.getData().add(new XYChart.Data((Number)Timer,(Number)(ServerSide.LineDetails.get("p1")[2][1]/10)));
                    tseries3.getData().add(new XYChart.Data((Number)Timer,(Number)(ServerSide.LineDetails.get("p1")[3][1]/10)));
                    int[][] x = {{0,0},{0,0},{0,0},{0,0}};
                    synchronized (new Object()) {
                        ServerSide.LineDetails.put("p1", x);
                    }
                }
            }
            public void run(){
                int Timer = 0;
                try{
                    while(true){
                        Thread.sleep(10000);
                        Timer = Timer + 10;
                        Platform.runLater(new updaterunnable(Timer));
                    }
                }catch(Exception e){

                }

            }
        }

        Thread update = new updatethread();
        update.start();
        LineChart.getData().addAll(series);
        LineChart.getData().addAll(series1);
        LineChart.getData().addAll(series2);
        LineChart.getData().addAll(series3);
        LineChart2.getData().addAll(tseries);
        LineChart2.getData().addAll(tseries1);
        LineChart2.getData().addAll(tseries2);
        LineChart2.getData().addAll(tseries3);

        root1.getChildren().addAll(LineChart, LineChart2);

        primaryStage.setScene(new Scene(root1, 1200, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        ServerSide s = new ServerSide();
        Thread Serverthread = new Thread(s);
        Serverthread.start();
        launch(args);



        DatabaseConnection data = new DatabaseConnection();
        data.startConnection();
        Report r = new Report();
        try{
            r.generateReport(data.GetData());
        }catch(Exception e){
            System.out.println(e);
        }


    }
}
