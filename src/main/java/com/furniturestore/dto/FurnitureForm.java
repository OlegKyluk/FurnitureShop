package com.furniturestore.dto;

import com.furniturestore.entity.CollectionName;
import com.furniturestore.entity.Color;
import com.furniturestore.entity.Denomination;
import com.furniturestore.entity.TypeFurniture;

public class FurnitureForm {

    private Long id;

    private String price;

    private CollectionName collectionName;

    private TypeFurniture typeFurniture;

    private Denomination denomination;

    private Color color;

    private String path;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public CollectionName getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(CollectionName collectionName) {
        this.collectionName = collectionName;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
