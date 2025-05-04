package org.kdojo.module2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvoiceTest {

    private InvoiceItem item1;
    private InvoiceItem item2;
    private Invoice invoice;

    @BeforeEach
    void setUp() {
        item1 = new InvoiceItem("Item 1", 2, 50); // 2 * 50 = 100
        item2 = new InvoiceItem("Item 2", 1, 150); // 1 * 150 = 150
        invoice = new Invoice(Arrays.asList(item1, item2), 10, 23);
    }

    @Test
    void testSubtotal() {
        Invoice invoice = new Invoice(Arrays.asList(item1, item2), 0, 0);
        double subtotal = invoice.getSum();
        // Subtotal should be 100 + 150 = 250
        assertEquals(250, subtotal);
    }

    @Test
    void testTotalWithDiscount() {
        // Amount after 10% discount: 250 * 0.9 = 225
        assertEquals(225, invoice.getTotalWithDiscount());
    }

    @Test
    void testTotalWithTax() {
        // Amount with 23% tax: 225 * 1.23 = 276.75
        assertEquals(276.75, invoice.getTotalWithTax());
    }

    @Test
    void testInvoiceWithNoDiscountAndNoTax() {
        // Invoice with no discount and no tax, subtotal should be 250
        Invoice invoiceNoDiscountNoTax = new Invoice(Arrays.asList(item1, item2), 0, 0);
        assertEquals(250, invoiceNoDiscountNoTax.getTotalWithTax());
    }

    @Test
    void testInvoiceWithZeroItems() {
        // Invoice with no items, total should be 0
        Invoice emptyInvoice = new Invoice(Arrays.asList(), 0, 0);
        assertEquals(0, emptyInvoice.getTotalWithTax());
    }
}