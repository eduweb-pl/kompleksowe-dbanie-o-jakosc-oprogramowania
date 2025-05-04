class InvoiceItem:
    def __init__(self, description, quantity, unit_price):
        self.description = description
        self.quantity = quantity
        self.unit_price = unit_price

    def total_price(self):
        return self.quantity * self.unit_price


class Invoice:
    def __init__(self, items, discount=0, tax_rate=0):
        self.items = items  # List of InvoiceItem objects
        self.discount = discount  # Discount in percentage (e.g., 10 means 10%)
        self.tax_rate = tax_rate  # Tax rate in percentage (e.g., 23 means 23%)

    def subtotal(self):
        return sum(item.total_price() for item in self.items)

    def total_with_discount(self):
        return self.subtotal() * (1 - self.discount / 100)

    def total_with_tax(self):
        return self.total_with_discount() * (1 + self.tax_rate / 100)
