package com.furniturestore.service;

import com.furniturestore.entity.ShoppingCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {

    List<ShoppingCart> findAll();

    Optional<ShoppingCart> findOne(Long id);

    void save(ShoppingCart shoppingCart);

    void delete(Long id);

    Page<ShoppingCart> findAll(Pageable pageable);

    void addItem(Long id, Principal principal);

    void incrementItem(Long id, Principal principal);

    void decrementItem(Long id, Principal principal);

    void removeItem(Long id, Principal principal);

    void removeAllItems(Principal principal);


}
