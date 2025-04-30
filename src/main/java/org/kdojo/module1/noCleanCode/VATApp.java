package org.kdojo.module1.noCleanCode;

import java.util.Scanner;
import java.sql.*;

public class VATApp {
    public static void main(String[] args) {
        try {

            Scanner s = new Scanner(System.in);
            System.out.println("Enter price:");
            double priceFromInput = s.nextDouble();

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taxdb", "root", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT value FROM tax WHERE type='vat'");
            rs.next();
            double vat = rs.getDouble(1);
            conn.close();

            double priceWithVat = getPriceWithVat(priceFromInput, vat);

            System.out.println(priceWithVat);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static double getPriceWithVat(double price, double vat) {
        return price + (price * vat);
    }

}