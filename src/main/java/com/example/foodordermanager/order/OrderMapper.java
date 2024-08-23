package com.example.foodordermanager.order;

import com.example.foodordermanager.order.dto.*;
import com.example.foodordermanager.orderproduct.OrderProductEntity;
import com.example.foodordermanager.orderproduct.OrderProductMapper;
import com.example.foodordermanager.orderproduct.dto.OrderProductDTO;
import com.example.foodordermanager.orderproductaddon.dto.OrderProductAddonDTO;
import com.example.foodordermanager.payment.PaymentEntity;
import com.example.foodordermanager.product.ProductMapper;
import com.example.foodordermanager.table.TableEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDTO mapToOrderDTO(OrderEntity order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCustomerName(order.getCustomerName());
        orderDTO.setOrderStatus(order.getOrderStatus().name());
        orderDTO.setPriceTotal(order.getPriceTotal());
        orderDTO.setTableNumber(order.getTable().getNumber());
        return orderDTO;
    }

    public static OrderEntity mapToOrder(OrderDTO orderDTO, TableEntity tableEntity) {
        OrderEntity order = new OrderEntity();
        order.setId(orderDTO.getId());
        order.setCustomerName(orderDTO.getCustomerName());
        order.setTable(tableEntity);
        order.setOrderStatus(OrderStatus.valueOf(orderDTO.getOrderStatus()));
        order.setPriceTotal(orderDTO.getPriceTotal());
        return order;
    }

    public static OrderWithPayment toOrderWithPayment(OrderEntity orderEntity) {
        OrderWithPayment orderWithPayment = new OrderWithPayment();
        orderWithPayment.setId(orderEntity.getId());
        orderWithPayment.setCustomerName(orderEntity.getCustomerName());
        orderWithPayment.setOrderStatus(orderEntity.getOrderStatus().name());
        orderWithPayment.setTableNumber(orderEntity.getTable().getNumber());
        orderWithPayment.setPriceTotal(orderEntity.getPriceTotal());

        BigDecimal totalPaid = orderEntity.getPayment().stream()
                .map(PaymentEntity::getAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        orderWithPayment.setTotalPaid(totalPaid);

        return orderWithPayment;
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

                    BigDecimal productPrice = orderProductEntity.getProduct().getPrice().multiply(BigDecimal.valueOf(orderProductEntity.getQuantity()));
                    orderProductDTO.setPrice(productPrice);

                    String productName = orderProductEntity.getProduct().getName();
                    orderProductDTO.setProductName(productName);

                    List<OrderProductAddonDTO> orderProductAddonDTOS = orderProductEntity.getAddons().stream()
                            .map(orderProductAddonEntity -> {
                                OrderProductAddonDTO orderProductAddonDTO = new OrderProductAddonDTO();
                                orderProductAddonDTO.setAddonId(orderProductAddonEntity.getAddon().getId());
                                orderProductAddonDTO.setQuantity(orderProductAddonEntity.getQuantity());

                                BigDecimal addonPrice = orderProductAddonEntity.getAddon().getPrice().multiply(BigDecimal.valueOf(orderProductAddonEntity.getQuantity()));
                                orderProductAddonDTO.setPrice(addonPrice);

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

    public static CustomerOrderDTO fromOrders(String customerName, List<OrderEntity> orders) {
        CustomerOrderDTO customerOrderDTO = new CustomerOrderDTO();
        customerOrderDTO.setCustomerName(customerName);

        BigDecimal totalPrice = orders.stream()
                .map(OrderEntity::getPriceTotal)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        customerOrderDTO.setPriceTotal(totalPrice);

        List<OrderDetailsDTO> orderDetailsDTOS = orders.stream()
                .map(OrderMapper::toOrderDetailsDTO)
                .toList();

        List<OrderProductDTO> allProducts = orderDetailsDTOS.stream()
                .flatMap(orderDetailsDTO -> orderDetailsDTO.getProducts().stream())
                .collect(Collectors.toList());

        customerOrderDTO.setProducts(allProducts);

        return customerOrderDTO;
    }

}
