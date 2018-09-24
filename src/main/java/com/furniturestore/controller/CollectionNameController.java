package com.furniturestore.controller;

import com.furniturestore.dto.filter.SimpleFilter;
import com.furniturestore.entity.CollectionName;
import com.furniturestore.service.CollectionNameService;
import com.furniturestore.validator.CollectionNameValidator;
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
@RequestMapping("/admin/collectionName")
public class CollectionNameController {

    private final CollectionNameService collectionNameService;

    @Autowired
    public CollectionNameController(CollectionNameService collectionNameService) {
        this.collectionNameService = collectionNameService;
    }

    @InitBinder("collectionName")
    protected void bind(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new CollectionNameValidator(collectionNameService));
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute(value = "filter") SimpleFilter filter) {
        model.addAttribute("collectionNameList", collectionNameService.findAll(filter, pageable));
        return "/admin/collectionName/collectionNameList";
    }

    @RequestMapping(value = {"/collectionNameEdit", "/collectionNameEdit/{id}"}, method = RequestMethod.GET)
    public String saveAndEditForm(Model model, @PathVariable(required = false, name = "id") Long id) {
        if (null != id) {
            model.addAttribute("collectionName", collectionNameService.findById(id));
        } else {
            model.addAttribute("collectionName", new CollectionName());
        }
        return "/admin/collectionName/collectionNameForm";
    }

    @RequestMapping(value = "/collectionNameEdit", method = RequestMethod.POST)
    public String saveForm(@Valid CollectionName collectionName, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/collectionName/collectionNameForm";
        }
        collectionNameService.save(collectionName);
        return "redirect:/admin/collectionName" + getSimpleModelParams();
    }

    @RequestMapping(value = "/collectionNameDelete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(required = true, name = "id") Long id) {
        collectionNameService.delete(id);
        return "redirect:/admin/collectionName" + getSimpleModelParams();
    }
}
