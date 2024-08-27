package com.example.foodordermanager.productaddon;

import com.example.foodordermanager.productaddon.dto.ProductAddonDTO;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/{productId}/addons")
public class ProductAddonController {

    @Autowired
    private ProductAddonService productAddonService;

    @PermitAll
    @GetMapping
    public List<ProductAddonDTO> getAddonsForProduct(@PathVariable Long productId) {
        return productAddonService.getAddonsForProduct(productId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public void addAddonToProduct(@PathVariable Long productId, @RequestParam Long addonId) {
        productAddonService.addAddonToProduct(productId, addonId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public void removeAddonFromProduct(@PathVariable Long productId, @RequestParam Long addonId) {
        productAddonService.removeAddonFromProduct(productId, addonId);
    }
}
