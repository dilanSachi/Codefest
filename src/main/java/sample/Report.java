package sample;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.chart.DJChart;
import ar.com.fdvs.dj.domain.chart.DJChartOptions;
import ar.com.fdvs.dj.domain.chart.builder.DJBarChartBuilder;
import ar.com.fdvs.dj.domain.chart.builder.DJPieChartBuilder;
import ar.com.fdvs.dj.domain.chart.plot.DJAxisFormat;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;




public class Report {

    void generateReport(){
        DefaultPieDataset pieDataset = new DefaultPieDataset();
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
        }
    }
}