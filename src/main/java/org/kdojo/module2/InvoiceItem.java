package org.kdojo.module2;

public class InvoiceItem {
    private final String description;
    private final int quantity;
    private final double unitPrice;

    public InvoiceItem(String description, int quantity, double unitPrice) {
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public double totalPrice() {
        return quantity * unitPrice;
    }
}