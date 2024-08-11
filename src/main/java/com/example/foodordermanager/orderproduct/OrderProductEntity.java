package com.example.foodordermanager.orderproduct;

import com.example.foodordermanager.order.OrderEntity;
import com.example.foodordermanager.orderproductaddon.OrderProductAddonEntity;
import com.example.foodordermanager.product.ProductEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "order_product")
public class OrderProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    private Integer quantity;

    @OneToMany(mappedBy = "orderProduct", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProductAddonEntity> addons;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<OrderProductAddonEntity> getAddons() {
        return addons;
    }

    public void setAddons(List<OrderProductAddonEntity> addons) {
        this.addons = addons;
    }
}
