package org.kdojo.module2;

import java.util.List;

class Invoice {
    private final List<InvoiceItem> items;
    private final double discount;
    private final double taxRate;

    public Invoice(List<InvoiceItem> items, double discount, double taxRate) {
        this.items = items;
        this.discount = discount;
        this.taxRate = taxRate;
    }

    public double getNetPriceSum() {
        return items.stream().mapToDouble(InvoiceItem::totalPrice).sum();
    }

    public double getTotalWithDiscount() {
        return getNetPriceSum() * (1 - discount / 100);
    }

    public double getTotalWithTax() {
        return getTotalWithDiscount() * (1 + taxRate / 100);
    }
}