package com.awsome_pizza.model;

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

    @Column(name="order_number")
    private int orderNumber;

    @Column(name="client_name",nullable = false)
    private String clientName;

    @Column(name="pizza_type",nullable = false)
    private String pizzaType;

    @Column(name="status")
    private String Status;

    @Column(name="created_At")
    private LocalDateTime createdAt;

    @Column(name="completed_At")
    private LocalDateTime completedAt;
}
