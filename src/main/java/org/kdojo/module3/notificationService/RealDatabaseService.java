package org.kdojo.module3.notificationService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RealDatabaseService implements DatabaseService {
    private Connection connection;

    public RealDatabaseService() {
        try {
            // Initialize the H2 database connection
            connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
            try (Statement stmt = connection.createStatement()) {
                // Create a table for rows
                stmt.execute("CREATE TABLE rows (id INT AUTO_INCREMENT PRIMARY KEY, data VARCHAR(255))");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize database", e);
        }
    }

    @Override
    public boolean hasNewRows() {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM rows")) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to check for new rows", e);
        }
        return false;
    }

    @Override
    public void addNewRow() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("INSERT INTO rows (data) VALUES ('New row')");
        } catch (Exception e) {
            throw new RuntimeException("Failed to add new row", e);
        }
    }

    @Override
    public void clearRows() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM rows");
        } catch (Exception e) {
            throw new RuntimeException("Failed to clear rows", e);
        }
    }
}