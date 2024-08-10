package com.awsome_pizza.controller;

import com.awsome_pizza.dataTypes.OrderStatus;
import com.awsome_pizza.model.Order;
import com.awsome_pizza.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        try {
            Order createdOrder = orderService.createOrder(order);
            return new ResponseEntity(createdOrder, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        try {
            return new ResponseEntity(orderService.getAllOrders(),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        try{
            Order order = orderService.getORderById(id);
            if(order!=null){
                return new ResponseEntity(order,HttpStatus.OK);
            }
            return new ResponseEntity("Supplier with id: "+id+"was not found",HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id , @RequestParam OrderStatus status){
        try {
            Order order = orderService.updateOrderStatus(id,status);
            if(order!=null){
                return new ResponseEntity(order,HttpStatus.OK);
            }
            return new ResponseEntity("Supplier with id: "+id+"was not found",HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
