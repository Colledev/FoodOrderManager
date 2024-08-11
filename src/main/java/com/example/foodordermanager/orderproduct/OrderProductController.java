package com.example.foodordermanager.orderproduct;

import com.example.foodordermanager.orderproduct.dto.OrderProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-products")
public class OrderProductController {

    @Autowired
    private OrderProductService orderProductService;

    @PostMapping
    public ResponseEntity<OrderProductDTO> createOrderProduct(@RequestBody OrderProductDTO orderProductDTO) {
        OrderProductDTO createdOrderProduct = orderProductService.createOrderProduct(orderProductDTO);
        return new ResponseEntity<>(createdOrderProduct, HttpStatus.CREATED);
    }
}
