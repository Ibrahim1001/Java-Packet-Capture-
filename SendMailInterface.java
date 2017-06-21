package jpcappacketcapture;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.*;

public class SendMailInterface extends javax.swing.JFrame {
    public static String FilePath ; 
    public SendMailInterface() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TextFieldToSend = new javax.swing.JTextField();
        E_Field = new javax.swing.JLabel();
        Chose = new javax.swing.JButton();
        Send = new javax.swing.JButton();
        TextFieldPath = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        E_Field.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        E_Field.setText("E-Mail : ");

        Chose.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Chose.setText("Chose File ");
        Chose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChoseActionPerformed(evt);
            }
        });

        Send.setText("Send");
        Send.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.gray, java.awt.Color.darkGray));
        Send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendActionPerformed(evt);
            }
        });

        jLabel1.setText("Path : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(TextFieldPath, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Chose, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(E_Field, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextFieldToSend, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(Send, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(E_Field, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextFieldToSend, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextFieldPath, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Chose, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Send, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        this.setSize(389, 271);
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_formComponentShown

    private void ChoseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChoseActionPerformed
            JFileChooser FC = new JFileChooser(new File("C:\\JpcapCaptuerPacket"));
            FC.setDialogTitle("Chose a file");
            FC.showOpenDialog(null);
            File FileName = FC.getSelectedFile();
            FilePath = FileName.getAbsolutePath();
            TextFieldPath.setText(FilePath);
    }//GEN-LAST:event_ChoseActionPerformed

    private void SendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendActionPerformed
        if (TextFieldToSend.getText().equals("") && TextFieldPath.getText().equals("")){
            JOptionPane.showMessageDialog(null,
            " Empty Field ","ENTER",
            JOptionPane.ERROR_MESSAGE);
        }else if (TextFieldPath.getText().equals("")){
            JOptionPane.showMessageDialog(null,
            " Chose File ","ENTER",
            JOptionPane.ERROR_MESSAGE);
        }else if (TextFieldToSend.getText().equals("")){
            JOptionPane.showMessageDialog(null,
            " Input E-mail Address ","ENTER",
            JOptionPane.ERROR_MESSAGE);
        }else {
        SendMail.Send(TextFieldToSend);
        JOptionPane.showMessageDialog(null, "File Send");
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSED));
        }
    }//GEN-LAST:event_SendActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Chose;
    private javax.swing.JLabel E_Field;
    private javax.swing.JButton Send;
    public static javax.swing.JTextField TextFieldPath;
    public static javax.swing.JTextField TextFieldToSend;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
