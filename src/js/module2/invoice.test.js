const { InvoiceItem, Invoice } = require('./invoice');

describe('InvoiceItem', () => {
    test('should calculate total price correctly', () => {
        const item = new InvoiceItem('consulting', 2, 250);
        expect(item.totalPrice()).toBe(500);
    });
});

describe('Invoice', () => {
    let items;
    let invoice;
    const DISCOUNT = 10;
    const TAX_RATE = 23;
    const CONSULTING = { description: 'consulting', quantity: 2, unitPrice: 250 };
    const TRAINING = { description: 'training', quantity: 1, unitPrice: 500 };

    beforeEach(() => {
        items = [
            new InvoiceItem(CONSULTING.description, CONSULTING.quantity, CONSULTING.unitPrice),
            new InvoiceItem(TRAINING.description, TRAINING.quantity, TRAINING.unitPrice)
        ];
        invoice = new Invoice(items, DISCOUNT, TAX_RATE);
    });

    test('should calculate subtotal correctly', () => {
        expect(invoice.subtotal()).toBe(1000);
    });

    test('should calculate total with discount correctly', () => {
        expect(invoice.totalWithDiscount()).toBe(900);
    });

    test('should calculate total with tax correctly', () => {
        expect(invoice.totalWithTax()).toBe(1107);
    });

    test('should return correct description for each item', () => {
            expect(invoice.items[0].description).toBe('consulting');
            expect(invoice.items[1].description).toBe('training');
    });
});