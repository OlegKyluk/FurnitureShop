package com.furniturestore.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Furniture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    private TypeFurniture typeFurniture;

    @ManyToOne(fetch = FetchType.LAZY)
    private Denomination denomination;

    @ManyToOne(fetch = FetchType.LAZY)
    private CollectionName collectionName;

    @OneToMany(mappedBy = "furniture")
    private List<FurnitureInCard> furnitureInCardList = new ArrayList<>();

    private String path;

    private String description;

    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeFurniture getTypeFurniture() {
        return typeFurniture;
    }

    public void setTypeFurniture(TypeFurniture typeFurniture) {
        this.typeFurniture = typeFurniture;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public void setDenomination(Denomination denomination) {
        this.denomination = denomination;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CollectionName getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(CollectionName collectionName) {
        this.collectionName = collectionName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<FurnitureInCard> getFurnitureInCardList() {
        return furnitureInCardList;
    }

    public void setFurnitureInCardList(List<FurnitureInCard> furnitureInCardList) {
        this.furnitureInCardList = furnitureInCardList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Furniture)) return false;

        Furniture furniture = (Furniture) o;

        if (!getId().equals(furniture.getId())) return false;
        if (!getColor().equals(furniture.getColor())) return false;
        if (!getTypeFurniture().equals(furniture.getTypeFurniture())) return false;
        if (!getDenomination().equals(furniture.getDenomination())) return false;
        if (!getCollectionName().equals(furniture.getCollectionName())) return false;
        if (!getFurnitureInCardList().equals(furniture.getFurnitureInCardList())) return false;
        if (!getPath().equals(furniture.getPath())) return false;
        if (!getDescription().equals(furniture.getDescription())) return false;
        return getPrice().equals(furniture.getPrice());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getColor().hashCode();
        result = 31 * result + getTypeFurniture().hashCode();
        result = 31 * result + getDenomination().hashCode();
        result = 31 * result + getCollectionName().hashCode();
        result = 31 * result + getFurnitureInCardList().hashCode();
        result = 31 * result + getPath().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getPrice().hashCode();
        return result;
    }
}
