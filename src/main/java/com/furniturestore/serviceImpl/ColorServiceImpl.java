package com.furniturestore.serviceImpl;

import com.furniturestore.dto.filter.SimpleFilter;
import com.furniturestore.entity.Color;
import com.furniturestore.repository.ColorRepository;
import com.furniturestore.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.furniturestore.utils.ParamBuilder.*;

@Service
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    @Autowired
    public ColorServiceImpl(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;

    }

    @Override
    public List<Color> findAll() {
        return colorRepository.findAll();
    }

    @Override
    public Optional<Color> findById(Long id) {
        return colorRepository.findById(id);
    }

    @Override
    public void save(Color color) {
        colorRepository.save(color);
    }

    @Override
    public void delete(Long id) {
        colorRepository.deleteById(id);
    }

    @Override
    public Page<Color> findAll(SimpleFilter filter, Pageable pageable) {
        page = pageable.getPageNumber();
        size = pageable.getPageSize();
        search = String.valueOf(filter.getSearch());
        return colorRepository.findAll(filterMethod(filter), pageable);
    }

    @Override
    public Page<Color> findAll(Pageable pageable) {
        return colorRepository.findAll(pageable);
    }

    @Override
    public Color findByName(String name) {
        return colorRepository.findByName(name);
    }

    private Specification<Color> filterMethod(SimpleFilter filter) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            if (filter.getSearch().isEmpty()) return null;
            return criteriaBuilder.like(root.get("name"), filter.getSearch() + "%");
        });
    }

}
