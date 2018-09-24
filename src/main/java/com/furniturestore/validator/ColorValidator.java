package com.furniturestore.validator;

import com.furniturestore.entity.Color;
import com.furniturestore.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

@Component
public class ColorValidator implements Validator {

    private final ColorService colorService;

    @Autowired
    public ColorValidator(ColorService colorService) {
        this.colorService = colorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Color.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Color color = (Color) target;
        rejectIfEmptyOrWhitespace(errors, "name", "", "Cant`t be empty.");
        if (color.getName().length() >= 30) {
            errors.rejectValue("name", "", "Too many characters.");
        }

        if (color.getId() != null) {

        } else {
            if (colorService.findByName(color.getName()) != null) {
                errors.rejectValue("name", "", "Already exist");
            }
        }
    }
}
