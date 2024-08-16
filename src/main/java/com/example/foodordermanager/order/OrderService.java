package com.example.foodordermanager.order;

import com.example.foodordermanager.order.dto.OrderDTO;
import com.example.foodordermanager.order.dto.OrderDetailsDTO;
import com.example.foodordermanager.orderproduct.OrderProductEntity;
import com.example.foodordermanager.orderproduct.OrderProductRepository;
import com.example.foodordermanager.orderproduct.dto.OrderProductDTO;
import com.example.foodordermanager.orderproductaddon.OrderProductAddonEntity;
import com.example.foodordermanager.orderproductaddon.dto.OrderProductAddonDTO;
import com.example.foodordermanager.product.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    public void updateOrderTotalPrice(Long orderId) {
        OrderEntity order = findById(orderId);
        BigDecimal totalPrice = calculateTotalPrice(orderId);
        order.setPriceTotal(totalPrice);
        orderRepository.save(order);
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderMapper::mapToOrderDTO)
                .collect(Collectors.toList());
    }

    public OrderDetailsDTO getOrderDetails(Long id) {
        OrderEntity order = findById(id);
        return OrderMapper.toOrderDetailsDTO(order);
    }


    public OrderDTO createOrder(OrderDTO orderDTO) {
        OrderEntity order = OrderMapper.mapToOrder(orderDTO);
        OrderEntity savedOrder = orderRepository.save(order);
        return OrderMapper.mapToOrderDTO(savedOrder);
    }

    public OrderEntity findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public OrderDTO updateOrderStatus(Long id, OrderStatus newStatus) {
        OrderEntity order = findById(id);
        if (isValidStatusTransition(order.getOrderStatus(), newStatus)) {

            if (newStatus == OrderStatus.IN_PRODUCTION) {
                updateOrderTotalPrice(id);
            }

            order.setOrderStatus(newStatus);
            orderRepository.save(order);
        } else {
            throw new RuntimeException("Invalid status transition");
        }

        return OrderMapper.mapToOrderDTO(order);
    }

    private boolean isValidStatusTransition(OrderStatus currentStatus, OrderStatus newStatus) {
        switch (currentStatus) {
            case PENDING:
                return newStatus == OrderStatus.IN_PRODUCTION || newStatus == OrderStatus.CANCELED;
            case IN_PRODUCTION:
                return newStatus == OrderStatus.AWAITING_PAYMENT || newStatus == OrderStatus.CANCELED;
            case AWAITING_PAYMENT:
                return newStatus == OrderStatus.COMPLETED || newStatus == OrderStatus.CANCELED;
            case CANCELED:
                return newStatus == OrderStatus.CANCELED;
            default:
                return false;
        }
    }

    public BigDecimal calculateTotalPrice(Long orderId) {
        OrderEntity order = findById(orderId);
        List<OrderProductEntity> orderProducts = order.getOrderProducts();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderProductEntity orderProduct : orderProducts) {
            BigDecimal productPrice = orderProduct.getProduct().getPrice();
            totalPrice = totalPrice.add(productPrice.multiply(BigDecimal.valueOf(orderProduct.getQuantity())));

            List<OrderProductAddonEntity> addons = orderProduct.getAddons();
            for (OrderProductAddonEntity addon : addons) {
                BigDecimal addonPrice = addon.getAddon().getPrice();
                totalPrice = totalPrice.add(addonPrice.multiply(BigDecimal.valueOf(orderProduct.getQuantity())));
            }
        }
        return totalPrice;
    }
}
