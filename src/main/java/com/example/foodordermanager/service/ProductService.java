package com.example.foodordermanager.service;

import com.example.foodordermanager.dto.ProductDTO;
import com.example.foodordermanager.entity.ProductEntity;
import com.example.foodordermanager.repository.ProductRepository;
import com.example.foodordermanager.adapter.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ItemMapper::mapToProductDTO)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> createProducts(List<ProductDTO> productDTOs) {
        List<ProductEntity> products = productDTOs.stream()
                .map(ItemMapper::mapToProduct)
                .collect(Collectors.toList());
        List<ProductEntity> savedProducts = productRepository.saveAll(products);
        return savedProducts.stream()
                .map(ItemMapper::mapToProductDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        ProductEntity product = ItemMapper.mapToProduct(productDTO);
        ProductEntity savedProduct = productRepository.save(product);
        return ItemMapper.mapToProductDTO(savedProduct);
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        ProductEntity product = ItemMapper.mapToProduct(productDTO);
        ProductEntity updatedProduct = productRepository.save(product);
        return ItemMapper.mapToProductDTO(updatedProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}