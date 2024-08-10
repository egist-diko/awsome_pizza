package com.awsome_pizza.service;

import com.awsome_pizza.dataTypes.OrderStatus;
import com.awsome_pizza.model.Order;
import com.awsome_pizza.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Order createOrder(Order order){
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.IN_PROGRESS);
        orderRepository.save(order);
        entityManager.flush();
        entityManager.clear();
        return orderRepository.findById(order.getId()).orElse(null);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getORderById(Long id){
        return orderRepository.findById(id).orElse(null);
    }

    @Transactional
    public Order updateOrderStatus(Long id , OrderStatus status){
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
