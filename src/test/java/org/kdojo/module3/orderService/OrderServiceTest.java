package org.kdojo.module3.orderService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    private OrderRepository mockOrderRepository;
    private EmailNotificationService mockEmailNotificationService;
    private OrderService orderService;

    // Nadmiarowe użycie Dummy
    private List<String> dummyList;

    @BeforeEach
    void setUp() {
        // Mock: Tworzenie mocków dla wszystkich zależności
        mockOrderRepository = Mockito.mock(OrderRepository.class);
        mockEmailNotificationService = Mockito.mock(EmailNotificationService.class);

        // Dummy: Lista, która nie ma nic wspólnego z testowanym kodem
        dummyList = Mockito.mock(List.class);

        // Tworzenie testowanej instancji z mockami
        orderService = new OrderService(mockOrderRepository, mockEmailNotificationService);
    }

    @Test
    void shouldPlaceOrderAndNotifyAdmin() {
        // Stub: Niepotrzebne stubowanie metody, która nic nie zwraca (void)
        doNothing().when(mockOrderRepository).save(any(Order.class));

        // Stub: Niepotrzebne stubowanie metody, która również nic nie zwraca
        doNothing().when(mockEmailNotificationService).notifyAdmin(anyString());

        // Tworzenie obiektu Order
        Order order = new Order("Product A", 5);

        // Wywołanie metody biznesowej
        orderService.placeOrder(order);

        // Weryfikacja interakcji
        verify(mockOrderRepository, times(1)).save(order);
        verify(mockEmailNotificationService, times(1)).notifyAdmin("Order placed for: Product A, Quantity: 5");
    }

    @Test
    void shouldVerifyOrderIsSavedUsingSpy() {
        // Spy: Niepotrzebne użycie spya, bo nie ma tu realnej logiki do monitorowania
        OrderRepository spyOrderRepository = spy(OrderRepository.class);
        EmailNotificationService spyEmailService = spy(EmailNotificationService.class);

        // Nowy OrderService z spy zamiast mocków
        OrderService orderServiceWithSpy = new OrderService(spyOrderRepository, spyEmailService);

        // Tworzenie obiektu Order
        Order order = new Order("Product B", 10);

        // Wywołanie metody biznesowej
        orderServiceWithSpy.placeOrder(order);

        // Weryfikacja: Niepotrzebne monitorowanie metod
        verify(spyOrderRepository, times(1)).save(order);
        verify(spyEmailService, times(1)).notifyAdmin("Order placed for: Product B, Quantity: 10");
    }

    @Test
    void shouldDoNothingWhenDummyIsPassed() {
        // Dummy: Użycie niepotrzebnego dummy dla logiki biznesowej, która tego nie wymaga
        List<String> dummyRecipients = Mockito.mock(List.class);

        // Sprawdzenie, czy dummyList istnieje, chociaż jest bezużyteczna w tym teście
        assertNotNull(dummyRecipients);

        // Tworzenie nowego zamówienia
        Order order = new Order("Product C", 3);

        // Użycie prawdziwego orderService, bo test ma się skupić na realnej logice
        orderService.placeOrder(order);

        // Weryfikacja interakcji na mockach
        verify(mockOrderRepository, times(1)).save(order);
        verify(mockEmailNotificationService, times(1)).notifyAdmin("Order placed for: Product C, Quantity: 3");
    }

    @Test
    void shouldNotInteractWithUnusedMocks() {
        // Nadmiarowe mockowanie, które nie jest potrzebne w tym scenariuszu
        List<String> dummyList = Mockito.mock(List.class);

        // Tworzenie zamówienia
        Order order = new Order("Product D", 7);

        // Wywołanie metody biznesowej
        orderService.placeOrder(order);

        // Weryfikacja: Sprawdzenie interakcji z mockami, chociaż logika jest bardzo prosta
        verify(mockOrderRepository, times(1)).save(order);
        verify(mockEmailNotificationService, times(1)).notifyAdmin("Order placed for: Product D, Quantity: 7");

        // Niepotrzebna weryfikacja, że dummyList nie zostało użyte
        verifyNoInteractions(dummyList);
    }
}