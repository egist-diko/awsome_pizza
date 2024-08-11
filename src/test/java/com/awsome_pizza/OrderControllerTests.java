package com.awsome_pizza;


import static org.mockito.Mockito.*;
import com.awsome_pizza.controller.OrderController;
import com.awsome_pizza.dataTypes.OrderStatus;
import com.awsome_pizza.dataTypes.PizzaType;
import com.awsome_pizza.model.Order;
import com.awsome_pizza.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderControllerTests {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetAllOrders() throws Exception {
        Order order1 = new Order();
        order1.setClientName("John Doe");
        order1.setPizzaType(PizzaType.MARGHERITA);

        Order order2 = new Order();
        order2.setClientName("John Doe");
        order2.setPizzaType(PizzaType.MARGHERITA);

        List<Order> orders = Arrays.asList(order1, order2);
        when(orderService.getAllOrders()).thenReturn(orders);

        ResponseEntity<?> response = orderController.getAllOrders();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(((List<Order>) response.getBody()).contains(order1));
        assertTrue(((List<Order>) response.getBody()).contains(order2));
    }

    @Test
    public void testCreateOrder() throws Exception {
        Order order = new Order();
        order.setClientName("John Doe");
        order.setPizzaType(PizzaType.MARGHERITA);
        when(orderService.createOrder(any(Order.class))).thenReturn(order);

        ResponseEntity<?> response = orderController.createOrder(order);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(order, response.getBody());
    }

    @Test
    public void testUpdateOrder() throws Exception {
        Long orderId = 32L;
        OrderStatus status = OrderStatus.COMPLETED;
        Order order = new Order();
        order.setId(orderId);
        order.setClientName("John Doe");
        order.setPizzaType(PizzaType.MARGHERITA);
        when(orderService.updateOrderStatus(orderId, status)).thenReturn(order);

        ResponseEntity<?> response = orderController.updateOrderStatus(orderId, status);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order, response.getBody());
    }

    @Test
    public void testDeleteOrder() throws Exception {
        doNothing().when(orderService).deleteOrder(1L);

        ResponseEntity<?> response = orderController.deleteOrder(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetOrderById() throws Exception {
        Long orderId = 1L;
        Order order = new Order();
        order.setId(orderId);
        order.setClientName("John Doe");
        order.setPizzaType(PizzaType.MARGHERITA);
        when(orderService.getORderById(orderId)).thenReturn(Optional.of(order));

        ResponseEntity<?> response = orderController.getOrderById(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order, response.getBody());
    }
}

