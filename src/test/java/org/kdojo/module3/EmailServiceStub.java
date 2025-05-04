package org.kdojo.module3.notificationService;

import java.util.ArrayList;
import java.util.List;

public class EmailServiceStub implements EmailService {

    private final List<Email> sentEmails = new ArrayList<>();

    @Override
    public void sendEmail(String recipient, String subject, String message) {
        if (recipient != null && !recipient.isEmpty()) {
            sentEmails.add(new Email(recipient, subject, message));
        }
    }

    public boolean isEmailSent() {
        return !sentEmails.isEmpty();
    }

    public List<Email> getSentEmails() {
        return new ArrayList<>(sentEmails);
    }
}

