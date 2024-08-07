package com.example.foodordermanager.controller;

import com.example.foodordermanager.dto.ProductDTO;
import com.example.foodordermanager.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<List<ProductDTO>> createProducts(@RequestBody List<ProductDTO> productDTOs) {
        try {
            log.info("Received request to create products: {}", productDTOs);
            List<ProductDTO> createdProducts = productService.createProducts(productDTOs);
            log.info("Created products: {}", createdProducts);
            return ResponseEntity.ok(createdProducts);
        } catch (Exception e) {
            log.error("Error occurred while creating products", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        try {
            log.info("Received request to get all products");
            List<ProductDTO> products = productService.getAllProducts();
            log.info("Fetched products: {}", products);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            log.error("Error occurred while getting all products", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        try {
            log.info("Received request to update product with id: {}", id);
            productDTO.setId(id);
            ProductDTO updatedProduct = productService.updateProduct(productDTO);
            log.info("Updated product: {}", updatedProduct);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            log.error("Error occurred while updating product", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            log.info("Received request to delete product with id: {}", id);
            productService.deleteProduct(id);
            log.info("Deleted product with id: {}", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Error occurred while deleting product", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}