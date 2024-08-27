package com.example.foodordermanager.product;

import com.example.foodordermanager.product.dto.ProductDTO;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @PreAuthorize("hasRole('ADMIN')")
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

    @PermitAll
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/active")
    public ResponseEntity<List<ProductDTO>> getActiveProducts() {
        try {
            log.info("Received request to get active products");
            List<ProductDTO> products = productService.getActiveProducts();
            log.info("Fetched active products: {}", products);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            log.error("Error occurred while getting active products", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasRole('ADMIN')")
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