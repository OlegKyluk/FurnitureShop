package com.furniturestore.controller;

import com.furniturestore.dto.FurnitureForm;
import com.furniturestore.dto.filter.FurnitureFilter;
import com.furniturestore.service.*;
import com.furniturestore.validator.FurnitureValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

import static com.furniturestore.utils.ParamBuilder.getMultipleModelParams;

@Controller
@RequestMapping("/admin/furniture")
public class FurnitureController {

    private final FurnitureService furnitureService;

    private final ColorService colorService;

    private final TypeFurnitureService typeFurnitureService;

    private final DenominationService denominationService;

    private final CollectionNameService collectionNameService;


    @Autowired
    public FurnitureController(FurnitureService furnitureService, ColorService colorService,
                               TypeFurnitureService typeFurnitureService, DenominationService denominationService,
                               CollectionNameService collectionNameService) {
        this.furnitureService = furnitureService;
        this.colorService = colorService;
        this.typeFurnitureService = typeFurnitureService;
        this.denominationService = denominationService;
        this.collectionNameService = collectionNameService;
    }

    @InitBinder("furniture")
    protected void bind(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new FurnitureValidator(furnitureService));
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(Model model, @ModelAttribute("filter") @Valid FurnitureFilter filter, @PageableDefault Pageable pageable, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("furnitureList", furnitureService.findAll(filter, pageable));
            model.addAllAttributes(getModels());
            return "/admin/furniture/furnitureList";
        }
        model.addAttribute("furnitureList", furnitureService.findAll(filter, pageable));
        model.addAllAttributes(getModels());
        return "/admin/furniture/furnitureList";
    }

    @RequestMapping(value = {"/furnitureEdit", "/furnitureEdit/{id}"}, method = RequestMethod.GET)
    public String saveAndEditForm(Model model, @PathVariable(required = false, name = "id") Long id) {
        model.addAllAttributes(getModels());
        if (null != id) {
            model.addAttribute("furniture", furnitureService.findForm(id));
        } else {
            model.addAttribute("furniture", new FurnitureForm());
        }
        return "/admin/furniture/furnitureForm";
    }

    @RequestMapping(value = "/furnitureEdit", method = RequestMethod.POST)
    public String saveForm(Model model, @ModelAttribute("furniture") @Valid FurnitureForm furnitureForm,
                           BindingResult bindingResult, @RequestParam(value = "file") MultipartFile file) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAllAttributes(getModels());
            return "/admin/furniture/furnitureForm";
        }
        furnitureService.save(furnitureForm, file);
        return "redirect:/admin/furniture" + getMultipleModelParams();

    }

    @RequestMapping(value = "/furnitureDelete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(required = true, name = "id") Long id) {
        furnitureService.delete(id);
        return "redirect:/admin/furniture" + getMultipleModelParams();
    }

    private ModelMap getModels() {
        ModelMap model = new ModelMap();
        model.addAttribute("colorList", colorService.findAll());
        model.addAttribute("typeFurnitureList", typeFurnitureService.findAll());
        model.addAttribute("denominationList", denominationService.findAll());
        model.addAttribute("collectionNameList", collectionNameService.findAll());
        return model;
    }
}
