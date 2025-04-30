package org.kdojo.module1.noCleanCode;

import java.sql.*;

public class DatabaseService {
    static double getVatFromDb() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taxdb", "root", "password");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT value FROM tax WHERE type='vat'");
        rs.next();
        double vat = rs.getDouble(1);
        conn.close();
        return vat;
    }
}