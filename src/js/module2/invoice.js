class InvoiceItem {
    constructor(description, quantity, unitPrice) {
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    totalPrice() {
        return this.quantity * this.unitPrice;
    }
}

class Invoice {
    constructor(items, discount, taxRate) {
        this.items = items;
        this.discount = discount;
        this.taxRate = taxRate;
    }

    subtotal() {
        return this.items.reduce((sum, item) => sum + item.totalPrice(), 0);
    }

    totalWithDiscount() {
        return this.subtotal() * (1 - this.discount / 100);
    }

    totalWithTax() {
        return this.totalWithDiscount() * (1 + this.taxRate / 100);
    }
}

module.exports = { InvoiceItem, Invoice };