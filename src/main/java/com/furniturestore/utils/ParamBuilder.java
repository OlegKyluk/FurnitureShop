package com.furniturestore.utils;

import java.util.List;

public class ParamBuilder {

    public static int page;

    public static int size;

    public static String search;

    public static String maxPrice;

    public static String minPrice;

    public static List<Long> collectionNameId;

    public static List<Long> typeFurnitureId;

    public static List<Long> denominationId;

    public static List<Long> colorId;

    public static String getSimpleModelParams() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("?search=");
        buffer.append(String.valueOf(search));
        buffer.append("&size=");
        buffer.append(String.valueOf(size));
        buffer.append("&page=");
        buffer.append(String.valueOf(page));
        System.out.println(buffer.toString());
        return buffer.toString();
    }

    public static String getMultipleModelParams() {
        String page = ParamBuilder.getSimpleModelParams();
        StringBuilder buffer = new StringBuilder(page);
        if (!maxPrice.isEmpty()) {
            buffer.append("&max=");
            buffer.append(maxPrice);
        }
        if (!minPrice.isEmpty()) {
            buffer.append("&min=");
            buffer.append(minPrice);
        }
        if (!collectionNameId.isEmpty()) {
            for (Long id : collectionNameId) {
                buffer.append("&collectionNameId=");
                buffer.append(id);
            }
        }
        if (!typeFurnitureId.isEmpty()) {
            for (Long id : typeFurnitureId) {
                buffer.append("&typeFurnitureId=");
                buffer.append(id);
            }
        }
        if (!denominationId.isEmpty()) {
            for (Long id : denominationId) {
                buffer.append("&denominationId=");
                buffer.append(id);
            }
        }
        if (!colorId.isEmpty()) {
            for (Long id : colorId) {
                buffer.append("&colorId=");
                buffer.append(id);
            }
        }
        return buffer.toString();
    }


}
