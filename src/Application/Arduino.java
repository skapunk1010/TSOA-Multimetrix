package Application;

import gnu.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Enumeration;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeriesCollection;

/**
 *
 * @author erickmartinez
 */
public class Arduino implements SerialPortEventListener {
    /**
     * Constant to represent the ECG value on the array received on the Arduino.
     */
    private static final int ECG_VALUE = 0;
    /**
     * Constant to represent the Thermometer value on the array received on the Arduino.
     */
    private static final int THERMOMETER_VALUE = 1;
    /**
     * Constant to represent the Oximeter value on the array received on the Arduino.
     */
    private static final int OXIMETER_VALUE = 2;
    /**
     * Constant to represent the BPM value on the array received on the Arduino.
     */
    private static final int BPM_VALUE = 4;
    /**
     * The last value received for the Arduino that represent the ECG sensor.
     */
    private double lastECGValue = 0.0;
    /**
     * The last value received for the arduino that represent the BPM.
     */
    private double lastBPMValue = 0.0;
    /**
     * The last value received for the Arduino that represent the Pulse Rate Sensor.
     */
    private double lastThermometerValue = 0.0;
    /**
     * The last value received for the Arduino that represent the oximeter.
     */
    private double lastOximeterValue = 0.0;
    /**
     * Dataset where it will added the data received.
     */
    private TimeSeriesCollection dataset;
    /**
     * Flag that represent if the Arduino is receiving data.
     */
    private boolean receivedData = false;
    /**
     * Port where the arduino is connected.
     */
    private SerialPort serialPort;
    /**
     * Ports detected on the computer.
     */
    private Enumeration portsOfComputer;
    /**
     * The port we're normally going to use.
     */
    private static final String PORT_NAMES[] = {
        "/dev/cu.usbmodemfa131",//USB1 de mi mac
        "/dev/cu.usbmodemfd121",//USB2 de mi mac
        "/dev/tty.usbserial-A9007UX1", // Mac OS X
        "/dev/ttyUSB0", // Linux
        "COM5"// Windows
    };
    /**
     * A BufferedReader which will be fed by a InputStreamReader converting the bytes into characters making
     * the displayed results codepage independent.
     */
    private BufferedReader input;
    /**
     * Milliseconds to block while waiting for port open.
     */
    private static final int TIME_OUT = 2000;
    /**
     * Default bits per second for COM port.
     */
    private static final int DATA_RATE = 115200;

    public Arduino() {
        //connect();
    }

    public synchronized void connect(String portName) throws Exception,NoSuchPortException {
        CommPortIdentifier portId = searchPort(portName);
        if (portId == null) {
            System.out.println("Could not find COM port.");
            throw new NoSuchPortException();
        }

        // open serial port, and use class name for the appName.
        this.serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);

        // set port parameters
        this.serialPort.setSerialPortParams(DATA_RATE,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);

        // open the streams
        input = new BufferedReader(new InputStreamReader(this.serialPort.getInputStream()));

        // add event listeners
        serialPort.addEventListener(this);
        serialPort.notifyOnDataAvailable(true);
        System.out.println("Arduino Done!");
        
    }
        
    
    private synchronized CommPortIdentifier searchPort(String portName){
        CommPortIdentifier portId = null;
        portsOfComputer = CommPortIdentifier.getPortIdentifiers();
        this.portsOfComputer = CommPortIdentifier.getPortIdentifiers();
        serialPort = null;

        //First, Find an instance of serial port as set in PORT_NAMES.
        while (portsOfComputer.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portsOfComputer.nextElement();
            if (currPortId.getName().equals(portName)) {
                portId = currPortId;
                break;
            }
        }
        return portId;
    }

    /**
     * This should be called when you stop using the port. This will prevent port locking on platforms like
     * Linux.
     */
    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    /**
     * Handle an event on the serial port. Read the data and print it.
     */
    @Override
    public void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                String inputLine = input.readLine();
                 this.receivedData = true;
                if (inputLine.startsWith("S")) {
                    this.lastECGValue = new Double(inputLine.substring(1)).doubleValue();
                }else if(inputLine.startsWith("B")){
                    this.lastBPMValue = new Double(inputLine.substring(1)).doubleValue();
                }else if(inputLine.startsWith("T")){
                    this.lastThermometerValue = new Double(inputLine.substring(1)).doubleValue();
                }
                this.receivedData = false;
                Thread.sleep(100);
            } catch (Exception e) {
                System.err.println("Excepción serialEvent=" + e.toString());
                this.receivedData = false;
            }
        }// Ignore all the other eventTypes, but you should consider the other ones.
    }
    /**
     * 
     */
    public TimeSeriesCollection getDataset() {
        return dataset;
    }

    public void setDataset(TimeSeriesCollection dataset) {
        this.dataset = dataset;
    }
    /**
     * 
     */
    public double getLastECGValue() {
        return lastECGValue;
    }

    public double getLastOximeterValue() {
        return lastOximeterValue;
    }

    public double getLastThermometerValue() {
        return lastThermometerValue;
    }
    
    public double getLastBPMValue(){
        return lastBPMValue;
    }

    public BufferedReader getInput() {
        return input;
    }

    public SerialPort getSerialPort() {
        return serialPort;
    }

    public boolean isReceivedData() {
        return receivedData;
    }

    public boolean isConnected() {
        return this.serialPort != null ? true : false;
    }

    public Enumeration getPortsOfComputer() {
        return portsOfComputer;
    }

    public String[] getNameOfPortsOfComputer() {
        Enumeration ports = CommPortIdentifier.getPortIdentifiers();

        if (ports != null) {
            String names = "";
            int numberOfPorts = 0;
            while (ports.hasMoreElements()) {
                CommPortIdentifier currPortId = (CommPortIdentifier) ports.nextElement();
                names += currPortId.getName() + ",";
                numberOfPorts++;
            }
            if (numberOfPorts > 0) {
                names = names.substring(0, names.length() - 1);
                String[] namePorts = names.split(",");
                return namePorts;
            } else {
                return null;
            }


        } else {
            return null;
        }
    }
}
