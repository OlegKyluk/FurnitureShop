package com.furniturestore.repository;

import com.furniturestore.entity.CollectionName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionNameRepository extends JpaRepository<CollectionName, Long>, JpaSpecificationExecutor<CollectionName> {
    CollectionName findByName(String name);
}
