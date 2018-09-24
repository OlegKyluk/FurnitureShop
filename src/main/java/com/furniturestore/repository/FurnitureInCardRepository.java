package com.furniturestore.repository;

import com.furniturestore.entity.FurnitureInCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FurnitureInCardRepository extends JpaRepository<FurnitureInCard, Long> {
}
