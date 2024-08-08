package com.example.foodordermanager.productaddon;

import com.example.foodordermanager.productaddon.dto.ProductAddonDTO;
import com.example.foodordermanager.addon.dto.AddonDTO;
import com.example.foodordermanager.product.ProductEntity;
import com.example.foodordermanager.addon.AddonEntity;
import com.example.foodordermanager.addon.AddonRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ProductAddonMapper {

    public static ProductAddonDTO mapToAddonDTO(ProductEntity product, List<ProductAddonEntity> productAddonEntities, AddonRepository addonRepository) {
        List<AddonDTO> addons = productAddonEntities.stream()
                .map(productAddon -> {
                    AddonEntity addon = addonRepository.findById(productAddon.getAddonId())
                            .orElseThrow(() -> new RuntimeException("Addon not found"));
                    return new AddonDTO(
                            addon.getId(),
                            addon.getName(),
                            addon.getPrice()
                    );
                })
                .collect(Collectors.toList());

        return new ProductAddonDTO(product.getId(), product.getName(), addons);
    }

    public static ProductAddonEntity mapToProductAddon(ProductAddonDTO productAddonDTO) {
        ProductAddonEntity productAddonEntity = new ProductAddonEntity();
        productAddonEntity.setProductId(productAddonDTO.getProductId());
        if (!productAddonDTO.getAddons().isEmpty()) {
            productAddonEntity.setAddonId(productAddonDTO.getAddons().get(0).getId()); // Ajuste conforme necessário
        }
        return productAddonEntity;
    }
}
