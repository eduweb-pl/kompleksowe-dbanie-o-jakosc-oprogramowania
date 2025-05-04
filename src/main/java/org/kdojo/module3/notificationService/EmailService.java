package org.kdojo.module3.notificationService;

import java.util.List;

public interface EmailService {
    void sendEmail(String recipient, String subject, String message);

    List<Email> getSentEmails();
}
