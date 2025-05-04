package org.kdojo.module3.notificationService;

import java.util.List;

public class NotificationService {
    private final EmailService emailService;
    private final DatabaseService databaseService;
    private final List<String> recipients;
    private final String adminEmail;

    public NotificationService(EmailService emailService, DatabaseService databaseService, List<String> recipients, String adminEmail) {
        this.emailService = emailService;
        this.databaseService = databaseService;
        this.recipients = recipients;
        this.adminEmail = adminEmail;
    }

    public void notifyIfNewRow() {
        boolean hasNewRows;
        try {
            hasNewRows = databaseService.hasNewRows();
        } catch (RuntimeException e) {
            System.out.println("Error while connecting to DB");
            throw new DatabaseException("Error while connecting to DB");

        }
        if (hasNewRows) {
            String subject = "New row added";
            String message = "A new row has been added to the database.";
            try {
                emailService.sendEmail(adminEmail, subject, message);

                for (String recipient : recipients) {
                    String personalizedMessage = "Welcome " + extractNameFromEmail(recipient);
                    emailService.sendEmail(recipient, subject, personalizedMessage);
                }
            } catch (RuntimeException e) {
                System.out.println("Error while checking for new rows: ");
                throw new EmailServiceException("Error while sending email");

            }
        }
    }

    private String extractNameFromEmail(String email) {
        return email.split("@")[0];
    }
}
