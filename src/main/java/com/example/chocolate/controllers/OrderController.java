package com.example.chocolate.controllers;

import com.example.chocolate.entities.Order;
import com.example.chocolate.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        Order order = orderService.getOrderById(id);
        order.setQuantity(orderDetails.getQuantity());
        order.setOrderDate(orderDetails.getOrderDate());
        order.setDeliveryDate(orderDetails.getDeliveryDate());
        order.setStore(orderDetails.getStore());
        order.setProduct(orderDetails.getProduct());
        return orderService.saveOrder(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/store/{storeId}")
    public List<Order> getOrdersByStoreId(@PathVariable Long storeId) {
        return orderService.getOrdersByStoreId(storeId);
    }

    @GetMapping("/product/{productId}")
    public List<Order> getOrdersByProductId(@PathVariable Long productId) {
        return orderService.getOrdersByProductId(productId);
    }

    @GetMapping("/date-range")
    public List<Order> getOrdersByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return orderService.getOrdersByDateRange(startDate, endDate);
    }

    @GetMapping("/sort/order-date")
    public List<Order> sortOrdersByOrderDate() {
        return orderService.sortOrdersByOrderDate();
    }

    @GetMapping("/sort/delivery-date")
    public List<Order> sortOrdersByDeliveryDate() {
        return orderService.sortOrdersByDeliveryDate();
    }
}
