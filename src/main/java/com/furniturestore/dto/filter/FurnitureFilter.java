package com.furniturestore.dto.filter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FurnitureFilter {

    private String min = "";

    private String max = "";

    private BigDecimal minValue;

    private BigDecimal maxValue;

    private List<Long> colorId = new ArrayList<>();

    private List<Long> denominationId = new ArrayList<>();

    private List<Long> typeFurnitureId = new ArrayList<>();

    private List<Long> collectionNameId = new ArrayList<>();

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public BigDecimal getMinValue() {
        return minValue;
    }

    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }

    public BigDecimal getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }

    public List<Long> getColorId() {
        return colorId;
    }

    public void setColorId(List<Long> colorId) {
        this.colorId = colorId;
    }

    public List<Long> getDenominationId() {
        return denominationId;
    }

    public void setDenominationId(List<Long> denominationId) {
        this.denominationId = denominationId;
    }

    public List<Long> getTypeFurnitureId() {
        return typeFurnitureId;
    }

    public void setTypeFurnitureId(List<Long> typeFurnitureId) {
        this.typeFurnitureId = typeFurnitureId;
    }

    public List<Long> getCollectionNameId() {
        return collectionNameId;
    }

    public void setCollectionNameId(List<Long> collectionNameId) {
        this.collectionNameId = collectionNameId;
    }
}
