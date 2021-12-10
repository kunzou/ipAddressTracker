package me.kunzou.ipAddressTracker.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
  private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

  public void sendEmail(String ip) throws MessagingException {
    Transport.send(createMessage(ip));
  }

  private Message createMessage(String ip) throws MessagingException {
    final String username = System.getenv("EMAIL_USERNAME");
    final String password = System.getenv("EMAIL_PASSWORD");

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.live.com");
    props.put("mail.smtp.debug", "true");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(username, password);
        }
      });
    session.setDebug(true);
    Message message = new MimeMessage(session);
    message.setFrom(new
      InternetAddress("Pi<zoukun777@hotmail.com>"));
    message.setRecipients(Message.RecipientType.TO,
      InternetAddress.parse("kunzou@gmail.com"));
    message.setSubject("New ip address");
    message.setText(ip);

    return message;
  }
}
