package org.kdojo.module3.notificationService;

public class EmailServiceException extends RuntimeException {
    private final String message;

    public EmailServiceException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
