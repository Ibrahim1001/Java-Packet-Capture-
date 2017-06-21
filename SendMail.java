package jpcappacketcapture;


import java.util.Properties;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;



public class SendMail extends SendMailInterface {

public static void Send(JTextField TextfieldSend) {

    final String username = "captureimam@gmail.com";
    final String password = "abc321654";

    Properties props = new Properties();
    props.put("mail.smtp.auth", true);
    props.put("mail.smtp.starttls.enable", true);
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

    try {    
        String eMail = TextfieldSend.getText(); 
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(eMail));
        message.setSubject("Testing Subject");
        message.setText("oooooooooooooooooooo");

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();

        messageBodyPart = new MimeBodyPart();
        String file = FilePath ;
        String fileName = "JavaFileCapture";
        DataSource source = new FileDataSource(file);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);
        Transport.send(message);
        
    } catch (MessagingException e) {
        e.printStackTrace();
    }
  }
}