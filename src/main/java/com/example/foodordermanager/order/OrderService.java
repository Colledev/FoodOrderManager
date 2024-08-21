package com.example.foodordermanager.order;

import com.example.foodordermanager.order.dto.CustomerOrderDTO;
import com.example.foodordermanager.order.dto.OrderDTO;
import com.example.foodordermanager.order.dto.OrderDetailsDTO;
import com.example.foodordermanager.order.dto.OrderTableDTO;
import com.example.foodordermanager.orderproduct.OrderProductEntity;
import com.example.foodordermanager.orderproduct.OrderProductRepository;
import com.example.foodordermanager.orderproduct.dto.OrderProductDTO;
import com.example.foodordermanager.orderproductaddon.OrderProductAddonEntity;
import com.example.foodordermanager.orderproductaddon.dto.OrderProductAddonDTO;
import com.example.foodordermanager.product.dto.ProductDTO;
import com.example.foodordermanager.table.TableEntity;
import com.example.foodordermanager.table.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private TableRepository tableRepository;

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

        if (order.getOrderStatus() == OrderStatus.CANCELED || order.getOrderStatus() == OrderStatus.COMPLETED) {
            throw new RuntimeException("Order is either canceled or concluded");
        }

        return OrderMapper.toOrderDetailsDTO(order);
    }


    public OrderDTO createOrder(OrderDTO orderDTO) {
        TableEntity tableEntity = tableRepository.findByNumber(orderDTO.getTableNumber())
                .orElseThrow(() -> new RuntimeException("Table not found"));

        OrderEntity order = OrderMapper.mapToOrder(orderDTO, tableEntity);

        OrderEntity savedOrder = orderRepository.save(order);

        return OrderMapper.mapToOrderDTO(savedOrder);
    }

    public OrderTableDTO getOrdersByTableNumberGroupedByCustomer(Integer tableNumber) {
        TableEntity table = tableRepository.findByNumber(tableNumber)
                .orElseThrow(() -> new RuntimeException("Table not found"));

        List<OrderEntity> orders = orderRepository.findByTable(table).stream()
                .filter(order -> order.getOrderStatus() != OrderStatus.CANCELED &&
                        order.getOrderStatus() != OrderStatus.COMPLETED && order.getOrderStatus() != OrderStatus.PENDING)
                .toList();

        Map<String, List<OrderEntity>> ordersGroupedByCustomer = orders.stream()
                .collect(Collectors.groupingBy(OrderEntity::getCustomerName));

        List<CustomerOrderDTO> customerOrders = ordersGroupedByCustomer.entrySet().stream()
                .map(entry -> OrderMapper.fromOrders(entry.getKey(), entry.getValue()))
                .toList();

        // Total Price Calculation
        BigDecimal totalOverall = customerOrders.stream()
                .map(CustomerOrderDTO::getPriceTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // DTO Resume
        OrderTableDTO orderTableDTO = new OrderTableDTO();
        orderTableDTO.setTableNumber(tableNumber);
        orderTableDTO.setTotalPrice(totalOverall);
        orderTableDTO.setCustomerOrders(customerOrders);

        return orderTableDTO;
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
