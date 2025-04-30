package org.kdojo.module1.noCleanCode;

public class VatService {
    static double getPriceWithVat(double price, double vat) {
        return price + (price * vat);
    }
}