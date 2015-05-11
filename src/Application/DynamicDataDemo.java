package Application;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demonstration application showing a time series chart where you can dynamically add
 * (random) data by clicking on a button.
 *
 */
public class DynamicDataDemo extends ApplicationFrame {
//    SerialPort serialPort;
//        /** The port we're normally going to use. */
//	/**The port we're normally going to use.*/
//    private static final String PORT_NAMES[] = {
//        "/dev/cu.usbmodemfa131",//USB1 de mi mac
//        "/dev/cu.usbmodemfd121",//USB2 de mi mac
//        "/dev/tty.usbserial-A9007UX1", // Mac OS X
//        "/dev/ttyUSB0", // Linux
//        "COM5"// Windows
//    };
//	/**
//	* A BufferedReader which will be fed by a InputStreamReader 
//	* converting the bytes into characters 
//	* making the displayed results codepage independent
//	*/
//	private BufferedReader input;
//	/** The output stream to the port */
//	//private OutputStream output;
//	/** Milliseconds to block while waiting for port open */
//	private static final int TIME_OUT = 2000;
//	/** Default bits per second for COM port. */
//	//private static final int DATA_RATE = 9600;
//    private static final int DATA_RATE = 115200;
    
    /****************************************************************************************/
    
    /** The time series data. */
    private TimeSeries series;

    /** The most recent value added. */
    private double lastValue = 0.0;
    
    private Arduino arduino;

    /**
     * Constructs a new demonstration application.
     *
     * @param title  the frame title.
     */
    public DynamicDataDemo(final String title) {

        super(title);
        this.series = new TimeSeries("Random Data", Millisecond.class);
        final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
        final JFreeChart chart = createChart(dataset);
//        initialize();
        arduino = new Arduino();
        arduino.setDataset(dataset);

        final ChartPanel chartPanel = new ChartPanel(chart);        

        final JPanel content = new JPanel(new BorderLayout());
        content.add(chartPanel);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(content);

    }

    /**
     * Creates a sample chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return A sample chart.
     */
    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
            "Dynamic Data Demo", 
            "Time", 
            "Value",
            dataset, 
            true, 
            true, 
            false
        );
        final XYPlot plot = result.getXYPlot();
        ValueAxis axis = plot.getDomainAxis();
        axis.setAutoRange(false);
        axis.setFixedAutoRange(10000.0);  // 60 seconds
        axis.setRange(0.0, 1025.0);
        axis = plot.getRangeAxis();
        axis.setRange(0.0, 200.0); 
        return result;
    }
    
    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
       
//    public void initialize() {
//                // the next line is for Raspberry Pi and 
//                // gets us into the while loop and was suggested here was suggested http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
////                System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");
//
//		CommPortIdentifier portId = null;
//		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
//
//		//First, Find an instance of serial port as set in PORT_NAMES.
//		while (portEnum.hasMoreElements()) {
//			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
//			for (String portName : PORT_NAMES) {
//				if (currPortId.getName().equals(portName)) {
//					portId = currPortId;
//					break;
//				}
//			}
//		}
//		if (portId == null) {
//			System.out.println("Could not find COM port.");
//			return;
//		}
//
//		try {
//			// open serial port, and use class name for the appName.
//			serialPort = (SerialPort) portId.open(this.getClass().getName(),
//					TIME_OUT);
//
//			// set port parameters
//			serialPort.setSerialPortParams(DATA_RATE,
//					SerialPort.DATABITS_8,
//					SerialPort.STOPBITS_1,
//					SerialPort.PARITY_NONE);
//
//			// open the streams
//			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
//			//output = serialPort.getOutputStream();
//
//			// add event listeners
//			serialPort.addEventListener(this);
//			serialPort.notifyOnDataAvailable(true);
//		} catch (Exception e) {
//			System.err.println(e.toString());
//		}
//	}
//
//	/**
//	 * This should be called when you stop using the port.
//	 * This will prevent port locking on platforms like Linux.
//	 */
//	public synchronized void close() {
//		if (serialPort != null) {
//			serialPort.removeEventListener();
//			serialPort.close();
//		}
//	}
//
//	/**
//	 * Handle an event on the serial port. Read the data and print it.
//	 */
//	public synchronized void serialEvent(SerialPortEvent oEvent) {
//		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
//			try {
//				String inputLine=input.readLine();
//                this.lastValue = new Double(inputLine).doubleValue();
//                this.series.add(new Millisecond(), this.lastValue);
//                System.out.println(inputLine);
//			} catch (Exception e) {
//				System.err.println(e.toString());
//			}
//		}
//		// Ignore all the other eventTypes, but you should consider the other ones.
//	}

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

        final DynamicDataDemo demo = new DynamicDataDemo("Dynamic Data Demo");
        demo.pack();
        //RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}



