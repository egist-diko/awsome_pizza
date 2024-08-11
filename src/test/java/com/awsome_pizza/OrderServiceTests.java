package com.awsome_pizza;

import com.awsome_pizza.dataTypes.OrderStatus;
import com.awsome_pizza.dataTypes.PizzaType;
import com.awsome_pizza.model.Order;
import com.awsome_pizza.repository.OrderRepository;
import com.awsome_pizza.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderServiceTests {

    @InjectMocks
    private OrderService orderService;

    @MockBean
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

        Order result = orderService.createOrder(order);
        assertNotNull(result);
        assertEquals(result,order);
    }

    @Test
    void testGetAllOrders() {
        Order order1 = new Order();
        order1.setClientName("John Doe");
        order1.setPizzaType(PizzaType.MARGHERITA);

        Order order2 = new Order();
        order2.setClientName("John Doe");
        order2.setPizzaType(PizzaType.MARGHERITA);

        List<Order> orders = Arrays.asList(order1, order2);
        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderService.getAllOrders();

        assertEquals(2, result.size());
        assertTrue(result.contains(order1));
        assertTrue(result.contains(order2));
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testGetOrderById(){
        Long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);
        order.setClientName("John Doe");
        order.setPizzaType(PizzaType.MARGHERITA);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        Optional<Order> result = orderService.getORderById(orderId);
        assertEquals(result.get(),order);
    }


    @Test
    void testDeleteOrder(){
        Long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);
        order.setClientName("John Doe");
        order.setPizzaType(PizzaType.MARGHERITA);

        when(orderRepository.existsById(orderId)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> orderService.deleteOrder(orderId));
        verify(orderRepository, never()).deleteById(orderId);
        verify(orderRepository, times(1)).existsById(orderId);
    }

    @Test
    void testUpdateOrder() {
        Long orderId = 32L;
        OrderStatus status = OrderStatus.COMPLETED;
        Order order = new Order();
        order.setId(orderId);
        order.setClientName("John Doe");
        order.setPizzaType(PizzaType.MARGHERITA);

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> orderService.updateOrderStatus(orderId, status));
        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, never()).save(any(Order.class));
    }

    // Add more unit tests for other methods
}
