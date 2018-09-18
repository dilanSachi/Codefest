package sample;
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









abstract class ReportBase {

    protected AbstractColumn modelCol;
    protected AbstractColumn mfgCol;
    protected AbstractColumn qtyCol;
    protected AbstractColumn yearCol;

    public ReportBase() {
        modelCol = ColumnBuilder.getNew()
                .setColumnProperty("model", String.class.getName())
                .setTitle("Model").setWidth(70).build();
        mfgCol = ColumnBuilder.getNew()
                .setColumnProperty("manufacturer", String.class.getName())
                .setTitle("Manufacturer").setWidth(70).build();
        qtyCol = ColumnBuilder.getNew()
                .setColumnProperty("quantity", Integer.class.getName())
                .setTitle("Quantity").setWidth(70).build();
        yearCol = ColumnBuilder.getNew()
                .setColumnProperty("year", String.class.getName())
                .setTitle("Year").setWidth(70).build();
    }

    public Style getHeaderStyle() {
        Style headerStyle = new Style();
        headerStyle.setFont(Font.VERDANA_MEDIUM_BOLD);
        headerStyle.setBorderBottom(Border.PEN_2_POINT());
        headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
        headerStyle.setBackgroundColor(Color.DARK_GRAY);
        headerStyle.setTextColor(Color.WHITE);
        headerStyle.setTransparency(Transparency.OPAQUE);
        return headerStyle;
    }

    protected JRDataSource getDataSource() {
        JRDataSource dataSource = new JRBeanCollectionDataSource(
                DummyDB.getListOfProcessLines());
        return dataSource;

    }

}



public class Report extends ReportBase {

    private DynamicReportBuilder builder;

    public Report() throws Exception {
        builder = new DynamicReportBuilder();

        builder.addColumn(modelCol);
        builder.addColumn(mfgCol);
        builder.addColumn(qtyCol);
        builder.addColumn(yearCol);

        builder.setUseFullPageWidth(true);
        builder.setDefaultStyles(null, null, getHeaderStyle(), null);
    }

    private DJChart createBarChart() {

        DJAxisFormat categoryAxisFormat = new DJAxisFormat("category");
        categoryAxisFormat.setLabelFont(Font.ARIAL_SMALL);
        categoryAxisFormat.setLabelColor(Color.DARK_GRAY);
        categoryAxisFormat.setTickLabelFont(Font.ARIAL_SMALL);
        categoryAxisFormat.setTickLabelColor(Color.DARK_GRAY);
        categoryAxisFormat.setTickLabelMask("");
        categoryAxisFormat.setLineColor(Color.DARK_GRAY);

        DJAxisFormat valueAxisFormat = new DJAxisFormat("value");
        valueAxisFormat.setLabelFont(Font.ARIAL_SMALL);
        valueAxisFormat.setLabelColor(Color.DARK_GRAY);
        valueAxisFormat.setTickLabelFont(Font.ARIAL_SMALL);
        valueAxisFormat.setTickLabelColor(Color.DARK_GRAY);
        valueAxisFormat.setTickLabelMask("#,##0.0");
        valueAxisFormat.setLineColor(Color.DARK_GRAY);

        DJChart chart = new DJBarChartBuilder()
                .setX(20).setY(10).setWidth(500)
                .setHeight(250).setCentered(false)
                .setBackColor(Color.LIGHT_GRAY).setShowLegend(true)
                .setPosition(DJChartOptions.POSITION_FOOTER)
                .setTitle("Car Sales").setTitleColor(Color.DARK_GRAY)
                .setTitleFont(Font.ARIAL_BIG_BOLD)
                .setSubtitle("Models sold in year(s) 2009-2015")
                .setSubtitleColor(Color.DARK_GRAY)
                .setSubtitleFont(Font.GEORGIA_SMALL_BOLD)
                .setLegendColor(Color.DARK_GRAY)
                .setLegendFont(Font.ARIAL_SMALL_BOLD)
                .setLegendBackgroundColor(Color.WHITE)
                .setLegendPosition(DJChartOptions.EDGE_BOTTOM)
                .setTitlePosition(DJChartOptions.EDGE_TOP)
                .setLineStyle(DJChartOptions.LINE_STYLE_DOTTED)
                .setLineWidth(1)
                .setLineColor(Color.DARK_GRAY).setPadding(5)
                .setCategory((PropertyColumn) yearCol).addSerie(qtyCol)
                .setShowLabels(false).setCategoryAxisFormat(categoryAxisFormat)
                .setValueAxisFormat(valueAxisFormat).build();
        return chart;
    }

    private DJChart createPieChart() {
        DJChart chart = new DJPieChartBuilder()
                .setX(20).setY(10).setWidth(500)
                .setHeight(250).setCentered(false)
                .setBackColor(Color.LIGHT_GRAY).setShowLegend(true)
                .setPosition(DJChartOptions.POSITION_FOOTER)
                .setTitle("Car Sales").setTitleColor(Color.DARK_GRAY)
                .setTitleFont(Font.ARIAL_BIG_BOLD)
                .setSubtitle("Units sold in year(s) 2009-2015")
                .setSubtitleColor(Color.DARK_GRAY)
                .setSubtitleFont(Font.GEORGIA_SMALL_BOLD)
                .setLegendColor(Color.DARK_GRAY)
                .setLegendFont(Font.ARIAL_SMALL_BOLD)
                .setLegendBackgroundColor(Color.WHITE)
                .setLegendPosition(DJChartOptions.EDGE_BOTTOM)
                .setTitlePosition(DJChartOptions.EDGE_TOP)
                .setLineStyle(DJChartOptions.LINE_STYLE_DOTTED)
                .setLineWidth(1)
                .setLineColor(Color.DARK_GRAY).setPadding(5)
                .setKey((PropertyColumn) modelCol).addSerie(qtyCol)
                .setCircular(true).build();
        return chart;
    }

    public DynamicReport getPieReport() throws Exception {
        builder.addChart(createPieChart());
        return builder.build();
    }
    public DynamicReport getBarReport() throws Exception {
        builder.addChart(createBarChart());
        return builder.build();
    }
}