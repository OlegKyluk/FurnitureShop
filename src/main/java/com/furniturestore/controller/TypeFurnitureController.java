package com.furniturestore.controller;

import com.furniturestore.dto.filter.SimpleFilter;
import com.furniturestore.entity.TypeFurniture;
import com.furniturestore.service.TypeFurnitureService;
import com.furniturestore.validator.TypeFurnitureValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.furniturestore.utils.ParamBuilder.getSimpleModelParams;

@Controller
@RequestMapping("/admin/typeFurniture")
public class TypeFurnitureController {

    private final TypeFurnitureService typeFurnitureService;

    @Autowired
    public TypeFurnitureController(TypeFurnitureService typeFurnitureService) {
        this.typeFurnitureService = typeFurnitureService;

    }

    @InitBinder("typeFurniture")
    protected void bind(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new TypeFurnitureValidator(typeFurnitureService));
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute(value = "filter") SimpleFilter filter) {
        model.addAttribute("typeFurnitureList", typeFurnitureService.findAll(pageable, filter));
        return "/admin/typeFurniture/typeFurnitureList";
    }

    @RequestMapping(value = {"/typeFurnitureEdit", "/typeFurnitureEdit/{id}"}, method = RequestMethod.GET)
    public String saveAndEditForm(Model model, @PathVariable(required = false, name = "id") Long id) {
        if (null != id) {
            model.addAttribute("typeFurniture", typeFurnitureService.findOne(id));
        } else {
            model.addAttribute("typeFurniture", new TypeFurniture());
        }
        return "/admin/typeFurniture/typeFurnitureForm";
    }

    @RequestMapping(value = "/typeFurnitureEdit", method = RequestMethod.POST)
    public String saveForm(@Valid TypeFurniture typeFurniture, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/typeFurniture/typeFurnitureForm";
        }
        typeFurnitureService.save(typeFurniture);
        return "redirect:/admin/typeFurniture" + getSimpleModelParams();
    }

    @RequestMapping(value = "/typeFurnitureDelete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(required = true, name = "id") Long id) {
        typeFurnitureService.delete(id);
        return "redirect:/admin/typeFurniture" + getSimpleModelParams();
    }

}
