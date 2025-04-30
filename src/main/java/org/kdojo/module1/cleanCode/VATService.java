package org.kdojo.module1.cleanCode;

public class VATService {

    private final DatabaseService databaseService;

    public VATService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public double getVatRate() {
        return databaseService.getVatRateFromDatabase();
    }

    public double calculatePriceWithVat(double price) {
        return price + (price * databaseService.getVatRateFromDatabase());
    }
}
