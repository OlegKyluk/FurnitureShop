package com.furniturestore.controller;

import com.furniturestore.entity.ShoppingCart;
import com.furniturestore.entity.User;
import com.furniturestore.service.ShoppingCartService;
import com.furniturestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

    private final UserService userService;
    private final ShoppingCartService shoppingCartService;


    @Autowired
    public ShoppingCartController(UserService userService, ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(Model model, @PageableDefault Pageable pageable, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        ShoppingCart shoppingCart = user.getShoppingCart();
        model.addAttribute("shoppingCart", shoppingCart);
        return "shoppingCart";
    }

    @GetMapping(value = "/decrementQ/{id}")
    public String decrement(@PathVariable Long id, Principal principal) {
        shoppingCartService.decrementItem(id, principal);
        return "redirect:/cart";
    }

    @GetMapping(value = "/incrementQ/{id}")
    public String increment(@PathVariable Long id, Principal principal) {
        shoppingCartService.incrementItem(id, principal);
        return "redirect:/cart";
    }

    @GetMapping(value = "/remove/{id}")
    public String remove(@PathVariable Long id, Principal principal) {
        shoppingCartService.removeItem(id, principal);
        return "redirect:/cart";
    }

    @GetMapping(value = "/removeAll")
    public String removeAll(Principal principal) {
        shoppingCartService.removeAllItems(principal);
        return "redirect:/";
    }


}

