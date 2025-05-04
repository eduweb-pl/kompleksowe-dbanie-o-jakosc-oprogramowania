package org.kdojo.module3.notificationService;

import io.github.cdimascio.dotenv.Dotenv;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class NotificationEmailService implements EmailService {

    private final List<Email> sentEmails = new ArrayList<>();
    private final String username;
    private final String password;
    private final String host;
    private final Properties properties;

    public NotificationEmailService() {
        Dotenv dotenv = Dotenv.load();
        this.username = dotenv.get("EMAIL_USERNAME");
        this.password = dotenv.get("EMAIL_PASSWORD");
        this.host = dotenv.get("EMAIL_HOST");
        this.properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
    }

    @Override
    public void sendEmail(String recipient, String subject, String message) {
        try {
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(username));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            Transport.send(mimeMessage);

            sentEmails.add(new Email(recipient, subject, message));
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    @Override
    public List<Email> getSentEmails() {
        return new ArrayList<>(sentEmails);
    }
}