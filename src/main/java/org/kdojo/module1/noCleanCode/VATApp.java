package org.kdojo.module1.noCleanCode;

import java.util.Scanner;

public class VATApp {
    public static void main(String[] args) {
        try {

            Scanner s = new Scanner(System.in);
            System.out.println("Enter price:");
            double priceFromInput = s.nextDouble();

            double vat = DatabaseService.getVatFromDb();

            double priceWithVat = VatService.getPriceWithVat(priceFromInput, vat);

            System.out.println(priceWithVat);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}