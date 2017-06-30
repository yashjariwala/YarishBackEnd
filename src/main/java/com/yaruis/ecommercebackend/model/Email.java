package com.yaruis.ecommercebackend.model;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;



@Service
public class Email {

  public void send(UserCustomer user, String subject, String body) throws MessagingException {
	  
	  System.out.println("m1");
	  final String username = "yaruisshopanywhere@gmail.com";
      final String password = "yaruis1996";
	  System.out.println("m2");
	  Properties props = new Properties();
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.port", "587");

	  System.out.println("m3");
      Session session = Session.getInstance(props,new javax.mail.Authenticator() {
    	  
          protected PasswordAuthentication getPasswordAuthentication() {
        	  System.out.println("m4");
              return new PasswordAuthentication(username, password);
          }
        });

      try {
    	  System.out.println("m5");
          Message message = new MimeMessage(session);
          message.setFrom(new InternetAddress("YashJariwala"));
    	  System.out.println("m6");
          message.setRecipients(Message.RecipientType.TO,
              InternetAddress.parse(user.getUseremail()));
    	  System.out.println("m7");
          message.setSubject(subject);
    	  System.out.println("m8");
          message.setText(body);
    	  System.out.println("m9");
          Transport.send(message);

          System.out.println("Done");

      } catch (MessagingException e) {
    	  System.out.println("m10");
    	  e.printStackTrace();
         // throw new RuntimeException(e);
      }
  }
}