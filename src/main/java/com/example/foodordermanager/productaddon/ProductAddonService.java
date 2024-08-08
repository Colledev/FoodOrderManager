package com.example.foodordermanager.productaddon;

import com.example.foodordermanager.product.ProductRepository;
import com.example.foodordermanager.addon.AddonRepository;
import com.example.foodordermanager.productaddon.dto.ProductAddonDTO;
import com.example.foodordermanager.product.ProductEntity;
import com.example.foodordermanager.addon.AddonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductAddonService {

    @Autowired
    private ProductAddonRepository productAddonRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AddonRepository addonRepository;

    public List<ProductAddonDTO> getAddonsForProduct(Long productId) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        List<ProductAddonEntity> productAddonEntities = productAddonRepository.findAll().stream()
                .filter(productAddon -> productAddon.getProductId().equals(productId))
                .collect(Collectors.toList());

        return List.of(ProductAddonMapper.mapToAddonDTO(product, productAddonEntities, addonRepository));
    }

    public void addAddonToProduct(Long productId, Long addonId) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        AddonEntity addon = addonRepository.findById(addonId)
                .orElseThrow(() -> new RuntimeException("Addon not found"));

        ProductAddonEntity productAddon = new ProductAddonEntity();
        productAddon.setProductId(productId);
        productAddon.setAddonId(addonId);

        productAddonRepository.save(productAddon);
    }

    public void removeAddonFromProduct(Long productId, Long addonId) {
        ProductAddonId id = new ProductAddonId(productId, addonId);
        productAddonRepository.deleteById(id);
    }
}