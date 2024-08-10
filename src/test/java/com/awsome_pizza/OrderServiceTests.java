package com.awsome_pizza;

import com.awsome_pizza.dataTypes.OrderStatus;
import com.awsome_pizza.dataTypes.PizzaType;
import com.awsome_pizza.model.Order;
import com.awsome_pizza.repository.OrderRepository;
import com.awsome_pizza.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTests {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder() {
        Order order = new Order();
        order.setClientName("John Doe");
        order.setPizzaType(PizzaType.MARGHERITA);

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order createdOrder = orderService.createOrder(order);
        assertNotNull(createdOrder);
        assertEquals("John Doe", createdOrder.getClientName());
        assertEquals(PizzaType.MARGHERITA, createdOrder.getPizzaType());
        assertEquals(OrderStatus.IN_PROGRESS, createdOrder.getStatus());
    }

    // Add more unit tests for other methods
}
