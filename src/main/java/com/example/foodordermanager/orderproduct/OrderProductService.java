package com.example.foodordermanager.orderproduct;

import com.example.foodordermanager.order.OrderEntity;
import com.example.foodordermanager.order.OrderRepository;
import com.example.foodordermanager.orderproduct.dto.OrderProductDTO;
import com.example.foodordermanager.product.ProductEntity;
import com.example.foodordermanager.product.ProductRepository;
import com.example.foodordermanager.orderproductaddon.OrderProductAddonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderProductService {

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderProductAddonRepository orderProductAddonRepository;

    public OrderProductDTO createOrderProduct(OrderProductDTO orderProductDTO) {
        // Buscar a entidade OrderEntity e ProductEntity
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(orderProductDTO.getOrderId());
        Optional<ProductEntity> productEntityOptional = productRepository.findById(orderProductDTO.getProductId());

        if (orderEntityOptional.isEmpty() || productEntityOptional.isEmpty()) {
            throw new RuntimeException("Order or Product not found");
        }

        OrderEntity orderEntity = orderEntityOptional.get();
        ProductEntity productEntity = productEntityOptional.get();

        // Mapear DTO para Entidade
        OrderProductEntity orderProductEntity = OrderProductMapper.mapToEntity(orderProductDTO, orderEntity, productEntity);

        // Salvar a entidade OrderProduct e as entidades OrderProductAddon
        orderProductEntity = orderProductRepository.save(orderProductEntity);

        // Retornar o DTO
        return OrderProductMapper.mapToDTO(orderProductEntity);
    }
}
