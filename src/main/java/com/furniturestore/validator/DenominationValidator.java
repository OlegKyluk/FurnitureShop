package com.furniturestore.validator;

import com.furniturestore.entity.Denomination;
import com.furniturestore.service.DenominationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

@Component
public class DenominationValidator implements Validator {
    private final DenominationService denominationService;

    @Autowired
    public DenominationValidator(DenominationService denominationService) {
        this.denominationService = denominationService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Denomination.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Denomination denomination = (Denomination) target;
        rejectIfEmptyOrWhitespace(errors, "name", "", "Cant`t be empty.");
        if (denomination.getName().length() >= 30) {
            errors.rejectValue("name", "", "Too many characters.");
        } else if (denominationService.findByName(denomination.getName()) != null) {
            errors.rejectValue("name", "", "Already exist");
        }
    }
}
