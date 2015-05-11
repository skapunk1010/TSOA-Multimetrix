package Application;


import java.awt.Color;
import javax.swing.BorderFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.Range;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 *
 * @author erickmartinez
 */
public class Chart {
    
    /**
     * The number of subplots.
     */
    public static final int SUBPLOT_COUNT = 1;
    /**
     * The datasets.
     */
    private TimeSeriesCollection dataset;
    /**
     * The most recent value added to series 1.
     */
    private double lastValue = 0.0;
    /**
     * The plot of the chart
     */
    private final CombinedDomainXYPlot plot;
    /**
     * The domain range
     */
    private final TimeSeries series;
    /**
     * The axis with reference to Y axis
     */
    private final NumberAxis rangeYAxis;
    /**
     * The subplot which display on the chart
     */
    private final XYPlot subplot;
    /**
     * The axis with reference to X axis
     */
    private final ValueAxis axis;
    /**
     * The chart
     */
    private final JFreeChart chart;
    /**
     * The panel that content the chart
     */
    private final ChartPanel chartPanel;

    public Chart() {
        
        this.plot       = new CombinedDomainXYPlot(new DateAxis());
        this.series     = new TimeSeries("", Millisecond.class); //   Nombre eje X, tipo de dato
        this.dataset    = new TimeSeriesCollection(series);
        
        this.rangeYAxis = new NumberAxis("Valor del sensor de pulso");
        rangeYAxis.setAutoRangeIncludesZero(true);
        rangeYAxis.setRangeWithMargins(-1.0, 1030.0);//
        rangeYAxis.setAutoRange(false);//
        rangeYAxis.setRange(new Range(-1.0,800.0));
        rangeYAxis.setTickLabelPaint(Color.WHITE);       
        
        this.subplot = new XYPlot(this.dataset, null, rangeYAxis, new StandardXYItemRenderer());
        subplot.setBackgroundPaint(new Color(0,40,32)); ///
        subplot.setDomainGridlinePaint(Color.GREEN);
        subplot.setRangeGridlinePaint(Color.GREEN);
        plot.add(subplot);

        this.chart = new JFreeChart(null, plot);
        chart.setBorderPaint(Color.black);
        chart.setBorderVisible(true);
        chart.setBackgroundPaint(new Color(0,40,32));
        
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setWeight(5); //new Color(2,196,8)
        
        
        this.axis = plot.getDomainAxis();
        axis.setAutoRange(false);
        axis.setFixedAutoRange(10000.0);  // 10 seconds
        axis.setTickLabelsVisible(false);
        axis.setTickLabelPaint(Color.WHITE);
        
        
        this.chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 470));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    }
    
    public JFreeChart getChart() {
        return chart;
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    public TimeSeriesCollection getDataset() {
        return dataset;
    }

    public double getLastValue() {
        return lastValue;
    }

    public CombinedDomainXYPlot getPlot() {
        return plot;
    }

    public NumberAxis getRangeYAxis() {
        return rangeYAxis;
    }

    public TimeSeries getSeries() {
        return series;
    }

    public XYPlot getSubplot() {
        return subplot;
    }
    
    public void addData(double value){
        this.lastValue = value;
        this.dataset.getSeries(0).add(new Millisecond(), value);
    }
}
