package com.furniturestore.controller;

import com.furniturestore.entity.FurnitureInCard;
import com.furniturestore.entity.ShoppingCart;
import com.furniturestore.entity.User;
import com.furniturestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class QuantityController {

    private final UserRepository userRepository;

    @Autowired
    public QuantityController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ModelAttribute
    public void cartQuantity(Model model, Principal principal) {
        if (principal == null) return;
        User user = userRepository.findByEmail(principal.getName());
        ShoppingCart shoppingCart = user.getShoppingCart();
        int quantity = shoppingCart.getFurnitureInCards().stream().mapToInt(FurnitureInCard::getQuantity).sum();
        model.addAttribute("cartQuantity", quantity);
    }
}
