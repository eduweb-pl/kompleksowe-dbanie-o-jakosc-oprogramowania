package org.kdojo.module1.cleanCode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseService {

    public double getVatRateFromDatabase() {
        double vatRate = 0.23;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taxdb", "root", "password");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT value FROM tax WHERE type='vat'")) {

            if (rs.next()) {
                vatRate = rs.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vatRate;
    }
}
