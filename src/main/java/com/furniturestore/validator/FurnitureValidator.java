package com.furniturestore.validator;

import com.furniturestore.dto.FurnitureForm;
import com.furniturestore.service.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class FurnitureValidator implements Validator {

    private final Pattern REG = Pattern.compile("^([0-9]{1,17}\\.[0-9]{1,2})|([0-9]{1,17}\\,[0-9]{1,2})|([0-9]{1,17})$");

    private final FurnitureService furnitureService;

    @Autowired
    public FurnitureValidator(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FurnitureForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FurnitureForm form = (FurnitureForm) target;
        if (form.getPrice().equals("")) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "", "Can`t be empty.");
        } else if (!REG.matcher(form.getPrice()).matches()) {
            errors.rejectValue("price", "", "Can separated by , or . or only numbers.");
        }

        if (form.getId() != null) {

        } else if (form.getCollectionName() != null && form.getColor() != null && form.getDenomination() != null && form.getTypeFurniture() != null) {
            if (furnitureService.findUnique(form.getCollectionName(),
                    form.getColor(), form.getDenomination(),
                    form.getTypeFurniture()) != null)
                errors.reject("", "Already exist");
        }

    }

}
