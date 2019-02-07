// File Name SendEmail.java
package Controller;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail {

   public static void send() {    
      // Recipient's email ID needs to be mentioned.
      String to = "gabi939@gmail.com";

      // Sender's email ID needs to be mentioned
    /*  String from = "gabi939@gmail.com";

      // Assuming you are sending email from localhost
      String host = "localhost";*/

      
      final String username = "flipCoin.transfer@gmail.com";
      final String password = "FlipCoinTransfer2019"; 
      
      
      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.starttls.enable", "true");
      properties.put("mail.smtp.host", "smtp.gmail.com");
      properties.put("mail.smtp.port", "587");

      
      
   /*   properties.setProperty("mail.smtp.host", host);
      properties.setProperty("mail.user", "myuser");
      properties.setProperty("mail.password", "mypwd");*/

      // Get the default Session object.
      Session session = Session.getInstance(properties, new Authenticator() {

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			// TODO Auto-generated method stubN
			return  new PasswordAuthentication(username, password);
		}
    	
    	  
	});

      try {
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress("flipCoin.Transfer@gmail.com"));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("This is the Subject Line!");

         // Now set the actual message
         message.setText("This is actual message");

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      } catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}