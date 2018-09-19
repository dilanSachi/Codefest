package sample;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
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
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import static java.lang.Integer.parseInt;


public class Report {

    void generateReport(ArrayList<ResultSet> x) throws Exception{

        PDDocument document = new PDDocument();

        for (ResultSet r : x) {

            DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();
            while (r.next()) {
                System.out.println(r.getString("Error")+" "+r.getString("Month")+"-"+r.getString("Year"));
                lineDataset.addValue((Integer.parseInt(r.getString("Error"))), "Errors", r.getString("Month")+"-"+r.getString("Year"));
                lineDataset.addValue((Integer.parseInt(r.getString("Total"))), "Total", r.getString("Month")+"-"+r.getString("Year"));
            }
            System.out.println("x");
            JFreeChart chart = ChartFactory.createLineChart("Errors", "Date", "Errors", lineDataset);

            try {
                ChartUtilities.saveChartAsJPEG(new File("/home/madnisal/chart.jpg"), chart, 500, 300);
            } catch (Exception e) {
                System.out.println(e);
            }

            try {
                PDPage page = new PDPage(PDPage.PAGE_SIZE_A4);
                document.addPage(page);

                InputStream in = new FileInputStream(new File("/home/madnisal/chart.jpg"));
                PDJpeg img = new PDJpeg(document, in);
                PDPageContentStream contentStream = new PDPageContentStream(document, page);
                contentStream.drawImage(img, 10, 300);
                contentStream.close();

            } catch (IOException e) {
                System.out.println(e+"x");
            }
        }
        document.save("/home/madnisal/save.pdf");
        document.close();


    }
}