
package jpcappacketcapture;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import jpcap.*;


public class JpcapInterface extends javax.swing.JFrame {
    
    NetworkInterface[] Network_interface ;
    boolean CaptureState = false ;
    CaptureThread CAPTION ;
    JpcapCaptor JPC ;
    SendMailInterface SMail ; 
    
    private JPanel Panel1 ;
    private final JTabbedPane tabbed = new JTabbedPane();
    private JButton Stop ; 
    private JButton Save ; 
    private JButton Send ; 
    public static JTextArea TA_ShowPacket ; 
    
    int COUNTER = 0 ;
    int INDEX = 0 ;
    int i = 0 ;
    Random rand = new Random();
    int  n = rand.nextInt(1000);
    
    public JpcapInterface() {
        initComponents();
    }
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TextField = new javax.swing.JTextField();
        Scan = new javax.swing.JButton();
        LessMore = new javax.swing.JToggleButton();
        jScrollPane0 = new javax.swing.JScrollPane();
        TA_ShowInterface = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("PCapture"); // NOI18N
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldActionPerformed(evt);
            }
        });

        Scan.setText("Scan");
        Scan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ScanActionPerformed(evt);
            }
        });

        LessMore.setText("Less");
        LessMore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LessMoreActionPerformed(evt);
            }
        });

        TA_ShowInterface.setColumns(20);
        TA_ShowInterface.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        TA_ShowInterface.setRows(5);
        TA_ShowInterface.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.black, java.awt.Color.white));
        TA_ShowInterface.setMaximumSize(new java.awt.Dimension(1000, 1000));
        TA_ShowInterface.setMinimumSize(new java.awt.Dimension(1000, 1000));
        TA_ShowInterface.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                TA_ShowInterfaceAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane0.setViewportView(TA_ShowInterface);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane0, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Scan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(LessMore)
                        .addGap(26, 26, 26)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane0, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Scan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LessMore)
                        .addGap(25, 25, 25)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    private void TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldActionPerformed
        if (TextField.getText().equals("")){
            JOptionPane.showMessageDialog(null,
            " Empty Field ","ENTER",
            JOptionPane.ERROR_MESSAGE);
        }else {
            int T = Integer.parseInt(TextField.getText());
            for(i = 0 ; i<COUNTER ; i++){
            if (T > -1 && T < COUNTER ){
                AddJTabbleToFrame(T);
                CaptureState = true ;
                TA_ShowPacket.setText("");
                ShowCapture();
                TextField.setText("");
                break;
            }
        else {
            JOptionPane.showMessageDialog(
            null,"OUT OF RANGE. #Interface = 0 - " + (COUNTER-1 + "."));
            break;
        }
            }
        }
    }//GEN-LAST:event_TextFieldActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        TA_ShowInterface.setBounds(5, 8, 746, 183);
        TA_ShowInterface.setBackground(Color.BLACK);
        TA_ShowInterface.setWrapStyleWord(true);
        TA_ShowInterface.setDisabledTextColor(Color.GREEN);
        TA_ShowInterface.setEnabled(false);
        this.setBounds(300, 150, 894, 239);
        LessMore.setEnabled(false);   
    }//GEN-LAST:event_formComponentShown

    private void LessMoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LessMoreActionPerformed
        if (LessMore.isSelected()){
            this.setBounds(300, 150, 894, 239);
            LessMore.setText("More");
        }else{
            LessMore.setText("Less");
            this.setBounds(300, 150, 894, 550);
        }
    }//GEN-LAST:event_LessMoreActionPerformed

    private void ScanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ScanActionPerformed
        if (TextField.getText().equals("")){
            JOptionPane.showMessageDialog(null,
            " Empty Field ","ENTER",
            JOptionPane.ERROR_MESSAGE);
        }else {
            int T = Integer.parseInt(TextField.getText());
            for(i = 0 ; i<COUNTER ; i++){
            if (T > -1 && T < COUNTER ){
                AddJTabbleToFrame(T);
                CaptureState = true ;
                TA_ShowPacket.setText("");
                ShowCapture();
                TextField.setText("");
                break;
            }
        else {
            JOptionPane.showMessageDialog(
            null,"OUT OF RANGE. #Interface = 0 - " + (COUNTER-1 + "."));
            break;
        }
            }
        }
    }//GEN-LAST:event_ScanActionPerformed

    private void TA_ShowInterfaceAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_TA_ShowInterfaceAncestorAdded
        Network_interface = JpcapCaptor.getDeviceList();
        TA_ShowInterface.setText("");
        for (i = 0; i < Network_interface.length ; i++ ){
        TA_ShowInterface.append("\n+--------------+");    
        TA_ShowInterface.append( "\n|"+"intreface : " + i + " |\n");
        TA_ShowInterface.append("+-------------+-----------------------------------+\n"); 
        TA_ShowInterface.append("|Description : " + Network_interface[i].description+"\n");
        TA_ShowInterface.append("|DataLinDescription : " + Network_interface[i].datalink_description+"\n");
        TA_ShowInterface.append("+-------------------------------------------------+\n"); 
//            TextArea.append("\nAddresses : " + Network_interface[i].addresses);
//            TextArea.append("\nLoopback : " + Network_interface[i].loopback);
//            TextArea.append("\nMac_address : " + Network_interface[i].mac_address);
            COUNTER ++ ;
        }
    }//GEN-LAST:event_TA_ShowInterfaceAncestorAdded
        
    public void AddJTabbleToFrame(int T){
        tabbed.setBounds(6, 200, 862, 300);
        INDEX = T ;
        LessMore.setEnabled(true);            
        AddPanelToTabble(T ,tabbed);
        this.setBounds(300, 150, 894, 550);
    }
    
    public void AddPanelToTabble(int i, JTabbedPane e){
        
        Panel1  = new JPanel();
        Panel1.setBackground(Color.BLACK);
        Panel1.setBorder(null);

        e.add(" Scan Network "+ i , Panel1);
        add(e);
        
        AddTextAreaToPanel(Panel1); 
        AddButtonToPanel(Panel1);
       }
        
    public void AddTextAreaToPanel(JPanel PNL0){ 
        TA_ShowPacket = new JTextArea();
        TA_ShowPacket.setBackground(Color.BLACK);
        TA_ShowPacket.setLineWrap(true);
        //TA_ShowPacket.setWrapStyleWord(true);
        TA_ShowPacket.setDisabledTextColor(Color.GREEN);
        TA_ShowPacket.setEnabled(false);
        Font font = TA_ShowPacket.getFont();
        float size = font.getSize() + 2.0f;
        TA_ShowPacket.setFont( font.deriveFont(size));
 
        JScrollPane scrollPane = new JScrollPane(TA_ShowPacket);
        scrollPane.setBounds(0, 0, 855, 220);
        PNL0.add(scrollPane);
    }
    
    public void AddButtonToPanel(JPanel PNL){

        Stop = new JButton("Stop");
        Save = new JButton("Save");
        Send = new JButton("Send");
        
        PNL.setLayout(null);
        Stop.setSize(100, 25);
        Save.setSize(100, 25);
        Send.setSize(100, 25);
        Stop.setLocation(240, 233);
        Save.setLocation(360, 233);
        Send.setLocation(480, 233);
        
        
        
        Stop.setBackground(Color.RED);
        Save.setBackground(Color.RED);
        Send.setBackground(Color.RED);
        
        
        Stop.setForeground(Color.WHITE);
        Save.setForeground(Color.WHITE);
        Send.setForeground(Color.WHITE);

        
        PNL.add(Stop);
        PNL.add(Save);
        PNL.add(Send);
        
        AddActionLisenarToStop();
        AddActionLisenarToSave();
        AddActionLisenarToSend();
    }
   
    public void AddActionLisenarToStop(){
        Stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Stop();

        }
    });  
}
    
    public void AddActionLisenarToSave(){
        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    try {
                        Save(n);
                    } catch (IOException ex) {
                        Logger.getLogger(JpcapInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }
    });
}
        
    public void AddActionLisenarToSend(){
        Send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Send();
            }
    });        
}
    
    public void Save(int C) throws IOException {
        File PC_Folder = new File("C:\\JpcapCaptuerPacket");
        if (!PC_Folder.exists()){
            PC_Folder.mkdir();
        }
        String CaptureData = TA_ShowPacket.getText();
            File Data = new File("C:\\JpcapCaptuerPacket\\CaptureData"+C+".txt");
            try (FileOutputStream DataStream = new FileOutputStream(Data); 
                PrintStream OUT = new PrintStream(DataStream)) {
                OUT.print(CaptureData);
                JOptionPane.showMessageDialog(null, "Data Save");
            }
        }
        
    public void Send(){ 
        SMail = new SendMailInterface(); 
        SMail.setVisible(true);
    }
    
    public void Stop(){
        CaptureState = false ; 
        CAPTION.finished();
    }
    
    public void SaveAuto(int j) throws IOException{
        File PC_Folder = new File("C:\\JpcapCaptuerPacket");
        if (!PC_Folder.exists()){
            PC_Folder.mkdir();
        }
        String AutoCaptureData = TA_ShowPacket.getText();
            File Data = new File("C:\\JpcapCaptuerPacket\\CaptureData"+j+".txt");
            try (FileOutputStream DataStream = new FileOutputStream(Data); 
                PrintStream OUT = new PrintStream(DataStream)) {
                OUT.print(AutoCaptureData);
            }  
        }
    
    public void ShowCapture(){
        CAPTION = new CaptureThread() {
            @Override
            public Object construct() {
                TA_ShowPacket.setText("\n now caption on interface : " + INDEX + " .... " + 
                       "\n ------------------------------------------"+
                       "\n-------------------------------------------\n");
                try {
                    JPC = JpcapCaptor.openDevice(
                    Network_interface[INDEX], 65535, false, 20);
                    int  rn = rand.nextInt(1000);
                        while(CaptureState){
                        JPC.processPacket(1, new PacketContents());
                        SaveAuto(rn);
                    }
                    
                    JPC.close();
                } catch (IOException x){
                    System.out.print(x); 
                }
                return 0; 
            }
            @Override
            public void finished(){
                this.interrupt();
            }
        };
        CAPTION.start();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton LessMore;
    private javax.swing.JButton Scan;
    private javax.swing.JTextArea TA_ShowInterface;
    public javax.swing.JTextField TextField;
    private javax.swing.JScrollPane jScrollPane0;
    // End of variables declaration//GEN-END:variables
}




