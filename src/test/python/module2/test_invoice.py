import pytest
from src.python.module2.invoice import InvoiceItem, Invoice


def test_invoice_item_total_price():
    item = InvoiceItem(description="consulting", quantity=2, unit_price=250)
    assert item.total_price() == 500


# Fixture to set up an Invoice object with two items
@pytest.fixture
def invoice():
    items = [
        InvoiceItem(description="consulting", quantity=2, unit_price=250),
        InvoiceItem(description="training", quantity=1, unit_price=500)
    ]
    return Invoice(items=items, discount=10, tax_rate=23)


def test_invoice_subtotal(invoice):
    assert invoice.subtotal() == 1000


def test_invoice_total_with_discount(invoice):
    assert invoice.total_with_discount() == 900


def test_invoice_total_with_tax(invoice):
    assert invoice.total_with_tax() == 1107


def test_invoice_item_description_in_invoice(invoice):
    assert invoice.items[0].description == "consulting"
    assert invoice.items[1].description == "training"
