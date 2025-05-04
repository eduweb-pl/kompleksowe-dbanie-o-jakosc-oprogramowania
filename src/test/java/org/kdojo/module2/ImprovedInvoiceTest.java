package org.kdojo.module2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

class ImprovedInvoiceTest {

    private InvoiceItem consulting;
    private InvoiceItem training;
    private Invoice invoice;

    @BeforeEach
    void setUp() {
        consulting = new InvoiceItem("Consulting", 20, 250); // 20 * 250 = 5000
        training = new InvoiceItem("Training", 1, 500); // 1 * 500 = 500
        invoice = new Invoice(Arrays.asList(consulting, training), 10, 23);
    }

    @Test
    void shouldCalculateSubtotalAsSumOfAllItemsWithoutDiscount() {
        Invoice invoice = new Invoice(Arrays.asList(consulting, training), 0, 0);
        double subtotal = invoice.getSum();
        assertThat(subtotal).isEqualTo(5500);
    }

    @Test
    void shouldCalculateSubtotalAsSumOfAllItemsWithDiscount() {
        Invoice invoice = new Invoice(Arrays.asList(consulting, training), 10, 0);
        double totalWithDiscount = invoice.getTotalWithDiscount();
        assertThat(totalWithDiscount).isEqualTo(4950);
    }

    @Test
    void shouldCalculateGrossPriceIncludingTax() {
        double totalWithTax = invoice.getTotalWithTax();
        assertThat(totalWithTax).isEqualTo(6088.5);
    }

    @Test
    void shouldCalculateTotalWithTaxAsEqualToSubtotalWhenDiscountAndTaxAreZero() {
        Invoice invoiceNoDiscountNoTax = new Invoice(Arrays.asList(consulting, training), 0, 0);
        double totalWithTax = invoiceNoDiscountNoTax.getTotalWithTax();
        assertThat(totalWithTax).isEqualTo(5500);
    }

    @Test
    void shouldCalculateGrossPriceAsZeroWhenThereAreNoItems() {

    }
}
