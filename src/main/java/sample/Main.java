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

        /*DefaultPieDataset pieDataset = new DefaultPieDataset();
        pieDataset.setValue("Chrome", new Integer(42));
        pieDataset.setValue("Explorer", new Integer(24));
        pieDataset.setValue("Firefox", new Integer(24));
        pieDataset.setValue("Safari", new Integer(12));
        pieDataset.setValue("Opera", new Integer(8));
        JFreeChart chart = ChartFactory.createPieChart3D(
                "Browser Popularity", // Title
                pieDataset, // Dataset
                true, // Show legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );
        try {
            ChartUtilities.saveChartAsJPEG(new File("/home/madnisal/chart.jpg"), chart, 500, 300);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {

            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDPage.PAGE_SIZE_A4);
            document.addPage(page);

            InputStream in = new FileInputStream(new File("/home/madnisal/chart.jpg"));
            PDJpeg img = new PDJpeg(document, in);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.drawImage(img, 10, 300);
            contentStream.close();

            document.save("/home/madnisal/save.pdf");
            document.close();
        } catch (IOException e) {
            System.out.println(e+"x");
        } catch (COSVisitorException cos) {
            System.out.println(cos);
        }*/
        /*try {
            JasperPrint jp1 = DynamicJasperHelper.generateJasperPrint(
                    new Report().getPieReport(), new ClassicLayoutManager(),
                    DummyDB.getListOfCars());
            JasperPrint jp2 = DynamicJasperHelper.generateJasperPrint(
                    new Report().getBarReport(), new ClassicLayoutManager(),
                    DummyDB.getListOfCars());

            List<JRPrintPage> pages = jp2.getPages();
            for (int j = 0; j < pages.size(); j++) {
                JRPrintPage object = (JRPrintPage) pages.get(j);
                jp1.addPage(object);

            }
            JasperViewer.viewReport(jp1, false);
        }catch(Exception e){
            System.out.println(e);
        }*/
        /*DatabaseConnection data = new DatabaseConnection();
        data.startConnection();
        data.GetData();*/
    }
}
