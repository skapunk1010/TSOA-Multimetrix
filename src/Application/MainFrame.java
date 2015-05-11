package Application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


/**
 *
 * @author erickmartinez
 */
public class MainFrame extends JFrame {
    private Chart chart;
    private Arduino arduino;
    
    public MainFrame(){
        initComponents();
        chart = new Chart();
        arduino = new Arduino();
        arduino.setDataset(chart.getDataset());
        panelGraficaECG.setLayout(new BorderLayout());
        panelGraficaECG.add(chart.getChartPanel(),BorderLayout.CENTER);
        lblStatusPort.setText("No se ha conectado ningún dispositivo");
        loadPortsToComboBox();
        
        new Thread(new Runnable(){
            @Override
            public void run() {
                while(true){
                    try {
                        chart.addData(arduino.getLastECGValue());
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }
    
    private void loadPortsToComboBox(){
        cboPorts.removeAllItems();
        String[] portNames = arduino.getNameOfPortsOfComputer();
        for(String name : portNames){
            cboPorts.addItem(name);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGraficaECG = new javax.swing.JPanel();
        panelBpm = new javax.swing.JPanel();
        lblBpm = new javax.swing.JLabel();
        panelTermometro = new javax.swing.JPanel();
        lblTermometro = new javax.swing.JLabel();
        panelOximetro = new javax.swing.JPanel();
        lblOximetro = new javax.swing.JLabel();
        lblStatusPort = new javax.swing.JLabel();
        cboPorts = new javax.swing.JComboBox();
        btnConnect = new javax.swing.JButton();
        btnDisconnect = new javax.swing.JButton();

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
        });

        panelGraficaECG.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelGraficaECG.setPreferredSize(new java.awt.Dimension(854, 580));

        org.jdesktop.layout.GroupLayout panelGraficaECGLayout = new org.jdesktop.layout.GroupLayout(panelGraficaECG);
        panelGraficaECG.setLayout(panelGraficaECGLayout);
        panelGraficaECGLayout.setHorizontalGroup(
            panelGraficaECGLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 850, Short.MAX_VALUE)
        );
        panelGraficaECGLayout.setVerticalGroup(
            panelGraficaECGLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 552, Short.MAX_VALUE)
        );

        panelBpm.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelBpm.setPreferredSize(new java.awt.Dimension(145, 200));

        lblBpm.setFont(new java.awt.Font("Digital-7", 0, 90)); // NOI18N
        lblBpm.setForeground(new java.awt.Color(255, 0, 0));
        lblBpm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBpm.setText("90");

        org.jdesktop.layout.GroupLayout panelBpmLayout = new org.jdesktop.layout.GroupLayout(panelBpm);
        panelBpm.setLayout(panelBpmLayout);
        panelBpmLayout.setHorizontalGroup(
            panelBpmLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelBpmLayout.createSequentialGroup()
                .add(21, 21, 21)
                .add(lblBpm, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 94, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBpmLayout.setVerticalGroup(
            panelBpmLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelBpmLayout.createSequentialGroup()
                .addContainerGap()
                .add(lblBpm, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 124, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelTermometro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelTermometro.setPreferredSize(new java.awt.Dimension(145, 200));

        lblTermometro.setFont(new java.awt.Font("Digital-7", 0, 90)); // NOI18N
        lblTermometro.setForeground(new java.awt.Color(255, 0, 0));
        lblTermometro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTermometro.setText("90");

        org.jdesktop.layout.GroupLayout panelTermometroLayout = new org.jdesktop.layout.GroupLayout(panelTermometro);
        panelTermometro.setLayout(panelTermometroLayout);
        panelTermometroLayout.setHorizontalGroup(
            panelTermometroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelTermometroLayout.createSequentialGroup()
                .add(22, 22, 22)
                .add(lblTermometro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 94, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        panelTermometroLayout.setVerticalGroup(
            panelTermometroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelTermometroLayout.createSequentialGroup()
                .addContainerGap()
                .add(lblTermometro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 121, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelOximetro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelOximetro.setPreferredSize(new java.awt.Dimension(145, 180));

        lblOximetro.setFont(new java.awt.Font("Digital-7", 0, 90)); // NOI18N
        lblOximetro.setForeground(new java.awt.Color(255, 0, 0));
        lblOximetro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOximetro.setText("90");

        org.jdesktop.layout.GroupLayout panelOximetroLayout = new org.jdesktop.layout.GroupLayout(panelOximetro);
        panelOximetro.setLayout(panelOximetroLayout);
        panelOximetroLayout.setHorizontalGroup(
            panelOximetroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelOximetroLayout.createSequentialGroup()
                .add(18, 18, 18)
                .add(lblOximetro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        panelOximetroLayout.setVerticalGroup(
            panelOximetroLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelOximetroLayout.createSequentialGroup()
                .add(14, 14, 14)
                .add(lblOximetro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 126, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(panelBpm, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(panelTermometro, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(panelOximetro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(panelGraficaECG, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(5, 5, 5))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(btnConnect)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnDisconnect)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(cboPorts, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 229, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lblStatusPort, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 260, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(5, 5, 5)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(panelBpm, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(panelTermometro, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                        .add(18, 18, 18)
                        .add(panelOximetro, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                        .add(5, 5, 5))
                    .add(layout.createSequentialGroup()
                        .add(panelGraficaECG, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(cboPorts, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(lblStatusPort)
                            .add(btnConnect)
                            .add(btnDisconnect))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        // TODO add your handling code here:
        if(!arduino.isConnected()){
            arduino.connect();
        }else{
            
        }
    }//GEN-LAST:event_btnConnectActionPerformed

    private void btnDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisconnectActionPerformed
        // TODO add your handling code here:
        if(arduino.isConnected()){
            arduino.close();
        }
    }//GEN-LAST:event_btnDisconnectActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnDisconnect;
    private javax.swing.JComboBox cboPorts;
    private javax.swing.JLabel lblBpm;
    private javax.swing.JLabel lblOximetro;
    private javax.swing.JLabel lblStatusPort;
    private javax.swing.JLabel lblTermometro;
    private javax.swing.JPanel panelBpm;
    private javax.swing.JPanel panelGraficaECG;
    private javax.swing.JPanel panelOximetro;
    private javax.swing.JPanel panelTermometro;
    // End of variables declaration//GEN-END:variables


}
