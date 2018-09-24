package com.furniturestore.repository;

import com.furniturestore.entity.TypeFurniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeFurnitureRepository extends JpaRepository<TypeFurniture, Long>, JpaSpecificationExecutor<TypeFurniture> {
    TypeFurniture findByName(String name);
}
