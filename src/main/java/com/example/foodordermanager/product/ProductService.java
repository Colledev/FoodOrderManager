package com.example.foodordermanager.product;

import com.example.foodordermanager.product.dto.ProductDTO;
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
                .map(ProductMapper::mapToProductDTO)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> createProducts(List<ProductDTO> productDTOs) {
        List<ProductEntity> products = productDTOs.stream()
                .map(ProductMapper::mapToProduct)
                .collect(Collectors.toList());
        List<ProductEntity> savedProducts = productRepository.saveAll(products);
        return savedProducts.stream()
                .map(ProductMapper::mapToProductDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        ProductEntity product = ProductMapper.mapToProduct(productDTO);
        ProductEntity savedProduct = productRepository.save(product);
        return ProductMapper.mapToProductDTO(savedProduct);
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        ProductEntity product = ProductMapper.mapToProduct(productDTO);
        ProductEntity updatedProduct = productRepository.save(product);
        return ProductMapper.mapToProductDTO(updatedProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}