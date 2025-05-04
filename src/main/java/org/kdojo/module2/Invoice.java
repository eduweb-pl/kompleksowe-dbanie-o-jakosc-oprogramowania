package org.kdojo.module2;

import java.util.List;

class InvoiceItem {
    private final String description;
    private final int quantity;
    private final double unitPrice;

    public InvoiceItem(String description, int quantity, double unitPrice) {
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public double totalPrice() {
        return quantity * unitPrice;
    }
}

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

    public String getDescription(int position) {
        try{
            return items.get(position - 1).getDescription();
        }
        catch(IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("Item's number out of range");
        }
        
    }
}