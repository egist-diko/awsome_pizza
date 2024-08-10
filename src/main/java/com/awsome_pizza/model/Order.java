package com.awsome_pizza.model;

import com.awsome_pizza.dataTypes.OrderStatus;
import com.awsome_pizza.dataTypes.PizzaType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="order_number",unique = true)
    private int orderNumber;

    @Column(name="client_name",nullable = false)
    private String clientName;

    @Column(name="pizza_type",nullable = false)
    private PizzaType pizzaType;

    @Column(name="status")
    private OrderStatus Status;

    @Column(name="created_At")
    private LocalDateTime createdAt;

    @Column(name="completed_At")
    private LocalDateTime completedAt;
}
