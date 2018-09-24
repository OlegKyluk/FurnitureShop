package com.furniturestore.controller;

import com.furniturestore.dto.filter.SimpleFilter;
import com.furniturestore.entity.Denomination;
import com.furniturestore.service.DenominationService;
import com.furniturestore.validator.DenominationValidator;
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
@RequestMapping("/admin/denomination")
public class DenominationController {

    private final DenominationService denominationService;

    @Autowired
    public DenominationController(DenominationService denominationService) {
        this.denominationService = denominationService;
    }

    @InitBinder("denomination")
    protected void bind(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new DenominationValidator(denominationService));
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute(value = "filter") SimpleFilter filter) {
        model.addAttribute("denominationList", denominationService.findAll(pageable, filter));
        return "/admin/denomination/denominationList";
    }

    @RequestMapping(value = {"/denominationEdit", "/denominationEdit/{id}"}, method = RequestMethod.GET)
    public String saveAndEditForm(Model model, @PathVariable(required = false, name = "id") Long id) {
        if (null != id) {
            model.addAttribute("denomination", denominationService.findOne(id));
        } else {
            model.addAttribute("denomination", new Denomination());
        }
        return "/admin/denomination/denominationForm";
    }

    @RequestMapping(value = "/denominationEdit", method = RequestMethod.POST)
    public String saveForm(@Valid Denomination denomination, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/denomination/denominationForm";
        }
        denominationService.save(denomination);
        return "redirect:/admin/denomination" + getSimpleModelParams();
    }

    @RequestMapping(value = "/denominationDelete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(required = true, name = "id") Long id) {
        denominationService.delete(id);
        return "redirect:/admin/denomination" + getSimpleModelParams();
    }
}
