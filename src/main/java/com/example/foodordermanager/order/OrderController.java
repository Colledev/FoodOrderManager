package com.example.foodordermanager.order;

import com.example.foodordermanager.order.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        try {
            log.info("Received request to get all orders");
            List<OrderDTO> orders = orderService.getAllOrders();
            log.info("Fetched orders: {}", orders);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            log.error("Error occurred while getting all orders", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        try {
            log.info("Received request to create order: {}", orderDTO);
            OrderDTO createdOrder = orderService.createOrder(orderDTO);
            log.info("Created order: {}", createdOrder);
            return ResponseEntity.ok(createdOrder);
        } catch (Exception e) {
            log.error("Error occurred while creating order", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}