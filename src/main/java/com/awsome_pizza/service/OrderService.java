package com.awsome_pizza.service;

import com.awsome_pizza.dataTypes.OrderStatus;
import com.awsome_pizza.model.Order;
import com.awsome_pizza.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        return orderRepository.save(order);
//        entityManager.flush();
//        entityManager.clear();
//        return orderRepository.findById(order.getId()).orElse(null);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getORderById(Long id){
        return orderRepository.findById(id);
    }

    @Transactional
    public Order updateOrderStatus(Long id , OrderStatus status){
        Optional<Order> order = orderRepository.findById(id);
        Order tempOrder = order.get();
        if(order.isPresent()){
            tempOrder.setStatus(status);
            if(status.equals(OrderStatus.COMPLETED)) {
                tempOrder.setCompletedAt(LocalDateTime.now());
            }
        }
        return orderRepository.save(tempOrder);
    }

    @Transactional
    public void deleteOrder(Long id){
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Order not found with id " + id);
        }
        orderRepository.deleteById(id);
    }
}
