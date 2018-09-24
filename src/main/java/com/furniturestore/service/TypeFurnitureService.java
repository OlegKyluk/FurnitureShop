package com.furniturestore.service;

import com.furniturestore.entity.TypeFurniture;
import com.furniturestore.dto.filter.SimpleFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TypeFurnitureService {

    List<TypeFurniture> findAll();

    Optional<TypeFurniture> findOne(Long id);

    void save(TypeFurniture typeFurniture);

    void delete(Long id);

    Page<TypeFurniture> findAll(Pageable pageable, SimpleFilter filter);

    Page<TypeFurniture> findAll(Pageable pageable);

    TypeFurniture findByName(String name);

}
