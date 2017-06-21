
package jpcappacketcapture;

public class JPCMain extends JpcapInterface {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
        new JpcapInterface().setVisible(true);
            }
        });
    }
}
