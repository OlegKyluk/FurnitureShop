package com.furniturestore.controller;

import com.furniturestore.dto.filter.FurnitureFilter;
import com.furniturestore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

import static com.furniturestore.utils.ParamBuilder.getMultipleModelParams;

@Controller
@RequestMapping("/")
public class UserController {

    private final FurnitureService furnitureService;

    private final ColorService colorService;

    private final TypeFurnitureService typeFurnitureService;

    private final DenominationService denominationService;

    private final CollectionNameService collectionNameService;

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public UserController(FurnitureService furnitureService, ColorService colorService,
                          TypeFurnitureService typeFurnitureService, DenominationService denominationService,
                          CollectionNameService collectionNameService, ShoppingCartService shoppingCartService) {
        this.furnitureService = furnitureService;
        this.colorService = colorService;
        this.typeFurnitureService = typeFurnitureService;
        this.denominationService = denominationService;
        this.collectionNameService = collectionNameService;
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") FurnitureFilter filter) {
        model.addAttribute("furnitureList", furnitureService.findAll(filter, pageable));
        model.addAttribute("colorList", colorService.findAll());
        model.addAttribute("denominationList", denominationService.findAll());
        model.addAttribute("typeFurnitureList", typeFurnitureService.findAll());
        model.addAttribute("collectionNameList", collectionNameService.findAll());
        return "/home";
    }

    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public String addFurniture(@PathVariable Long id, Principal principal) {
        shoppingCartService.addItem(id, principal);
        return "redirect:/" + getMultipleModelParams();
    }

}

