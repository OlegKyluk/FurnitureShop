package com.furniturestore.serviceImpl;

import com.furniturestore.dto.filter.SimpleFilter;
import com.furniturestore.entity.TypeFurniture;
import com.furniturestore.repository.TypeFurnitureRepository;
import com.furniturestore.service.TypeFurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeFurnitureServiceImpl implements TypeFurnitureService {

    private final TypeFurnitureRepository typeFurnitureRepository;

    @Autowired
    public TypeFurnitureServiceImpl(TypeFurnitureRepository typeFurnitureRepository) {
        this.typeFurnitureRepository = typeFurnitureRepository;
    }

    @Override
    public List<TypeFurniture> findAll() {
        return typeFurnitureRepository.findAll();
    }

    @Override
    public Optional<TypeFurniture> findOne(Long id) {
        return typeFurnitureRepository.findById(id);
    }

    @Override
    public void save(TypeFurniture typeFurniture) {
        typeFurnitureRepository.save(typeFurniture);
    }

    @Override
    public void delete(Long id) {
        typeFurnitureRepository.deleteById(id);
    }

    @Override
    public Page<TypeFurniture> findAll(Pageable pageable, SimpleFilter filter) {
        return typeFurnitureRepository.findAll(filterMethod(filter), pageable);
    }

    @Override
    public Page<TypeFurniture> findAll(Pageable pageable) {
        return typeFurnitureRepository.findAll(pageable);
    }

    @Override
    public TypeFurniture findByName(String name) {
        return typeFurnitureRepository.findByName(name);
    }

    private Specification<TypeFurniture> filterMethod(SimpleFilter filter) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            if (filter.getSearch().isEmpty()) return null;
            return criteriaBuilder.like(root.get("name"), filter.getSearch() + "%");
        });
    }
}
