package org.kdojo.module3.orderService;

public class OrderService {
    private final OrderRepository orderRepository;
    private final EmailNotificationService emailNotificationService;

    public OrderService(OrderRepository orderRepository, EmailNotificationService emailNotificationService) {
        this.orderRepository = orderRepository;
        this.emailNotificationService = emailNotificationService;
    }

    public void placeOrder(Order order) {
        orderRepository.save(order);
        emailNotificationService.notifyAdmin("Order placed for: " + order.getProduct() + ", Quantity: " + order.getQuantity());
    }
}
