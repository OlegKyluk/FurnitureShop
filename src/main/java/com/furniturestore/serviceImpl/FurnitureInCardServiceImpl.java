package com.furniturestore.serviceImpl;

import com.furniturestore.entity.FurnitureInCard;
import com.furniturestore.repository.FurnitureInCardRepository;
import com.furniturestore.service.FurnitureInCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FurnitureInCardServiceImpl implements FurnitureInCardService {

    private final FurnitureInCardRepository furnitureInCardRepository;

    @Autowired
    public FurnitureInCardServiceImpl(FurnitureInCardRepository furnitureInCardRepository) {
        this.furnitureInCardRepository = furnitureInCardRepository;
    }

    @Override
    public List<FurnitureInCard> findAll() {
        return furnitureInCardRepository.findAll();
    }

    @Override
    public Optional<FurnitureInCard> findOne(Long id) {
        return furnitureInCardRepository.findById(id);
    }

    @Override
    public void save(FurnitureInCard furnitureInCard) {
            furnitureInCardRepository.save(furnitureInCard);
    }

    @Override
    public void delete(Long id) {
            furnitureInCardRepository.deleteById(id);
    }

    @Override
    public void deleteAll(List<FurnitureInCard> furnitureInCards) {
        furnitureInCardRepository.deleteAll(furnitureInCards);
    }
}
