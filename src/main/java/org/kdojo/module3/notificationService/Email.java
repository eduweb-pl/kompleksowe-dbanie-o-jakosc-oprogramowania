package org.kdojo.module3.notificationService;

public class Email {
    private final String recipient;
    private final String subject;
    private final String message;

    public Email(String recipient, String subject, String message) {
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    // Getters, equals, hashCode, and toString methods
}