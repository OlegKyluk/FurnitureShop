package com.furniturestore.validator;

import com.furniturestore.entity.TypeFurniture;
import com.furniturestore.service.TypeFurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

@Component
public class TypeFurnitureValidator implements Validator {

    private final TypeFurnitureService typeFurnitureService;

    @Autowired
    public TypeFurnitureValidator(TypeFurnitureService typeFurnitureService) {
        this.typeFurnitureService = typeFurnitureService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return TypeFurniture.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TypeFurniture typeFurniture = (TypeFurniture) target;
        rejectIfEmptyOrWhitespace(errors, "name", "", "Cant`t be empty.");
        if (typeFurniture.getName().length() >= 30) {
            errors.rejectValue("name", "", "Too many characters.");
        } else if (typeFurnitureService.findByName(typeFurniture.getName()) != null) {
            errors.rejectValue("name", "", "Already exist");
        }
    }
}