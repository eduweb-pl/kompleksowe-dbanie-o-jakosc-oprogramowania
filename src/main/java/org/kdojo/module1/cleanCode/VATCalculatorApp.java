package org.kdojo.module1.cleanCode;

import java.util.Scanner;

public class VATCalculatorApp {
    public static void main(String[] args) {
        double price = readPriceFromUser();
        VATService vatService = new VATService(new DatabaseService());
        double priceWithVat = vatService.calculatePriceWithVat(price);
        displayPriceWithVat(priceWithVat);
    }

    private static double readPriceFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter price:");
        return scanner.nextDouble();
    }

    private static void displayPriceWithVat(double priceWithVat) {
        System.out.println("Price with VAT: " + priceWithVat);
    }
}