package com.example.foodordermanager.order;

import com.example.foodordermanager.order.dto.OrderDTO;
import com.example.foodordermanager.order.dto.OrderDetailsDTO;
import com.example.foodordermanager.orderproduct.OrderProductEntity;
import com.example.foodordermanager.orderproduct.OrderProductMapper;
import com.example.foodordermanager.orderproduct.dto.OrderProductDTO;
import com.example.foodordermanager.orderproductaddon.dto.OrderProductAddonDTO;
import com.example.foodordermanager.product.ProductMapper;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDTO mapToOrderDTO(OrderEntity order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCustomerName(order.getCustomerName());
        orderDTO.setOrderStatus(order.getOrderStatus().name());
        orderDTO.setPriceTotal(order.getPriceTotal());
        return orderDTO;
    }

    public static OrderEntity mapToOrder(OrderDTO orderDTO) {
        OrderEntity order = new OrderEntity();
        order.setId(orderDTO.getId());
        order.setCustomerName(orderDTO.getCustomerName());
        order.setOrderStatus(OrderStatus.valueOf(orderDTO.getOrderStatus()));
        order.setPriceTotal(orderDTO.getPriceTotal());
        return order;
    }

    public static OrderDetailsDTO toOrderDetailsDTO(OrderEntity orderEntity) {
        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
        orderDetailsDTO.setOrderId(orderEntity.getId());
        orderDetailsDTO.setOrderStatus(orderEntity.getOrderStatus());
        orderDetailsDTO.setPriceTotal(orderEntity.getPriceTotal().doubleValue());

        List<OrderProductDTO> orderProductDTOS = orderEntity.getOrderProducts().stream()
                .map(orderProductEntity -> {
                    OrderProductDTO orderProductDTO = new OrderProductDTO();
                    orderProductDTO.setOrderId(orderEntity.getId());
                    orderProductDTO.setId(orderProductEntity.getId());
                    orderProductDTO.setProductId(orderProductEntity.getProduct().getId());
                    orderProductDTO.setQuantity(orderProductEntity.getQuantity());

                    String productName = orderProductEntity.getProduct().getName();
                    orderProductDTO.setProductName(productName);

                    List<OrderProductAddonDTO> orderProductAddonDTOS = orderProductEntity.getAddons().stream()
                            .map(orderProductAddonEntity -> {
                                OrderProductAddonDTO orderProductAddonDTO = new OrderProductAddonDTO();
                                orderProductAddonDTO.setAddonId(orderProductAddonEntity.getAddon().getId());
                                orderProductAddonDTO.setQuantity(orderProductAddonEntity.getQuantity());

                                String addonName = orderProductAddonEntity.getAddon().getName();
                                orderProductAddonDTO.setAddonName(addonName);

                                return orderProductAddonDTO;
                            })
                            .collect(Collectors.toList());

                    orderProductDTO.setAddons(orderProductAddonDTOS);

                    return orderProductDTO;
                })
                .collect(Collectors.toList());

        orderDetailsDTO.setProducts(orderProductDTOS);

        return orderDetailsDTO;
    }
}
