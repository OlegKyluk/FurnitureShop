package com.furniturestore.controller;

import com.furniturestore.dto.filter.SimpleFilter;
import com.furniturestore.entity.Color;
import com.furniturestore.service.ColorService;
import com.furniturestore.validator.ColorValidator;
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
@RequestMapping("/admin/color")
public class ColorController {

    private final ColorService colorService;

    @Autowired
    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @InitBinder("color")
    protected void bind(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new ColorValidator(colorService));
    }

    @GetMapping
    public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute(value = "filter") SimpleFilter filter) {
        model.addAttribute("colorList", colorService.findAll(filter, pageable));
        return "/admin/color/colorList";
    }

    @RequestMapping(value = {"/colorEdit", "/colorEdit/{id}"}, method = RequestMethod.GET)
    public String saveAndEditForm(Model model, @PathVariable(required = false, name = "id") Long id) {
        if (null != id) {
            model.addAttribute("color", colorService.findById(id));
        } else {
            model.addAttribute("color", new Color());
        }
        return "/admin/color/colorForm";
    }

    @RequestMapping(value = "/colorEdit", method = RequestMethod.POST)
    public String saveForm(@Valid Color color, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/color/colorForm";
        }
        colorService.save(color);
        return "redirect:/admin/color" + getSimpleModelParams();
    }

    @GetMapping(value = "/colorDelete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        colorService.delete(id);
        return "redirect:/admin/color" + getSimpleModelParams();

    }

}
