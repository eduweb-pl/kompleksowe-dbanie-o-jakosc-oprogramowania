package org.kdojo.module3.notificationService;

public interface DatabaseService {
    boolean hasNewRows();

    void addNewRow();

    void clearRows();
}
