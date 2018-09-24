package com.furniturestore.repository;

import com.furniturestore.entity.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FurnitureRepository extends JpaRepository<Furniture, Long>, JpaSpecificationExecutor<Furniture> {

    Furniture findByCollectionNameIdAndColorIdAndDenominationIdAndTypeFurnitureId(Long collectionName,
                                                                                          Long color,
                                                                                          Long denomination,
                                                                                          Long typeFurniture);
}
