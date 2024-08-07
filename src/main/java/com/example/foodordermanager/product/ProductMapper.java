package com.example.foodordermanager.product;

import com.example.foodordermanager.product.dto.ProductDTO;

public class ProductMapper {
    public static ProductDTO mapToProductDTO(ProductEntity product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setProductCategory(product.getProductCategory());
        productDTO.setImageUrl(product.getProductImage());
        return productDTO;
    }

    public static ProductEntity mapToProduct(ProductDTO productDTO) {
        ProductEntity product = new ProductEntity();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setProductImage(productDTO.getImageUrl());
        product.setProductCategory(productDTO.getProductCategory());
        return product;
    }
}