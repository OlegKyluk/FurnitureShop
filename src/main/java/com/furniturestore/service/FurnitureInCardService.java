package com.furniturestore.service;

import com.furniturestore.entity.FurnitureInCard;

import java.util.List;
import java.util.Optional;

public interface FurnitureInCardService {

    List<FurnitureInCard> findAll();

    Optional<FurnitureInCard> findOne(Long id);

    void save(FurnitureInCard furnitureInCard);

    void delete(Long id);

    void deleteAll(List<FurnitureInCard> furnitureInCards);

}
