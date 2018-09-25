package com.furniturestore.serviceImpl;

import com.furniturestore.entity.Denomination;
import com.furniturestore.dto.filter.SimpleFilter;
import com.furniturestore.repository.DenominationRepository;
import com.furniturestore.service.DenominationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DenominationServiceImpl implements DenominationService {

	private final DenominationRepository denominationRepository;

	@Autowired
	public DenominationServiceImpl(DenominationRepository denominationRepository) {
		this.denominationRepository = denominationRepository;
	}

	@Override
	public List<Denomination> findAll() {
		return denominationRepository.findAll();
	}

	@Override
	public Optional<Denomination> findOne(Long id) {
		return denominationRepository.findById(id);
	}

	@Override
	public void save(Denomination denomination) {
		denominationRepository.save(denomination);
	}

	@Override
	public void delete(Long id) {
		denominationRepository.deleteById(id);
	}

	@Override
	public Page<Denomination> findAll(Pageable pageable, SimpleFilter filter) {
		return denominationRepository.findAll(filterMethod(filter), pageable);
	}

	@Override
	public Page<Denomination> findAll(Pageable pageable) {
		return denominationRepository.findAll(pageable);
	}

	@Override
	public Denomination findByName(String name) {
		return denominationRepository.findByName(name);
	}

	private Specification<Denomination> filterMethod(SimpleFilter filter) {
		return ((root, criteriaQuery, criteriaBuilder) -> {
			if (filter.getSearch().isEmpty())
				return null;
			return criteriaBuilder.like(root.get("name"), filter.getSearch() + "%");
		});
	}
}
