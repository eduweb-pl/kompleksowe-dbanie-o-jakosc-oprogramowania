package org.kdojo.module3.notificationService;

public class DatabaseException extends RuntimeException {
    private final String message;

    public DatabaseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}