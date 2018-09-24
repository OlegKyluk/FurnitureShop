package com.furniturestore.repository;

import com.furniturestore.entity.Denomination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DenominationRepository extends JpaRepository<Denomination, Long>, JpaSpecificationExecutor<Denomination> {
    Denomination findByName(String name);
}
