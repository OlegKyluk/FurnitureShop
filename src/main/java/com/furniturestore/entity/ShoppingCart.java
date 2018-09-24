package com.furniturestore.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "shoppingCart")
    private List<User> userList = new ArrayList<>();

    @OneToMany(mappedBy = "shoppingCart")
    private List<FurnitureInCard> furnitureInCards = new ArrayList<>();

    private BigDecimal totalPrice;

    public ShoppingCart() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<FurnitureInCard> getFurnitureInCards() {
        return furnitureInCards;
    }

    public void setFurnitureInCards(List<FurnitureInCard> furnitureInCards) {
        this.furnitureInCards = furnitureInCards;
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
        if (!(o instanceof ShoppingCart)) return false;

        ShoppingCart that = (ShoppingCart) o;

        if (!getId().equals(that.getId())) return false;
        if (!getUserList().equals(that.getUserList())) return false;
        if (!getFurnitureInCards().equals(that.getFurnitureInCards())) return false;
        return getTotalPrice().equals(that.getTotalPrice());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getUserList().hashCode();
        result = 31 * result + getFurnitureInCards().hashCode();
        result = 31 * result + getTotalPrice().hashCode();
        return result;
    }
}
