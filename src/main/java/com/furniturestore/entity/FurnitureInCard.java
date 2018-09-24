package com.furniturestore.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class FurnitureInCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Furniture furniture;

    private int quantity;

    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private ShoppingCart shoppingCart;

    public FurnitureInCard() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FurnitureInCard)) return false;

        FurnitureInCard that = (FurnitureInCard) o;

        if (getQuantity() != that.getQuantity()) return false;
        if (!getId().equals(that.getId())) return false;
        if (!getFurniture().equals(that.getFurniture())) return false;
        if (!getTotalPrice().equals(that.getTotalPrice())) return false;
        return getShoppingCart().equals(that.getShoppingCart());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getFurniture().hashCode();
        result = 31 * result + getQuantity();
        result = 31 * result + getTotalPrice().hashCode();
        result = 31 * result + getShoppingCart().hashCode();
        return result;
    }
}
