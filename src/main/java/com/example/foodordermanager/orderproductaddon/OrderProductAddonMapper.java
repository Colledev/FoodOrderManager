package com.example.foodordermanager.orderproductaddon;

import com.example.foodordermanager.addon.AddonEntity;
import com.example.foodordermanager.orderproduct.OrderProductEntity;
import com.example.foodordermanager.orderproductaddon.dto.OrderProductAddonDTO;

public class OrderProductAddonMapper {

    public static OrderProductAddonEntity mapToEntity(OrderProductAddonDTO dto, OrderProductEntity orderProduct) {
        OrderProductAddonEntity entity = new OrderProductAddonEntity();
        entity.setOrderProduct(orderProduct);
        AddonEntity addon = new AddonEntity();
        addon.setId(dto.getAddonId());
        entity.setAddon(addon);
        entity.setQuantity(dto.getQuantity());

        return entity;
    }

    public static OrderProductAddonDTO mapToDTO(OrderProductAddonEntity entity) {
        OrderProductAddonDTO dto = new OrderProductAddonDTO();
        dto.setAddonId(entity.getAddon().getId());
        dto.setQuantity(entity.getQuantity());

        return dto;
    }
}
