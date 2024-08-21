package com.example.foodordermanager.orderproduct;

import com.example.foodordermanager.order.OrderEntity;
import com.example.foodordermanager.orderproduct.dto.OrderProductDTO;
import com.example.foodordermanager.orderproductaddon.dto.OrderProductAddonDTO;
import com.example.foodordermanager.product.ProductEntity;
import com.example.foodordermanager.orderproductaddon.OrderProductAddonMapper;
import com.example.foodordermanager.orderproductaddon.OrderProductAddonEntity;

import java.util.List;
import java.util.stream.Collectors;

public class OrderProductMapper {

    public static OrderProductEntity mapToEntity(OrderProductDTO dto, OrderEntity order, ProductEntity product) {
        OrderProductEntity entity = new OrderProductEntity();
        entity.setOrder(order);
        entity.setProduct(product);
        entity.setQuantity(dto.getQuantity());

        List<OrderProductAddonEntity> addons = dto.getAddons().stream()
                .map(addonDTO -> OrderProductAddonMapper.mapToEntity(addonDTO, entity))
                .collect(Collectors.toList());

        entity.setAddons(addons);

        return entity;
    }

    public static OrderProductDTO mapToDTO(OrderProductEntity entity) {
        OrderProductDTO dto = new OrderProductDTO();
        dto.setOrderId(entity.getOrder().getId());
        dto.setProductId(entity.getProduct().getId());
        dto.setQuantity(entity.getQuantity());

        List<OrderProductAddonDTO> addons = entity.getAddons().stream()
                .map(OrderProductAddonMapper::mapToDTO)
                .collect(Collectors.toList());

        dto.setAddons(addons);

        return dto;
    }
}
