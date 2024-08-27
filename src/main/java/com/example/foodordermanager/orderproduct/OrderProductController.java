package com.example.foodordermanager.orderproduct;

import com.example.foodordermanager.orderproduct.dto.OrderProductDTO;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-products")
public class OrderProductController {

    @Autowired
    private OrderProductService orderProductService;

    @PermitAll
    @PostMapping
    public ResponseEntity<OrderProductDTO> createOrderProduct(@RequestBody OrderProductDTO orderProductDTO) {
        OrderProductDTO createdOrderProduct = orderProductService.createOrderProduct(orderProductDTO);
        return new ResponseEntity<>(createdOrderProduct, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('CASHIER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderProduct(@PathVariable Long id) {
        orderProductService.deleteOrderProductAndUpdatePrice(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
