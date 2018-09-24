package com.furniturestore.serviceImpl;

import com.furniturestore.dto.filter.SimpleFilter;
import com.furniturestore.entity.CollectionName;
import com.furniturestore.repository.CollectionNameRepository;
import com.furniturestore.service.CollectionNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.furniturestore.utils.ParamBuilder.*;

@Service
public class CollectionNameServiceImpl implements CollectionNameService {

    private final CollectionNameRepository collectionNameRepository;

    @Autowired
    public CollectionNameServiceImpl(CollectionNameRepository collectionNameRepository) {
        this.collectionNameRepository = collectionNameRepository;
    }

    @Override
    public List<CollectionName> findAll() {
        return collectionNameRepository.findAll();
    }

    @Override
    public Optional<CollectionName> findById(Long id) {
        return collectionNameRepository.findById(id);
    }

    @Override
    public void save(CollectionName collectionName) {
        collectionNameRepository.save(collectionName);
    }

    @Override
    public void delete(Long id) {
        collectionNameRepository.deleteById(id);
    }

    @Override
    public Page<CollectionName> findAll(SimpleFilter filter, Pageable pageable) {
        page = pageable.getPageNumber();
        size = pageable.getPageSize();
        search = String.valueOf(filter.getSearch());
        return collectionNameRepository.findAll(filterMethod(filter), pageable);
    }

    @Override
    public Page<CollectionName> findAll(Pageable pageable) {
        return collectionNameRepository.findAll(pageable);
    }

    @Override
    public CollectionName findByName(String name) {
        return collectionNameRepository.findByName(name);
    }

    private Specification<CollectionName> filterMethod(SimpleFilter filter) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            if (filter.getSearch().isEmpty()) return null;
            return criteriaBuilder.like(root.get("name"), filter.getSearch() + "%");
        });
    }
}
