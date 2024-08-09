package com.awsome_pizza.service;

import com.awsome_pizza.model.Order;
import com.awsome_pizza.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order){
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("Pending");
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getORderById(Long id){
        return orderRepository.findById(id).orElse(null);
    }

    public Order updateOrderStatus(Long id , String status){
        Order order = getORderById(id);
        if(order != null){
            order.setStatus(status);
            if(status.equals("Completed")) {
                order.setCompletedAt(LocalDateTime.now());
            }
        }
        return orderRepository.save(order);
    }
}
