package com.furniturestore.service;

import com.furniturestore.entity.CollectionName;
import com.furniturestore.dto.filter.SimpleFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CollectionNameService {

    List<CollectionName> findAll();

    Optional<CollectionName> findById(Long id);

    void save(CollectionName collectionName);

    void delete(Long id);

    Page<CollectionName> findAll(SimpleFilter filter,Pageable pageable);

    Page<CollectionName> findAll(Pageable pageable);

    CollectionName findByName(String name);
}
