/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

 
import java.io.UnsupportedEncodingException;
import java.util.Date;
 
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 
public class EmailUtil {
 
    /**
     * Utility method to send simple HTML email
     * @param session
     * @param toEmail
     * @param subject
     * @param body
     */
public static void sendAttachmentEmail(Session session, String toEmail, String subject, String body){
    try{
         MimeMessage msg = new MimeMessage(session);
         msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
         msg.addHeader("format", "flowed");
         msg.addHeader("Content-Transfer-Encoding", "8bit");
         String subjectList = "Airport Parking Rates  ";
         
         msg.setFrom(new InternetAddress("repairs@mobileautomechanic.info", subjectList));
 
         msg.setReplyTo(InternetAddress.parse("repairs@mobileautomechanic.info", false));
 
         msg.setSubject(subject, "UTF-8");
 
         msg.setSentDate(new Date());
 
         msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
           
         // Create the message body part
         BodyPart messageBodyPart = new MimeBodyPart();
 
         // Fill the message
         messageBodyPart.setContent(body, "text/html");
          
         // Create a multipart message for attachment
         Multipart multipart = new MimeMultipart();
 
         // Set text message part
         multipart.addBodyPart(messageBodyPart);
 
         // Second part is attachment
         messageBodyPart = new MimeBodyPart();
         String filename = "src/test/java/example/airport.jpg";
         
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         multipart.addBodyPart(messageBodyPart);
 
         // Send the complete message parts
         msg.setContent(multipart, "text/html");
 
         // Send message
         Transport.send(msg);
         System.out.println("EMail Sent Successfully with attachment!!");
      }catch (MessagingException e) {
         e.printStackTrace();
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
    }
}
}