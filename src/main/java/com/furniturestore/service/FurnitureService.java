package com.furniturestore.service;

import com.furniturestore.dto.FurnitureForm;
import com.furniturestore.dto.filter.FurnitureFilter;
import com.furniturestore.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FurnitureService {

    List<Furniture> findAll();

    Page<Furniture> findAll(FurnitureFilter filter, Pageable pageable);

    Page<Furniture> findAll(Pageable pageable);

    Optional<Furniture> findOne(Long id);

    Optional<FurnitureForm> findForm(Long id);

    void save(FurnitureForm form, MultipartFile file) throws IOException;

    void delete(Long id);

    Furniture findUnique(CollectionName collectionName,
                         Color color,
                         Denomination denomination,
                         TypeFurniture typeFurniture);

}
