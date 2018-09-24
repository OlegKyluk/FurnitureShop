package com.furniturestore.serviceImpl;

import com.furniturestore.dto.FurnitureForm;
import com.furniturestore.dto.filter.FurnitureFilter;
import com.furniturestore.entity.*;
import com.furniturestore.repository.FurnitureRepository;
import com.furniturestore.service.FurnitureService;
import com.furniturestore.specification.FurnitureSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static com.furniturestore.utils.ParamBuilder.*;

@Service
public class FurnitureServiceImpl implements FurnitureService {

    private final FurnitureRepository furnitureRepository;

    @Autowired
    public FurnitureServiceImpl(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    @Override
    public List<Furniture> findAll() {
        return furnitureRepository.findAll();
    }

    @Override
    public Page<Furniture> findAll(Pageable pageable) {
        return furnitureRepository.findAll(pageable);
    }

    @Override
    public Page<Furniture> findAll(FurnitureFilter filter, Pageable pageable) {
        page = pageable.getPageNumber();
        size = pageable.getPageSize();
        maxPrice = filter.getMax();
        minPrice = filter.getMin();
        collectionNameId = filter.getCollectionNameId();
        typeFurnitureId = filter.getTypeFurnitureId();
        denominationId = filter.getDenominationId();
        colorId = filter.getColorId();
        return furnitureRepository.findAll(new FurnitureSpecification(filter), pageable);
    }

    @Override
    public Optional<Furniture> findOne(Long id) {
        return furnitureRepository.findById(id);
    }

    @Override
    public Optional<FurnitureForm> findForm(Long id) {
        Optional<Furniture> entity = findOne(id);
        FurnitureForm form = new FurnitureForm();
        entity.ifPresent(furniture -> {
            form.setId(furniture.getId());
            form.setPrice(String.valueOf(furniture.getPrice()));
            form.setCollectionName(furniture.getCollectionName());
            form.setTypeFurniture(furniture.getTypeFurniture());
            form.setDenomination(furniture.getDenomination());
            form.setColor(furniture.getColor());
            form.setPath(furniture.getPath());
            form.setDescription(furniture.getDescription());

        });
        return Optional.of(form);
    }

    @Override
    public void save(FurnitureForm form, MultipartFile file) throws IOException {
        Furniture entity = new Furniture();
        if (!file.getOriginalFilename().isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("/home/oleh_kulyk/eclipse-workspace/FurnitureShop-master.zip_expanded/FurnitureShop-master/" + file.getOriginalFilename());
            Files.write(path, bytes);
            form.setPath(String.valueOf(path));
        }
        entity.setId(form.getId());
        entity.setPrice(new BigDecimal(form.getPrice().replace(',', '.')));
        entity.setCollectionName(form.getCollectionName());
        entity.setTypeFurniture(form.getTypeFurniture());
        entity.setDenomination(form.getDenomination());
        entity.setColor(form.getColor());
        entity.setPath(form.getPath());
        entity.setDescription(form.getDescription());
        furnitureRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        furnitureRepository.deleteById(id);
    }

    @Override
    public Furniture findUnique(CollectionName collectionName, Color color, Denomination denomination, TypeFurniture typeFurniture) {
        return furnitureRepository.findByCollectionNameIdAndColorIdAndDenominationIdAndTypeFurnitureId(collectionName.getId(),
                color.getId(), denomination.getId(), typeFurniture.getId());
    }


}
