package com.furniturestore.service;

import com.furniturestore.entity.Denomination;
import com.furniturestore.dto.filter.SimpleFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DenominationService {

	List<Denomination> findAll();

	Optional<Denomination> findOne(Long id);

	void save(Denomination denomination);

	void delete(Long id);

	Page<Denomination> findAll(Pageable pageable, SimpleFilter filter);

	Page<Denomination> findAll(Pageable pageable);

	Denomination findByName(String name);
}
