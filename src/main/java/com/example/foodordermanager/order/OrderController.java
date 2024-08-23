package com.example.foodordermanager.order;

import com.example.foodordermanager.order.dto.*;
import com.example.foodordermanager.payment.dto.PaymentListDTO;
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
    public ResponseEntity<List<OrderWithPayment>> getAllOrders() {
        try {
            log.info("Received request to get all orders");
            List<OrderWithPayment> orders = orderService.getAllOrders();
            log.info("Fetched orders: {}", orders);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            log.error("Error occurred while getting all orders", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @GetMapping("/completed")
    public ResponseEntity<List<OrderWithPayment>> getAllOrdersConcluded() {
        try {
            log.info("Received request to get all concluded orders");
            List<OrderWithPayment> orders = orderService.getAllOrdersConcluded();
            log.info("Fetched concluded orders: {}", orders);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            log.error("Error occurred while getting all concluded orders", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @GetMapping("/open")
    public ResponseEntity<List<OrderWithPayment>> getAllOrdersOpen() {
        try {
            log.info("Received request to get all open orders");
            List<OrderWithPayment> orders = orderService.getAllOrdersOpen();
            log.info("Fetched open orders: {}", orders);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            log.error("Error occurred while getting all open orders", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        try {
            if (!isValidStatus(orderDTO.getOrderStatus())) {
                return ResponseEntity.badRequest().body(null);
            }

            log.info("Received request to create order: {}", orderDTO);
            OrderDTO createdOrder = orderService.createOrder(orderDTO);
            log.info("Created order: {}", createdOrder);
            return ResponseEntity.ok(createdOrder);
        } catch (Exception e) {
            log.error("Error occurred while creating order", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable Long id, @RequestBody OrderStatusUpdateDTO orderStatusUpdateDTO) {
        try {
            log.info("Received request to update order status by id: {}", id);
            OrderDTO updatedOrder = orderService.updateOrderStatus(id, orderStatusUpdateDTO.getOrderStatus());
            log.info("Updated order: {}", updatedOrder);
            return ResponseEntity.ok(updatedOrder);
        } catch (Exception e) {
            log.error("Error occurred while updating order status", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/table/{tableNumber}/close")
    public ResponseEntity<OrderTableDTO> closeTable(@PathVariable Integer tableNumber) {
        try {
            log.info("Closing table and fetching orders by table number: {}", tableNumber);
            OrderTableDTO OrderTable = orderService.getOrdersByTableNumberGroupedByCustomer(tableNumber);
            log.info("Fetched customer orders: {}", OrderTable);
            return ResponseEntity.ok(OrderTable);
        } catch (Exception e) {
            log.error("Error occurred while closing table", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailsDTO> getOrderById(@PathVariable Long id) {
        try {
            log.info("Received request to get order by id: {}", id);
            OrderDetailsDTO order = orderService.getOrderDetails(id);
            log.info("Fetched order: {}", order);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            log.error("Error occurred while getting order by id", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/{id}/payment")
    public ResponseEntity<String> processPayments(@PathVariable Long id, @RequestBody PaymentListDTO paymentListDTO) {
        try {
            orderService.processPayments(id, paymentListDTO.getPayments());
            return ResponseEntity.ok("Payment processed successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the payment");
        }
    }

    private boolean isValidStatus(String status) {
        try {
            OrderStatus.valueOf(status);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
