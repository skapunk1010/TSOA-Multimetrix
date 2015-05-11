package Application;

import javax.swing.JFrame;

/**
 * @author erickmartinez
 */
public class Application extends JFrame{

    /**
     * @param args the command line argume
     * nts
     */
    public static void main(String[] args) {
        // TODO code application logic here
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainFrame x = new MainFrame();
                x.pack();
                x.setVisible(true);
            }
        });
    }
}
