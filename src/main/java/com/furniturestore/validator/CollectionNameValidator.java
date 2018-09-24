package com.furniturestore.validator;

import com.furniturestore.entity.CollectionName;
import com.furniturestore.service.CollectionNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

@Component
public class CollectionNameValidator implements Validator {

    private final CollectionNameService collectionNameService;

    @Autowired
    public CollectionNameValidator(CollectionNameService collectionNameService) {
        this.collectionNameService = collectionNameService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CollectionName.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CollectionName collectionName = (CollectionName) target;
        rejectIfEmptyOrWhitespace(errors, "name", "", "Cant`t be empty.");
        if (collectionName.getName().length() >= 30) {
            errors.rejectValue("name", "", "Too many characters.");
        } else if (collectionNameService.findByName(collectionName.getName()) != null) {
            errors.rejectValue("name", "", "Already exist");
        }
    }
}
