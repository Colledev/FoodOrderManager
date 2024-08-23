package com.example.foodordermanager.orderproduct;

import com.example.foodordermanager.order.OrderEntity;
import com.example.foodordermanager.order.OrderRepository;
import com.example.foodordermanager.order.OrderService;
import com.example.foodordermanager.order.OrderStatus;
import com.example.foodordermanager.orderproduct.dto.OrderProductDTO;
import com.example.foodordermanager.product.ProductEntity;
import com.example.foodordermanager.product.ProductRepository;
import com.example.foodordermanager.orderproductaddon.OrderProductAddonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    @Autowired
    private OrderService orderService;

    public OrderProductDTO createOrderProduct(OrderProductDTO orderProductDTO) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(orderProductDTO.getOrderId());
        Optional<ProductEntity> productEntityOptional = productRepository.findById(orderProductDTO.getProductId());

        if (orderEntityOptional.isEmpty() || productEntityOptional.isEmpty()) {
            throw new RuntimeException("Order or Product not found");
        }

        OrderEntity orderEntity = orderEntityOptional.get();
        ProductEntity productEntity = productEntityOptional.get();

        if (orderEntity.getOrderStatus() != OrderStatus.PENDING) {
            throw new RuntimeException("Cannot add products to an order that is already in production");
        }

        OrderProductEntity orderProductEntity = OrderProductMapper.mapToEntity(orderProductDTO, orderEntity, productEntity);

        orderProductEntity = orderProductRepository.save(orderProductEntity);

        return OrderProductMapper.mapToDTO(orderProductEntity);
    }

    public void deleteOrderProductAndUpdatePrice(Long orderProductId) {
        Optional<OrderProductEntity> orderProductOptional = orderProductRepository.findById(orderProductId);

        if (orderProductOptional.isEmpty()) {
            throw new RuntimeException("Order product not found");
        }

        OrderProductEntity orderProductEntity = orderProductOptional.get();
        OrderEntity orderEntity = orderProductEntity.getOrder();

        if (orderEntity.getOrderStatus() == OrderStatus.IN_PRODUCTION ||
                orderEntity.getOrderStatus() == OrderStatus.AWAITING_PAYMENT) {

            orderProductRepository.deleteById(orderProductId);

            orderService.updateOrderTotalPrice(orderEntity.getId());
        } else {
            throw new RuntimeException("Cannot delete products from an order with the current status");
        }
    }
}
