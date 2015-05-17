package Application;

import gnu.io.NoSuchPortException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.data.Range;
import javax.swing.*;

/**
 *
 * @author erickmartinez
 * @version 1.00.00
 */
public class MainFrame extends JFrame {

    private Chart chart;
    private Arduino arduino;

    public MainFrame() {
        initComponents();
        chart = new Chart();
        arduino = new Arduino();
        arduino.setDataset(chart.getDataset());
        panelGraficaECG.setLayout(new BorderLayout());
        panelGraficaECG.add(chart.getChartPanel(), BorderLayout.CENTER);
        lblStatusPort.setText("No se ha conectado ningún dispositivo");
        lblTermometroC.setText("0ºC");
        lblTermometroF.setText("0ºF");
        lblBpm.setText("0");
        loadPortsToComboBox();
        loadTimeRangeCombo();
        new Thread(new Runnable() {

            @Override
            public void run() {

                while (true) {
                    try {
                        chart.addData(arduino.getLastECGValue());
                        chart.getRangeYAxis().setRange(new Range(400.0,700.0));
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                double farenheit = 0.0;
                while (true) {
                    try {
                        lblBpm.setText(Double.toString(arduino.getLastBPMValue()));
                        lblTermometroC.setText(Double.toString(farenheit = arduino.getLastThermometerValue()) + "ºC");
                        lblTermometroF.setText(Double.toString((farenheit * 1.8) + 32) + "ºF");
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }

    private void loadPortsToComboBox() {
        cboPorts.removeAllItems();
        String[] portNames = arduino.getNameOfPortsOfComputer();
        if (portNames != null) {
            for (String name : portNames) {
                cboPorts.addItem(name);
            }
            cboPorts.setSelectedIndex(-1);
        }
    }

    private void loadTimeRangeCombo() {
        cboXTimeRange.removeAllItems();
        cboXTimeRange.addItem("5 segundos");
        cboXTimeRange.addItem("10 segundos");
        cboXTimeRange.addItem("15 segundos");
        cboXTimeRange.addItem("30 segundos");
        cboXTimeRange.addItem("45 segundos");
        cboXTimeRange.addItem("60 segundos");
        cboXTimeRange.setSelectedIndex(1); //default value
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGraficaECG = new javax.swing.JPanel();
        panelTermometro = new javax.swing.JPanel();
        lblTermometroF = new javax.swing.JLabel();
        lblTermometroTitulo = new javax.swing.JLabel();
        lblTermometroC = new javax.swing.JLabel();
        panelBpm = new javax.swing.JPanel();
        lblBpmTitulo = new javax.swing.JLabel();
        lblBpm = new javax.swing.JLabel();
        lblStatusPort = new javax.swing.JLabel();
        cboPorts = new javax.swing.JComboBox();
        btnConnect = new javax.swing.JButton();
        btnDisconnect = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        cboXTimeRange = new javax.swing.JComboBox();
        lblTiempo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Multimetrix");
        setBackground(new java.awt.Color(204, 204, 204));
        setForeground(new Color(0,40,32));
        setLocation(new java.awt.Point(20, 23));
        setMinimumSize(new java.awt.Dimension(1024, 600));
        setPreferredSize(new java.awt.Dimension(1024, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelGraficaECG.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelGraficaECG.setPreferredSize(new java.awt.Dimension(854, 580));

        org.jdesktop.layout.GroupLayout panelGraficaECGLayout = new org.jdesktop.layout.GroupLayout(panelGraficaECG);
        panelGraficaECG.setLayout(panelGraficaECGLayout);
        panelGraficaECGLayout.setHorizontalGroup(
            panelGraficaECGLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );
        panelGraficaECGLayout.setVerticalGroup(
            panelGraficaECGLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 484, Short.MAX_VALUE)
        );

        panelTermometro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelTermometro.setPreferredSize(new java.awt.Dimension(145, 200));

        lblTermometroF.setFont(new java.awt.Font("Digital-7", 0, 36)); // NOI18N
        lblTermometroF.setForeground(new java.awt.Color(255, 0, 0));
        lblTermometroF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTermometroF.setText("90");
        lblTermometroF.setPreferredSize(new java.awt.Dimension(40, 25));

        lblTermometroTitulo.setFont(new java.awt.Font("Digital-7", 0, 30)); // NOI18N
        lblTermometroTitulo.setForeground(new java.awt.Color(255, 0, 0));
        lblTermometroTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTermometroTitulo.setText("TEMPERATURA");
        lblTermometroTitulo.setPreferredSize(new java.awt.Dimension(40, 25));

        lblTermometroC.setFont(new java.awt.Font("Digital-7", 0, 36)); // NOI18N
        lblTermometroC.setForeground(new java.awt.Color(255, 0, 0));
        lblTermometroC.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTermometroC.setText("90");
        lblTermometroC.setPreferredSize(new java.awt.Dimension(40, 25));

        org.jdesktop.layout.GroupLayout panelTermometroLayout = new org.jdesktop.layout.GroupLayout(panelTermometro);
        panelTermometro.setLayout(panelTermometroLayout);
        panelTermometroLayout.setHorizontalGroup(
            panelTermometroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(lblTermometroTitulo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
            .add(panelTermometroLayout.createSequentialGroup()
                .add(0, 0, Short.MAX_VALUE)
                .add(lblTermometroF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 122, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(6, 6, 6))
            .add(panelTermometroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(panelTermometroLayout.createSequentialGroup()
                    .add(lblTermometroC, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 116, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(0, 130, Short.MAX_VALUE)))
        );
        panelTermometroLayout.setVerticalGroup(
            panelTermometroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, panelTermometroLayout.createSequentialGroup()
                .add(lblTermometroTitulo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lblTermometroF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(5, 5, 5))
            .add(panelTermometroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(org.jdesktop.layout.GroupLayout.TRAILING, panelTermometroLayout.createSequentialGroup()
                    .addContainerGap(40, Short.MAX_VALUE)
                    .add(lblTermometroC, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(5, 5, 5)))
        );

        panelBpm.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelBpm.setPreferredSize(new java.awt.Dimension(145, 200));

        lblBpmTitulo.setFont(new java.awt.Font("Digital-7", 0, 40)); // NOI18N
        lblBpmTitulo.setForeground(new java.awt.Color(255, 0, 0));
        lblBpmTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBpmTitulo.setText("BPM");
        lblBpmTitulo.setMaximumSize(new java.awt.Dimension(40, 25));
        lblBpmTitulo.setMinimumSize(new java.awt.Dimension(40, 25));
        lblBpmTitulo.setPreferredSize(new java.awt.Dimension(40, 25));

        lblBpm.setFont(new java.awt.Font("Digital-7", 0, 40)); // NOI18N
        lblBpm.setForeground(new java.awt.Color(255, 0, 0));
        lblBpm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBpm.setText("90");
        lblBpm.setMaximumSize(new java.awt.Dimension(40, 25));
        lblBpm.setMinimumSize(new java.awt.Dimension(40, 25));
        lblBpm.setPreferredSize(new java.awt.Dimension(40, 25));

        org.jdesktop.layout.GroupLayout panelBpmLayout = new org.jdesktop.layout.GroupLayout(panelBpm);
        panelBpm.setLayout(panelBpmLayout);
        panelBpmLayout.setHorizontalGroup(
            panelBpmLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(lblBpmTitulo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
            .add(panelBpmLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(panelBpmLayout.createSequentialGroup()
                    .addContainerGap()
                    .add(lblBpm, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelBpmLayout.setVerticalGroup(
            panelBpmLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelBpmLayout.createSequentialGroup()
                .add(lblBpmTitulo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 48, Short.MAX_VALUE))
            .add(panelBpmLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(org.jdesktop.layout.GroupLayout.TRAILING, panelBpmLayout.createSequentialGroup()
                    .addContainerGap(43, Short.MAX_VALUE)
                    .add(lblBpm, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(5, 5, 5)))
        );

        lblStatusPort.setText("Status Port");

        cboPorts.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnConnect.setText("Connect");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        btnDisconnect.setText("Disconnect");
        btnDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisconnectActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh Ports");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        cboXTimeRange.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboXTimeRange.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboXTimeRangeItemStateChanged(evt);
            }
        });

        lblTiempo.setText("Visibilidad:");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(5, 5, 5)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(panelGraficaECG, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1217, Short.MAX_VALUE)
                        .add(5, 5, 5))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(btnConnect)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btnDisconnect))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                .add(lblTiempo)
                                .add(15, 15, 15)))
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(cboPorts, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 229, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(lblStatusPort, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 333, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(btnRefresh)
                            .add(cboXTimeRange, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 229, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 18, Short.MAX_VALUE)
                        .add(panelBpm, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(panelTermometro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 250, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(5, 5, 5)
                .add(panelGraficaECG, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .add(5, 5, 5)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(panelTermometro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(panelBpm, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(34, 34, 34)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(btnConnect)
                                    .add(btnDisconnect)
                                    .add(cboPorts, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(lblStatusPort)))
                            .add(btnRefresh))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(cboXTimeRange, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(lblTiempo))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        // TODO add your handling code here:
        if (cboPorts.getSelectedIndex() != -1 && !arduino.isConnected()) {
            String portSelected = (String) cboPorts.getSelectedItem();
            try {
                arduino.connect(portSelected);
                lblStatusPort.setText("Arduino Connected on port " + portSelected);
                lblStatusPort.setForeground(Color.BLACK);
            } catch (NoSuchPortException ex) {
            } catch (gnu.io.PortInUseException piuex) {
                lblStatusPort.setText("El puerto está utilizado en otra aplicación.");
            } catch (Exception e) {
                System.err.println("Excepción initilize=" + e.toString());
                lblStatusPort.setText("Can't connect to port " + portSelected);
                lblStatusPort.setForeground(Color.RED);
            }
        } else {
        }
    }//GEN-LAST:event_btnConnectActionPerformed

    private void btnDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisconnectActionPerformed
        // TODO add your handling code here:
        if (cboPorts.getSelectedIndex() != -1 && arduino.isConnected()) {
            arduino.close();
            lblStatusPort.setText("No se ha conectado ningún dispositivo");
            lblStatusPort.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_btnDisconnectActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        loadPortsToComboBox();
        lblStatusPort.setText("No se ha conectado ningún dispositivo");
        lblStatusPort.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void cboXTimeRangeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboXTimeRangeItemStateChanged
        // TODO add your handling code here:
        if (cboXTimeRange.getSelectedIndex() != -1) {
            int index = cboXTimeRange.getSelectedIndex();
            int seconds;
            switch (index) {
                case 0:  //5 Segundos
                    seconds = 5;
                    break;
                case 1: //10 Segundos
                    seconds = 10;
                    break;
                case 2: //15 Segundos
                    seconds = 15;
                    break;
                case 3: //30 Segundos
                    seconds = 30;
                    break;
                case 4: //45 Segundos
                    seconds = 45;
                    break;
                case 5:  //60 Segundos
                    seconds = 60;
                    break;
                default: //default valie
                    seconds = 10;
                    break;

            }
            chart.setRange(seconds);
        }
    }//GEN-LAST:event_cboXTimeRangeItemStateChanged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        arduino.close();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnDisconnect;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox cboPorts;
    private javax.swing.JComboBox cboXTimeRange;
    private javax.swing.JLabel lblBpm;
    private javax.swing.JLabel lblBpmTitulo;
    private javax.swing.JLabel lblStatusPort;
    private javax.swing.JLabel lblTermometroC;
    private javax.swing.JLabel lblTermometroF;
    private javax.swing.JLabel lblTermometroTitulo;
    private javax.swing.JLabel lblTiempo;
    private javax.swing.JPanel panelBpm;
    private javax.swing.JPanel panelGraficaECG;
    private javax.swing.JPanel panelTermometro;
    // End of variables declaration//GEN-END:variables
}
