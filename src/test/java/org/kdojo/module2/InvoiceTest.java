package org.kdojo.module2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kdojo.module1.cleanCode.DatabaseService;
import org.kdojo.module1.cleanCode.VATService;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {
    private VATService vatService;
    private DatabaseService databaseService;

    @BeforeEach
    void setUp() {
        databaseService = new DatabaseService();
        vatService = new VATService(databaseService);
    }

    @Test
    void shouldCalculatePriceWithVat() {
        // given
        double price = 100.0;
        double expectedPriceWithVat = 123.0; // 100 + (100 * 0.23)

        // when
        double actualPriceWithVat = vatService.calculatePriceWithVat(price);

        // then
        assertEquals(expectedPriceWithVat, actualPriceWithVat, 0.01);
    }

    @Test
    void shouldReturnCorrectVatRate() {
        // given
        double expectedVatRate = 0.23;

        // when
        double actualVatRate = vatService.getVatRate();

        // then
        assertEquals(expectedVatRate, actualVatRate, 0.01);
    }
} 